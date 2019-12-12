package com.cpsc41102.homework3.model;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class CourseEnrollment extends PersistentObject{
    protected String mCourseID;
    protected String mGrade;
    protected int mCWID;

    public CourseEnrollment(String courseID, String grade, int CWID) {
        mCourseID = courseID;
        mGrade = grade;
        mCWID = CWID;
    }

    public CourseEnrollment(){}

    public String getCourseID() {
        return mCourseID;
    }

    public void setCourseID(String courseID) {
        mCourseID = courseID;
    }

    public String getGrade() {
        return mGrade;
    }

    public void setGrade(String grade) {
        mGrade = grade;
    }

    public int getCWID() {
        return mCWID;
    }

    public void setCWID(int CWID) {
        mCWID = CWID;
    }

    @Override
    public void insert(SQLiteDatabase db) {
        ContentValues vals = new ContentValues();
        vals.put("CourseName", mCourseID);
        vals.put("CourseGrade", mGrade);
        vals.put("CWID", mCWID);
        db.insert("Courses", null, vals);
    }

    @Override
    public void createTable(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE IF NOT EXISTS Courses (CourseName Text, CourseGrade Text, CWID Integer)");
    }

    @Override
    public void initFrom(SQLiteDatabase db, Cursor c) {
        mCourseID = c.getString(c.getColumnIndex("CourseName"));
        mGrade = c.getString(c.getColumnIndex("CourseGrade"));
        mCWID = c.getInt(c.getColumnIndex("CWID"));
    }



}
