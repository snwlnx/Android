package com.dbexample.dbapplication;

import android.app.Activity;
import android.app.LoaderManager;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.Loader;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.Map;

public class MainActivity extends Activity implements EditRowDialog.EditRowsListener,LoaderManager.LoaderCallbacks<Cursor>{

    public static String INTENT_DRIVER_ID       = "driver_id";
    public static String INTENT_NAME        = "name";
    public static String INTENT_SURNAME     = "surname";

    public static String INTENT_CAR_BRAND   = "car";
    public static String INTENT_MODEL       = "model";


    private DbHelper           mHelper;
    private CursorAdapter      mAdapter;
    private DbAsyncLoader      mLoader;
    private BroadcastReceiver  mReceiver;


    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(mReceiver);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_main);

        mHelper   =  new DbHelper(getApplicationContext());
        SQLiteDatabase db =  mHelper.getWritableDatabase();

        Cursor cursor = db.rawQuery(DbContract.Cars.SQL_JOIN,null);

        ListView lw = (ListView)findViewById(R.id.listView);
        mAdapter = new CustomCursorAdapter(cursor,getApplicationContext(),
        CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        lw.setAdapter(mAdapter);

        lw.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                showEditDialog(position);
           }
        });

        IntentFilter filter = new IntentFilter("com.dbexample.Broadcast");

        getLoaderManager().initLoader(1,null,this);

        mReceiver = new DbBroadCastReceiver(mLoader);
        registerReceiver(mReceiver, filter);


    }



    private void showEditDialog(int position){
        EditRowDialog editDialog = EditRowDialog.newInstance(position);
        editDialog.show(getFragmentManager(),"");
        editDialog.setData(mLoader.getData(position));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.action_settings:
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        // This is called when a new Loader needs to be created.  This
        // sample only has one Loader with no arguments, so it is simple.
        mLoader = new DbAsyncLoader(getApplicationContext());
        return mLoader;
    }

    @Override public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        // Set the new data in the adapter.
        mAdapter.swapCursor(data);

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        // For whatever reason, the Loader's data is now unavailable.
        // Remove any references to the old data by replacing it with
        // a null Cursor.
        mAdapter.swapCursor(null);
    }


    @Override
    public void editRows(Map data) {
        Intent intent = new Intent(this,DbService.class);
        intent.putExtra(INTENT_NAME,(String)data.get(INTENT_NAME));
        intent.putExtra(INTENT_SURNAME,(String)data.get(INTENT_SURNAME));
        intent.putExtra(INTENT_CAR_BRAND,(String)data.get(INTENT_CAR_BRAND));
        intent.putExtra(INTENT_DRIVER_ID,(String)data.get(INTENT_DRIVER_ID));
        startService(intent);
    }
}
