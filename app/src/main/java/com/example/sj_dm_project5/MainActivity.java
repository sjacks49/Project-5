package com.example.sj_dm_project5;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    static String TAG_MAIN = "Monkas";
    private DatabaseMan manager;
    private ScrollView scrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        manager = new DatabaseMan(this);
        scrollView = findViewById(R.id.mainScroll);
        updateView();
    }

    protected void onStart() {
        super.onStart();
        updateView();
    }

    private void updateView() {
        ArrayList<Job> jobs = manager.selectAll();

        scrollView.removeAllViews();

        LinearLayout LabelColumn = new LinearLayout(this);
        LabelColumn.setOrientation(LinearLayout.VERTICAL);

        TextView[] labels = new TextView[jobs.size()];

        for (int i = 0; i < jobs.size(); i++){
            String text = jobs.get(i).toString();
            labels[i] = new TextView(this);
            labels[i].setText(text);
            labels[i].setTextSize(24);
            labels[i].setPadding(10,10,10,10);
            LabelColumn.addView(labels[i]);
        }
        scrollView.addView(LabelColumn);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_add) {
            Log.w(TAG_MAIN,"action add");

            Intent addIntent = new Intent(this, AddActivity.class);
            this.startActivity(addIntent);

            return true;
        }
        if (id == R.id.action_delete) {
            Log.w(TAG_MAIN, "action delete");

            Intent deleteIntent = new Intent(this, DeleteActivity.class);
            this.startActivity(deleteIntent);

            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
