package com1.example.app1;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;

/**
 * Created by korolkov on 10/6/13.
 */
public class NoticeDialog extends DialogFragment {

    boolean showDialog = true;
    CheckBox checkBox;
    Activity activity;
    NoticeDialog(Activity activity) {
        this.activity  = activity;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.dialog2, container);
        getDialog().setTitle("Hello");
        checkBox = (CheckBox)view.findViewById(R.id.checkboxCheese);
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        Button btnOk = (Button)view.findViewById(R.id.btnOk);
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkBox.isChecked()) {
                    activity.findViewById(R.id.buttonPage4).setEnabled(false);
                    NoticeDialog.this.dismiss();

                }

            }
        });

        Button btnCancel = (Button)view.findViewById(R.id.btnCancel);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NoticeDialog.this.dismiss();
            }
        });

 /*       checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (NoticeDialog.this.checkBox.isChecked()) {
                    showDialog = false;
                }else
                    showDialog = true;

            }
        });*/

        return view;

    }

}
