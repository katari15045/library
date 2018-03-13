package com.github.katari15045.iiitdlibrary.Fragment.Biblio;

import android.content.res.Resources;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.github.katari15045.iiitdlibrary.Activity.MainActivity;
import com.github.katari15045.iiitdlibrary.R;

/**
 * Created by Saketh Katari on 13-03-2018.
 */

public class MyTabLayout {
    private static TabLayout tabLayout = null;

    public static void init(int index){
        tabLayout = MainActivity.getTabLayout();
        addTabs();
        tabLayout.getTabAt(index).select();
        tabLayout.addOnTabSelectedListener(new MyTabListener());
    }

    private static void addTabs(){
        if(tabLayout.getTabAt(0) != null){
            return;
        }
        Resources resources = ((AppCompatActivity)MainActivity.getContext()).getResources();
        TabLayout.Tab tabOverview = tabLayout.newTab();
        tabOverview.setText(resources.getString(R.string.biblio_fragment_overview_title));
        tabLayout.addTab(tabOverview);
        TabLayout.Tab tabCopies = tabLayout.newTab();
        tabCopies.setText(resources.getString(R.string.biblio_fragment_copies_title));
        tabLayout.addTab(tabCopies);
    }

    public static void hideTabLayout(){
        MainActivity.getTabLayout().setVisibility(View.GONE);
        MainActivity.getViewPager().setVisibility(View.GONE);
    }

    public static void showTabLayout(){
        MainActivity.getTabLayout().setVisibility(View.VISIBLE);
        MainActivity.getViewPager().setVisibility(View.VISIBLE);
    }

    public static TabLayout getTabLayout(){
        return tabLayout;
    }
}

class MyTabListener implements TabLayout.OnTabSelectedListener{
    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        Fragment fragment = null;
        if(tab.getPosition() == 0){
            Log.d("SAK", "Clicked on Book::Overview");
            fragment = new BiblioOverviewFragment();
        }else if(tab.getPosition() == 1){
            Log.d("SAK", "Clicked on Book::Copies");
            fragment = new BiblioCopyFragment();
        }
        MainActivity.replaceFragment(fragment);
        MainActivity.currentFragment = fragment;
    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }
}