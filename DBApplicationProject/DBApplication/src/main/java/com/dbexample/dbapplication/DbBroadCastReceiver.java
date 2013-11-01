package com.dbexample.dbapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by korolkov on 11/1/13.
 */
public class DbBroadCastReceiver extends BroadcastReceiver {

    public  interface DbObserver {
        public void onChange();
    }

    private DbObserver mObserver;

    DbBroadCastReceiver(DbObserver observer){
        mObserver = observer;
    }
    @Override
    public void onReceive(Context context, Intent intent) {
        mObserver.onChange();
    }
}
