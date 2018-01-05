package com.mecklenburgsolutions.mudgie;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class CalendarEventAddActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar_event_add);

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        String longDate = intent.getStringExtra(CalendarActivity.DATE_TEXT);
        Date date = new Date(Long.valueOf(longDate));
        String formattedDate = new SimpleDateFormat("MM/dd/yyyy").format(date);
        // Capture the layout's TextView and set the string as its text
        EditText dateTextView = findViewById(R.id.calendar_add_date_entry);
        dateTextView.setText(formattedDate);

        //Set Default Time
        EditText timeTextView = findViewById(R.id.calendar_add_time_entry);
        timeTextView.setText("Add Time of Event");
    }
}
