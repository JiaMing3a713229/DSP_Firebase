package com.example.dspfirebase;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
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

public class BadroomFragment extends Fragment {

    private badroomViewModel badroomViewModel;
    private String bad_temp;
    private String bad_hum;
    private String bad_mq2;
    private String bad_earthquake;
    DatabaseReference dref;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        badroomViewModel =
                new ViewModelProvider(this).get(badroomViewModel.class);

        View root = inflater.inflate(R.layout.fragment_badroom, container, false);


        final TextView textView = root.findViewById(R.id.bad_temp_result);
        final TextView textView1 = root.findViewById(R.id.bad_hum_result2);
        final TextView textView_bad_mq2 = root.findViewById(R.id.bad_mq2_result);
        final TextView textView_bad_Ear = root.findViewById(R.id.bad_Earthquake_result);

        dref = FirebaseDatabase.getInstance().getReference();
        dref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                bad_temp=snapshot.child("Bedroom_Temperature").getValue().toString();
                bad_hum=snapshot.child("Bedroom_Humidity").getValue().toString();
                bad_mq2=snapshot.child("Bedroom_MQ2").getValue().toString();
                bad_earthquake=snapshot.child("Bedroom_Earthquake").getValue().toString();
                textView.setText(bad_temp);
                textView1.setText(bad_hum);
                textView_bad_mq2.setText(bad_mq2);
                textView_bad_Ear.setText(bad_earthquake);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        return root;
        //return inflater.inflate(R.layout.fragment_badroom,container,false);
    }
}
