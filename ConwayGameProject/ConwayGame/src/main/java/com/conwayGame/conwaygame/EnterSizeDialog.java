package com.conwayGame.conwaygame;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class EnterSizeDialog extends DialogFragment {


    private EditText mEditText;

    GameActivity activity;

    EnterSizeDialog(GameActivity activity) {
        this.activity = activity;

    }

    public interface EnterSizeListener {
        void enterSize(EditText editText);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog1, container);

        getDialog().setTitle("SET SIZE");

        mEditText = (EditText) view.findViewById(R.id.dialogText);

        Button btnOk = (Button)view.findViewById(R.id.btnOk);
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity.enterSize(EnterSizeDialog.this.mEditText);
                EnterSizeDialog.this.dismiss();
            }
        });

        Button btnCancel = (Button)view.findViewById(R.id.btnCancel);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EnterSizeDialog.this.dismiss();
                activity.grid.start();
            }
        });
        return view;

    }
}