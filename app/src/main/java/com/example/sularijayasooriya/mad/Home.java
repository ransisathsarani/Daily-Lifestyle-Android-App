package com.example.sularijayasooriya.mad;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.LinearLayout;

public class Home extends AppCompatActivity implements View.OnClickListener{

    private CardView diaryc ,reminderc ,grocerylistc  ;
    LinearLayout logoutc, profilec ;
    private Session session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //defining cards
        diaryc = (CardView)findViewById(R.id.diarycard);
        reminderc = (CardView)findViewById(R.id.remindercard);
        grocerylistc = (CardView)findViewById(R.id.grocerylistcard);
        logoutc = (LinearLayout)findViewById(R.id.logoutcard);
        profilec = (LinearLayout)findViewById(R.id.profilecard);


        //add click listener to the cards
        diaryc.setOnClickListener(this);
        reminderc.setOnClickListener(this);
        grocerylistc.setOnClickListener(this);
        logoutc.setOnClickListener(this);
        profilec.setOnClickListener(this);

        session = new Session(this);
        if(!session.loggedin()){
            logout();
        }

        logoutc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
            }
        });


    }

    private void logout() {
        session.setLoggedin((false));
        finish();
        startActivity(new Intent(Home.this,Login.class));
    }

    @Override
    public void onClick(View v) {

        Intent i ;
        switch (v.getId()){
            case R.id.diarycard :i= new Intent(this,Memo.class); startActivity(i);break;

            case  R.id.remindercard :i =new  Intent(this,EventMain.class);startActivity(i);break;

            case  R.id.grocerylistcard :i =new  Intent(this,GroceryMain.class);startActivity(i);break;

            case  R.id.profilecard :i =new Intent(this,UserProfile.class);startActivity(i);break;

            default:break;
        }


    }


}