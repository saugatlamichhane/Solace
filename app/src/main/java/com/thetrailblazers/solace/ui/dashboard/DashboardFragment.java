package com.thetrailblazers.solace.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.thetrailblazers.solace.Therapist;
import com.thetrailblazers.solace.TherapistAdapter;
import com.thetrailblazers.solace.Yoga;
import com.thetrailblazers.solace.YogaAdapter;
import com.thetrailblazers.solace.databinding.FragmentDashboardBinding;

import java.util.ArrayList;

public class DashboardFragment extends Fragment {

    private FragmentDashboardBinding binding;
    DatabaseReference databaseYoga;
    YogaAdapter yogaAdapter;
    ArrayList<Yoga> listYoga;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        DashboardViewModel dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final RecyclerView yogaRecyclerView = binding.recyclerDashboard;
        databaseYoga = FirebaseDatabase.getInstance().getReference("yoga");
        yogaRecyclerView.setHasFixedSize(true);
        yogaRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        listYoga = new ArrayList<>();
        yogaAdapter = new YogaAdapter(getContext(), listYoga);
        yogaRecyclerView.setAdapter(yogaAdapter);
        databaseYoga.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot: snapshot.getChildren()) {
                    Yoga yoga = dataSnapshot.getValue(Yoga.class);
                    listYoga.add(yoga);
                }
                yogaAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}