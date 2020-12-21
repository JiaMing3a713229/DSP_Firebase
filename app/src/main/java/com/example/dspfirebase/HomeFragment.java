package com.example.dspfirebase;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

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
    DatabaseReference dref;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        View root = inflater.inflate(R.layout.fragment_home, container, false);


        final TextView textView3= root.findViewById(R.id.home_temp_result);
        final TextView textView4 = root.findViewById(R.id.home_hum_result2);

        dref = FirebaseDatabase.getInstance().getReference();
        dref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //home_temp=snapshot.child("home_temp").getValue().toString();
                //home_hum=snapshot.child("home_hum").getValue().toString();
                home_temp=snapshot.child("Livingroom_Temperature").getValue().toString();
                home_hum=snapshot.child("Livingroom_Humidity").getValue().toString();
                textView3.setText(home_temp);
                textView4.setText(home_hum);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        return root;
        //return inflater.inflate(R.layout.fragment_badroom,container,false);
    }
}
