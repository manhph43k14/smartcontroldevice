package com.example.xoaphm.smartdevicecontrol.control;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.xoaphm.smartdevicecontrol.R;
import com.example.xoaphm.smartdevicecontrol.block.BlockActivity;
import com.example.xoaphm.smartdevicecontrol.block.DoorBlockActivity;
import com.example.xoaphm.smartdevicecontrol.models.Header;
import com.example.xoaphm.smartdevicecontrol.models.Option;
import com.example.xoaphm.smartdevicecontrol.models.Title;
import com.example.xoaphm.smartdevicecontrol.room.DoorRoomActivity;

import java.util.ArrayList;
import java.util.List;


public class ControlActivity extends AppCompatActivity implements MenuAdapter.OnItemClickListener {

    ImageView imgElectronic, imgRing, imgDoor;
    Toolbar toolbarControl;
    //recyclerView
    private RecyclerView mRecycleViewOption;
    private List<Object> mObjects;
    //sliding
    private DrawerLayout mDrawerLayout;
    private LinearLayout mLlMain;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_control);
        initViews();
        initData();
        initAdapter();
        initDrawerLayout();
        handleOnclick();
    }

    private void initViews() {
        toolbarControl = findViewById(R.id.toolbarControl);
        if (getSupportActionBar() != null) {
            setSupportActionBar(toolbarControl);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu_white_48dp);
        }
        imgElectronic = findViewById(R.id.imgElectronic);
        imgRing = findViewById(R.id.imgRing);
        imgDoor = findViewById(R.id.imgDoor);
        mDrawerLayout = findViewById(R.id.drawerLayout);
        mLlMain = findViewById(R.id.llMain);
        mRecycleViewOption = findViewById(R.id.recyclerViewOption);
    }

    private void initData() {
        mObjects = new ArrayList<>();
        // Thêm phần header
        mObjects.add(new Header());
        //Thêm title
        mObjects.add(new Title("Thiết lập"));
        //Thêm option (phần name dùng để hiển thị)
        mObjects.add(new Option("Cài đặt thiết bị điện", R.drawable.ic_btn_electronic));

        mObjects.add(new Option("Cài đặt chuông", R.drawable.ic_btn_ring));

        mObjects.add(new Option("Cài đặt cửa", R.drawable.ic_btn_door));

        mObjects.add(new Option("Đăng xuất", R.drawable.logo));




    }

    private void initAdapter() {
        //option
        MenuAdapter mMenuAdapter = new MenuAdapter(mObjects, this);
        mRecycleViewOption.setLayoutManager(new LinearLayoutManager(this,1,false));
        mRecycleViewOption.setAdapter(mMenuAdapter);
    }

    private void initDrawerLayout() {
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbarControl, R.string.numberone, R.string.numberTwo) {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
                mLlMain.setTranslationX(mRecycleViewOption.getWidth() * slideOffset);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };
        mDrawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
    }

    private void handleOnclick() {
        imgElectronic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), BlockActivity.class);
                startActivity(intent);
            }
        });

        imgRing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), BlockActivity.class);
                startActivity(intent);
            }
        });

        imgDoor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), DoorBlockActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onClickItem(int position) {
        if (mObjects.get(position) instanceof Option) {
            Option option = (Option) mObjects.get(position);
            switch (option.getName()) {
                case "Cài đặt thiết bị điện":
                    //Bấm vô chữ cdtbd thì xử lí ở đây
                    break;
                case "Cài đặt chuông":
                    //Bấm vô chữ cdc thì xử lí ở đây
                    break;
                case "Cài đặt cửa":
                    //Bấm vô chữ cdc thì xử lí ở đây
                    break;
                case "Đăng xuất":
                    //Bấm vô chữ exit thì xử lí ở đây
                    break;
            }
        }
    }
}