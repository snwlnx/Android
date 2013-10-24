package com1.example.app1;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

/**
 * Created by korolkov on 10/5/13.
 */
public class ActivityPage4 extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page4);

    }


    public void showNoticeDialog(View view) {
        NoticeDialog noticeDialog = new NoticeDialog(this);
        noticeDialog.show(getFragmentManager(),"");
    }
}
