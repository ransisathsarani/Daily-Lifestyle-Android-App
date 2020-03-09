package com.example.sularijayasooriya.mad;

import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.QuickContactBadge;
import android.widget.Toast;

public class Memo extends AppCompatActivity {
    MemoDBHelper myDb;
    EditText editEntry ,editTextId;
    Button btnAddData;
    Button btnViewAll;
    Button btnViewUpdate;
    Button btnDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memo);
        myDb = new MemoDBHelper(this);

        editEntry = (EditText)findViewById(R.id.editText_entry);
        editTextId = (EditText)findViewById(R.id.editText_id);
        btnAddData = (Button)findViewById(R.id.button_add);
        btnViewAll = (Button)findViewById(R.id.button_view);
        btnViewUpdate = (Button)findViewById(R.id.button_update);
        btnDelete = (Button)findViewById(R.id.button_delete);
        AddData();
        viewAll();
        UpdateData();
        DeleteData();
    }
    public void DeleteData() {
        btnDelete.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Integer deleteRows = myDb.deleteData(editTextId.getText().toString());
                        if (deleteRows > 0)
                            Toast.makeText(Memo.this, "Data deleted Successfully!!", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(Memo.this, "Data not Deleted", Toast.LENGTH_LONG).show();


                    }
                }
        );
    }
    public void UpdateData(){
        btnViewUpdate.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v){
                        boolean isUpdate = myDb.updateData(editTextId.getText().toString(),editEntry.getText().toString());
                        if (isUpdate == true)
                            Toast.makeText(Memo.this, "Data Updated Successfully!!", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(Memo.this, "Data not Updated", Toast.LENGTH_LONG).show();



                    }
                }
        );
    }
    public  void AddData() {
        btnAddData.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isInserted = myDb.insertData(editEntry.getText().toString());
                        if (isInserted == true)
                            Toast.makeText(Memo.this, "Data Inserted Successfully!!", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(Memo.this, "Data not Inserted", Toast.LENGTH_LONG).show();
                    }
                });

    }
    public void viewAll(){

        btnViewAll.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v){
                        Cursor res = myDb.getAllData();
                        if (res.getCount() == 0) {
                            //show message
                            showMessage("Error","Nothing found");
                            return;
                        }
                        StringBuffer buffer = new StringBuffer();
                        while (res.moveToNext()) {
                            buffer.append("Id :"+res.getString(0)+"\n");
                            buffer.append("Entry :"+res.getString(1)+"\n\n");
                        }
                        //show all data
                        showMessage("Data",buffer.toString());

                    }


                }
        );{

        }
    }
    public  void showMessage(String title,String Message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }


}

