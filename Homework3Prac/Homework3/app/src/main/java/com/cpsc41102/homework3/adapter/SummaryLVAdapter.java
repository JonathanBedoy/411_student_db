package com.cpsc41102.homework3.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.cpsc41102.homework3.R;
import com.cpsc41102.homework3.StudentDetailActivity;
import com.cpsc41102.homework3.model.Student;
import com.cpsc41102.homework3.model.StudentDB;

import java.util.ArrayList;

public class SummaryLVAdapter extends BaseAdapter {

    protected int cnt = 0;
    StudentDB mStudentDB;
    ArrayList<Student> mStudents;


    public SummaryLVAdapter(Context context){
        mStudentDB = new StudentDB(context);
        mStudentDB.retrieveStudentObjects();
//        for(int i=0; i<mStudents.size(); i++){
//            Log.d("FIRSTNAME", "SummaryLVAdapter: " + mStudents.get(i).getFirstName());
//        }
    }

    public void refreshStudents() {
        mStudentDB.retrieveStudentObjects();
    }
    @Override
    public int getCount() {
        return mStudentDB.getStudents().size();
    }

    @Override
    public Object getItem(int i) {
        return mStudentDB.getStudents().get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View row_view;

        if (view == null) {
            cnt++;
            LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
            row_view = inflater.inflate(R.layout.student_row, viewGroup, false);
        } else row_view = view;

        Student s = mStudentDB.getStudents().get(i);

        TextView firstNameView = row_view.findViewById(R.id.first_name);
        firstNameView.setText(s.getFirstName());
        Log.d("SummaryLVAdapter", "getView: " + s.getFirstName());
        TextView lastNameView = row_view.findViewById(R.id.last_name);
        lastNameView.setText(" "+s.getLastName());
        row_view.setTag(new Integer(i));
        row_view.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //
                        Toast.makeText(view.getContext(), "View Object was touched", Toast.LENGTH_SHORT).show();


                        // Page Navigation
                        Intent intent = new Intent(view.getContext(), StudentDetailActivity.class);
                        intent.putExtra("PersonIndex", ((Integer) view.getTag()).intValue());
                        view.getContext().startActivity(intent);
                    }
                }
        );
        return row_view;
    }
}
