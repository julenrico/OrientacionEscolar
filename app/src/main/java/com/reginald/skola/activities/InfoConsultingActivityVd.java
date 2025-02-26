package com.reginald.skola.activities;

import android.animation.Animator;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.reginald.skola.R;
import com.reginald.skola.fragments.FragmentsAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

public class InfoConsultingActivityVd extends AppCompatActivity {

    FloatingActionButton fab, fab1, fab2, fab3;
    LinearLayout fabLayout1, fabLayout2, fabLayout3;
    View fabBGLayout;
    boolean isFABOpen;
    TextView textViewTest;
    TextView textViewListaDeGrados;
    TextView textViewGradosFavoritos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /*COMPROBAR SI ES LA PRIMERA VEZ QUE SE EJECUTA LA APP*/

        boolean isFirstRun = getSharedPreferences("PREFERENCE", MODE_PRIVATE)
                .getBoolean("isFirstRun", true);

        if (isFirstRun) {
            getSharedPreferences("PREFERENCE", MODE_PRIVATE).edit()
                    .putBoolean("isFirstRun", false).apply();
            startActivity(new Intent(InfoConsultingActivityVd.this, MainActivity.class));
        }


        setContentView(R.layout.activity_info_consulting_vd);
        FragmentsAdapter fragmentsAdapter = new FragmentsAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(fragmentsAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);


        textViewTest = findViewById(R.id.textViewTest);
        textViewListaDeGrados = findViewById(R.id.textViewListaDeGrados);
        textViewGradosFavoritos = findViewById(R.id.textViewGradosFavoritos);

        fabLayout1 = findViewById(R.id.fabLayout1);
        fabLayout2 = findViewById(R.id.fabLayout2);
        fabLayout3 = findViewById(R.id.fabLayout3);
        fab = findViewById(R.id.fab);
        fab1 = findViewById(R.id.fab1);
        fab2 = findViewById(R.id.fab2);
        fab3 = findViewById(R.id.fab3);
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
            isFABOpen = false;
            startActivity(new Intent(InfoConsultingActivityVd.this, QuestionsActivity.class));
        });

        fab2.setOnClickListener(view -> {
            fragmentsAdapter.setFav(false);
            closeFABMenu();
            isFABOpen = false;
        });

        fab3.setOnClickListener(view -> {
            closeFABMenu();
            isFABOpen = false;
            fragmentsAdapter.setFav(true);
        });


        fabBGLayout.setOnClickListener(view -> closeFABMenu());
    }

    /*Métodos del Floating Action Button*/

    private void showFABMenu() {
        isFABOpen = true;
        fabLayout1.setVisibility(View.VISIBLE);
        fabLayout2.setVisibility(View.VISIBLE);
        fabLayout3.setVisibility(View.VISIBLE);
        textViewTest.setVisibility(View.VISIBLE);
        textViewListaDeGrados.setVisibility(View.VISIBLE);
        textViewGradosFavoritos.setVisibility(View.VISIBLE);
        fabBGLayout.setVisibility(View.VISIBLE);
        fab.animate().rotationBy(45);
        fabLayout1.animate().translationY(-getResources().getDimension(R.dimen.standard_55));
        fabLayout2.animate().translationY(-getResources().getDimension(R.dimen.standard_100));
        fabLayout3.animate().translationY(-getResources().getDimension(R.dimen.standard_145));
        fabBGLayout.setBackgroundColor(Color.argb(170, 98, 115, 120));
    }

    private void closeFABMenu() {
        textViewTest.setVisibility(View.GONE);
        textViewListaDeGrados.setVisibility(View.GONE);
        textViewGradosFavoritos.setVisibility(View.GONE);
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