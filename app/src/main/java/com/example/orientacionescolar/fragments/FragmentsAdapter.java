package com.example.orientacionescolar.fragments;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.orientacionescolar.R;

public class FragmentsAdapter extends FragmentPagerAdapter {

    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.tab_text_1, R.string.tab_text_2, R.string.tab_text_3};
    private final Context mContext;

    private DegreesFragment degreesFragment;


    public FragmentsAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    /*Dependiendo del Tab en el que estés, devuelve un fragment diferente*/
    @NonNull
    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                degreesFragment = new DegreesFragment();
                return degreesFragment;
            case 1:
                return new HighGradesFragment();
            case 2:
                return new MediumGradesAdapter();
            default:
                return null;
        }
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(TAB_TITLES[position]);
    }

    // Cantidad de Tabs a mostrar
    @Override
    public int getCount() {

        return 3;
    }

    public void setFav(boolean fav) {
        degreesFragment.setFav(fav);
    }
}