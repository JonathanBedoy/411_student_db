package com.cpsc41102.homework3;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.NonNull;

import com.cpsc41102.homework3.adapter.SummaryLVAdapter;

import java.io.File;

public class SummaryLVActivity extends Activity {

    protected ListView mSummaryView;
    protected Menu addMenu;
    protected Button btn;
    protected final String TAG = "Summary Screen";
    protected SummaryLVAdapter ad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.summary_listview);
        //

        mSummaryView = findViewById(R.id.summary_list_view_id);

        ad = new SummaryLVAdapter(this);
        mSummaryView.setAdapter(ad);

        File dbFile = this.getDatabasePath("student.db");
        SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(dbFile,null);

        db.execSQL("CREATE TABLE IF NOT EXISTS Student (FirstName Text, LastName Text, CWID Integer)");
        db.execSQL("CREATE TABLE IF NOT EXISTS Courses (CourseName Text, CourseGrade Text, CWID Integer)");

        db.execSQL("DELETE FROM Courses WHERE CWID=?", new String[]{"123456789"});
        db.execSQL("DELETE FROM Student WHERE CWID=?", new String[]{"123456789"});
        db.execSQL("INSERT INTO Student VALUES ('Jonathan', 'Bedoy', 123456789)");
        db.execSQL("INSERT INTO Courses VALUES ('CPSC 449', 'C+', 123456789)");
        db.execSQL("INSERT INTO Courses VALUES ('CPSC 311', 'B-', 123456789)");


        db.execSQL("DELETE FROM Student WHERE CWID=?", new String[]{"234567891"});
        db.execSQL("DELETE FROM Courses WHERE CWID=?", new String[]{"234567891"});
        db.execSQL("INSERT INTO Student VALUES (?,?,?)", new String[]{"John", "Snow", "234567891"});
        db.execSQL("INSERT INTO Courses VALUES ('CPSC 449', 'F', 234567891)");
        db.execSQL("INSERT INTO Courses VALUES ('CPSC 311', 'B-', 234567891)");

        db.delete("Student", "CWID=?", new String[]{"345678912"});
        db.execSQL("DELETE FROM Courses WHERE CWID=?", new String[]{"345678912"});
        ContentValues vals = new ContentValues();
        vals.put("FirstName", "Johnny");
        vals.put("LastName", "Apple");
        vals.put("CWID", "345678912");
        db.insert("Student", null, vals);
        db.execSQL("INSERT INTO Courses VALUES ('CPSC 332', 'C', 345678912)");
        db.execSQL("INSERT INTO Courses VALUES ('CPSC 301', 'A', 345678912)");

//        Cursor cursor = db.query("Student", null, null, null, null, null, null);
//        if(cursor.getCount() > 0){
//            while (cursor.moveToNext()){
//                String fName = cursor.getString(cursor.getColumnIndex("FirstName"));
//                int cwid = cursor.getInt(cursor.getColumnIndex("CWID"));
//                //
//                Log.d("Database Log", fName + ": " + cwid);
//            }
//        }

        db.close();

        btn = (Button)findViewById(R.id.add_student_button);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SummaryLVActivity.this, AddStudentActivity.class));
            }
        });

    }

    @Override
    protected void onStart() {
        ad.notifyDataSetChanged();
        ad.refreshStudents();
        super.onStart();
    }

    @Override
    protected void onPause() {
        Log.d(TAG, "onPause() called ");
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.d(TAG, "onStop() called");
        super.onStop();
    }

    @Override
    protected void onResume() {
        Log.d(TAG, "onResume() called");
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        Log.d(TAG, "onDestroy() called");
        super.onDestroy();
    }
}