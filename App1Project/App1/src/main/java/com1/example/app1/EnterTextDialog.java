package com1.example.app1;

import android.app.AlertDialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class EnterTextDialog extends DialogFragment {


    private EditText mEditText;

    EnterTextDialog(EditTextDialogListener activity) {
        this.activity = activity;
    }

    EditTextDialogListener activity;

    public interface EditTextDialogListener {
        void onEditText(EditText editText);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog1, container);
        getDialog().setTitle("Hello");
        mEditText = (EditText) view.findViewById(R.id.dialogText);

        Button btnOk = (Button)view.findViewById(R.id.btnOk);
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity.onEditText(EnterTextDialog.this.mEditText);
                EnterTextDialog.this.dismiss();
            }
        });

        Button btnCancel = (Button)view.findViewById(R.id.btnCancel);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EnterTextDialog.this.dismiss();
            }
        });
        return view;

    }
}