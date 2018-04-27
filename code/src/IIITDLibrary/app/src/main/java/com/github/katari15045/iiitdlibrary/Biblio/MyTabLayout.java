package com.github.katari15045.iiitdlibrary.Biblio;

import android.content.res.Resources;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.github.katari15045.iiitdlibrary.Main.MainActivity;
import com.github.katari15045.iiitdlibrary.Misc.Global;
import com.github.katari15045.iiitdlibrary.R;

/**
 * Created by Saketh Katari on 13-03-2018.
 */

public class MyTabLayout {
    private static TabLayout tabLayout = null;
    public static boolean tabZeroClicked = false;
    public static boolean tabOneClicked = false;

    public static void init(int index){
        tabLayout = Global.tabLayout;
        addTabs();
        tabLayout.getTabAt(index).select();
        tabLayout.addOnTabSelectedListener(new MyTabListener());
    }

    private static void addTabs(){
        if(tabLayout.getTabAt(0) != null){
            return;
        }
        Resources resources = ((AppCompatActivity)Global.context).getResources();
        TabLayout.Tab tabOverview = tabLayout.newTab();
        tabOverview.setText(resources.getString(R.string.biblio_fragment_overview_title));
        tabLayout.addTab(tabOverview);
        TabLayout.Tab tabCopies = tabLayout.newTab();
        tabCopies.setText(resources.getString(R.string.biblio_fragment_copies_title));
        tabLayout.addTab(tabCopies);

    }

    public static void hideTabLayout(){
        Global.tabLayout.setVisibility(View.GONE);
    }

    public static void showTabLayout(){
        Global.tabLayout.setVisibility(View.VISIBLE);
    }
}

class MyTabListener implements TabLayout.OnTabSelectedListener{
    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        Fragment fragment = null;
        if(tab.getPosition() == 0){
            if(MyTabLayout.tabZeroClicked){
                return;
            }
            Log.d("SAK", "Clicked on Book::Overview");
            fragment = new BiblioOverviewFragment();
            MyTabLayout.tabZeroClicked = true;
            MyTabLayout.tabOneClicked = false;
        }else if(tab.getPosition() == 1){
            if(MyTabLayout.tabOneClicked){
                return;
            }
            Log.d("SAK", "Clicked on Book::Copies");
            fragment = new BiblioCopyFragment();
            MyTabLayout.tabZeroClicked = false;
            MyTabLayout.tabOneClicked = true;
        }
        Global.replaceFragment(fragment);
        Global.currentFragment = fragment;
    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }
}