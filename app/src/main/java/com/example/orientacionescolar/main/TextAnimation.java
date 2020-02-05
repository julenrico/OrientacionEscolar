package com.example.orientacionescolar.main;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatTextView;

public class TextAnimation extends AppCompatTextView {
    private CharSequence mText;
    private int mIndex;
    private long mDelay = 150;
    public TextAnimationListener listener;

    public TextAnimation(Context context) {
        super(context);
    }

    public TextAnimation(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /*Hilo de la animación*/
    private Handler mHandler = new Handler();
    private Runnable characterAdder = new Runnable() {
        @Override
        public void run() {
            setText(mText.subSequence(0, mIndex++));

            if (mIndex < mText.length()) {
                mHandler.postDelayed(characterAdder, mDelay);
            } else {
                if (listener != null) {
                    listener.onFinish();
                }
            }
        }
    };

    /*Animación del texto*/
    public void animateText(CharSequence txt) {
        mText = txt;
        mIndex = 0;

        setText("");
        mHandler.removeCallbacks(characterAdder);
        mHandler.postDelayed(characterAdder, mDelay);
    }

    public void setCharacterDelay(long m) {
        mDelay = m;
    }

    public boolean textEnded() {
        return mIndex == mText.length();
    }

    public void setListener(TextAnimationListener listener) {
        this.listener = listener;
    }

    public interface TextAnimationListener {
        void onFinish();
    }

}
