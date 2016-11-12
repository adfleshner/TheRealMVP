package com.example.afleshner.therealmvp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        if(getIntent().getData().toString().equals("Hello")){
            Toast.makeText(this, "Hello", Toast.LENGTH_SHORT).show();
        }


    }
}
