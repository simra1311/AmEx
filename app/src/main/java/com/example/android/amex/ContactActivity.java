package com.example.android.amex;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class ContactActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        Button submit = (Button) findViewById(R.id.choice);

        submit.setOnClickListener(new View.OnClickListener() {
                                      @Override
                                      public void onClick(View v) {
                                          RadioGroup choice = (RadioGroup) findViewById(R.id.radiogroup);
                                          int selectedId = choice.getCheckedRadioButtonId();
                                          RadioButton option = (RadioButton) findViewById(selectedId);
                                          String form = option.getText().toString().trim();
                                          Toast.makeText(ContactActivity.this,form,Toast.LENGTH_SHORT).show();
                                          Intent i;
                                          if (form.equals("Complaint Form")) {
                                              i = new Intent(ContactActivity.this, complaint.class);
                                              startActivity(i);
                                          }
                                          else if (form.equals("Feedback Form")) {
                                              i = new Intent(ContactActivity.this, Feedback.class);
                                              startActivity(i);
                                          }
                                          else if (form.equals("Help Form")) {
                                              i = new Intent(ContactActivity.this, help.class);
                                              startActivity(i);
                                          }


                                      }
                                  }
        );
    }
}
