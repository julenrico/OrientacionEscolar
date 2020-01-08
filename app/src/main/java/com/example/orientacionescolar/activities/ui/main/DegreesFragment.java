package com.example.orientacionescolar.activities.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.orientacionescolar.DatabaseHelper;
import com.example.orientacionescolar.R;
import com.example.orientacionescolar.RecyclerAdapter;

/**
 * A placeholder fragment containing a simple view.
 */
public class DegreesFragment extends Fragment implements RecyclerAdapter.listItemClick{

    private DatabaseHelper databaseHelper;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        databaseHelper = new DatabaseHelper(getContext(),"dataBase",null,1);
        View root = inflater.inflate(R.layout.fragment_degrees, container, false);

        RecyclerView recyclerView = root.findViewById(R.id.recyclerView);
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        RecyclerAdapter adapter = new RecyclerAdapter(databaseHelper.getCountDegrees(),this);

        recyclerView.setAdapter(adapter);

        return root;
    }

    @Override
    public void onListItemClick(int clickedItem) {


    }
}