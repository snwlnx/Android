package com.dbexample.dbapplication;

import android.app.Activity;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.util.HashMap;
import java.util.Map;

public class EditRowDialog extends DialogFragment {

    private String[]         data;
    private int              position;

    private EditText         name;
    private EditText         surname;
    private EditText         carBrand;

    private EditRowsListener listener;



    public static EditRowDialog newInstance(int position) {
        EditRowDialog dialog = new EditRowDialog();
        dialog.setPosition(position);
        return dialog;
    }

    private void setPosition(int position) {
        this.position = position;
    }

    public void setData(String[] data) {
        this.data = data;
    }


    public interface EditRowsListener {
        void editRows(Map data);
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        listener = (EditRowsListener)activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog, container);

        name     = (EditText) view.findViewById(R.id.name);
        surname  = (EditText) view.findViewById(R.id.surname);
        carBrand = (EditText) view.findViewById(R.id.brand);

        name.setText(data[0]);
        surname.setText(data[1]);
        carBrand.setText(data[2]);

        Button btnOk = (Button)view.findViewById(R.id.btnOk);
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Map<String,String> data = new HashMap<String, String>();
                data.put(MainActivity.INTENT_DRIVER_ID,Integer.toString(position));
                data.put(MainActivity.INTENT_NAME,name.getText().toString());
                data.put(MainActivity.INTENT_SURNAME,surname.getText().toString());
                data.put(MainActivity.INTENT_CAR_BRAND,carBrand.getText().toString());

                listener.editRows(data);
                EditRowDialog.this.dismiss();
            }
        });

        Button btnCancel = (Button)view.findViewById(R.id.btnCancel);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditRowDialog.this.dismiss();
            }
        });
        return view;

    }
}