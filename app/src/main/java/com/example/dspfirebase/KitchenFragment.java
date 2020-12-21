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

public class KitchenFragment extends Fragment {

    private KitchenViewModel kitchenViewModel;
    private String kitchen_temp;
    private String kitchen_hum;
    DatabaseReference dref;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        kitchenViewModel =
                new ViewModelProvider(this).get(KitchenViewModel.class);

        View root = inflater.inflate(R.layout.fragment_kitchen, container, false);


        final TextView textView9= root.findViewById(R.id.kitchen_temp_result);
        final TextView textView10 = root.findViewById(R.id.kitchen_hum_result2);

        dref = FirebaseDatabase.getInstance().getReference();
        dref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //home_temp=snapshot.child("home_temp").getValue().toString();
                //home_hum=snapshot.child("home_hum").getValue().toString();
                kitchen_temp=snapshot.child("Kitchen_temperature").getValue().toString();
                kitchen_hum=snapshot.child("Kitchen_humidity").getValue().toString();
                textView9.setText(kitchen_temp);
                textView10.setText(kitchen_hum);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        return root;
        //return inflater.inflate(R.layout.fragment_badroom,container,false);
    }
}
