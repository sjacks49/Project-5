package com.example.sj_dm_project5;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import java.util.ArrayList;
import java.util.Locale;

public class DatabaseMan extends SQLiteOpenHelper {

    private static final String DB_NAME = "JobDB";
    private static final int DB_VERSION = 1;
    private static final String NAME = "job";
    private static final String ID = "id";
    private static final String POSITION = "position";
    private static final String SALARY = "salary";

    public DatabaseMan(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String format = "create table %s(%s integer primary key autoincrement, %s text, %s real)";
        String sqlCreateStr = String.format(format, NAME, ID, POSITION, SALARY);
        db.execSQL(sqlCreateStr);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + NAME);
        onCreate(db);
    }

    public void add(Job job){
        String sqlInsertStr = String.format(Locale.US,
                "insert into %s values(null, '%s', '%f')",
                NAME, job.getPosition(), job.getSalary());

        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL(sqlInsertStr);
        db.close();
    }

    public void deleteByID(int delID){
        String sqlDeleteStr = String.format(Locale.US,
                "delete from %s where %s = %d",
                NAME, ID, delID);

        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(sqlDeleteStr);
        db.close();
    }

    public ArrayList<Job> selectAll() {
        String sqlQueryStr = String.format(Locale.US,
                "select * from %s ", NAME);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sqlQueryStr, null);

        ArrayList<Job> JobList = new ArrayList<>();
        while (cursor.moveToNext()){
            int id = cursor.getInt(0);
            String position = cursor.getString(1);
            double salary = cursor.getDouble(2);
            JobList.add(new Job(id, position, salary));
        }
        db.close();

        return JobList;
    }
}
