package ru.mvd.national.guard.forces.test;

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

import java.io.IOException;
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
        setSupportActionBar(toolbar);
        toolbarSetting(toolbar);

        try {
            user = new User(new DataSet(TestActivity.this));
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        listView = (ListView) findViewById(R.id.listView);
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        fio = (TextView)findViewById(R.id.fio);
        fio.setText(getIntent().getStringExtra("name"));
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
            if (selected == 0) {
                Toast.makeText(this, "Выберите вариант ответа", Toast.LENGTH_LONG).show();
            } else {
                if (selected == 1 && selectedItems.get(0).equals(user.getTestQuestion(currentInx).getRightAns())) {
                    Toast.makeText(this, "Well done!", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(this, "Bad one " + selectedItems.get(0), Toast.LENGTH_SHORT).show();
                }
                selectedItems.clear();
                currentInx++;
                if (currentInx != user.getSize()) {
                    textFieldInitial();
                    questionNumber.setText(getString(R.string.question_number_this) + (currentInx + 1));
                } else {
                    Intent intent = new Intent(TestActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private void textFieldInitial() {
        question.setText(user.getTestQuestion(currentInx).getQuestion() + "\n" + user.getTestQuestion(currentInx).getRightAns());
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
                Toast.makeText(TestActivity.this, "You select item" + selectedItem, Toast.LENGTH_LONG).show();
                if (selectedItems.contains(selectedItem)) {
                    selectedItems.remove(selectedItem);
                } else {
                    selectedItems.add(selectedItem);
                }
            }
        });
    }

}
