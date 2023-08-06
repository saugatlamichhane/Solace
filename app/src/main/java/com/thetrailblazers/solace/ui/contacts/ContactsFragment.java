package com.thetrailblazers.solace.ui.contacts;

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
import com.thetrailblazers.solace.MainActivity;
import com.thetrailblazers.solace.Therapist;
import com.thetrailblazers.solace.TherapistAdapter;
import com.thetrailblazers.solace.databinding.FragmentContactsBinding;

import java.util.ArrayList;

public class ContactsFragment extends Fragment {

    private FragmentContactsBinding binding;
    DatabaseReference databaseContacts;
    TherapistAdapter therapistAdapter;
    ArrayList<Therapist> listTherapist;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ContactsViewModel notificationsViewModel =
                new ViewModelProvider(this).get(ContactsViewModel.class);

        binding = FragmentContactsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final RecyclerView contactsRecyclerView = binding.recyclerContacts;
        databaseContacts = FirebaseDatabase.getInstance().getReference("contacts");
        contactsRecyclerView.setHasFixedSize(true);
        contactsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        listTherapist = new ArrayList<>();
        therapistAdapter = new TherapistAdapter(getContext(), listTherapist);
        contactsRecyclerView.setAdapter(therapistAdapter);
        databaseContacts.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot: snapshot.getChildren()) {
                    Therapist therapist = dataSnapshot.getValue(Therapist.class);
                    listTherapist.add(therapist);
                }
                therapistAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        //notificationsViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}