package com.codingblocks.layouts;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    Button btn1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        EditText et1 = findViewById(R.id.et1);
        btn1 = findViewById(R.id.btn1);
        String a = ((EditText)findViewById(R.id.et1)).getText().toString();

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("TEXT", "Display Text" + a);
            }
        });


    }
}
