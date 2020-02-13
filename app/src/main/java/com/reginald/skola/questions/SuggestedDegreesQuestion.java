package com.reginald.skola.questions;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.reginald.skola.R;
import com.reginald.skola.main.DatabaseHelper;
import com.reginald.skola.main.RecyclerAdapter;

import static com.reginald.skola.questions.BranchQuestion.branchId;
import static com.reginald.skola.questions.ProvinciaQuestion.provincia;

/*Fragment que muestra los grados sugeridos/recomendados*/

public class SuggestedDegreesQuestion extends Fragment implements RecyclerAdapter.listItemClick {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        DatabaseHelper databaseHelper = new DatabaseHelper(getContext());

        View root = inflater.inflate(R.layout.questions_suggested_degree, container, false);

        Button buttonContinuar = root.findViewById(R.id.buttonContinuar);
        buttonContinuar.setOnClickListener(v -> getActivity().finish());

        RecyclerView recyclerView2 = root.findViewById(R.id.recyclerView2);

        /*Cargar la recycler con los grados devueltos por la consulta*/
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView2.setLayoutManager(linearLayoutManager);
        recyclerView2.setAdapter(new RecyclerAdapter(databaseHelper.getSuggestedUniversityDegrees(branchId, provincia), this, getContext()));

        return root;
    }

    @Override
    public void onListItemClick(int clickedItem) {

    }
}
