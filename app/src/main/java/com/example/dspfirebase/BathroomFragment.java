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

public class BathroomFragment extends Fragment {

    private BathroomViewModel bathroomViewModel;
    private String bath_temp;
    private String bath_hum;
    private String bath_co1;
    DatabaseReference dref;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        bathroomViewModel =
                new ViewModelProvider(this).get(BathroomViewModel.class);

        View root = inflater.inflate(R.layout.fragment_bathroom, container, false);


        final TextView textView5 = root.findViewById(R.id.bath_temp_result);
        final TextView textView6 = root.findViewById(R.id.bath_hum_result2);
        final TextView textView_bath_co1 = root.findViewById(R.id.bath_co1_result2);

        dref = FirebaseDatabase.getInstance().getReference();
        dref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                bath_temp=snapshot.child("Bathroom_Temperature").getValue().toString();
                bath_hum=snapshot.child("Bathroom_Humidity").getValue().toString();
                bath_co1=snapshot.child("Bathroom_MQ7").getValue().toString();
                textView5.setText(bath_temp);
                textView6.setText(bath_hum);
                textView_bath_co1.setText(bath_co1);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        return root;
        //return inflater.inflate(R.layout.fragment_badroom,container,false);
    }
}
