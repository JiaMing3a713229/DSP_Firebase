package com.example.dspfirebase;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.media.audiofx.BassBoost;
import android.os.Bundle;
import android.os.Looper;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Random;

public class HomeFragment extends Fragment {


    private HomeViewModel homeViewModel;
    private String home_temp;
    private String home_hum;
    private String home_pm25;
    private String home_earthquake;
    DatabaseReference dref;

    //3/8
    private String home_Crutch_result;
    private String home_Watch_result;

    //GPS variable
    FusedLocationProviderClient fusedLocationProviderClient;
    TextView textView_living_GPS;
    TextView textView_living_longitude;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        View root = inflater.inflate(R.layout.fragment_home, container, false);


        final TextView textView3 = root.findViewById(R.id.home_temp_result);
        final TextView textView4 = root.findViewById(R.id.home_hum_result2);
        final TextView textView_living_pm25 = root.findViewById(R.id.home_pm25_result);
        final TextView textView_living_earthquake = root.findViewById(R.id.home_earthquake_result);
        final TextView textView_living_Crutch = root.findViewById(R.id.home_Crutch_result);//3/8
        final TextView textView_living_Watch = root.findViewById(R.id.home_watch_result);//3/8
        //gps variable
        textView_living_GPS = root.findViewById(R.id.home_GPS_result);
        textView_living_longitude = root.findViewById(R.id.home_longitude_result);
        //initial FusedLocationProviderClient
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(getActivity());

        //check permission
        if (ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(getActivity(),
                        Manifest.permission.ACCESS_COARSE_LOCATION)
                        == PackageManager.PERMISSION_GRANTED) {
            getCurrentLocation();
        }else {
            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION},100);
        }

        dref = FirebaseDatabase.getInstance().getReference();
        dref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //home_temp=snapshot.child("home_temp").getValue().toString();
                //home_hum=snapshot.child("home_hum").getValue().toString();
                home_temp = snapshot.child("Livingroom_Temperature").getValue().toString();
                home_hum = snapshot.child("Livingroom_Humidity").getValue().toString();
                home_pm25 = snapshot.child("Livingroom_PM25").getValue().toString();
                home_earthquake = snapshot.child("Livingroom_Earthquake").getValue().toString();
                home_Crutch_result = snapshot.child("Crutch_Ultrasound").getValue().toString();//3/8
                home_Watch_result = snapshot.child("Watch_Heartbeat").getValue().toString();
                textView3.setText(home_temp);
                textView4.setText(home_hum);
                textView_living_pm25.setText(home_pm25);
                textView_living_earthquake.setText(home_earthquake);
                textView_living_Crutch.setText(home_Crutch_result);
                textView_living_Watch.setText(home_Watch_result);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        return root;
        //return inflater.inflate(R.layout.fragment_badroom,container,false);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode ==100&& (grantResults.length>0)&&
                (grantResults[0]+grantResults[1]==PackageManager.PERMISSION_GRANTED)){
            getCurrentLocation();
        }else {
            Toast.makeText(getActivity(),"Perssion denid",Toast.LENGTH_SHORT).show();
        }
    }

    private void getCurrentLocation() {
        //initialize location manger
        LocationManager locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);

        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
                || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {

            if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            fusedLocationProviderClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
                @Override
                public void onComplete(@NonNull Task<Location> task) {
                    Location location = task.getResult();
                    if (location != null) {
                        textView_living_GPS.setText(String.valueOf(location.getLatitude()));
                        textView_living_longitude.setText(String.valueOf(location.getLongitude()));
                    } else {
                        LocationRequest locationRequest = new LocationRequest()
                                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                                .setInterval(10000)
                                .setFastestInterval(1000)
                                .setNumUpdates(1);
                        LocationCallback locationCallback = new LocationCallback() {
                            @Override
                            public void onLocationResult(@NonNull LocationResult locationResult) {

                                Location location1 = locationResult.getLastLocation();
                                textView_living_GPS.setText(String.valueOf(location.getLatitude()));
                                textView_living_longitude.setText(String.valueOf(location.getLongitude()));
                            }
                        };

                        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                            // TODO: Consider calling
                            //    ActivityCompat#requestPermissions
                            // here to request the missing permissions, and then overriding
                            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                            //                                          int[] grantResults)
                            // to handle the case where the user grants the permission. See the documentation
                            // for ActivityCompat#requestPermissions for more details.
                            return;
                        }
                        fusedLocationProviderClient.requestLocationUpdates(locationRequest
                                , locationCallback, Looper.myLooper());
                    }
                }
            });
        }else {

            startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));

        }
    }
}
