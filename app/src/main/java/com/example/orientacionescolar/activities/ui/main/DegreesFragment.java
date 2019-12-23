package com.example.orientacionescolar.activities.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.orientacionescolar.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class DegreesFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_degrees, container, false);

        String[] exampleGrades = {"University Degree 1",
                                    "University Degree 2",
                                    "University Degree 3"};

        ListView listView = root.findViewById(R.id.universityDegreesList);

        ArrayAdapter<String> listViewAdapter = new ArrayAdapter<>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                exampleGrades
        );

        listView.setAdapter(listViewAdapter);
        return root;
    }
    //TODO: LISTVIEWS AS GMAIL
}