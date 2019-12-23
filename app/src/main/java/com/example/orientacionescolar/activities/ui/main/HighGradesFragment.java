package com.example.orientacionescolar.activities.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.orientacionescolar.R;

public class HighGradesFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_high_grades, container, false);

        String[] exampleGrades = {"High Grade 1",
                                    "High Grade 2",
                                    "High Grade 3"};

        ListView listView = root.findViewById(R.id.highGradesList);

        ArrayAdapter<String> listViewAdapter = new ArrayAdapter<>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                exampleGrades
        );

        listView.setAdapter(listViewAdapter);
        return root;
    }
}
