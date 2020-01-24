package com.example.orientacionescolar.questions;

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

import com.example.orientacionescolar.R;
import com.example.orientacionescolar.main.TextAnimation;
import com.example.orientacionescolar.activities.QuestionsActivity;

public class CareerOrGrade extends Fragment implements View.OnClickListener {

    private EmptyQuestion emptyQuestion;
    private FragmentManager fragmentManager;

    private ConstraintLayout layoutGradoMedio;
    private ConstraintLayout layoutGradoSuperior;
    private ConstraintLayout layoutUniversidad;

    ConstraintLayout regiLayout;

    private TextAnimation textAnimation;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.questions_career_or_grade, container, false);

        layoutGradoMedio = root.findViewById(R.id.layoutGradoMedio);
        layoutGradoSuperior = root.findViewById(R.id.layoutGradoSuperior);
        layoutUniversidad = root.findViewById(R.id.layoutUniversidad);
        fragmentManager = getActivity().getSupportFragmentManager();

        layoutGradoMedio.setOnClickListener(this);
        layoutGradoSuperior.setOnClickListener(this);
        layoutUniversidad.setOnClickListener(this);

        textAnimation = ((QuestionsActivity) getActivity()).findViewById(R.id.tv);

        regiLayout = ((QuestionsActivity) getActivity()).regiLayout;

        emptyQuestion = new EmptyQuestion();

        return root;
    }

    @Override
    public void onClick(View v) {
        if (v == layoutGradoMedio) {
            regiLayout.setEnabled(true);
            fragmentTransaction();
        } else if (v == layoutGradoSuperior) {
            regiLayout.setEnabled(true);
            fragmentTransaction();
        } else if (v == layoutUniversidad) {
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
