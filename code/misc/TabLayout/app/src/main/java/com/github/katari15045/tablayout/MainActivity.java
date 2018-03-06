package com.github.katari15045.tablayout;


import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewPager viewPager = findViewById(R.id.activity_main_view_pager);
        FragmentAdapter fragmentAdapter = new FragmentAdapter(getSupportFragmentManager());
        fragmentAdapter.addFragment(new FirstFragment(), getResources().
                getString(R.string.fragment_one_title));
        fragmentAdapter.addFragment(new SecondFragment(), getResources().
                getString(R.string.fragment_two_title));
        fragmentAdapter.addFragment(new ThirdFragment(), getResources().
                getString(R.string.fragment_three_title));
        viewPager.setAdapter(fragmentAdapter);
        TabLayout tableLayout = findViewById(R.id.activity_main_tab_layout);
        tableLayout.setupWithViewPager(viewPager);
    }

}
