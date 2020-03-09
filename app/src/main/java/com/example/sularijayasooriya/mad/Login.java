package com.example.sularijayasooriya.mad;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    EditText e1, e2;
    Button b1;
    DatabaseHelper db;
    private Session session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        session = new Session(this);
        db = new DatabaseHelper(this);
        e1 =(EditText)findViewById(R.id.editText);
        e2 = (EditText)findViewById(R.id.editText2);
        b1 =(Button)findViewById(R.id.button);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email=e1.getText().toString();
                String password = e2.getText().toString();
                Boolean Chkemailpass =db.emailpassword(email,password);

                if(Chkemailpass==true) {
                    session.setLoggedin(true);
                    Toast.makeText(getApplicationContext(), "Successfully Logged in", Toast.LENGTH_SHORT).show();
                    openHome();
                }

                else
                    Toast.makeText(getApplicationContext(),"Wrong Email or Password",Toast.LENGTH_SHORT).show();


            }
        });

        if (session.loggedin()){
            startActivity(new Intent(Login.this,Home.class));
            finish();
        }


    }

    public void openHome(){
        Intent intent= new Intent(this,Home.class);
        startActivity(intent);
    }



}
