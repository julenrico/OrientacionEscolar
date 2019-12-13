package com.example.orientacionescolar.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.orientacionescolar.R;
import com.example.orientacionescolar.TextAnimation;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.textfield.TextInputLayout;

public class QuestionsActivity extends AppCompatActivity implements View.OnTouchListener, View.OnClickListener {

    Button btnConfirmar;

    TextView txtDate;
    TextView txtName;

    TextAnimation ta;

    TextInputLayout txtInputName;
    TextInputLayout txtInputDate;

    ConstraintLayout l;

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
        ta.setListener(new TextAnimation.TextAnimationListener() {
            @Override
            public void onFinish() {
                contFinish++;
                switch (contFinish){
                    case 1:
                        Toast.makeText(QuestionsActivity.this, "Pulsa la pantalla para continuar...", Toast.LENGTH_SHORT).show();
                        break;
                    case 2:
                        Toast.makeText(QuestionsActivity.this, "Pulsa 2 veces la pantalla para avanzar al final del texto...", Toast.LENGTH_SHORT).show();
                        break;
                    case 3:
                        txtInputName.setVisibility(View.VISIBLE);
                        txtInputDate.setVisibility(View.VISIBLE);
                        btnConfirmar.setVisibility(View.VISIBLE);
                        Toast.makeText(QuestionsActivity.this, "Pulsa el botón para continuar...", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if(event.getAction()==MotionEvent.ACTION_DOWN){
            if (ta.textEnded()){
                v.performClick();
            }
            ta.setCharacterDelay(10);
            return true;
        }
        else if(event.getAction()==MotionEvent.ACTION_UP){
            ta.setCharacterDelay(50);
            return true;
        }
        return false;
    }

    //TODO: USE FRAGMENTS AND ORDER THE CODE

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
            MaterialDatePicker.Builder<Long> builder = MaterialDatePicker.Builder.datePicker();
            MaterialDatePicker<Long> picker = builder.build();
            picker.show(getSupportFragmentManager(), picker.toString());
        }
    }
    //TODO: MAKE DOUBLE CLICK EVENT

}