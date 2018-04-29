package com.github.katari15045.iiitdlibrary.Misc;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;

import com.github.katari15045.iiitdlibrary.Main.NavDrawer;

/**
 * Created by Saketh Katari on 14-03-2018.
 */

public class Global {

    public static FragmentManager fragmentManager = null;
    public static int fragmentContainer = -1;
    public static Fragment currentFragment = null;
    public static Context context = null;
    public static ActionBar actionBar = null;
    public static TabLayout tabLayout = null;
    public static NavDrawer navDrawer = null;

    public static void changeActionBarTitle(String newTitle){
        Global.actionBar.setTitle(newTitle);
    }

    public static void replaceFragment(Fragment fragment){
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(fragmentContainer, fragment);
        transaction.commit();
        currentFragment = fragment;
    }
}
