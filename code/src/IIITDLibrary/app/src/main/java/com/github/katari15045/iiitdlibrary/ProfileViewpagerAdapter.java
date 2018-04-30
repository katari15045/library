package com.github.katari15045.iiitdlibrary;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by Mayank on 3/4/2018.
 */

public class ProfileViewpagerAdapter extends FragmentPagerAdapter
{
    ArrayList<Fragment> fragmentList;
    ArrayList<String> titleList;
    public ProfileViewpagerAdapter(FragmentManager fm) {
        super(fm);
        fragmentList = new ArrayList<>();
        titleList = new ArrayList<>();
        setupTitle();
    }
    private void setupTitle()
    {
        titleList.add("Summary");
        titleList.add("Update");
        titleList.add("Messaging");
        titleList.add("Discharge");
        titleList.add("Suggestions");
        titleList.add("History");
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }
    public void addFragment(Fragment fragment)
    {
        fragmentList.add(fragment);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titleList.get(position);
    }
}
