package com1.example.app1;



import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import android.text.Editable;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ActivityPage1 extends FragmentActivity implements EnterTextDialog.EditTextDialogListener {

    final  public static String EditText = "EditText";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page1);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main, menu);

        return super.onCreateOptionsMenu(menu);
    }

    public void clickButton1(View view) {
        Toast.makeText(getApplicationContext(), "Нажата кнопка", Toast.LENGTH_SHORT).show();
    }
    public void clickButton2(View view) {
        Toast.makeText(getApplicationContext(), "Нажата кнопка", Toast.LENGTH_SHORT).show();
    }

    public void startActivityPage2(MenuItem item) {

        Intent intent = new Intent(ActivityPage1.this, ActivityPage2.class);
        intent.putExtra(EditText,((EditText)findViewById(R.id.enterText)).getText().toString());
        startActivity(intent);
    }

    public void startActivityPage3(MenuItem item) {

        Intent intent = new Intent(ActivityPage1.this, ActivityPage3.class);
        startActivity(intent);
    }

    public void startActivityPage4(MenuItem item) {

        Intent intent = new Intent(ActivityPage1.this, ActivityPage4.class);
        intent.putExtra(EditText,((EditText)findViewById(R.id.enterText)).getText().toString());
        startActivity(intent);
    }

    public void showEditDialog(View view) {
        EnterTextDialog enterTextDialog = new EnterTextDialog(this);
        enterTextDialog.show(getFragmentManager(),"");
    }

    @Override
    public void onEditText(EditText eText) {
        EditText editText = (EditText)findViewById(R.id.enterText);
        Editable oldText  =  editText.getText().append("\n"+eText.getText().toString());
    }
}
