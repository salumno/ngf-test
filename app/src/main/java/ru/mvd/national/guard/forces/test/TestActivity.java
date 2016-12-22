package ru.mvd.national.guard.forces.test;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class TestActivity extends AppCompatActivity {

    private User user;

    private ArrayList<HashMap<String, Object>> questionList;
    ListView listView;


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

        fio = (TextView)findViewById(R.id.fio);
        fio.setText(getIntent().getStringExtra("name"));
        question = (TextView)findViewById(R.id.question);
        questionNumber = (TextView)findViewById(R.id.questionNumber);

        listView = (ListView) findViewById(R.id.listView);
        questionList = new ArrayList<HashMap<String, Object>>();

        textFieldInitial();
        currentInx++;
        questionNumber.setText(getString(R.string.question_number_this) + currentInx);


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
            if (currentInx == user.getSize()) {
                Intent intent = new Intent(TestActivity.this, MainActivity.class);
                startActivity(intent);
            } else {
                textFieldInitial();
                currentInx++;
                questionNumber.setText(getString(R.string.question_number_this) + currentInx);
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private void textFieldInitial() {
        HashMap<String, Object> q;
        questionList.clear();
        question.setText(user.getTestQuestion(currentInx).getQuestion());

        q = new HashMap<>();
        q.put("answer", user.getTestQuestion(currentInx).getAns1());
        questionList.add(q);

        q = new HashMap<>();
        q.put("answer", user.getTestQuestion(currentInx).getAns2());
        questionList.add(q);

        q = new HashMap<>();
        q.put("answer", user.getTestQuestion(currentInx).getAns3());
        questionList.add(q);

        q = new HashMap<>();
        q.put("answer", user.getTestQuestion(currentInx).getAns4());
        questionList.add(q);

        SimpleAdapter adapter = new SimpleAdapter(this, questionList, R.layout.list_item, new String[]{"answer"}, new int[]{R.id.ans});
        listView.setAdapter(adapter);
    }

}
