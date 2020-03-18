package com.example.xoaphm.smartdevicecontrol.block;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.xoaphm.smartdevicecontrol.R;
import com.example.xoaphm.smartdevicecontrol.room.RoomActivity;

public class BlockActivity extends AppCompatActivity {
    LinearLayout  a, b, c;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_block);
        a = (LinearLayout) findViewById(R.id.a);
        b = (LinearLayout) findViewById(R.id.b);
        c = (LinearLayout) findViewById(R.id.c);

        a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), RoomActivity.class);
                startActivity(intent);
            }
        });

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), RoomActivity.class);
                startActivity(intent);
            }
        });

        c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), RoomActivity.class);
                startActivity(intent);
            }
        });
    }
}
