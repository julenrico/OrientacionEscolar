package com.example.orientacionescolar.activities;

import android.os.Bundle;
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

public class QuestionsActivity extends AppCompatActivity implements View.OnTouchListener, View.OnClickListener {

    TextAnimation ta;

    ConstraintLayout l;

    Animation carouselAnimation, btnAnim;

    FragmentManager fragmentManager = getSupportFragmentManager();
    public FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();


    int contSwitch=0;
    int contFinish=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generic_questions);

        fragmentTransaction.setCustomAnimations(R.anim.carousel_animation,R.anim.scale_down);

        ta = findViewById(R.id.tv);
        ta.setText("");
        ta.setCharacterDelay(50);
        ta.animateText(getResources().getString(R.string.txtPresentation1));
        l = findViewById(R.id.root);
        l.setOnTouchListener(this);
        l.setOnClickListener(this);

        carouselAnimation = AnimationUtils.loadAnimation(this,R.anim.carousel_animation);
        btnAnim = AnimationUtils.loadAnimation(this,R.anim.button_animation);

        ta.setListener(() -> {
            contFinish++;
            switch (contFinish){
                case 1:

                    break;
                case 2:

                    break;
                case 3:
                    BranchQuestion fragment = new BranchQuestion();
                    fragmentTransaction.add(R.id.fragmentLayouts, fragment);
                    fragmentTransaction.commit();

                    break;
                case 4:

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
        if(ta.textEnded()){
            switch (contSwitch){
                case 0:
                    ta.setCharacterDelay(50);
                    ta.animateText(getResources().getString(R.string.txtPresentation2));
                    break;
                case 1:
                    ta.setCharacterDelay(50);
                    ta.animateText(getResources().getString(R.string.txtPresentation3));
                    break;
                case 2:

                    break;
            }
            contSwitch++;
        }
    }
    //TODO: MAKE DOUBLE CLICK EVENT
}