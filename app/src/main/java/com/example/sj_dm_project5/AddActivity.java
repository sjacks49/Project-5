package com.example.sj_dm_project5;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddActivity extends AppCompatActivity {
    private DatabaseMan dbManager;

    public void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        dbManager = new DatabaseMan( this );
        setContentView( R.layout.activity_add );
    }

    public void insert( View v ) {
        // Retrieve name and price
        EditText positionET = findViewById( R.id.position_input );
        EditText salaryET = findViewById( R.id.SalaryInput );

        String position = positionET.getText( ).toString( );
        String SalaryString = salaryET.getText( ).toString( );

        // insert new candy in database
        double salary;

        try {
            salary = Double.parseDouble( SalaryString );
        } catch( NumberFormatException nfe ) {
            salary = 0;
        }

        Job job = new Job(0, position, salary);
        dbManager.add( job );
        Toast.makeText( this, "Job added", Toast.LENGTH_SHORT ).show( );

        // clear data
        positionET.setText( "" );
        salaryET.setText( "" );
    }

}
