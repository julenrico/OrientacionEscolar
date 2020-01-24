package com.example.orientacionescolar.questions;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.orientacionescolar.R;
import com.example.orientacionescolar.main.TextAnimation;
import com.example.orientacionescolar.activities.QuestionsActivity;

public class BranchQuestion extends Fragment implements View.OnClickListener {

    private ImageButton buttonArt;
    private ImageButton buttonScience;
    private ImageButton buttonHealth;
    private ImageButton buttonEngineering;
    private ImageButton buttonLaw;

    public int branchId;

    private TextAnimation textAnimation;

    private EmptyQuestion emptyQuestion;
    private FragmentManager fragmentManager;

    ConstraintLayout regiLayout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.questions_branch, container, false);

        buttonArt = root.findViewById(R.id.buttonArt);
        buttonScience = root.findViewById(R.id.buttonScience);
        buttonHealth = root.findViewById(R.id.buttonHealth);
        buttonEngineering = root.findViewById(R.id.buttonEngineering);
        buttonLaw = root.findViewById(R.id.buttonLaw);

        buttonArt.setOnClickListener(this);
        buttonScience.setOnClickListener(this);
        buttonHealth.setOnClickListener(this);
        buttonEngineering.setOnClickListener(this);
        buttonLaw.setOnClickListener(this);

        textAnimation = ((QuestionsActivity) getActivity()).findViewById(R.id.tv);

        regiLayout = ((QuestionsActivity) getActivity()).regiLayout;

        fragmentManager = getActivity().getSupportFragmentManager();

        emptyQuestion = new EmptyQuestion();
        return root;
    }

    @Override
    public void onClick(View v) {

        if (v == buttonArt) {
            branchId = 1;
            regiLayout.setEnabled(true);
            fragmentTransaction();
        } else if (v == buttonScience) {
            branchId = 2;
            regiLayout.setEnabled(true);
            fragmentTransaction();
        } else if (v == buttonHealth) {
            branchId = 3;
            regiLayout.setEnabled(true);
            fragmentTransaction();
        } else if (v == buttonEngineering) {
            branchId = 5;
            regiLayout.setEnabled(true);
            fragmentTransaction();
        } else if (v == buttonLaw) {
            branchId = 4;
            regiLayout.setEnabled(true);
            fragmentTransaction();
        }
    }

    public void fragmentTransaction() {
        FragmentTransaction fragmentTransactionEmpty = fragmentManager.beginTransaction();
        fragmentTransactionEmpty.setCustomAnimations(R.anim.scale_up, R.anim.scale_down);
        fragmentTransactionEmpty.replace(R.id.fragmentLayouts, emptyQuestion);
        fragmentTransactionEmpty.commit();
    }
}
