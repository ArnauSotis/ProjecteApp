package com.example.david.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

public class Activity2  extends Activity {
    TextView txview2;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(new GameView(this));

    }
//        txview2 = (TextView) findViewById(R.id.textView2);
//        Bundle intentdata = getIntent().getExtras();
//        String txtpas = intentdata.getString("userName");
//        String txtpas2 = intentdata.getString("password");
//        txview2.setText(txtpas + txtpas2);

        //Toast.makeText(Activity2.this, "Successful", Toast.LENGTH_LONG).show();
}
