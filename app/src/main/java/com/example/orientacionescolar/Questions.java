package com.example.orientacionescolar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

public class Questions extends AppCompatActivity implements View.OnTouchListener, View.OnClickListener {
     TextAnimation ta;
     TextView txtName;
     TextInputLayout txtInputName;
     ConstraintLayout l;
     int contSwitch=0;
     int contFinish=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generic_questions);
        txtName=findViewById(R.id.txtName);
        txtInputName=findViewById(R.id.txtInputName);
        ta = findViewById(R.id.tv);
        ta.setText("");
        ta.setCharacterDelay(50);
        ta.animateText(getResources().getString(R.string.txtPresentation1));
        l = findViewById(R.id.root);
        l.setOnTouchListener(this);
        l.setOnClickListener(this);
        ta.setListener(new TextAnimation.TextAnimationListener() {
            @Override
            public void onFinish() {
                contFinish++;
                if(contFinish==3){
                    txtInputName.setVisibility(View.VISIBLE);
                }
                Toast.makeText(Questions.this, "Pulsa la pantalla para continuar...", Toast.LENGTH_SHORT).show();
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

    //TODO: ADD AGE SPINNER AND USE FRAGMENTS

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

    }


