package com.example.assignment2;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

import com.example.assignment2.models.Country;

import java.util.List;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    private List<Country> countries;

    public ViewPagerAdapter(FragmentManager fm, List<Country> countryList) {
        super(fm);
        this.countries = countryList;
    }


    @Override
    public Fragment getItem(int index) {
        weatherFragment w = weatherFragment.newInstance();
        Bundle bundle = new Bundle();
        bundle.putSerializable("country", countries.get(index));
        w.setArguments(bundle);
        return w;
    }

    @Override
    public int getCount() {
        return countries.size();
    }
}
