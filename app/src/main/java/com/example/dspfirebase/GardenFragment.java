package com.example.dspfirebase;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import android.webkit.WebView;
import android.webkit.WebSettings;
import android.webkit.WebViewClient;

import java.util.Random;

public class GardenFragment extends Fragment {

    private GardenViewModel gardenViewModel;
    private View view;
    //private String Garden_temp;
    //private String Garden_hum;
    private WebView webView;
    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;
    private Button button5;
    private Button button6;
    //DatabaseReference dref;

    //拐杖
    private String crutch_distance;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        //選擇視窗




        gardenViewModel =
                new ViewModelProvider(this).get(GardenViewModel.class);

        View root = inflater.inflate(R.layout.fragment_garden, container, false);

        WebView webview = root.findViewById(R.id.garden_webview);

        webview.setWebViewClient(new WebViewClient());
        WebSettings webSettings = webview.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setUseWideViewPort(true);
        webview.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setSupportZoom(true);
        webSettings.setBuiltInZoomControls(true); //設定內建的縮放控制元件。若為false，則該WebView不可縮放
        webSettings.setDisplayZoomControls(false); //隱藏原生的縮放控制元件
        webview.loadUrl("https://thingspeak.com/channels/1024822/charts/1?bgcolor=%23ffffff&color=%23d62020&dynamic=true&results=60&title=%E6%BA%AB%E5%BA%A6%E6%84%9F%E6%B8%AC&type=line");
        //final TextView textView7 = root.findViewById(R.id.garden_temp_result);
        //final TextView textView8 = root.findViewById(R.id.garden_hum_result2);

        //dref = FirebaseDatabase.getInstance().getReference();
        //dref.addValueEventListener(new ValueEventListener() {
            //@Override
            //public void onDataChange(@NonNull DataSnapshot snapshot) {
                //Garden_temp=snapshot.child("Garden_temperature").getValue().toString();
                //Garden_hum=snapshot.child("Garden_humidity").getValue().toString();
                //textView7.setText(Garden_temp);
                //textView8.setText(Garden_hum);
            //}

            //@Override
           // public void onCancelled(@NonNull DatabaseError error) {

            //}
        //});
        //按鈕事件
        button1=root.findViewById(R.id.button_temp);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                webview.loadUrl("https://thingspeak.com/channels/1024822/charts/1?bgcolor=%23ffffff&color=%23d62020&dynamic=true&results=60&title=%E6%BA%AB%E5%BA%A6%E6%84%9F%E6%B8%AC&type=line");
            }
        });
        button2=root.findViewById(R.id.button_humidity);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                webview.loadUrl("https://thingspeak.com/channels/1024822/charts/3?bgcolor=%23ffffff&color=%23d62020&dynamic=true&results=60&title=%E9%9B%A8%E6%B0%B4%E6%84%9F%E6%B8%AC&type=line");
            }
        });
        button3=root.findViewById(R.id.button_pm);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                webview.loadUrl("https://thingspeak.com/channels/1024822/charts/4?bgcolor=%23ffffff&color=%23d62020&dynamic=true&results=60&title=%E5%A4%AA%E9%99%BD%E7%B4%AB%E5%A4%96%E7%B7%9A%E6%84%9F%E6%B8%AC&type=line");
            }
        });
        button4=root.findViewById(R.id.button_purple);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                webview.loadUrl("https://thingspeak.com/channels/1024822/charts/4?bgcolor=%23ffffff&color=%23d62020&dynamic=true&results=60&title=%E5%A4%AA%E9%99%BD%E7%B4%AB%E5%A4%96%E7%B7%9A%E6%84%9F%E6%B8%AC&type=line");
            }
        });
        button5=root.findViewById(R.id.button_rain);
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                webview.loadUrl("https://thingspeak.com/channels/1024822/charts/2?bgcolor=%23ffffff&color=%23d62020&dynamic=true&results=60&title=%E6%BF%95%E5%BA%A6%E6%84%9F%E6%B8%AC&type=line");
            }
        });
        button6=root.findViewById(R.id.button_rain_level);
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                webview.loadUrl("https://thingspeak.com/channels/1024822/charts/3?bgcolor=%23ffffff&color=%23d62020&dynamic=true&results=60&title=%E9%9B%A8%E6%B0%B4%E6%84%9F%E6%B8%AC&type=line");
            }
        });



        return root;
        //return inflater.inflate(R.layout.fragment_badroom,container,false);

    }
    public void btn_temp(View view){

    }




}
