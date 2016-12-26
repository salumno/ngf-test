package ru.mvd.national.guard.forces.test;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ResultActivity extends AppCompatActivity {

    private ListView userIncorrectAnswersList;

    private MenuItem ok;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.current_result_activity_title);
        setSupportActionBar(toolbar);

        userIncorrectAnswersList = (ListView)findViewById(R.id.listViewIncorrectAnswers);
        ArrayList<DataFieldResult> answer = getIntent().getParcelableArrayListExtra("incorrect");
        userIncorrectAnswersList.setAdapter(new IncorrectAnswerListAdapter(this, answer));
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_test_activity, menu);
        ok = menu.findItem(R.id.next);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.equals(ok)) {
            Intent intent = new Intent(ResultActivity.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    public void onBackPressed() {
        Toast.makeText(this, R.string.by_the_way, Toast.LENGTH_SHORT).show();
    }

}
