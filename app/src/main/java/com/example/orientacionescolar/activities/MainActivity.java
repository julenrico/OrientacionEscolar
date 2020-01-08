package com.example.orientacionescolar.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.icu.text.IDNA;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.orientacionescolar.DatabaseHelper;
import com.example.orientacionescolar.R;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ViewListener;

public class MainActivity extends AppCompatActivity {

    CarouselView carouselView;
    Animation carouselAnimation, btnAnim;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         databaseHelper = new DatabaseHelper(this,"dataBase",null,1);
         Cursor cursor = databaseHelper.getReadableDatabase().rawQuery("select * from university_degrees\n" +
                 "    join university_center_degrees ucd on university_degrees.degree_id = ucd.degree_id\n" +
                 "    join university_degree_branches udb on university_degrees.degree_branch = udb.branch_id\n" +
                 "    join university_degree_centers udc on ucd.center_id = udc.center_id\n" +
                 "    join university_degree_campus u on udc.center_campus = u.campus_id",null);
         if(cursor.getCount()<1){
             databaseHelper.loadDataFromApi();
         }
         Log.d("CHORIMALO",String.valueOf(cursor.getCount()));

        setContentView(R.layout.activity_main);

        carouselView = findViewById(R.id.carouselView);
        carouselView.setPageCount(3);

        carouselAnimation = AnimationUtils.loadAnimation(this,R.anim.carousel_animation);
        carouselView.startAnimation(carouselAnimation);

        carouselView.setFillColor(getResources().getColor(R.color.colorPrimary));
        carouselView.setStrokeColor(getResources().getColor(R.color.colorPrimaryDark));
        btnAnim = AnimationUtils.loadAnimation(this,R.anim.button_animation);
        Button btnNext = findViewById(R.id.btnNext);
        btnNext.startAnimation(btnAnim);
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
