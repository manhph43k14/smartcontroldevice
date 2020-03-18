package com.example.xoaphm.smartdevicecontrol.room;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.example.xoaphm.smartdevicecontrol.R;
import com.firebase.client.Firebase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DoorRoomActivity extends AppCompatActivity implements View.OnClickListener {
    private Firebase myFirebase;
    private ImageView door1, door2, door3, door4, door5, door6;
    boolean linear1 = false, linear2 = false, linear3 = false, linear4 = false, linear5 = false, linear6 = false;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_door);
        Firebase.setAndroidContext(this);
        init();


        DatabaseReference database = FirebaseDatabase.getInstance().getReference("door");
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int value = dataSnapshot.getValue(Integer.class);
                if (value == 1) {
                    door1.setImageResource(R.drawable.ic_door_unlock);
//                    button4.setBackgroundColor(getResources().getColor(R.color.colorButtonOn));
                } else {
                    door1.setImageResource(R.drawable.ic_door_lock);
//                    button4.setBackgroundColor(getResources().getColor(R.color.colorButtonOff));
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        myFirebase = new Firebase("https://iot-001-84230.firebaseio.com/");
    }

    private void init() {
        door1 = (ImageView) findViewById(R.id.door1);
        door2 = (ImageView) findViewById(R.id.door2);
        door3 = (ImageView) findViewById(R.id.door3);
        door4 = (ImageView) findViewById(R.id.door4);
        door5 = (ImageView) findViewById(R.id.door5);
        door6 = (ImageView) findViewById(R.id.door6);


        door1.setOnClickListener(this);
        door2.setOnClickListener(this);
        door3.setOnClickListener(this);
        door4.setOnClickListener(this);
        door5.setOnClickListener(this);
        door6.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.door1:
                if (linear1 == true) {
                    linear1 = false;
                    door1.setImageResource(R.drawable.ic_door_lock);
                    myFirebase.child("door").setValue(0);
                } else {
                    linear1 = true;
                    door1.setImageResource(R.drawable.ic_door_unlock);
                    myFirebase.child("door").setValue(1);
                }
                break;
//            case R.id.door2:
//
//                break;
        }
    }
}
