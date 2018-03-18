package com.example.android.amex;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class complaint extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaint);
    }

    public void complain(View view){

        Intent i=new Intent(this,chat.class);
        EditText type  =(EditText)findViewById(R.id.type);
        String ty=type.getText().toString();
        EditText complaint  =(EditText)findViewById(R.id.complaint);
        String comp=complaint.getText().toString();
        i.putExtra("Complaint",comp);
        i.putExtra("type",ty);
        startActivity(i);
    }
}
