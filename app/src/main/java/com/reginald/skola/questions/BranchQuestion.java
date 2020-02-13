package com.reginald.skola.questions;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.reginald.skola.R;
import com.reginald.skola.activities.QuestionsActivity;

public class BranchQuestion extends Fragment implements View.OnClickListener {


    private ConstraintLayout artsLayout;
    private ConstraintLayout sciencesLayout;
    private ConstraintLayout healthLayout;
    private ConstraintLayout engineeringLayout;
    private ConstraintLayout lawsLayout;


    static int branchId;

    private EmptyQuestion emptyQuestion;
    private FragmentManager fragmentManager;

    private ConstraintLayout regiLayout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.questions_branch, container, false);

        artsLayout = root.findViewById(R.id.artsLayout);
        sciencesLayout = root.findViewById(R.id.sciencesLayout);
        healthLayout = root.findViewById(R.id.healthLayout);
        engineeringLayout = root.findViewById(R.id.engineeringLayout);
        lawsLayout = root.findViewById(R.id.lawsLayout);

        artsLayout.setOnClickListener(this);
        sciencesLayout.setOnClickListener(this);
        healthLayout.setOnClickListener(this);
        engineeringLayout.setOnClickListener(this);
        lawsLayout.setOnClickListener(this);

        regiLayout = ((QuestionsActivity) getActivity()).regiLayout;

        fragmentManager = getActivity().getSupportFragmentManager();

        emptyQuestion = new EmptyQuestion();
        return root;
    }

    /*Habilitar el cuadro de texto y cambiar de fragment*/
    @Override
    public void onClick(View v) {

        if (v == artsLayout) {
            branchId = 1;
            regiLayout.setEnabled(true);
            fragmentTransaction();
        } else if (v == sciencesLayout) {
            branchId = 2;
            regiLayout.setEnabled(true);
            fragmentTransaction();
        } else if (v == healthLayout) {
            branchId = 3;
            regiLayout.setEnabled(true);
            fragmentTransaction();
        } else if (v == lawsLayout) {
            branchId = 4;
            regiLayout.setEnabled(true);
            fragmentTransaction();
        } else if (v == engineeringLayout) {
            branchId = 5;
            regiLayout.setEnabled(true);
            fragmentTransaction();
        }
    }

    /*Cambiar de fragment*/
    private void fragmentTransaction() {
        FragmentTransaction fragmentTransactionEmpty = fragmentManager.beginTransaction();
        fragmentTransactionEmpty.replace(R.id.fragmentLayouts, emptyQuestion);
        fragmentTransactionEmpty.commit();
    }
}
