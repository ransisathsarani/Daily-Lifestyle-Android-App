package com.example.sularijayasooriya.mad;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EventMain extends AppCompatActivity {

    SQLiteOpenHelper openHelper;
    SQLiteDatabase database;

    EventDatabaseHelper myDb;
    EditText editTitle, editDate, editTime, editLocation,editID;
    Button addButton;
    Button listButton;
    Button deleteButton;
    Button updateButton;


    public void init() {
        addButton = (Button) findViewById(R.id.addButon);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                openSecondPage();


            }

            private void openSecondPage() {
                Intent int2 = new Intent(EventMain.this, EventSecondPage.class);
                startActivity(int2);

            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_main);
        myDb = new EventDatabaseHelper(this);

        init();


        editID = (EditText)findViewById(R.id.id) ;
        editTitle = (EditText) findViewById(R.id.title);
        editDate = (EditText) findViewById(R.id.date);
        editTime = (EditText) findViewById(R.id.time);
        editLocation = (EditText) findViewById(R.id.location);
        addButton = (Button) findViewById(R.id.addButon);
        listButton = (Button)findViewById(R.id.listButton);
        updateButton = (Button)findViewById(R.id.updateButton);
        deleteButton = (Button)findViewById(R.id.deleteButton);


        addData();
        viewList();

        deleteData();
        updateData();

    }
    public void deleteData(){
        deleteButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Integer deleteRows = myDb.deleteData(editID.getText().toString());

                        if(deleteRows > 0){
                            Toast.makeText(EventMain.this,"Data deleted!",Toast.LENGTH_LONG).show();

                        }
                        else{
                            Toast.makeText(EventMain.this,"Not deleted",Toast.LENGTH_LONG).show();

                        }

                    }
                }
        );
    }


    public void updateData(){
        updateButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        boolean isUpdated = myDb.updateData(editID.getText().toString(),
                                editTitle.getText().toString(),
                                editDate.getText().toString(),
                                editTime.getText().toString(),
                                editLocation.getText().toString());


                        if(isUpdated == true){
                            Toast.makeText(EventMain.this,"Data updated successfully",Toast.LENGTH_LONG).show();

                        }
                        else{
                            Toast.makeText(EventMain.this,"Unsuccessfull!!!!",Toast.LENGTH_LONG).show();

                        }
                    }
                }
        );
    }

    public void viewList(){
        listButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Cursor res = myDb.getAllData();

                        if(res.getCount() == 0){
                            //show message
                            showMessage("Error","Nothing found");
                            return ;

                        }
                        StringBuffer buffer = new StringBuffer();

                        while(res.moveToNext()){

                            buffer.append("id:" + res.getString(0) + "\n");
                            buffer.append("title:" + res.getString(1) + "\n");
                            buffer.append("date:" + res.getString(2) + "\n");
                            buffer.append("time:" + res.getString(3) + "\n");
                            buffer.append("location:" + res.getString(4) + "\n\n");
                        }

                        //show all data

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

    public void addData(){
        addButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        boolean isInserted =  myDb.insertData( editTitle.getText().toString(),
                                editDate.getText().toString(),
                                editTime.getText().toString(),
                                editLocation.getText().toString());

                        if(isInserted == true){
                            Toast.makeText(EventMain.this,"Data inserted successfully",Toast.LENGTH_LONG).show();

                        }
                        else
                            Toast.makeText(EventMain.this,"Unsuccessfull!!!!!",Toast.LENGTH_LONG).show();
                    }
                });
    }
/**
 public void addData() {
 addButton.setOnClickListener(new View.OnClickListener() {
@Override
public void onClick(View view) {
String newTitle = editTitle.getText().toString();

String newDate = editDate.getText().toString();
String newTime = editTime.getText().toString();
String newLoc = editLocation.getText().toString();
if (editTitle.length() != 0) {

addData(newTitle, newDate, newTime, newLoc);
editTitle.setText("");
editDate.setText("");
editTime.setText("");
editLocation.setText("");
} else {
Toast.makeText(getApplicationContext(), "Add all fields", Toast.LENGTH_SHORT).show();
;
}

}
});




 }

 public  void listData(){
 listButton.setOnClickListener(new View.OnClickListener() {
@Override
public void onClick(View view) {
Cursor res = myDb.getAllData();

if(res.getCount() == 0){
//show message
showMessage("Error","Nothing found");
return;
}
StringBuffer buffer = new StringBuffer();
while(res.moveToNext()){
buffer.append("id: "+ res.getString(0) + "\n ");
buffer.append("title: "+ res.getString(1) + "\n ");
buffer.append("date: "+ res.getString(2) + "\n ");
buffer.append("time: "+ res.getString(3) + "\n ");
buffer.append("location: "+ res.getString(4) + "\n\n  ");
}
//show all data
showMessage("Data",buffer.toString());
}
});
 }

 public void showMessage  (String title,String Message){

 AlertDialog.Builder builder = new AlertDialog.Builder(this);
 builder.setCancelable(true);
 builder.setTitle(title);
 builder.setMessage(Message);
 builder.show();
 }


 public void addData(String title,String date,String time,String location){
 boolean insertData = myDb.addData(title,date,time,location);

 if(insertData){
 Toast.makeText(getApplicationContext(),"Successful",Toast.LENGTH_SHORT).show();;
 }else{
 Toast.makeText(getApplicationContext(),"Failed",Toast.LENGTH_SHORT).show();;
 }


 }**/



}