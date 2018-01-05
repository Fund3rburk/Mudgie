package com.mecklenburgsolutions.mudgie;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.support.annotation.RequiresApi;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tfunderburk on 12/30/17.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "mudgieDB";
    private static final String CALENDAR_TABLE = "Calendar";
    private static final String EVENT_ID = "EventId";
    private static final String EVENT_DATETIME = "EventDateTime";
    private static final String EVENT_DESCRIPTION = "EventDesc";


    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
     String createCalendarTable = "CREATE TABLE "+ CALENDAR_TABLE +" ("+ EVENT_ID +" INTEGER PRIMARY KEY AUTOINCREMENT, "+
             EVENT_DESCRIPTION +" TEXT, "+ EVENT_DATETIME +" TEXT)";

     db.execSQL(createCalendarTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1){
        db.execSQL("DROP TABLE IF EXISTS " + CALENDAR_TABLE);
        onCreate(db);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void addCalendarEvents(CalendarEvent calendarEvent){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(EVENT_DESCRIPTION, calendarEvent.getEventDescription());
        values.put(EVENT_DATETIME, calendarEvent.getEventDateTime().toString());

        db.insert(CALENDAR_TABLE, null, values);
        db.close();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public CalendarEvent getCalendarEvent(int id){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(CALENDAR_TABLE, new String[] {EVENT_ID, EVENT_DESCRIPTION, EVENT_DATETIME}, EVENT_ID + "=?" ,
                new String[]{String.valueOf(id)}, null, null, null, null);

        if (cursor != null){
            cursor.moveToFirst();
        }

        int eventId = Integer.parseInt(cursor.getString(0));
        String eventDescription = cursor.getString(1);
        LocalDateTime localDateTime = LocalDateTime.parse(cursor.getString(2), DateTimeFormatter.ISO_LOCAL_DATE_TIME);

        CalendarEvent calendarEvent = new CalendarEvent(eventId, eventDescription, localDateTime);

        return calendarEvent;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public List<CalendarEvent> getAllCalendarEvents(){
        List<CalendarEvent> calendarEvents = new ArrayList<CalendarEvent>();

        String selectQuery = "SELECT * FROM " + CALENDAR_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if(cursor.moveToFirst()){
            do {
               CalendarEvent calendarEvent = new CalendarEvent();
               calendarEvent.setId(Integer.parseInt(cursor.getString(0)));
               calendarEvent.setEventDescription(cursor.getString(1));
               calendarEvent.setEventDateTime(LocalDateTime.parse(cursor.getString(2), DateTimeFormatter.ISO_LOCAL_DATE_TIME));

               calendarEvents.add(calendarEvent);
            } while (cursor.moveToNext());
        }

        return calendarEvents;
    }

    public int updateCalendarEvent(CalendarEvent calendarEvent){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(EVENT_DESCRIPTION, calendarEvent.getEventDescription());
        values.put(EVENT_DATETIME, calendarEvent.getEventDateTime().toString());

        return db.update(CALENDAR_TABLE, values, EVENT_ID + "=?",
                new String[]{String.valueOf(calendarEvent.getId())});
    }

    public void deleteCalendarEvent(CalendarEvent calendarEvent){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(CALENDAR_TABLE, EVENT_ID + "=?",
                new String[]{String.valueOf(calendarEvent.getId())});

        db.close();
    }

    public int getCalendarEventCount(){
        String countQuery = "SELECT * FROM " + CALENDAR_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        return cursor.getCount();
    }
}
