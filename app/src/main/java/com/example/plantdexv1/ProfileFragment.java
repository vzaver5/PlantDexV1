package com.example.plantdexv1;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ProfileFragment extends Fragment {
    String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
    DatabaseReference userDb = FirebaseDatabase.getInstance().getReference().child("user:" +userId);
    TextView nameTextView;
    ListView listView;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        //this to set delegate/listener back to this class
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    public void onViewCreated(@NonNull final View view, final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //Get the information from the database and fill in the profile page
        //User's name
        String name = FirebaseAuth.getInstance().getCurrentUser().getDisplayName();
        nameTextView = (TextView) getView().findViewById(R.id.nameProfileFragment);
        nameTextView.setText(name);
        listView = (ListView) getView().findViewById(R.id.virtualGardenListView);


        //Virtual Garden
        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<String> virtualGarden = new ArrayList<>();
                for(DataSnapshot ds : dataSnapshot.getChildren()) {
                    String plant = ds.getKey();
                    System.out.println("Adding: " + plant);
                    virtualGarden.add(plant);
                }
                Log.d("TAG", String.valueOf(virtualGarden));
                //Load virtual garden in the profile
                ArrayAdapter<String> adapter  = new ArrayAdapter<String>(getContext(),
                        android.R.layout.simple_list_item_1, virtualGarden);
                listView.setAdapter(adapter);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {}
        };
        userDb.child("virtualGarden").addListenerForSingleValueEvent(eventListener);
    }
}
