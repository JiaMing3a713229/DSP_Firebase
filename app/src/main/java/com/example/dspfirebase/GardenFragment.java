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

public class GardenFragment extends Fragment {

    private GardenViewModel gardenViewModel;
    private String Garden_temp;
    private String Garden_hum;
    DatabaseReference dref;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        gardenViewModel =
                new ViewModelProvider(this).get(GardenViewModel.class);

        View root = inflater.inflate(R.layout.fragment_garden, container, false);


        final TextView textView7 = root.findViewById(R.id.garden_temp_result);
        final TextView textView8 = root.findViewById(R.id.garden_hum_result2);

        dref = FirebaseDatabase.getInstance().getReference();
        dref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Garden_temp=snapshot.child("Garden_temperature").getValue().toString();
                Garden_hum=snapshot.child("Garden_humidity").getValue().toString();
                textView7.setText(Garden_temp);
                textView8.setText(Garden_hum);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        return root;
        //return inflater.inflate(R.layout.fragment_badroom,container,false);
    }
}
