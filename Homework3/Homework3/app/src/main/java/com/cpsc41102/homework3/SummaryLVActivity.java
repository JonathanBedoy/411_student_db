package com.cpsc41102.homework3;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import androidx.annotation.NonNull;

import com.cpsc41102.homework3.adapter.SummaryLVAdapter;

public class SummaryLVActivity extends Activity {

    protected ListView mSummaryView;
    protected Menu addMenu;
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
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_menu,menu);
        addMenu = menu;
        menu.findItem(R.id.action_add).setVisible(true);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.action_add){
            Intent intent = new Intent(SummaryLVActivity.this, StudentDetailActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        Log.d(TAG, "onStart() called ");
        ad.notifyDataSetChanged();
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