package com.github.katari15045.tablayout;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by Saketh Katari on 06-03-2018.
 */

public class FragmentAdapter extends FragmentPagerAdapter {

    private ArrayList<Fragment> mFragments = null;
    private ArrayList<String> mtitles = null;

    public FragmentAdapter(FragmentManager fragmentManager){
        super(fragmentManager);
        mFragments = new ArrayList<>();
        mtitles = new ArrayList<>();
    }

    void addFragment(Fragment fragment, String title){
        mFragments.add(fragment);
        mtitles.add(title);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mtitles.get(position);
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }
}
