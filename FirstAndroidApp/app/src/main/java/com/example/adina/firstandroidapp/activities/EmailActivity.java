package com.example.adina.firstandroidapp.activities;

/**
 * Created by Adina on 11/5/2016.
 */
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.adina.firstandroidapp.R;

import io.realm.Realm;

public class EmailActivity extends Activity{
    EditText emailTo,subject,content;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button startBtn = (Button) findViewById(R.id.buttonSend);
        emailTo = (EditText) findViewById(R.id.editTextTo);
        subject = (EditText) findViewById(R.id.editTextSubject);
        content = (EditText) findViewById(R.id.editTextMessage);

        startBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                sendEmail();
            }
        });
    }
    protected void sendEmail() {
        String[] TO = {emailTo.getText().toString()};
        Log.v("Send email", "start");
        Log.v("Email to", emailTo.getText().toString());
        Intent emailIntent = new Intent(Intent.ACTION_SEND);

        emailIntent.setType("text/plain");
        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject.getText().toString());
        emailIntent.putExtra(Intent.EXTRA_TEXT, content.getText().toString());

        try {
            startActivity(Intent.createChooser(emailIntent, "Send mail..."));
            finish();
            Log.v("Finished.", "");
        }
        catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(EmailActivity.this, "There is no email client installed.", Toast.LENGTH_SHORT).show();
        }
    }
    /** Called when the user clicks the View List button */
    public void viewList(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}