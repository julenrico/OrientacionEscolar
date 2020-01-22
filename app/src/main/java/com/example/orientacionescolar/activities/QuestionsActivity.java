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
import com.example.orientacionescolar.TextAnimation;
import com.example.orientacionescolar.activities.ui.main.questions.BranchQuestion;
import com.example.orientacionescolar.activities.ui.main.questions.CareerOrGrade;

public class QuestionsActivity extends AppCompatActivity implements View.OnTouchListener, View.OnClickListener {

    TextAnimation ta;

    ConstraintLayout regiLayout;

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
        ta.animateText(getResources().getString(R.string.txtPresentation1));
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
                    break;

                case 6:
                    FragmentTransaction fragmentTransactionGradeOrDegree = fragmentManager.beginTransaction();
                    fragmentTransactionGradeOrDegree.setCustomAnimations(R.anim.scale_up, R.anim.scale_down);
                    CareerOrGrade careerOrGrade = new CareerOrGrade();
                    fragmentTransactionGradeOrDegree.replace(R.id.fragmentLayouts, careerOrGrade);
                    fragmentTransactionGradeOrDegree.commit();
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
                        ta.animateText(getResources().getString(R.string.txtPresentation2));
                        break;
                    case 1:
                        ta.setCharacterDelay(50);
                        ta.animateText(getResources().getString(R.string.txtPresentation3));
                        break;
                    case 2:
                        ta.setCharacterDelay(50);
                        ta.animateText(getResources().getString(R.string.txtPresentation4));
                        break;
                    case 3:
                        ta.setCharacterDelay(50);
                        ta.animateText(getResources().getString(R.string.txtPresentation5));
                        break;
                    case 4:
                        ta.setCharacterDelay(50);
                        ta.animateText("Bernis huele mal");
                }
                contSwitch++;
            }
    }
    //TODO: MAKE DOUBLE CLICK EVENT
}