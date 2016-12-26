package ru.mvd.national.guard.forces.test;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class ResultReviewActivity extends AppCompatActivity {

    ArrayList<String> resultList;

    private MenuItem clear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_review);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.current_result_review_title);
        setSupportActionBar(toolbar);
        toolbarSetting(toolbar);

        ListView resultView = (ListView)findViewById(R.id.listViewResults);
        resultList = new ArrayList<>();
        openFile("results");
        ArrayAdapter adapter = new ArrayAdapter(this, R.layout.result_item, resultList);
        resultView.setAdapter(adapter);

    }

    private void openFile(String fileName) {
        try {
            InputStream inputStream = openFileInput(fileName);
            if (inputStream != null) {
                InputStreamReader isr = new InputStreamReader(inputStream);
                BufferedReader reader = new BufferedReader(isr);
                String line;
                while ((line = reader.readLine()) != null) {
                    resultList.add(line);
                }
                inputStream.close();
            }
        } catch (Throwable t) {
            Toast.makeText(this, R.string.empty_result, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_result_review_actvity, menu);
        clear = menu.findItem(R.id.clearResults);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.equals(clear)) {
            AlertDialog alertDialog = setAlertDialogBuilderClear().create();
            alertDialog.show();
        }
        return super.onOptionsItemSelected(item);
    }

    private AlertDialog.Builder setAlertDialogBuilderClear() {
        AlertDialog.Builder builder = new AlertDialog.Builder(ResultReviewActivity.this);
        builder.setTitle(R.string.result_delete)
                .setMessage(R.string.sure_message)
                .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        try {
                            ResultReviewActivity.this.deleteFile("results");
                            Toast.makeText(ResultReviewActivity.this, R.string.delete_success, Toast.LENGTH_SHORT).show();
                            ResultReviewActivity.this.recreate();
                        } catch (Exception ex) {
                            Toast.makeText(ResultReviewActivity.this, R.string.delete_fail, Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
        return builder;
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

    public void onBackPressed() {
        this.finish();
    }

}
