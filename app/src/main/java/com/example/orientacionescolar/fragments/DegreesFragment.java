package com.example.orientacionescolar.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.orientacionescolar.main.DatabaseHelper;
import com.example.orientacionescolar.R;
import com.example.orientacionescolar.main.RecyclerAdapter;


/**
 * A placeholder fragment containing a simple view.
 */
public class DegreesFragment extends Fragment implements RecyclerAdapter.listItemClick {

    private DatabaseHelper databaseHelper;

    private RecyclerView recyclerView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        databaseHelper = new DatabaseHelper(getContext());

        View root = inflater.inflate(R.layout.fragment_degrees, container, false);

        recyclerView = root.findViewById(R.id.recyclerView);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        recyclerView.setAdapter(new RecyclerAdapter(databaseHelper.getUniversityDegrees(), this, getContext()));

        return root;
    }

    @Override
    public void onListItemClick(int clickedItem) {


    }

    void setFav(boolean fav) {
        RecyclerAdapter adapter;
        if (fav) {
            adapter = new RecyclerAdapter(databaseHelper.getFavUniversityDegrees(), this, getContext());
        } else {
            adapter = new RecyclerAdapter(databaseHelper.getUniversityDegrees(), this, getContext());
        }
        recyclerView.setAdapter(adapter);
    }
}