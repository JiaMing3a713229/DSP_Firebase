package com.example.dspfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;

import android.app.NotificationChannel;
import android.app.NotificationChannelGroup;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class
MainActivity extends AppCompatActivity {


    public String uID;
    private String Crutch_Damage;
    DatabaseReference dref;

    //通知群組
    // 群組(ID必須唯一)
    public static final String GROUP_ID_STORE = "100";
    public static final String GROUP_NAME_STORE = "警示通知";
    // 頻道(ID必須唯一)
    public static final String CHANNEL_ID_FOOD = "101";
    public static final String CHANNEL_NAME_FOOD = "維修通知";
    public static final String CHANNEL_DESCRIPTION_FOOD = "附近店家的新品、優惠";

    private NotificationManager manager = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        getSupportActionBar().hide(); //隱藏標題
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN); //隱藏狀態

        BottomNavigationView bottomNavigationView=findViewById(R.id.bottom_nav);
        bottomNavigationView.setOnNavigationItemSelectedListener(navigationItemSelectedListener);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new com.example.dspfirebase.HomeFragment()).commit();





        dref = FirebaseDatabase.getInstance().getReference();


        dref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                Crutch_Damage=snapshot.child("Crutch_Danger").getValue().toString();

                switch (Crutch_Damage){

                    case "1":
                        //啟動服務
                        manager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);

                        //建立群組
                        NotificationChannelGroup group = new NotificationChannelGroup(GROUP_ID_STORE,GROUP_NAME_STORE);
                        manager.createNotificationChannelGroup(group);
                        //建立頻道
                        NotificationChannel channel = new NotificationChannel(CHANNEL_ID_FOOD,CHANNEL_NAME_FOOD,NotificationManager.IMPORTANCE_HIGH);
                        manager.createNotificationChannel(channel);

                        //發出通知

                        NotificationCompat.Builder builder = new NotificationCompat.Builder(MainActivity.this,CHANNEL_ID_FOOD);
                        builder.setContentTitle("發送預警");
                        builder.setContentText("離前方障礙物過近請離開");
                        builder.setSmallIcon(R.drawable.ic_message);
                        manager.notify(1,builder.build());

                }





            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });




    }

    private BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment = null;

            switch (item.getItemId()){
                case R.id .nav_home:
                    selectedFragment = new com.example.dspfirebase.HomeFragment();
                    break;
                case R.id.nav_badroom:
                    selectedFragment = new com.example.dspfirebase.BadroomFragment();
                    break;
                case R.id.nav_bath:
                    selectedFragment = new com.example.dspfirebase.BathroomFragment();
                    break;
                case R.id.nav_kitchen:
                    selectedFragment = new com.example.dspfirebase.KitchenFragment();
                    break;
                case R.id.nav_garden:
                    selectedFragment = new com.example.dspfirebase.GardenFragment();
                    break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,selectedFragment).commit();
            return true;
        }
    };

}