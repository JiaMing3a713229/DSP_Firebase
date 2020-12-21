package com.example.dspfirebase;

import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class GardenViewModel extends ViewModel {


    //DatabaseReference dref;


    private MutableLiveData<String> mText;


    public GardenViewModel() {

        mText = new MutableLiveData<>();
        mText.setValue("23.4");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
