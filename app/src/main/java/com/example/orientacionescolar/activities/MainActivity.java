package com.example.orientacionescolar.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.icu.text.IDNA;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.orientacionescolar.R;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ViewListener;

public class MainActivity extends AppCompatActivity {

    CarouselView carouselView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        carouselView = findViewById(R.id.carouselView);
        carouselView.setPageCount(3);

        carouselView.setFillColor(getResources().getColor(R.color.colorPrimary));
        carouselView.setStrokeColor(getResources().getColor(R.color.colorPrimaryDark));
        Button btnNext = findViewById(R.id.btnNext);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, InfoConsultingActivityVd.class));

            }
        });

        carouselView.setViewListener(new ViewListener() {
            @Override
            public View setViewForPosition(int position) {

                View view = getLayoutInflater().inflate(R.layout.carousel_view,null);
                ImageView imgView = view.findViewById(R.id.imgCarousel);
                TextView txtFrase = view.findViewById(R.id.txtFrase);
                TextView txtParrafo = view.findViewById(R.id.txtParrafo);

                switch (position){
                    case 0:
                        imgView.setBackground(getResources().getDrawable(R.drawable.ic_image_carousel1,null));
                        txtFrase.setText(getResources().getString(R.string.slideFrase1));
                        txtParrafo.setText(getResources().getString(R.string.slideParrafo1));
                        break;
                    case 1:
                        imgView.setBackground(getResources().getDrawable(R.drawable.ic_image_carousel2,null));
                        txtFrase.setText(getResources().getString(R.string.slideFrase2));
                        txtParrafo.setText(getResources().getString(R.string.slideParrafo2));
                        break;
                    case 2:
                        imgView.setBackground(getResources().getDrawable(R.drawable.ic_image_carousel3,null));
                        txtFrase.setText(getResources().getString(R.string.slideFrase3));
                        txtParrafo.setText(getResources().getString(R.string.slideParrafo3));
                        break;
                }
                return view;
            }
        });

    }

}
