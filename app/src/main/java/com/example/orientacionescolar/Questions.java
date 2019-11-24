package com.example.orientacionescolar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Checkable;
import android.widget.TextView;
import android.widget.Toast;

public class Questions extends AppCompatActivity implements View.OnTouchListener, View.OnClickListener{
     TextAnimation ta;
     ConstraintLayout l;
     int cont=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generic_questions);
        ta = findViewById(R.id.tv);
        ta.setText("");
        ta.setCharacterDelay(50);
        ta.animateText(getResources().getString(R.string.txtPresentation1));
        l = findViewById(R.id.root);
        l.setOnTouchListener(this);
        l.setOnClickListener(this);
    }

 //TODO:CONSTANTLY EXECUTE TOAST PRESS TO CONTINUE

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

    @Override
    public void onClick(View v) {
            if(ta.textEnded()){
                switch (cont){
                    case 0:
                        ta.setCharacterDelay(50);
                        ta.animateText(getResources().getString(R.string.txtPresentation2));
                        break;
                    case 1:
                        CheckBox cb = new CheckBox(this);
                        //TODO:MAKE COMPONENTS VISIBLE
                        l.addView(cb);
                        break;
                    case 2:

                        break;
                }
                cont++;
            }
        }

    }


