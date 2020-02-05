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
import com.example.orientacionescolar.activities.QuestionsActivity;

/*Para fin de curso, cuando tenga los datos de los grados superiores y grados medios, poder elegir grado superior o grado medio*/

public class CareerOrGrade extends Fragment implements View.OnClickListener {

    private EmptyQuestion emptyQuestion;
    private FragmentManager fragmentManager;

    private ConstraintLayout layoutGradoMedio;
    private ConstraintLayout layoutGradoSuperior;
    private ConstraintLayout layoutUniversidad;

    private ConstraintLayout regiLayout;

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

        layoutGradoSuperior.setEnabled(false);
        layoutGradoMedio.setEnabled(false);

        regiLayout = ((QuestionsActivity) getActivity()).regiLayout;

        emptyQuestion = new EmptyQuestion();

        return root;
    }

    @Override
    public void onClick(View v) {
        if (v == layoutGradoMedio) {
            boolean isMediumGrade = true;
            regiLayout.setEnabled(true);
            fragmentTransaction();
        } else if (v == layoutGradoSuperior) {
            boolean isHighGrade = true;
            regiLayout.setEnabled(true);
            fragmentTransaction();
        } else if (v == layoutUniversidad) {
            boolean isUniversityDegree = true;
            regiLayout.setEnabled(true);
            fragmentTransaction();
        }
    }

    private void fragmentTransaction() {
        FragmentTransaction fragmentTransactionEmpty = fragmentManager.beginTransaction();
        fragmentTransactionEmpty.setCustomAnimations(R.anim.scale_up, R.anim.scale_down);
        fragmentTransactionEmpty.replace(R.id.fragmentLayouts, emptyQuestion);
        fragmentTransactionEmpty.commit();
    }
}
