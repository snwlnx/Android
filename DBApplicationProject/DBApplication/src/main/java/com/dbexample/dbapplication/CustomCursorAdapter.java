package com.dbexample.dbapplication;

import android.annotation.TargetApi;
import android.content.Context;
import android.database.Cursor;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

/**
 * Created by korolkov on 10/30/13.
 */
public class CustomCursorAdapter  extends CursorAdapter {

    private static class Holder{
        int      nameIndex      = 1;
        int      surnameIndex   = 2;
        int      carBrandIndex  = 3;
        TextView name;
        TextView surname;
        TextView carBrand;
    }


    CustomCursorAdapter(Cursor cursor, Context context,int flag){
        super(context,cursor,flag);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        View view        =  LayoutInflater.from(context).inflate(R.layout.list_item,null);
        Holder holder    =  new Holder();
        holder.name      = (TextView)view.findViewById(R.id.name);
        holder.surname   = (TextView)view.findViewById(R.id.surname);
        holder.carBrand  = (TextView)view.findViewById(R.id.carBrand);
        view.setTag(holder);
        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        Holder holder = (Holder)view.getTag();
        holder.name.setText(cursor.getString(holder.nameIndex));
        holder.surname.setText(cursor.getString(holder.surnameIndex));
        holder.carBrand.setText(cursor.getString(holder.carBrandIndex));
    }
}
