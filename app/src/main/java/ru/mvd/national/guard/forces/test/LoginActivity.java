package ru.mvd.national.guard.forces.test;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private EditText surname;
    private EditText name;
    private EditText patronymic;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbarSetting(toolbar);

        surname = (EditText)findViewById(R.id.editSurname);
        name = (EditText)findViewById(R.id.editName);
        patronymic = (EditText)findViewById(R.id.editPatronymic);

    }

    public void toTestActivityButton(View view) {
        Intent intent = new Intent(LoginActivity.this, TestActivity.class);
        if (editTextCheck()) {
            String exName = name.getText().toString().toUpperCase().substring(0,1);
            String exSurname = surname.getText().toString();
            String exPatronymic = patronymic.getText().toString().toUpperCase().substring(0,1);
            String extraName = exSurname + " " + exName + "." + exPatronymic + ".";
            intent.putExtra("name",extraName);
            vanishEditText();
            startActivity(intent);
        } else {
            Toast.makeText(getApplicationContext(), R.string.empty_field, Toast.LENGTH_SHORT).show();
        }

    }

    private boolean editTextCheck() {
        if (surname.getText().length() != 0 && name.getText().length() != 0 && patronymic.getText().length() != 0) {
            return true;
        } else {
            return false;
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

    private void vanishEditText() {
        surname.setText(null);
        name.setText(null);
        patronymic.setText(null);
    }
}
