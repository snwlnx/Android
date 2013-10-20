package my.example.myexpandablelistview;

import android.app.ExpandableListActivity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.List;

import my.example.myexpandablelistview.lesson2.FourthActivity;
import my.example.myexpandablelistview.lesson2.SecondActivity;
import my.example.myexpandablelistview.lesson2.ThirdActivity;
import my.example.myexpandablelistview.lesson3.AsyncTaskSimpleActivity;
import my.example.myexpandablelistview.lesson3.DownloadQueueActivity;

public class MainActivity extends Activity {
    final String LOG_TAG = "myLogs";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity);
        ArrayList<ArrayList<String>> lessons = new ArrayList<ArrayList<String>>();

        ArrayList<String> activitiesLesson2 = new ArrayList<String>();
        activitiesLesson2.add("StdActivity");
        activitiesLesson2.add("ActivityForResult");
        activitiesLesson2.add("CustomActivity");

        ArrayList<String> activitiesLesson3 = new ArrayList<String>();
        activitiesLesson3.add("DownLoadActivity");
        activitiesLesson3.add("QueueActivity");

        lessons.add(activitiesLesson2);
        lessons.add(activitiesLesson3);

        ExpandableListView    listView = (ExpandableListView)findViewById(R.id.list_item);


        ExpandableListAdapter adapter = new ListViewAdapter(getApplicationContext(),lessons);
        listView.setAdapter(adapter);
        listView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view,
                                        int lessonId, int childId, long l) {

                switch (lessonId) {
                    case 0:{
                        switch (childId){
                            case 0:{
                                startActivity(new Intent(MainActivity.this, SecondActivity.class));
                            }
                            case 1:{
                                startActivity(new Intent(MainActivity.this, ThirdActivity.class));
                            }
                            case 2:{
                                startActivity(new Intent(MainActivity.this, FourthActivity.class));
                            }
                        }
                    }
                    case 1:{
                        switch (childId){
                            case 0:{
                                startActivity(new Intent(MainActivity.this, AsyncTaskSimpleActivity.class));
                            }
                            case 1:{
                                startActivity(new Intent(MainActivity.this, DownloadQueueActivity.class));
                            }
                        }
                    }
                }
                return true;
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
