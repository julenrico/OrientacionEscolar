package com.example.orientacionescolar.questions;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.orientacionescolar.R;
import com.example.orientacionescolar.activities.InfoConsultingActivityVd;
import com.example.orientacionescolar.main.DatabaseHelper;
import com.example.orientacionescolar.main.RecyclerAdapter;

import static com.example.orientacionescolar.questions.BranchQuestion.branchId;
import static com.example.orientacionescolar.questions.ProvinciaQuestion.provincia;

public class SuggestedDegreesQuestion extends Fragment implements RecyclerAdapter.listItemClick {

    private DatabaseHelper databaseHelper;

    private RecyclerView recyclerView2;

    private Button buttonContinuar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        databaseHelper = new DatabaseHelper(getContext());

        View root = inflater.inflate(R.layout.questions_suggested_degree, container, false);

        buttonContinuar = root.findViewById(R.id.buttonContinuar);
        buttonContinuar.setOnClickListener(v -> {
            startActivity(new Intent(getActivity(), InfoConsultingActivityVd.class));
            getActivity().finish();
        });

        recyclerView2 = root.findViewById(R.id.recyclerView2);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView2.setLayoutManager(linearLayoutManager);
        recyclerView2.setAdapter(new RecyclerAdapter(databaseHelper.getSuggestedUniversityDegrees(branchId, provincia), this, getContext()));

        return root;
    }

    @Override
    public void onListItemClick(int clickedItem) {

    }
}
