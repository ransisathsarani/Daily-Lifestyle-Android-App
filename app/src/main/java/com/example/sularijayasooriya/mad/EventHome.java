package com.example.sularijayasooriya.mad;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class EventHome extends AppCompatActivity {

    public Button redirectButton1;

    public void init_1(){
        redirectButton1 = (Button)findViewById(R.id.home_add_event);
        redirectButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent func2 = new Intent(EventHome.this,MainActivity.class);

                startActivity(func2);

            }
        });
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_home);

        init_1();
    }
}
