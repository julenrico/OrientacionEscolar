package com.example.orientacionescolar.activities;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.orientacionescolar.R;
import com.synnapps.carouselview.CarouselView;

public class MainActivity extends AppCompatActivity {

    CarouselView carouselView;
    Animation carouselAnimation, btnAnim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, 123);

        carouselView = findViewById(R.id.carouselView);
        carouselView.setPageCount(3);

        carouselAnimation = AnimationUtils.loadAnimation(this, R.anim.carousel_animation);
        carouselView.startAnimation(carouselAnimation);

        carouselView.setFillColor(getResources().getColor(R.color.colorPrimary));
        carouselView.setStrokeColor(getResources().getColor(R.color.colorPrimaryDark));
        btnAnim = AnimationUtils.loadAnimation(this, R.anim.button_animation);
        Button btnNext = findViewById(R.id.btnNext);
        btnNext.startAnimation(btnAnim);
        btnNext.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, InfoConsultingActivityVd.class)));
        finish();

        carouselView.setViewListener(position -> {

            View view = getLayoutInflater().inflate(R.layout.carousel_view, null);
            ImageView imgView = view.findViewById(R.id.imgCarousel);
            TextView txtFrase = view.findViewById(R.id.txtFrase);
            TextView txtParrafo = view.findViewById(R.id.txtParrafo);

            switch (position) {
                case 0:
                    imgView.setBackground(getResources().getDrawable(R.drawable.ic_image_carousel1, null));
                    txtFrase.setText(getResources().getString(R.string.slideFrase1));
                    txtParrafo.setText(getResources().getString(R.string.slideParrafo1));
                    break;
                case 1:
                    imgView.setBackground(getResources().getDrawable(R.drawable.ic_image_carousel2, null));
                    txtFrase.setText(getResources().getString(R.string.slideFrase2));
                    txtParrafo.setText(getResources().getString(R.string.slideParrafo2));
                    break;
                case 2:
                    imgView.setBackground(getResources().getDrawable(R.drawable.ic_image_carousel3, null));
                    txtFrase.setText(getResources().getString(R.string.slideFrase3));
                    txtParrafo.setText(getResources().getString(R.string.slideParrafo3));
                    break;
            }
            return view;
        });

    }

}
