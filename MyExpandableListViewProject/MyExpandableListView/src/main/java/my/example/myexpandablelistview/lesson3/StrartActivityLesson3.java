package my.example.myexpandablelistview.lesson3;

import my.example.myexpandablelistview.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


/**
 * Created with IntelliJ IDEA.
 * User: korolkov
 * Date: 9/28/13
 * Time: 9:54 AM
 * To change this template use File | Settings | File Templates.
 */
public class StrartActivityLesson3 extends Activity {
    private static final int THIRD_ACTIVITY = 100500;
    private static final String BUTTON_TWO_STATE = "button_two_state";
    private static final String ACTION_FOURTH_ACTIVITY = "com.example.examples.FOURTH_ACTIVITY";
    private Button btn2;
    private String btn2Text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);

        if(savedInstanceState != null) {
            btn2Text = savedInstanceState.getString(BUTTON_TWO_STATE);

        }
        setContentView(R.layout.lesson3);

        Button btn1 = (Button) findViewById(R.id.button1);

        btn1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(StrartActivityLesson3.this, AsyncTaskSimpleActivity.class);
                startActivity(i);
            }
        });

        Button btn2 = (Button) findViewById(R.id.button2);

        btn2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(StrartActivityLesson3.this, DownloadQueueActivity.class);
                startActivity(i);
            }
        });



    }
}
