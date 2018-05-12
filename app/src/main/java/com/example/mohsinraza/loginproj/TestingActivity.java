package com.example.mohsinraza.loginproj;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class TestingActivity extends AppCompatActivity {
private TextView displayParameter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testing);
        displayParameter=(TextView)findViewById(R.id.displayValues);
        Intent ii=getIntent();
        displayParameter.setText(ii.getStringExtra("username")+ ii.getStringExtra("password"));
        //firstImage=(ImageView)findViewById(R.id.)
    }
}
