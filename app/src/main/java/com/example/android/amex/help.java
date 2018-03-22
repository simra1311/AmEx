package com.example.android.amex;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class help extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
    }

    public void help(View view){

       // Intent i=new Intent(this,chat.class);
        EditText type3  =(EditText)findViewById(R.id.type3);
        String type=type3.getText().toString();
        EditText help =(EditText)findViewById(R.id.help);
        String hel=help.getText().toString();
//        Toast.makeText(help.this,hel,Toast.LENGTH_SHORT).show();
//        i.putExtra("type",type);
//        i.putExtra("help",hel);
//        startActivity(i);
        Toast.makeText(this,"Complaint Received",Toast.LENGTH_SHORT).show();

        String message = "Type: "+type+"\nQuery: " + hel;
        Intent LaunchIntent = getPackageManager().getLaunchIntentForPackage("com.example.sakshigarg.authfirebase");
        LaunchIntent.putExtra("NAME",UserInformation.getName());
        LaunchIntent.putExtra("EMAIL",UserInformation.getEmail());
        LaunchIntent.putExtra("PASSWORD",UserInformation.getPassword());
        LaunchIntent.putExtra("MESSAGE",message);
        startActivity(LaunchIntent);

        finish();
    }
}
