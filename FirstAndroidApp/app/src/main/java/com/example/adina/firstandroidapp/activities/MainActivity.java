package com.example.adina.firstandroidapp.activities;

import android.app.Activity;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import com.example.adina.firstandroidapp.Config;
import com.example.adina.firstandroidapp.R;
import com.example.adina.firstandroidapp.UI.MyAdapter;
import com.example.adina.firstandroidapp.activities.authentication.LogInActivity;
import com.example.adina.firstandroidapp.helper.RealmHelper;
import com.example.adina.firstandroidapp.model.Movie;

import com.example.adina.firstandroidapp.util.NotificationUtils;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class MainActivity extends Activity {

    public static RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private ArrayList<Movie> movieList = new ArrayList<>();
    private MyAdapter adapter;
    public static Realm realm;
    private EditText movieTitleTxt, movieYearTxt, movieDirectorTxt, movieRatingTxt;
    private Button signOut;
    private FirebaseAuth auth;
    private FirebaseAuth.AuthStateListener authListener;
    private DatabaseReference mDatabaseRef;
    private String mUserId;
    private static final String TAG = MainActivity.class.getSimpleName();
    private BroadcastReceiver mRegistrationBroadcastReceiver;
    private TextView txtRegId, txtMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.v("MainActivity", "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        //RECYCLER VIEW SETUP
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        //BIND
        adapter = new MyAdapter(this, movieList);
        recyclerView.setAdapter(adapter);

        //REALM SETUP
//        realm.init(this);
//        RealmConfiguration config = new RealmConfiguration.Builder()
//                .deleteRealmIfMigrationNeeded()
//                .build();
//        //RealmConfiguration config = new RealmConfiguration.Builder().build();
//        realm = Realm.getInstance(config);
//
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(MainActivity.this, EmailActivity.class));
//            }
//        });
//        //RETRIEVE
//
//        //Refresh
//        RealmHelper helper = new RealmHelper(realm);
//        movieList = helper.rfetrieve();
//        adapter = new MyAdapter(MainActivity.this, movieList);
//        recyclerView.setAdapter(adapter);

        signOut = (Button) findViewById(R.id.sign_out);

        //get firebase auth instance
        auth = FirebaseAuth.getInstance();

        //get current user
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        //reference to the root node of the database
        mDatabaseRef = FirebaseDatabase.getInstance().getReference().child("movies");
        mUserId = user.getUid();

        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signOut();
            }
        });
        /*Notifications*/
        txtRegId = (TextView) findViewById(R.id.txt_reg_id);
        txtMessage = (TextView) findViewById(R.id.txt_push_message);

        mRegistrationBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

                // checking for type intent filter
                if (intent.getAction().equals(Config.REGISTRATION_COMPLETE)) {
                    // gcm successfully registered
                    // now subscribe to `global` topic to receive app wide notifications
                    FirebaseMessaging.getInstance().subscribeToTopic(Config.TOPIC_GLOBAL);

                    displayFirebaseRegId();

                } else if (intent.getAction().equals(Config.PUSH_NOTIFICATION)) {
                    // new push notification is received

                    String message = intent.getStringExtra("message");

                    Toast.makeText(getApplicationContext(), "Push notification: " + message, Toast.LENGTH_LONG).show();

                    txtMessage.setText(message);
                }
            }
        };

        displayFirebaseRegId();
    }

    //sign out method
    public void signOut() {
        auth.signOut();
        startActivity(new Intent(MainActivity.this, LogInActivity.class));
    }

    private void displayEditDialog(){
        final Dialog dialog = new Dialog(this);
        dialog.setTitle("Edit movie");
        dialog.setContentView(R.layout.input_dialog);

    }
    private void displayInputDialog(){
        final Dialog dialog = new Dialog(this);
        dialog.setTitle("Add movie");
        dialog.setContentView(R.layout.input_dialog);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);

        movieTitleTxt = (EditText) dialog.findViewById(R.id.movieTitle);
        movieYearTxt = (EditText) dialog.findViewById(R.id.movieYear);
        movieDirectorTxt = (EditText) dialog.findViewById(R.id.movieDirector);
        final NumberPicker nrPicker = (NumberPicker) dialog.findViewById(R.id.movieRating);
        nrPicker.setMaxValue(10);
        nrPicker.setMinValue(0);
        //nrPicker.setWrapSelectorWheel(false);

        Button saveButton = (Button) dialog.findViewById(R.id.saveButton);

        saveButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //Get DATA
                String title = movieTitleTxt.getText().toString();
                String director = movieDirectorTxt.getText().toString();
                String year = movieYearTxt.getText().toString();
                String rating =  "" + nrPicker.getValue();

                Movie movie = new Movie(title, year, director, rating);

                //Save data to firebase
                mDatabaseRef.push().setValue(movie);

                //---------------Save data to realm-----------
//                RealmHelper helper = new RealmHelper(realm);
//                helper.save(movie);
//                movieTitleTxt.setText("");
//
//                //Refresh
//                movieList = helper.retrieve();
//                adapter = new MyAdapter(MainActivity.this, movieList);
//                recyclerView.setAdapter(adapter);

                dialog.dismiss();
            }
        });
        dialog.show();
    }
    public void AddMovie(View view){
        Log.v("MainActivity", "Add Movie Button Pressed");
        displayInputDialog();
    }

    public void EditMovie(View view){
        Log.v("MainActivity", "Edit Movie Button Pressed");
        displayEditDialog();
    }

    // Fetches reg id from shared preferences
    // and displays on the screen
    private void displayFirebaseRegId() {
        SharedPreferences pref = getApplicationContext().getSharedPreferences(Config.SHARED_PREF, 0);
        String regId = pref.getString("regId", null);

        Log.e(TAG, "Firebase reg id: " + regId);

        if (!TextUtils.isEmpty(regId))
            txtRegId.setText("Firebase Reg Id: " + regId);
        else
            txtRegId.setText("Firebase Reg Id is not received yet!");
    }

    @Override
    protected void onResume() {
        super.onResume();

        // register GCM registration complete receiver
        LocalBroadcastManager.getInstance(this).registerReceiver(mRegistrationBroadcastReceiver,
                new IntentFilter(Config.REGISTRATION_COMPLETE));

        // register new push message receiver
        // by doing this, the activity will be notified each time a new message arrives
        LocalBroadcastManager.getInstance(this).registerReceiver(mRegistrationBroadcastReceiver,
                new IntentFilter(Config.PUSH_NOTIFICATION));

        // clear the notification area when the app is opened
        NotificationUtils.clearNotifications(getApplicationContext());
    }

    @Override
    protected void onPause() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mRegistrationBroadcastReceiver);
        super.onPause();
    }

}

