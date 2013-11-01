package com.dbexample.dbapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by korolkov on 10/26/13.
 */
public class DbHelper extends SQLiteOpenHelper {

    public static final int     DATABASE_VERSION  =  1;
    public static final String  DATABASE_NAME     =  "Drivers.db";


    private void insertDrivers(SQLiteDatabase database){

        ContentValues values;

        for(int i = 1; i < 11; i++) {
            values = new ContentValues();
            values.put(DbContract.Drivers.COLUMN_NAME_DRIVER_ID,i);
            values.put(DbContract.Drivers.COLUMN_NAME_FIRST_NAME,"name"+i);
            values.put(DbContract.Drivers.COLUMN_NAME_LAST_NAME,"surname"+i);
            values.put(DbContract.Drivers.COLUMN_NAME_LICENSE,i);
            database.insert(DbContract.Drivers.TABLE_NAME,null,values);
        }
    }

    private void insertCars(SQLiteDatabase database){
        ContentValues values;

        for(int i = 1; i < 11; i++) {
            values = new ContentValues();
            values.put(DbContract.Cars.COLUMN_CAR_TO_DRIVER_ID,i);
            values.put(DbContract.Cars.COLUMN_NAME_BRAND,"brand"+i);
            values.put(DbContract.Cars.COLUMN_NAME_MODEL,"model"+i);
            database.insert(DbContract.Cars.TABLE_NAME,null,values);
        }

    }


    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(DbContract.Drivers.SQL_CREATE_DRIVERS);
        sqLiteDatabase.execSQL(DbContract.Cars.SQL_CREATE_CARS);
        insertDrivers(sqLiteDatabase);
        insertCars(sqLiteDatabase);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i2) {
        sqLiteDatabase.execSQL(DbContract.Drivers.SQL_DELETE_DRIVERS);
        sqLiteDatabase.execSQL(DbContract.Cars.SQL_DELETE_CARS);
        onCreate(sqLiteDatabase);
    }
}
