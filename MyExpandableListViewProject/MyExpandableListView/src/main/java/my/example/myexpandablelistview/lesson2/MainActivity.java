package my.example.myexpandablelistview.lesson2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import my.example.myexpandablelistview.R;
public class MainActivity extends Activity {

	private static final int THIRD_ACTIVITY = 100500;
	private static final String BUTTON_TWO_STATE = "button_two_state";
	private Button btn2;
	private String btn2Text;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if(savedInstanceState != null) {
			btn2Text = savedInstanceState.getString(BUTTON_TWO_STATE);
			
		}
		setContentView(R.layout.activity_main);
		
		Button btn1 = (Button) findViewById(R.id.button1);
		btn1.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent(MainActivity.this, SecondActivity.class);
				startActivity(i);
			}
		});
		
		btn2 = (Button) findViewById(R.id.button2);
		if(btn2Text != null) {
			btn2.setText(btn2Text);
		}
		btn2.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				startActivityForResult(new Intent(MainActivity.this, ThirdActivity.class), THIRD_ACTIVITY);
			}
		});
		
		
	}
	
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putString(BUTTON_TWO_STATE, btn2.getText().toString());
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if(resultCode == Activity.RESULT_OK && requestCode == THIRD_ACTIVITY) {
			String extra = data.getStringExtra(ThirdActivity.THIRD_ACTIVITY_EXTRA);
			btn2.setText(extra.equals("") ? "No" : extra);
		}
	}


}
