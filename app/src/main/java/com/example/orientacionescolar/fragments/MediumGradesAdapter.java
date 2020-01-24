package com.example.orientacionescolar.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.orientacionescolar.R;

public class MediumGradesAdapter extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_medium_grades, container, false);

        String[] exampleGrades = {"Medium Grade 1",
                "Medium Grade 2",
                "Medium Grade 3"};

        ListView listView = root.findViewById(R.id.mediumGradesList);

        ArrayAdapter<String> listViewAdapter = new ArrayAdapter<>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                exampleGrades
        );

        listView.setAdapter(listViewAdapter);
        return root;
    }
}
