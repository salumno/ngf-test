package ru.mvd.national.guard.forces.test;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.toolbar_title_main_menu);
        setSupportActionBar(toolbar);
    }


    public void mainButtonOnClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.start_test_button_main_menu:
                intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
                break;
            case R.id.result_button_main_menu:
                intent = new Intent(MainActivity.this, ResultReviewActivity.class);
                startActivity(intent);
                break;
            case R.id.exit_button_main_menu:
                MainActivity.this.finish();
                break;
        }
    }

}
