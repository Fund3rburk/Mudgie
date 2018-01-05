package com.mecklenburgsolutions.mudgie;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CalendarView;
import android.widget.EditText;

public class CalendarActivity extends AppCompatActivity {
    public static final String DATE_TEXT = "com.mecklenburgsolutions.mudgie.DATE_TEXT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "The want to add a calendar event bruh!", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });


    }

    public void createEvent(View view){
        Intent intent = new Intent(this, CalendarEventAddActivity.class);
        CalendarView calendarView = (CalendarView) findViewById(R.id.calendar_content);
        String longdate = String.valueOf(calendarView.getDate());
        intent.putExtra(DATE_TEXT, longdate);
        startActivity(intent);
    }

}


