package com.cpsc41102.homework3;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.cpsc41102.homework3.model.CourseEnrollment;
import com.cpsc41102.homework3.model.Student;
import com.cpsc41102.homework3.model.StudentDB;

import java.util.ArrayList;

public class StudentDetailActivity extends AppCompatActivity {
    public final static String EXTRA_MESSAGE = "MESSAGE";
    private ListView obj;
    DBHelper mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mydb = new DBHelper(this);
        ArrayList array_list = mydb.getAllCotacts();
        ArrayAdapter arrayAdapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1, array_list);

        obj = (ListView)findViewById(R.id.listView1);
        obj.setAdapter(arrayAdapter);
        obj.setOnItemClickListener(new OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,long arg3) {
                // TODO Auto-generated method stub
                int id_To_Search = arg2 + 1;

                Bundle dataBundle = new Bundle();
                dataBundle.putInt("id", id_To_Search);

                Intent intent = new Intent(getApplicationContext(),DisplayContact.class);

                intent.putExtras(dataBundle);
                startActivity(intent);
            }
        });
    }
//    protected Menu detailMenu;
//    protected final String TAG = "Detail Screen";
//    StudentDB mStudentDB;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        setContentView(R.layout.activity_student_detail);
//
//        mStudentDB = new StudentDB(this);
//        mStudentDB.retrieveStudentObjects();
//        Button btn = (Button) findViewById(R.id.add_course_button_id);
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                LinearLayout nameView = (LinearLayout) findViewById(R.id.add_course_line);
//                LinearLayout pageView = new LinearLayout(StudentDetailActivity.this);
//                pageView.setOrientation(LinearLayout.HORIZONTAL);
//                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
//                        LinearLayout.LayoutParams.WRAP_CONTENT,
//                        LinearLayout.LayoutParams.WRAP_CONTENT
//                );
//                params.setMargins(1, 1, 1, 1);
//                nameView.setBackgroundColor(Color.BLACK);
//
//                EditText courseView = new EditText(StudentDetailActivity.this);
//
//                courseView.setBackgroundColor(Color.WHITE);
//                courseView.setWidth(328);
//                courseView.setLayoutParams(params);
//                courseView.setGravity(Gravity.CENTER_HORIZONTAL);
//                pageView.addView(courseView);
//
//
//                EditText gradeView = new EditText(StudentDetailActivity.this);
//
//                gradeView.setBackgroundColor(Color.WHITE);
//                gradeView.setWidth(328);
//                gradeView.setLayoutParams(params);
//                gradeView.setGravity(Gravity.CENTER_HORIZONTAL);
//                pageView.addView(gradeView);
//
//                nameView.addView(pageView);
//                nameView.invalidate();
//
//            }
//        });
//    }
//
//
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.detail_screen_menu,menu);
//        detailMenu = menu;
//        menu.findItem(R.id.action_done).setVisible(true);
//        return super.onCreateOptionsMenu(menu);
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        if(item.getItemId() == R.id.action_done){
//            EditText fNameView = findViewById(R.id.first_name_val_id);
//            EditText lNameView = findViewById(R.id.last_name_val_id);
//            EditText cwidView = findViewById(R.id.cwid_val_id);
//
//            ArrayList<Student> sList = mStudentDB.getStudents();
//            Student sObj = new Student();
//            sObj.setFirstName(fNameView.getText().toString());
//            sObj.setLastName(lNameView.getText().toString());
//            sObj.setCWID(Integer.parseInt(cwidView.getText().toString()));
//
//            ArrayList<CourseEnrollment> cList = new ArrayList<>();
//
//            //go through each child/cell of the course table
//            LinearLayout llView = findViewById(R.id.add_course_line);
//            int count = llView.getChildCount();
//            for(int i=0; i< count; i++){
//                View view = llView.getChildAt(i);
//                LinearLayout row = (LinearLayout) view;
//                int counter = row.getChildCount();
//
//                String courseId, grade;
//                CourseEnrollment course = new CourseEnrollment();
//                Log.d("CHILD", "onOptionsItemSelected: " + i);
//                for(int j=0; j<counter; j++){
//                    View v = row.getChildAt(j);
//                    Log.d("CHILD", "onOptionsItemSelected: " + j);
//                    EditText editText = (EditText)v;
//                    Log.d("EDITTEXT CHILD", editText.getText().toString());
//                    if(j == 0){
//                        courseId = editText.getText().toString();
//                        course.setCourseID(courseId);
//                    }else{
//                        grade = editText.getText().toString();
//                        course.setGrade(grade);
//                    }
//                    editText.setEnabled(false);
//                }
//                cList.add(course);
//
//            }
//
//            sObj.setCourses(cList);
//
//            fNameView.setEnabled(false);
//            lNameView.setEnabled(false);
//            cwidView.setEnabled(false);
//            sList.add(sObj);
//
//
//            mStudentDB.setStudents(sList);
//        }
//
//
//        return super.onOptionsItemSelected(item);
//    }
//
//    @Override
//    protected void onStart() {
//        Log.d(TAG, "onStart() called ");
//        super.onStart();
//    }
//
//    @Override
//    protected void onPause() {
//        Log.d(TAG, "onPause() called ");
//        super.onPause();
//    }
//
//    @Override
//    protected void onStop() {
//        Log.d(TAG, "onStop() called");
//        super.onStop();
//    }
//
//    @Override
//    protected void onResume() {
//        Log.d(TAG, "onResume() called");
//        super.onResume();
//    }
//
//    @Override
//    protected void onDestroy() {
//        Log.d(TAG, "onDestroy() called");
//        super.onDestroy();
//    }
}

