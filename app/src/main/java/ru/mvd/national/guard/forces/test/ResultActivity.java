package ru.mvd.national.guard.forces.test;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ResultActivity extends AppCompatActivity {

    private ListView userIncorrectAnswersList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbarSetting(toolbar);


        userIncorrectAnswersList = (ListView)findViewById(R.id.listViewIncorrectAnswers);
        ArrayList<DataFieldResult> answer = new ArrayList<>();
        answer = getIntent().getParcelableArrayListExtra("incorrect");
        if (answer == null) {
            Toast.makeText(this, "У вас нет ошибок", Toast.LENGTH_SHORT).show();
        } else {
            ArrayAdapter<DataFieldResult> arrayAdapter = new ArrayAdapter<>(this, R.layout.incorrect_answer_list_item, answer);
            userIncorrectAnswersList.setAdapter(arrayAdapter);
        }

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

}
