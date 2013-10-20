package my.example.myexpandablelistview.lesson2;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;
import my.example.myexpandablelistview.R;



public class SecondActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.second_activity);
		Toast.makeText(this, "Start SecondActivity", Toast.LENGTH_SHORT).show();
	}

}
