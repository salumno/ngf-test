package ru.mvd.national.guard.forces.test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;

public class TestActivity extends AppCompatActivity {

    private User user;

    private Button nextQuestion;

    private TextView fio;
    private TextView questionNumber;
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
            user = new User(new DataSet(TestActivity.this));
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        nextQuestion = (Button)findViewById(R.id.toNextQuestionButton);

        fio = (TextView)findViewById(R.id.fio);
        fio.setText(getIntent().getStringExtra("name"));
        question = (TextView)findViewById(R.id.question);
        questionNumber = (TextView)findViewById(R.id.questionNumber);
        ans1 = (TextView)findViewById(R.id.ans1);
        ans2 = (TextView)findViewById(R.id.ans2);
        ans3 = (TextView)findViewById(R.id.ans3);
        ans4 = (TextView)findViewById(R.id.ans4);

        textFieldInitial();
        currentInx++;
        questionNumber.setText("Вопрос " + currentInx);

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
        question.setText(user.getTestQuestion(currentInx).getQuestion());
        ans1.setText(user.getTestQuestion(currentInx).getAns1());
        ans2.setText(user.getTestQuestion(currentInx).getAns2());
        ans3.setText(user.getTestQuestion(currentInx).getAns3());
        ans4.setText(user.getTestQuestion(currentInx).getAns4());
    }

    public void nextQuestionOnClick(View view) {
        textFieldInitial();
        currentInx++;
        if (currentInx == user.getSize()) {
            nextQuestion.setClickable(false);
        }
    }

}
