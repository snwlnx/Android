package my.example.myexpandablelistview;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;

import java.util.ArrayList;

public class MainActivity extends Activity {
    final String LOG_TAG = "myLogs";
    ArrayList<ArrayList<String>> lessons;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PackageInfo pInfo;



        setContentView(R.layout.activity);


        lessons = new ArrayList<ArrayList<String>>();

        ArrayList<String> activitiesLesson2 = new ArrayList<String>();
        ArrayList<String> activitiesLesson3 = new ArrayList<String>();

        try {
            pInfo =  getPackageManager().getPackageInfo("my.example.myexpandablelistview", 1);

            for (ActivityInfo activityInfo: pInfo.activities){
                if (activityInfo != null) {
                    if (activityInfo.name.contains("lesson2")) {
                        activitiesLesson2.add(activityInfo.name);
                    } else if (activityInfo.name.contains("lesson3")) {
                        activitiesLesson3.add(activityInfo.name);
                    }
                    Log.v("act",activityInfo.name);
                }
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        lessons.add(activitiesLesson2);
        lessons.add(activitiesLesson3);

        ExpandableListView    listView = (ExpandableListView)findViewById(R.id.list_item);


        ExpandableListAdapter adapter = new ListViewAdapter(getApplicationContext(),lessons);
        listView.setAdapter(adapter);
        listView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view,
                                        int lessonId, int childId, long l) {
                try {
                    startActivity(new Intent(MainActivity.this,Class.forName(lessons.get(lessonId).get(childId))));
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
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
