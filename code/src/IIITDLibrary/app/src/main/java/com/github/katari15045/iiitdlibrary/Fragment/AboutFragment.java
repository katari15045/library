package com.github.katari15045.iiitdlibrary.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;

import com.github.katari15045.iiitdlibrary.Activity.MainActivity;
import com.github.katari15045.iiitdlibrary.Gui.NavDrawer;
import com.github.katari15045.iiitdlibrary.R;

public class AboutFragment extends Fragment{

    private View view = null;
    private static String title = null;
    private ScrollView scrollView = null;

    public AboutFragment(){
        title = MainActivity.getContext().getResources().getString(R.string.about_fragment_title);
    }

    @Override
    public void onResume() {
        Log.d("SAK", "AboutFragment::onResume()");
        super.onResume();
        NavDrawer.hideItem(0);
        MainActivity.changeActionBarTitle(title);
    }

    // Storing & Retrieving Scroll position : https://stackoverflow.com/a/29208325/8279892
    @Override
    public void onSaveInstanceState(Bundle outState) {
        Log.d("SAK", "AboutFragment::onSaveInstanceState()");
        super.onSaveInstanceState(outState);
        float[] scrollPos = {scrollView.getScrollX(), scrollView.getScrollY()};
        outState.putFloatArray("SCROLL_POS", scrollPos);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        Log.d("SAK", "AboutFragment::onActivityCreated()");
        super.onActivityCreated(savedInstanceState);
        if(savedInstanceState == null){
            return;
        }
        final float[] scrollPos = savedInstanceState.getFloatArray("SCROLL_POS");
        if(scrollPos != null){
            scrollView.post(new Runnable() {
                @Override
                public void run() {
                    scrollView.scrollTo((int)scrollPos[0], (int)scrollPos[1]);
                }
            });
        }
    }

    @Override
    public void onPause() {
        Log.d("SAK", "AboutFragment::onPause()");
        super.onPause();
        NavDrawer.showItem(0);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d("SAK", "AboutFragment::onCreateView()");
        view = inflater.inflate(R.layout.fragment_about, null);
        scrollView = view.findViewById(R.id.fragment_about_scroll_view);
        return view;
    }

    public static String getTitle(){
        return title;
    }
}