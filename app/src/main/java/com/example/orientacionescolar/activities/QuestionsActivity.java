package com.example.orientacionescolar.activities;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.orientacionescolar.R;
import com.example.orientacionescolar.TextAnimation;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Calendar;

public class QuestionsActivity extends AppCompatActivity implements View.OnTouchListener, View.OnClickListener, DatePickerDialog.OnDateSetListener {

    Button btnConfirmar;

    TextView txtDate;
    TextView txtName;

    TextAnimation ta;

    TextInputLayout txtInputName;
    TextInputLayout txtInputDate;

    ConstraintLayout l;

    Animation carouselAnimation, btnAnim;

    int contSwitch=0;
    int contFinish=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generic_questions);
        btnConfirmar=findViewById(R.id.btnConfirmar);
        txtName=findViewById(R.id.txtName);
        txtDate=findViewById(R.id.txtDate);
        txtInputName=findViewById(R.id.txtInputName);
        txtInputDate=findViewById(R.id.txtInputDate);
        ta = findViewById(R.id.tv);
        ta.setText("");
        ta.setCharacterDelay(50);
        ta.animateText(getResources().getString(R.string.txtPresentation1));
        l = findViewById(R.id.root);
        l.setOnTouchListener(this);
        l.setOnClickListener(this);
        btnConfirmar.setOnClickListener(this);
        txtDate.setOnClickListener(this);
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
                    txtInputName.startAnimation(carouselAnimation);
                    txtInputDate.startAnimation(carouselAnimation);
                    btnConfirmar.startAnimation(btnAnim);
                    txtInputName.setVisibility(View.VISIBLE);
                    txtInputDate.setVisibility(View.VISIBLE);
                    btnConfirmar.setVisibility(View.VISIBLE);

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
        if(v==txtDate){
            showDatePickerDialog();

        }
    }

    private void showDatePickerDialog() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                R.style.MyDatePickerDialogTheme,
                this,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
        );
        datePickerDialog.show();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        String date = dayOfMonth+"/"+month+"/"+year;
        txtDate.setText(date);
        txtDate.clearFocus();
    }
    //TODO: MAKE DOUBLE CLICK EVENT

}