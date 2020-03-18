package com.example.xoaphm.smartdevicecontrol.block;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Switch;

import com.example.xoaphm.smartdevicecontrol.R;
import com.example.xoaphm.smartdevicecontrol.room.DoorRoomActivity;
import com.firebase.client.Firebase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DoorBlockActivity extends AppCompatActivity implements View.OnClickListener {
    private Firebase myFirebase;
    private Switch sw1;
    private LinearLayout door_a, door_b;
    boolean linear1 = false,linear2 = false,linear3 = false, switch1 = false;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_block_door);
        Firebase.setAndroidContext(this);
        init();


        DatabaseReference database = FirebaseDatabase.getInstance().getReference("door");
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int value = dataSnapshot.getValue(Integer.class);
                if (value == 1) {
//                    button4.setImageResource(R.drawable.ic_btn_on);
//                    door_a.setBackgroundColor(getResources().getColor(R.color.colorButtonOn));
                    sw1.setChecked(true);
                } else {
//                    button4.setImageResource(R.drawable.ic_btn_off);
//                    door_a.setBackgroundColor(getResources().getColor(R.color.colorButtonOff));
                    sw1.setChecked(false);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        myFirebase = new Firebase("https://iot-001-84230.firebaseio.com/");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.switch1:
                if(switch1 == true){
                    switch1 = false;
                    sw1.setChecked(false);
                    myFirebase.child("door").setValue(0);
                } else {
                    switch1 = true;
                    sw1.setChecked(true);
                    myFirebase.child("door").setValue(1);
                }
                break;
            case R.id.door_a:
                Intent intent = new Intent(v.getContext(), DoorRoomActivity.class);
                startActivity(intent);
                break;
            case R.id.door_b:
                intent = new Intent(v.getContext(), DoorRoomActivity.class);
                startActivity(intent);
                break;
        }
    }

    private void init() {
        sw1 = (Switch) findViewById(R.id.switch1);
        door_a = (LinearLayout) findViewById(R.id.door_a);
        door_b = (LinearLayout) findViewById(R.id.door_b);

        sw1.setOnClickListener(this);
        door_a.setOnClickListener(this);
        door_b.setOnClickListener(this);
    }
}

