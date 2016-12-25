package ru.mvd.national.guard.forces.test;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ResultActivity extends AppCompatActivity {

    private ListView userIncorrectAnswersList;

    private MenuItem ok;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        userIncorrectAnswersList = (ListView)findViewById(R.id.listViewIncorrectAnswers);
        ArrayList<DataFieldResult> answer = getIntent().getParcelableArrayListExtra("incorrect");
        userIncorrectAnswersList.setAdapter(new CustomListAdapter(this, answer));
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_test_activity, menu);
        ok = menu.findItem(R.id.next);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.equals(ok)) {
            Intent intent = new Intent(ResultActivity.this, MainActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

}
