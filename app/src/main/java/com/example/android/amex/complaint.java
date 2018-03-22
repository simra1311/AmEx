package com.example.android.amex;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class complaint extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaint);
    }

    public void complain(View view){

//        Intent i=new Intent(this,chat.class);
        EditText type  =(EditText)findViewById(R.id.type);
        String ty=type.getText().toString();
        EditText complaint  =(EditText)findViewById(R.id.complaint);
        String comp = complaint.getText().toString();
//        i.putExtra("Complaint",comp);
//        i.putExtra("type",ty);
       // startActivity(i);
        Toast.makeText(this,"Complaint Received",Toast.LENGTH_SHORT).show();

//        Intent launchIntent = new Intent(Intent.ACTION_MAIN);
//        launchIntent.addCategory(Intent.CATEGORY_LAUNCHER);
//        ComponentName cp = new ComponentName("com.example.sakshigarg.authfirebase", "MainActivity");
//        launchIntent.setComponent(cp);
//
//        startActivity(launchIntent);
        String message = "Type: "+ty+"\nComplaint: " + comp;
        Intent LaunchIntent = getPackageManager().getLaunchIntentForPackage("com.example.sakshigarg.authfirebase");
        LaunchIntent.putExtra("NAME",UserInformation.getName());
        LaunchIntent.putExtra("EMAIL",UserInformation.getEmail());
        LaunchIntent.putExtra("PASSWORD",UserInformation.getPassword());
        LaunchIntent.putExtra("MESSAGE",message);
        startActivity(LaunchIntent);

        finish();
    }
}
