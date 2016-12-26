package ru.mvd.national.guard.forces.test;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class TestActivity extends AppCompatActivity {

    private User user;

    private ArrayList<String> selectedItems;
    private ListView listView;
    private ArrayAdapter<String> adapter;

    private MenuItem next;

    private TextView fio;
    private TextView questionNumber;
    private TextView question;

    private int currentInx = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        toolbarSetting(toolbar);

        try {
            user = new User(new DataSet(TestActivity.this), getIntent().getStringExtra("name"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        listView = (ListView) findViewById(R.id.listView);
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        fio = (TextView)findViewById(R.id.fio);
        fio.setText(user.getUserName());
        question = (TextView)findViewById(R.id.question);
        questionNumber = (TextView)findViewById(R.id.questionNumber);

        selectedItems = new ArrayList<>();


        textFieldInitial();

        questionNumber.setText(getString(R.string.question_number_this) + (currentInx + 1));

    }

    private void toolbarSetting(Toolbar toolbar) {
        toolbar.setNavigationIcon(R.drawable.back_arrow);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_test_activity, menu);
        next = menu.findItem(R.id.next);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.equals(next)) {
            byte selected = (byte)selectedItems.size();
            if (selected == 0 || selected > 1) {
                Toast.makeText(this, R.string.one_variant, Toast.LENGTH_SHORT).show();
            } else {
                if (selectedItems.get(0).equals(user.getTestQuestion(currentInx).getRightAns())) {
                    user.setRightAnswerCount(user.getRightAnswerCount() + 1);
                } else {
                    user.addIncorrectAnswerToList(currentInx, selectedItems.get(0));
                }
                selectedItems.clear();
                currentInx++;
                if (currentInx != user.getSize()) {
                    textFieldInitial();
                    questionNumber.setText(getString(R.string.question_number_this) + (currentInx + 1));
                } else {
                    String result = user.getUserName() + getString(R.string.correct_answer_count) + user.getRightAnswerCount();
                    writeFile("results", result);
                    if (user.getIncorrectAnswerSet().size() == 0) {
                        AlertDialog alertDialog = setBuilderSettingsSingle().create();
                        alertDialog.show();
                    } else {
                        AlertDialog alertDialog = setBuilderSettingsBad().create();
                        alertDialog.show();
                    }
                }
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private AlertDialog.Builder setBuilderSettingsBad() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.test_final)
                .setMessage(getString(R.string.your_score) + user.getRightAnswerCount() + getString(R.string.from) + user.getSize() + getString(R.string.from_questions))
                .setPositiveButton(R.string.mistake_check, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(TestActivity.this, ResultActivity.class);
                        ArrayList<DataFieldResult> list  = user.getIncorrectAnswerSet();
                        intent.putExtra("incorrect", list);
                        startActivity(intent);
                        finish();
                    }
                })
                .setCancelable(false)
                .setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialogInterface) {
                        Toast.makeText(TestActivity.this, R.string.choose_next_move, Toast.LENGTH_LONG);
                    }
                });
        return builder;
    }

    private AlertDialog.Builder setBuilderSettingsSingle() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.test_final)
                .setMessage(R.string.all_fine)
                .setPositiveButton(R.string.to_main_menu, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(TestActivity.this, MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                    }
                })
                .setCancelable(false)
                .setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialogInterface) {
                        Toast.makeText(TestActivity.this, R.string.choose_next_move, Toast.LENGTH_LONG).show();
                    }
                });
        return builder;
    }

    private void textFieldInitial() {
        question.setText(user.getTestQuestion(currentInx).getQuestion());
        String[] items = {
                user.getTestQuestion(currentInx).getAns1(),
                user.getTestQuestion(currentInx).getAns2(),
                user.getTestQuestion(currentInx).getAns3(),
                user.getTestQuestion(currentInx).getAns4()
        };
        adapter = new ArrayAdapter<>(this, R.layout.list_item, R.id.questionItem, items);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String selectedItem = ((TextView)view).getText().toString();
                if (selectedItems.contains(selectedItem)) {
                    selectedItems.remove(selectedItem);
                } else {
                    selectedItems.add(selectedItem);
                }
            }
        });
    }

    private void writeFile(String name, String data) {
        StringBuilder builder = openFile(name);
        builder.append(data);
        try {
            OutputStream outputStream = openFileOutput(name, 0);
            OutputStreamWriter osw = new OutputStreamWriter(outputStream);
            osw.write(builder.toString());
            osw.close();
        } catch (Throwable t) {
            Toast.makeText(getApplicationContext(),
                    "Exception: " + t.toString(), Toast.LENGTH_LONG).show();
        }
    }

    private StringBuilder openFile(String fileName) {
        StringBuilder builder = new StringBuilder();
        try {
            InputStream inputStream = openFileInput(fileName);

            if (inputStream != null) {
                InputStreamReader isr = new InputStreamReader(inputStream);
                BufferedReader reader = new BufferedReader(isr);
                String line;
                while ((line = reader.readLine()) != null) {
                    builder.append(line + "\n");
                }
                inputStream.close();
            }
        } catch (Throwable t) {
            Toast.makeText(getApplicationContext(), "Результаты сохранены", Toast.LENGTH_LONG).show();
        }
        return builder;
    }

}
