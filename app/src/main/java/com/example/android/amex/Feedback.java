package com.example.android.amex;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

        EditText experience  =(EditText)findViewById(R.id.experience);
        String exp=experience.getText().toString();
        EditText suggestion =(EditText)findViewById(R.id.suggestion);
        String sugg=suggestion.getText().toString();

        Toast.makeText(this,"Complaint Received",Toast.LENGTH_SHORT).show();

        String message = "Experience: "+exp+"\nSuggestions: " + sugg;
        Intent LaunchIntent = getPackageManager().getLaunchIntentForPackage("com.example.sakshigarg.authfirebase");
        LaunchIntent.putExtra("NAME",UserInformation.getName());
        LaunchIntent.putExtra("EMAIL",UserInformation.getEmail());
        LaunchIntent.putExtra("PASSWORD",UserInformation.getPassword());
        LaunchIntent.putExtra("MESSAGE",message);
        startActivity(LaunchIntent);

        finish();

    }
}
