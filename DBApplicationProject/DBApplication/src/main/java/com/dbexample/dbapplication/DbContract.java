package com.dbexample.dbapplication;

import android.provider.BaseColumns;

/**
 * Created by korolkov on 10/26/13.
 */
public final class DbContract {


    public DbContract(){};

    private static final String TEXT_TYPE    = " TEXT ";
    private static final String INTEGER_TYPE = " INTEGER ";
    private static final String COMMA_SEP    = " , ";

    public static abstract class Drivers implements BaseColumns{

        public static final String TABLE_NAME               = "drivers";
        public static final String COLUMN_NAME_DRIVER_ID    = "driver_id";
        public static final String COLUMN_NAME_FIRST_NAME   = "first_name";
        public static final String COLUMN_NAME_LAST_NAME    = "last_name";
        public static final String COLUMN_NAME_B_YEAR = "year";
        public static final String COLUMN_NAME_LICENSE      = "license";

        public static final String SQL_CREATE_DRIVERS =
                "CREATE TABLE "                         + Drivers.TABLE_NAME + " ( " +
                        _ID                     + " INTEGER PRIMARY KEY, " +
                        COLUMN_NAME_DRIVER_ID   + TEXT_TYPE    + COMMA_SEP +
                        COLUMN_NAME_FIRST_NAME  + TEXT_TYPE    + COMMA_SEP +
                        COLUMN_NAME_LAST_NAME   + TEXT_TYPE    + COMMA_SEP +
                        COLUMN_NAME_LICENSE     + TEXT_TYPE    +
                        " );";

        public static final String SQL_DELETE_DRIVERS =
                "DROP TABLE IF EXISTS " + TABLE_NAME;

        public static final String SQL_JOIN_TABLES = "SELECT "+Drivers.TABLE_NAME+"."+_ID+COMMA_SEP
                +COLUMN_NAME_FIRST_NAME+"," +" "+ COLUMN_NAME_LAST_NAME+","+COLUMN_NAME_B_YEAR +
                " FROM "  +      TABLE_NAME+";";
/*                " INNER JOIN "  + Cars.TABLE_NAME + " ON " +COLUMN_NAME_DRIVER_ID + " = " +
               Cars.COLUMN_NAME_DRIVER_TO_CAR_ID+";";*/

    }



    public static abstract class Cars implements BaseColumns{

        public static final String TABLE_NAME               = "cars";
        public static final String COLUMN_CAR_TO_DRIVER_ID  = "driverid";
        public static final String COLUMN_NAME_BRAND        = "brand";
        public static final String COLUMN_NAME_MODEL        = "model";

        public static final String SQL_CREATE_CARS =
                "CREATE TABLE "                 +  TABLE_NAME + " (" +
                        _ID                     +  " INTEGER PRIMARY KEY," +
                        COLUMN_NAME_BRAND       +  TEXT_TYPE + COMMA_SEP +
                        COLUMN_CAR_TO_DRIVER_ID +  TEXT_TYPE + COMMA_SEP +
                        COLUMN_NAME_MODEL       +  TEXT_TYPE +
                        " );";
        public static final  String SQL_JOIN = "select cars._id,first_name,last_name, brand,model,driverid "+
                " from cars inner join drivers on drivers.driver_id = cars.driverid" ;

        public static final String SQL_DELETE_CARS =
                "DROP TABLE IF EXISTS " + TABLE_NAME;

    }




}
