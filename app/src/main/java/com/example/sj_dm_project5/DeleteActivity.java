package com.example.sj_dm_project5;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;


public class DeleteActivity extends AppCompatActivity {
    private DatabaseMan dbManager;

    public void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        dbManager = new DatabaseMan( this );
        updateView( );
    }

    // Build a View dynamically with all the candies
    public void updateView( ) {

        ArrayList<Job> jobs = dbManager.selectAll( );

        RelativeLayout layout = new RelativeLayout( this );
        ScrollView scrollView = new ScrollView( this );

        RadioGroup group = new RadioGroup( this );
        for ( Job job : jobs ) {
            RadioButton rb = new RadioButton( this );
            rb.setId(job.getID());
            rb.setText( job.toString( ) );
            group.addView( rb );
        }

        // set up event handling
        RadioButtonHandler rbh = new RadioButtonHandler( );
        group.setOnCheckedChangeListener(rbh);

        scrollView.addView(group);
        layout.addView( scrollView );

        setContentView( layout );
    }

    public void deleteDialog() {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Delete Confirmation");
        alert.setMessage("Delete Item?");
        DeleteDialog delete = new DeleteDialog();
        alert.setPositiveButton("Yes", delete);
        alert.setNegativeButton("No", delete);
        alert.show();
    }

    private class DeleteDialog implements AlertDialog.OnClickListener {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            switch (which) {
                case DialogInterface.BUTTON_POSITIVE:
                    //Yes Button Clicked
                    //dbManager.deleteByID();
                    Toast.makeText(DeleteActivity.this, "Job deleted",
                            Toast.LENGTH_SHORT).show();
                    break;

                case DialogInterface.BUTTON_NEGATIVE:
                    //No Button Clicked
                    dialog.dismiss();
                    break;
            }
        }
    }


    private class RadioButtonHandler
            implements RadioGroup.OnCheckedChangeListener {

        static final String DELETE = "Delete";

        public void onCheckedChanged(RadioGroup group, int checkedId) {

            Log.w(DELETE, "deleting "+checkedId);

            dbManager.deleteByID(checkedId);

            Toast.makeText(DeleteActivity.this, "Job deleted",
                    Toast.LENGTH_SHORT).show();
            // update screen
            updateView();
        }
    }
}
