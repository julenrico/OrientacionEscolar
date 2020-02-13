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

/*Fragment que maneja la pregunta de la provincia*/

public class ProvinciaQuestion extends Fragment implements View.OnClickListener {

    private EmptyQuestion emptyQuestion;
    private FragmentManager fragmentManager;

    private ConstraintLayout layoutAraba;
    private ConstraintLayout layoutBizkaia;
    private ConstraintLayout layoutGipuzkoa;

    private ConstraintLayout regiLayout;

    public static int provincia;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.questions_provincia, container, false);

        layoutAraba = root.findViewById(R.id.layoutAraba);
        layoutBizkaia = root.findViewById(R.id.layoutBizkaia);
        layoutGipuzkoa = root.findViewById(R.id.layoutGipuzkoa);
        fragmentManager = getActivity().getSupportFragmentManager();

        layoutAraba.setOnClickListener(this);
        layoutBizkaia.setOnClickListener(this);
        layoutGipuzkoa.setOnClickListener(this);

        regiLayout = ((QuestionsActivity) getActivity()).regiLayout;

        emptyQuestion = new EmptyQuestion();

        return root;
    }

    /*Al clickar en una provincia, habilitar el cuadro de texto y guardar resusltado en variable publica a la que accedo para la consulta dinámica de grados sugeridos*/
    @Override
    public void onClick(View v) {
        if (v == layoutAraba) {
            provincia = 1;
            regiLayout.setEnabled(true);
            fragmentTransaction();
        } else if (v == layoutBizkaia) {
            provincia = 2;
            regiLayout.setEnabled(true);
            fragmentTransaction();
        } else if (v == layoutGipuzkoa) {
            provincia = 3;
            regiLayout.setEnabled(true);
            fragmentTransaction();
        }
    }

    private void fragmentTransaction() {
        FragmentTransaction fragmentTransactionEmpty = fragmentManager.beginTransaction();
        fragmentTransactionEmpty.replace(R.id.fragmentLayouts, emptyQuestion);
        fragmentTransactionEmpty.commit();
    }
}
