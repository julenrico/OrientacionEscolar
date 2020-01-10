package com.example.orientacionescolar.activities.ui.main;

import android.os.Bundle;
import android.util.Log;
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
import com.example.orientacionescolar.UniversityDegree;
import com.example.orientacionescolar.activities.InfoConsultingActivityVd;
import com.like.LikeButton;
import com.like.OnLikeListener;

import java.util.ArrayList;
import java.util.Objects;

/**
 * A placeholder fragment containing a simple view.
 */
public class DegreesFragment extends Fragment implements RecyclerAdapter.listItemClick{

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        DatabaseHelper databaseHelper = new DatabaseHelper(getContext());
        View root = inflater.inflate(R.layout.fragment_degrees, container, false);
        RecyclerView recyclerView = root.findViewById(R.id.recyclerView);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        ArrayList<UniversityDegree> universityDegrees = databaseHelper.getUniversityDegrees();

        universityDegrees.forEach(d-> Log.d("DEGREE",d.getCampus().getCampusName()));

        RecyclerAdapter adapter = new RecyclerAdapter(universityDegrees,this);

        recyclerView.setAdapter(adapter);

        return root;
    }

    @Override
    public void onListItemClick(int clickedItem) {


    }
}