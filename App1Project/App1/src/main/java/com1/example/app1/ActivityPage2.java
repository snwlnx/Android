package com1.example.app1;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.TextView;

/**
 * Created by korolkov on 10/5/13.
 */
public class ActivityPage2 extends Activity {


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.page_2, menu);

        return super.onCreateOptionsMenu(menu);
    }


        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page2);

        Bundle bundle = getIntent().getExtras();
        String text = bundle.getString(ActivityPage1.EditText);



        TextView textViewLeft = (TextView)findViewById(R.id.LeftText);
        TextView textViewRight = (TextView)findViewById(R.id.RightText);

            if (!text.isEmpty()) {
                textViewLeft.setText(text.substring(0,text.length()/2));
                textViewRight.setText(text.substring(text.length()/2+1,text.length()));

            }

    }


}
