package my.example.myexpandablelistview.lesson3;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;


import java.util.concurrent.TimeUnit;

import my.example.myexpandablelistview.R;

public class AsyncTaskSimpleActivity extends Activity {

	private MyTask   mMyTask;
	private TextView mCounter;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.asynctasksimple_main);
		Log.d("qwe", "create MainActivity: " + this.hashCode());

		mCounter = (TextView) findViewById(R.id.tv);

		mMyTask = (MyTask) getLastNonConfigurationInstance();
		if (mMyTask == null) {
			mMyTask = new MyTask();
			mMyTask.execute();
		}
		
		mMyTask.link(this);

		Log.d("qwe", "create MyTask: " + mMyTask.hashCode());
	}
	

	public Object onRetainNonConfigurationInstance() {
		mMyTask.unLink();
		return mMyTask;
	}

	static class MyTask extends AsyncTask<String, Integer, Void> {

		AsyncTaskSimpleActivity activity;

		void link(AsyncTaskSimpleActivity act) {
			activity = act;
		}

		void unLink() {
			activity = null;
		}

		@Override
		protected Void doInBackground(String... params) {
			try {
				for (int i = 1; i <= 10; i++) {
					TimeUnit.SECONDS.sleep(1);
					publishProgress(i);
					Log.d("qwe", "i = " + i + ", MyTask: " + this.hashCode()
							+ ", MainActivity: " + activity.hashCode());
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			return null;
		}

		@Override
		protected void onProgressUpdate(Integer... values) {
			super.onProgressUpdate(values);
			activity.mCounter.setText("i = " + values[0]);
		}
	}

}
