package com.reginald.skola.main;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatTextView;

public class TextAnimation extends AppCompatTextView {
    private CharSequence sequence;
    private int index;
    private long animationDelay = 150;
    public TextAnimationListener listener;

    public TextAnimation(Context context) {
        super(context);
    }

    public TextAnimation(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /*Hilo de la animación*/
    private Handler handler = new Handler();
    private Runnable characterAdder = new Runnable() {
        @Override
        public void run() {
            setText(sequence.subSequence(0, index++));

            if (index < sequence.length()) {
                handler.postDelayed(characterAdder, animationDelay);
            } else {
                if (listener != null) {
                    listener.onFinish();
                }
            }
        }
    };

    /*Animación del texto*/
    public void animateText(CharSequence txt) {
        sequence = txt;
        index = 0;

        setText("");
        handler.removeCallbacks(characterAdder);
        handler.postDelayed(characterAdder, animationDelay);
    }

    public void setCharacterDelay(long m) {
        animationDelay = m;
    }

    public boolean textEnded() {
        return index == sequence.length();
    }

    public void setListener(TextAnimationListener listener) {
        this.listener = listener;
    }

    public interface TextAnimationListener {
        void onFinish();
    }

}
