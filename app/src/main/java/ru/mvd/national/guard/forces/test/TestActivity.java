package ru.mvd.national.guard.forces.test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;

public class TestActivity extends AppCompatActivity {

    private DataSet test;

    private Button nextQuestion;

    private TextView fio;
    private TextView question;
    private TextView ans1;
    private TextView ans2;
    private TextView ans3;
    private TextView ans4;

    private int currentInx = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbarSetting(toolbar);

        try {
            test = new DataSet(TestActivity.this);
        } catch (IOException e) {
            e.printStackTrace();
        }

        nextQuestion = (Button)findViewById(R.id.toNextQuestionButton);

        fio = (TextView)findViewById(R.id.fio);
        fio.setText(getIntent().getStringExtra("name"));
        question = (TextView)findViewById(R.id.question);
        ans1 = (TextView)findViewById(R.id.ans1);
        ans2 = (TextView)findViewById(R.id.ans2);
        ans3 = (TextView)findViewById(R.id.ans3);
        ans4 = (TextView)findViewById(R.id.ans4);

        textFieldInitial();
        currentInx++;

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

    private void textFieldInitial() {
        question.setText(test.getDataField(currentInx).getQuestion());
        ans1.setText(test.getDataField(currentInx).getAns1());
        ans2.setText(test.getDataField(currentInx).getAns2());
        ans3.setText(test.getDataField(currentInx).getAns3());
        ans4.setText(test.getDataField(currentInx).getAns4());
    }

    public void nextQuestionOnClick(View view) {
        textFieldInitial();
        currentInx++;
        if (currentInx == test.getSize()) {
            nextQuestion.setClickable(false);
        }
    }

}
