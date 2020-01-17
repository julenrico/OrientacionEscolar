package com.example.orientacionescolar.activities;

import android.animation.Animator;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.orientacionescolar.R;
import com.example.orientacionescolar.activities.ui.main.FragmentsAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

public class InfoConsultingActivityVd extends AppCompatActivity {

    FloatingActionButton fab, fab1, fab2, fab3;
    LinearLayout fabLayout1, fabLayout2, fabLayout3;
    View fabBGLayout;
    boolean isFABOpen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_consulting_vd);
        FragmentsAdapter fragmentsAdapter = new FragmentsAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(fragmentsAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);

        //BOOK LIKE ANIMATION
        /*BookFlipPageTransformer bookFlipPageTransformer = new BookFlipPageTransformer();
        bookFlipPageTransformer.setEnableScale(true);
        bookFlipPageTransformer.setScaleAmountPercent(10f);
        viewPager.setPageTransformer(true, bookFlipPageTransformer);*/

        fabLayout1 =  findViewById(R.id.fabLayout1);
        fabLayout2 =  findViewById(R.id.fabLayout2);
        fabLayout3 =  findViewById(R.id.fabLayout3);
        fab =  findViewById(R.id.fab);
        fab1 =  findViewById(R.id.fab1);
        fab2 =  findViewById(R.id.fab2);
        fab3 =  findViewById(R.id.fab3);
        fabBGLayout = findViewById(R.id.fabBGLayout);
        fab.setOnClickListener(view -> {
            if (!isFABOpen) {
                showFABMenu();
            } else {
                closeFABMenu();
            }
        });

        fab1.setOnClickListener(view -> {
            closeFABMenu();
            isFABOpen=false;
            startActivity(new Intent(InfoConsultingActivityVd.this, QuestionsActivity.class));
            //Toast.makeText(InfoConsultingActivityVd.this, "Mantén pulsado para que el texto avance más rápido...", Toast.LENGTH_LONG).show();
        });

        fab2.setOnClickListener(view -> {
            fragmentsAdapter.setFav(false);
            closeFABMenu();
            isFABOpen=false;
        });

        fab3.setOnClickListener(view ->{
            closeFABMenu();
            isFABOpen=false;
            fragmentsAdapter.setFav(true);
        });


        fabBGLayout.setOnClickListener(view -> closeFABMenu());
    }

    private void showFABMenu() {
        isFABOpen = true;
        fabLayout1.setVisibility(View.VISIBLE);
        fabLayout2.setVisibility(View.VISIBLE);
        fabLayout3.setVisibility(View.VISIBLE);
        fabBGLayout.setVisibility(View.VISIBLE);
        fab.animate().rotationBy(45);
        fabLayout1.animate().translationY(-getResources().getDimension(R.dimen.standard_55));
        fabLayout2.animate().translationY(-getResources().getDimension(R.dimen.standard_100));
        fabLayout3.animate().translationY(-getResources().getDimension(R.dimen.standard_145));
        fabBGLayout.setBackgroundColor(Color.argb(170,98, 115, 120));
    }

    private void closeFABMenu() {
        isFABOpen = false;
        fabBGLayout.setVisibility(View.GONE);
        fab.animate().rotation(0);
        fabLayout1.animate().translationY(0);
        fabLayout2.animate().translationY(0);
        fabLayout3.animate().translationY(0);
        fabLayout3.animate().translationY(0).setListener(new Animator.AnimatorListener() {

            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                if (!isFABOpen) {
                    fabLayout1.setVisibility(View.GONE);
                    fabLayout2.setVisibility(View.GONE);
                    fabLayout3.setVisibility(View.GONE);
                }
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
    }

    @Override
    public void onBackPressed() {
        if (isFABOpen) {
            closeFABMenu();
        } else {
            super.onBackPressed();
        }
    }


}