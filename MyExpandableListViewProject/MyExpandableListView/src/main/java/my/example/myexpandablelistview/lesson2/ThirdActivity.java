package my.example.myexpandablelistview.lesson2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import my.example.myexpandablelistview.R;


public class ThirdActivity extends Activity {

	protected static final String THIRD_ACTIVITY_EXTRA = "third_extra";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.third_activity);
		
		final EditText edit = (EditText) findViewById(R.id.edit_text);
		
		Button btn = (Button) findViewById(R.id.btn);
		btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent();
				i.putExtra(THIRD_ACTIVITY_EXTRA, edit.getText().toString());
				setResult(Activity.RESULT_OK, i);
				finish();
			}
		});
	}

}
