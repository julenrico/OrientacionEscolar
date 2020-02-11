package com.reginald.skola.activities;

import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.reginald.skola.R;
import com.reginald.skola.main.TextAnimation;
import com.reginald.skola.questions.BranchQuestion;
import com.reginald.skola.questions.CareerOrGrade;
import com.reginald.skola.questions.ProvinciaQuestion;
import com.reginald.skola.questions.SuggestedDegreesQuestion;

public class QuestionsActivity extends AppCompatActivity implements View.OnTouchListener, View.OnClickListener {

    TextAnimation ta;

    public ConstraintLayout regiLayout;

    Animation carouselAnimation;
    Animation btnAnim;
    Animation blinkAnimation;

    Thread thread;

    int contSwitch = 0;
    int contFinish = 0;

    ImageView regiImage;
    ImageView imageViewTriangle;

    FragmentManager fragmentManager = getSupportFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generic_questions);

        regiImage = findViewById(R.id.imageView);
        imageViewTriangle = findViewById(R.id.imageViewTriangle);
        ta = findViewById(R.id.tv);
        ta.setText("");
        ta.setCharacterDelay(50);
        ta.animateText(getResources().getString(R.string.txtReginald1));
        regiLayout = findViewById(R.id.regiLayout);
        regiLayout.setOnClickListener(this);
        regiLayout.setOnTouchListener(this);

        /*Cargar animaciones*/

        carouselAnimation = AnimationUtils.loadAnimation(this, R.anim.carousel_animation);
        btnAnim = AnimationUtils.loadAnimation(this, R.anim.button_animation);
        blinkAnimation = AnimationUtils.loadAnimation(this, R.anim.blink_animation);

        /*Controlar qué texto ha acabado de mostrarse y en base a eso, cargar otro fragment*/
        ta.setListener(() -> {
            contFinish++;
            imageViewTriangle.setVisibility(View.VISIBLE);
            thread = new Thread(() -> imageViewTriangle.startAnimation(blinkAnimation));
            thread.start();
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

                case 4:
                    FragmentTransaction fragmentTransactionGradeOrDegree = fragmentManager.beginTransaction();
                    fragmentTransactionGradeOrDegree.setCustomAnimations(R.anim.scale_up, R.anim.scale_down);
                    CareerOrGrade careerOrGrade = new CareerOrGrade();
                    fragmentTransactionGradeOrDegree.replace(R.id.fragmentLayouts, careerOrGrade);
                    fragmentTransactionGradeOrDegree.commit();
                    regiLayout.setEnabled(false);
                    break;
                case 5:
                    FragmentTransaction fragmentTransactionProvincia = fragmentManager.beginTransaction();
                    fragmentTransactionProvincia.setCustomAnimations(R.anim.scale_up, R.anim.scale_down);
                    ProvinciaQuestion provinciaQuestion = new ProvinciaQuestion();
                    fragmentTransactionProvincia.replace(R.id.fragmentLayouts, provinciaQuestion);
                    fragmentTransactionProvincia.commit();
                    regiLayout.setEnabled(false);
                    break;
                case 6:
                    FragmentTransaction fragmentTransactionGrados = fragmentManager.beginTransaction();
                    fragmentTransactionGrados.setCustomAnimations(R.anim.scale_up, R.anim.scale_down);
                    SuggestedDegreesQuestion suggestedDegrees = new SuggestedDegreesQuestion();
                    fragmentTransactionGrados.replace(R.id.fragmentLayouts, suggestedDegrees);
                    fragmentTransactionGrados.commit();
                    regiLayout.setEnabled(false);
                    break;
            }
        });
    }

    /*Manejar el tocar el cuadro de texto. Si ha acabado el texto, ejecutar el onclick, si no, texto a más velocidad*/
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

    /*Cada click, pasa al siguiente texto*/
    @Override
    public void onClick(View v) {
        thread.interrupt();
        imageViewTriangle.clearAnimation();
        imageViewTriangle.setVisibility(View.INVISIBLE);
        if (ta.textEnded()) {
            switch (contSwitch) {
                case 0:
                    regiImage.startAnimation(carouselAnimation);
                    regiImage.setVisibility(View.VISIBLE);
                    ta.setCharacterDelay(50);
                    ta.animateText(getResources().getString(R.string.txtReginald2));
                    break;
                case 1:
                    ta.setCharacterDelay(50);
                    ta.animateText(getResources().getString(R.string.txtReginald3));
                    break;
                case 2:
                    ta.setCharacterDelay(50);
                    ta.animateText(getResources().getString(R.string.txtReginald4));
                    break;
                case 3:
                    ta.setCharacterDelay(50);
                    ta.animateText(getResources().getString(R.string.txtReginald5));
                    break;
                case 4:
                    ta.setCharacterDelay(50);
                    ta.animateText(getResources().getString(R.string.txtReginald6));
                    break;
            }
            contSwitch++;
        }
    }


    //TODO: MAKE DOUBLE CLICK EVENT
}