package com.cpsc41102.homework3.model;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.io.File;
import java.util.ArrayList;

public class StudentDB {

    protected ArrayList<Student> mStudents;
    SQLiteDatabase mSQLiteDatabase;

    public StudentDB(Context context){
        File dbFile = context.getDatabasePath("student.db");
        mSQLiteDatabase = SQLiteDatabase.openOrCreateDatabase(dbFile, null);

        new Student().createTable(mSQLiteDatabase);
        new CourseEnrollment().createTable(mSQLiteDatabase);
    }

    public ArrayList<Student> getStudents(){ return mStudents; }

    public void setStudents(ArrayList<Student> students) { mStudents = students; }

    public ArrayList<Student> retrieveStudentObjects(){
        mStudents = new ArrayList<>();
        Cursor cursor = mSQLiteDatabase.query("Student", null, null, null, null, null, null);
        if(cursor.getCount() > 0){
            while(cursor.moveToNext()){
                Student sObj = new Student();
                sObj.initFrom(mSQLiteDatabase, cursor);
                ArrayList<CourseEnrollment> courses = new ArrayList<>();

                mStudents.add(sObj);
            }
        }

        return mStudents;
    }

}
