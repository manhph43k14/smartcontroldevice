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


public class RoomActivity extends AppCompatActivity implements View.OnClickListener {
    private Firebase myFirebase;
    private ImageView button4, button5, button6, button7, button8, button9;
    private Boolean stt4 = false, stt5 = false, stt6 = false, stt7 = false, stt8 = false, stt9 = false;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room);
        Firebase.setAndroidContext(this);
        init();

        DatabaseReference database = FirebaseDatabase.getInstance().getReference("light");
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int value = dataSnapshot.getValue(Integer.class);
                if (value == 1) {
                    button4.setImageResource(R.drawable.ic_btn_on);
//                    button4.setBackgroundColor(getResources().getColor(R.color.colorButtonOn));
                } else {
                    button4.setImageResource(R.drawable.ic_btn_off);
//                    button4.setBackgroundColor(getResources().getColor(R.color.colorButtonOff));
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
            case R.id.button4:
                if (stt4 == true) {
                    stt4 = false;
                    button4.setImageResource(R.drawable.ic_btn_off);
//                    button4.setBackgroundColor(getResources().getColor(R.color.colorButtonOff));
                    myFirebase.child("light").setValue(0);
                } else {
                    stt4 = true;
                    button4.setImageResource(R.drawable.ic_btn_on);
//                    button4.setBackgroundColor(getResources().getColor(R.color.colorButtonOn));
                    myFirebase.child("light").setValue(1);
                }
                break;
//            case R.id.button5:
//                if (stt5 == true) {
//                    stt5 = false;
//                    button5.setImageResource(R.drawable.ic_btn_off);
//                } else {
//                    stt5 = true;
//                    button5.setImageResource(R.drawable.ic_btn_on);
//                }
//                break;
//            case R.id.button6:
//                if (stt6 == true) {
//                    stt6 = false;
//                    button6.setImageResource(R.drawable.ic_btn_off);
//                } else {
//                    stt6 = true;
//                    button6.setImageResource(R.drawable.ic_btn_on);
//                }
//                break;
//            case R.id.button7:
//                if (stt7 == true) {
//                    stt7 = false;
//                    button7.setImageResource(R.drawable.ic_btn_off);
//                } else {
//                    stt7 = true;
//                    button7.setImageResource(R.drawable.ic_btn_on);
//                }
//                break;
//            case R.id.button8:
//                button5.setImageResource(R.drawable.ic_btn_off);
//                if (stt8 == true) {
//                    stt8 = false;
//                    button8.setImageResource(R.drawable.ic_btn_off);
//                } else {
//                    stt8 = true;
//                    button8.setImageResource(R.drawable.ic_btn_on);
//                }
//                break;
//            case R.id.button9:
//                if (stt9 == true) {
//                    stt9 = false;
//                    button9.setImageResource(R.drawable.ic_btn_off);
//                } else {
//                    stt9 = true;
//                    button9.setImageResource(R.drawable.ic_btn_on);
//                }
//                break;
        }
    }

    private void init() {
        button4 = (ImageView) findViewById(R.id.button4);
        button5 = (ImageView) findViewById(R.id.button5);
        button6 = (ImageView) findViewById(R.id.button6);
        button7 = (ImageView) findViewById(R.id.button7);
        button8 = (ImageView) findViewById(R.id.button8);
        button9 = (ImageView) findViewById(R.id.button9);

        button4.setOnClickListener(this);
        button5.setOnClickListener(this);
        button6.setOnClickListener(this);
        button7.setOnClickListener(this);
        button8.setOnClickListener(this);
        button9.setOnClickListener(this);
    }


}
