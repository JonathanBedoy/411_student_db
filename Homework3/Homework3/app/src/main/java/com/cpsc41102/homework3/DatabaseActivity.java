package com.cpsc41102.homework3;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;

import java.io.File;

public class DatabaseActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        File dbFile = this.getDatabasePath("student.db");
        Log.d("Database Log", ""+dbFile);
        SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(dbFile,null);

        db.execSQL("CREATE TABLE Student (FirstName Text, LastName Text, CWID Integer)");

//        db.execSQL("DELETE FROM Student WHERE CWID=?", new String[]{"123456789"});
        db.execSQL("INSERT INTO Student VALUES ('Theresa', 'Tanubrata', 123456789)");


//        db.execSQL("DELETE FROM Student WHERE CWID=?", new String[]{"2345677891"});
        db.execSQL("INSERT INTO Student VALUES (?,?,?)", new String[]{"Regina", "Falange", "2345677891"});

//        db.delete("Student", "CWID=?", new String[]{"345678912"});
        ContentValues vals = new ContentValues();
        vals.put("FirstName", "James");
        vals.put("LastName", "Karaire");
        vals.put("CWID", "345678912");
        db.insert("Student", null, vals);

        Cursor cursor = db.query("Student", null, null, null, null, null, null);
        if(cursor.getCount() > 0){
            while (cursor.moveToNext()){
                String fName = cursor.getString(cursor.getColumnIndex("FirstName"));
                int cwid = cursor.getInt(cursor.getColumnIndex("CWID"));
                //
                Log.d("Database Log", fName + ": " + cwid);
            }
        }


        db.close();

    }
}
