package com.example.android.amex;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Feedback extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
    }

    public void feedback(View view){

        Intent i=new Intent(this,chat.class);
        EditText experience  =(EditText)findViewById(R.id.experience);
        String exp=experience.getText().toString();
        EditText suggestion =(EditText)findViewById(R.id.suggestion);
        String sugg=suggestion.getText().toString();
        Toast.makeText(Feedback.this,sugg,Toast.LENGTH_SHORT).show();
        i.putExtra("experience",exp);
        i.putExtra("suggestion",sugg);
        startActivity(i);

    }
}
