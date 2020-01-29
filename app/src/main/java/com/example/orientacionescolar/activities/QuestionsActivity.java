package com.example.orientacionescolar.activities;

import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.orientacionescolar.R;
import com.example.orientacionescolar.main.TextAnimation;
import com.example.orientacionescolar.questions.BranchQuestion;
import com.example.orientacionescolar.questions.CareerOrGrade;
import com.example.orientacionescolar.questions.ProvinciaQuestion;

public class QuestionsActivity extends AppCompatActivity implements View.OnTouchListener, View.OnClickListener {

    TextAnimation ta;

    public ConstraintLayout regiLayout;

    Animation carouselAnimation, btnAnim;

    int contSwitch = 0;
    int contFinish = 0;

    FragmentManager fragmentManager = getSupportFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generic_questions);

        ta = findViewById(R.id.tv);
        ta.setText("");
        ta.setCharacterDelay(50);
        ta.animateText(getResources().getString(R.string.txtEjemplo1));
        regiLayout = findViewById(R.id.regiLayout);
        regiLayout.setOnClickListener(this);
        regiLayout.setOnTouchListener(this);

        carouselAnimation = AnimationUtils.loadAnimation(this, R.anim.carousel_animation);
        btnAnim = AnimationUtils.loadAnimation(this, R.anim.button_animation);

        ta.setListener(() -> {
            contFinish++;
            Log.d("CONTFINISH", String.valueOf(contFinish));
            switch (contFinish) {

                case 3:
                    FragmentTransaction fragmentTransactionBranch = fragmentManager.beginTransaction();
                    fragmentTransactionBranch.setCustomAnimations(R.anim.scale_up, R.anim.scale_down);
                    BranchQuestion branchQuestion = new BranchQuestion();
                    fragmentTransactionBranch.add(R.id.fragmentLayouts, branchQuestion);
                    fragmentTransactionBranch.commit();
                    regiLayout.setEnabled(false);
                    break;

                case 6:
                    FragmentTransaction fragmentTransactionGradeOrDegree = fragmentManager.beginTransaction();
                    fragmentTransactionGradeOrDegree.setCustomAnimations(R.anim.scale_up, R.anim.scale_down);
                    CareerOrGrade careerOrGrade = new CareerOrGrade();
                    fragmentTransactionGradeOrDegree.replace(R.id.fragmentLayouts, careerOrGrade);
                    fragmentTransactionGradeOrDegree.commit();
                    regiLayout.setEnabled(false);
                    break;
                case 9:
                    FragmentTransaction fragmentTransactionProvincia = fragmentManager.beginTransaction();
                    fragmentTransactionProvincia.setCustomAnimations(R.anim.scale_up, R.anim.scale_down);
                    ProvinciaQuestion provinciaQuestion = new ProvinciaQuestion();
                    fragmentTransactionProvincia.replace(R.id.fragmentLayouts, provinciaQuestion);
                    fragmentTransactionProvincia.commit();
                    regiLayout.setEnabled(false);
                    break;
            }
        });
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                if (ta.textEnded()) {
                    v.performClick();
                }
                ta.setCharacterDelay(10);
                return true;
            } else if (event.getAction() == MotionEvent.ACTION_UP) {
                ta.setCharacterDelay(50);
                return true;
            }
        return false;
    }

    @Override
    public void onClick(View v) {
            if (ta.textEnded()) {
                switch (contSwitch) {
                    case 0:
                        ta.setCharacterDelay(50);
                        ta.animateText(getResources().getString(R.string.txtEjemplo2));
                        break;
                    case 1:
                        ta.setCharacterDelay(50);
                        ta.animateText(getResources().getString(R.string.txtEjemplo3));
                        break;
                    case 2:
                        ta.setCharacterDelay(50);
                        ta.animateText(getResources().getString(R.string.txtEjemplo4));
                        break;
                    case 3:
                        ta.setCharacterDelay(50);
                        ta.animateText(getResources().getString(R.string.txtEjemplo5));
                        break;
                    case 4:
                        ta.setCharacterDelay(50);
                        ta.animateText(getResources().getString(R.string.txtEjemplo6));
                        break;
                    case 5:
                        ta.setCharacterDelay(50);
                        ta.animateText(getResources().getString(R.string.txtEjemplo7));
                        break;
                    case 6:
                        ta.setCharacterDelay(50);
                        ta.animateText(getResources().getString(R.string.txtEjemplo8));
                        break;
                    case 7:
                        ta.setCharacterDelay(50);
                        ta.animateText(getResources().getString(R.string.txtEjemplo9));
                        break;
                }
                contSwitch++;
            }
    }
    //TODO: MAKE DOUBLE CLICK EVENT
}