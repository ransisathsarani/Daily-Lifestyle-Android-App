package com.example.sularijayasooriya.mad;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UserProfile extends AppCompatActivity {

    EditText iddd,email,pass;
    Button vieww,delete,updd,logout;
    DatabaseHelper db;

    private Session s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        db = new DatabaseHelper(UserProfile.this);
        iddd = (EditText)findViewById(R.id.id);
        email =(EditText)findViewById(R.id.email);
        pass = (EditText)findViewById(R.id.pass);
        updd = (Button)findViewById(R.id.updd);
        delete = (Button)findViewById(R.id.delete);
        vieww = (Button)findViewById(R.id.vieww);
        logout = (Button)findViewById(R.id.logout) ;

        UpdateData();
        DeleteData();
        viewAll();

        s = new Session(this);
        if(!s.loggedin()){
            logout();
        }

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
            }
        });

    }

    private void logout() {
        s.setLoggedin((false));
        finish();
        startActivity(new Intent(UserProfile.this,Login.class));
    }

    public void UpdateData() {
        updd.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isUpdate = db.updateData(iddd.getText().toString(),
                                email.getText().toString(),
                                pass.getText().toString());
                        if(isUpdate == true)
                            Toast.makeText(UserProfile.this,"User Data Is Updated Successfully", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(UserProfile.this,"Update Failed, Try Again",Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

    public void DeleteData() {
        delete.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Integer deletedRows = db.deleteData(iddd.getText().toString());
                        if(deletedRows > 0)
                            Toast.makeText(UserProfile.this,"User Deleted",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(UserProfile.this,"Try Again",Toast.LENGTH_LONG).show();
                    }
                }
        );
    }


    public void viewAll() {
        vieww.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cursor res = db.getAllData();
                        if(res.getCount() == 0) {
                            // show message
                            showMessage("Error","Nothing found");
                            return;
                        }

                        StringBuffer buffer = new StringBuffer();
                        while (res.moveToNext()) {
                            buffer.append("Id :"+ res.getString(0)+"\n");
                            buffer.append("Email :"+ res.getString(1)+"\n");
                            buffer.append("Password :"+ res.getString(2)+"\n");
                        }

                        // Show all data
                        showMessage("Data",buffer.toString());
                    }
                }
        );
    }

    public void showMessage(String title,String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }


}