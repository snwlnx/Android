package com.dbexample.dbapplication;

import android.app.IntentService;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by korolkov on 11/1/13.
 */
public class DbService extends IntentService {

    private SQLiteOpenHelper mHelper;


    public DbService(){
        super("dbService");
        mHelper   =  new DbHelper(this);
    }


    @Override
    protected void onHandleIntent(Intent intent) {

        String name     = intent.getStringExtra(MainActivity.INTENT_NAME),
               surname  = intent.getStringExtra(MainActivity.INTENT_SURNAME),
               brand    = intent.getStringExtra(MainActivity.INTENT_CAR_BRAND),
               driverId = intent.getStringExtra(MainActivity.INTENT_DRIVER_ID);

        SQLiteDatabase db =  mHelper.getWritableDatabase();

        ContentValues cvDrivers = new ContentValues();
        cvDrivers.put(DbContract.Drivers.COLUMN_NAME_FIRST_NAME,name);
        cvDrivers.put(DbContract.Drivers.COLUMN_NAME_LAST_NAME,surname);

        db.update(DbContract.Drivers.
                TABLE_NAME,cvDrivers," "+DbContract.Drivers.COLUMN_NAME_DRIVER_ID + " = ? ",
                new String[]{ driverId });

        ContentValues cvCars = new ContentValues();
        cvCars.put(DbContract.Cars.COLUMN_NAME_BRAND,brand);

        db.update(DbContract.Cars.TABLE_NAME,cvCars,DbContract.Cars.COLUMN_CAR_TO_DRIVER_ID + " = ? ",
                new String[]{ driverId });


        Intent i = new Intent();
        i.setAction("com.dbexample.Broadcast");
        sendBroadcast(i);


    }
}
