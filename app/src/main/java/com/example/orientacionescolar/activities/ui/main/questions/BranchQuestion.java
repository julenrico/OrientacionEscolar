package com.example.orientacionescolar.activities.ui.main.questions;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.orientacionescolar.R;
import com.example.orientacionescolar.TextAnimation;
import com.example.orientacionescolar.activities.QuestionsActivity;

public class BranchQuestion extends Fragment implements View.OnClickListener {

    private ImageButton buttonArt;
    private ImageButton buttonScience;
    private ImageButton buttonHealth;
    private ImageButton buttonEngineering;
    private ImageButton buttonLaw;

    public int branchId;

    private TextAnimation textAnimation;

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

        return root;
    }


    @Override
    public void onClick(View v) {

        if (v == buttonArt) {
            branchId = 1;
            textAnimation.setCharacterDelay(50);
            textAnimation.animateText(getResources().getString(R.string.txtPresentation4));
        } else if (v == buttonScience) {
            branchId = 2;
            textAnimation.setCharacterDelay(50);
            textAnimation.animateText(getResources().getString(R.string.txtPresentation4));
        } else if (v == buttonHealth) {
            branchId = 3;
            textAnimation.setCharacterDelay(50);
            textAnimation.animateText(getResources().getString(R.string.txtPresentation4));
        } else if (v == buttonEngineering) {
            branchId = 5;
            textAnimation.setCharacterDelay(50);
            textAnimation.animateText(getResources().getString(R.string.txtPresentation4));
        } else if (v == buttonLaw) {
            branchId = 4;
            textAnimation.setCharacterDelay(50);
            textAnimation.animateText(getResources().getString(R.string.txtPresentation4));
        }
    }
}
