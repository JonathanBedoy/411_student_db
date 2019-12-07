package com.cpsc41102.homework3.model;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class Student extends PersistentObject{
    protected String mFirstName;
    protected String mLastName;
    protected int mCWID;
    protected ArrayList<CourseEnrollment> mCourses;

    public Student(String fName, String lName, int cwid){
        mFirstName = fName;
        mLastName = lName;
        mCWID = cwid;
    }

    public Student(){}


    public String getFirstName() {
        return mFirstName;
    }

    public void setFirstName(String firstName) {
        mFirstName = firstName;
    }

    public String getLastName() {
        return mLastName;
    }

    public void setLastName(String lastName) {
        mLastName = lastName;
    }

    public int getCWID() {
        return mCWID;
    }

    public void setCWID(int CWID) {
        mCWID = CWID;
    }

    public ArrayList<CourseEnrollment> getCourses() {
        return mCourses;
    }

    public void setCourses(ArrayList<CourseEnrollment> courses) {
        mCourses = courses;
    }

    @Override
    public void insert(SQLiteDatabase db) {
        ContentValues vals = new ContentValues();
        vals.put("FirstName", mFirstName);
        vals.put("LastName", mLastName);
        vals.put("CWID", mCWID);
        db.insert("Student", null, vals);
        for(int i=0; i<mCourses.size(); i++){
            mCourses.get(i).insert(db);
        }
    }

    @Override
    public void initFrom(SQLiteDatabase db, Cursor c) {
        mFirstName = c.getString(c.getColumnIndex("FirstName"));
        mLastName = c.getString(c.getColumnIndex("LastName"));
        mCWID = c.getInt(c.getColumnIndex("CWID"));

        mCourses = new ArrayList<>();
        Cursor cursor = db.query("Course", null,
                "Student=?", new String[]{new Integer(mCWID).toString()},
                null, null, null);
        if(cursor.getCount() > 0){
            while(cursor.moveToNext()){
                CourseEnrollment cObj = new CourseEnrollment();
                cObj.initFrom(db, cursor);
            }
        }
    }


    @Override
    public void createTable(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS Student (FirstName Text, LastName Text, CWID INTEGER)");
    }
}
