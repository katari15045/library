package com.github.katari15045.iiitdlibrary.Static.Resources;

import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.katari15045.iiitdlibrary.Main.NavDrawer;
import com.github.katari15045.iiitdlibrary.Misc.Global;
import com.github.katari15045.iiitdlibrary.R;
import com.github.katari15045.iiitdlibrary.Static.Resources.DailyNewsPapers.DailyNewsPapersFragment;
import com.github.katari15045.iiitdlibrary.Static.Resources.EResource.EResourcesFragment;

import org.w3c.dom.Text;

import java.net.URL;

public class ResourcesFragment extends Fragment{

    private View view = null;
    private static String title = null;
    private ScrollView scrollView = null;

    public ResourcesFragment(){
        title = ((AppCompatActivity) Global.context).getResources().getString
                (R.string.nav_drawer_resources_title);
    }

    @Override
    public void onResume() {
        Log.d("SAK", "ResourceFragment::onResume()");
        super.onResume();
        NavDrawer.hideItem(1);
        Global.changeActionBarTitle(title);
    }

    // Storing & Retrieving Scroll position : https://stackoverflow.com/a/29208325/8279892
    @Override
    public void onSaveInstanceState(Bundle outState) {
        Log.d("SAK", "ResourceFragment::onSaveInstanceState()");
        super.onSaveInstanceState(outState);
        float[] scrollPos = {scrollView.getScrollX(), scrollView.getScrollY()};
        outState.putFloatArray("SCROLL_POS", scrollPos);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        Log.d("SAK", "ResourceFragment::onActivityCreated()");
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
        Log.d("SAK", "ResourceFragment::onPause()");
        super.onPause();
        NavDrawer.showItem(1);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d("SAK", "ResourceFragment::onCreateView()");
        view = inflater.inflate(R.layout.fragment_resources, null);
        scrollView = view.findViewById(R.id.fragment_resources_scroll_view);
        setListeners();
        return  view;
    }

    private void setListeners(){
        Resources resources = Global.context.getResources();
        CardView cardViewEResources = view.findViewById(
                R.id.fragment_resource_cardview_e_resources);
        cardViewEResources.setOnClickListener(new ResourcesFragmentListener(
                new EResourcesFragment()));
        CardView cardViewDailyNewsPapers = view.findViewById(
                R.id.fragment_resource_cardview_daily_news_papers);
        cardViewDailyNewsPapers.setOnClickListener(new ResourcesFragmentListener(
                new DailyNewsPapersFragment()));
        CardView cardViewIndianBooks = view.findViewById(
                R.id.fragment_resource_cardview_computing_books_by_indian_authors);
        cardViewIndianBooks.setOnClickListener(new ResourceUrlListener(resources.getString(
                R.string.resources_computing_books_by_indian_authors_link)));
        CardView cardViewJournals = view.findViewById(
                R.id.fragment_resource_cardview_journal_and_magazines);
        cardViewJournals.setOnClickListener(new ResourceUrlListener(resources.getString(
                R.string.resources_journals_magazines_link)));
        CardView cardViewProofReader = view.findViewById(
                R.id.fragment_resource_cardview_proof_reader);
        cardViewProofReader.setOnClickListener(new ResourceUrlListener(resources.getString(
                R.string.resources_proof_reader_link)));
        CardView cardViewPlag = view.findViewById(
                R.id.fragment_resource_cardview_anti_plagiarism_tool);
        cardViewPlag.setOnClickListener(new ResourceUrlListener(resources.getString(
                R.string.resources_anti_plagiarism_tool_urkund_link)));
        CardView cardViewNptel = view.findViewById(
                R.id.fragment_resource_cardview_video_lectures_nptel);
        cardViewNptel.setOnClickListener(new ResourceUrlListener(resources.getString(
                R.string.resources_video_lectures_nptel_link)));
        TextView textViewMendeley = view.findViewById(R.id.fragment_resource_textview_mendeley);
        textViewMendeley.setOnClickListener(new ResourceUrlListener(resources.getString(
                R.string.resources_mendeley_link)));
        TextView textViewZotero = view.findViewById(R.id.fragment_resource_textview_zotero);
        textViewZotero.setOnClickListener(new ResourceUrlListener(resources.getString(
                R.string.resources_zotero_link)));
        TextView textViewAcadEarth = view.findViewById(
                R.id.fragment_resource_textview_academic_earth);
        textViewAcadEarth.setOnClickListener(new ResourceUrlListener(resources.getString(
                R.string.resources_academic_earth_link)));
        TextView textViewCoursera = view.findViewById(R.id.fragment_resource_textview_coursera);
        textViewCoursera.setOnClickListener(new ResourceUrlListener(resources.getString(
                R.string.resources_coursera_link)));
        TextView textViewEdx = view.findViewById(R.id.fragment_resource_textview_edx);
        textViewEdx.setOnClickListener(new ResourceUrlListener(resources.getString(
                R.string.resources_edx_link)));
        TextView textViewMit = view.findViewById(R.id.fragment_resource_textview_mit);
        textViewMit.setOnClickListener(new ResourceUrlListener(resources.getString(
                R.string.resources_mit_opencourseware_link)));
        TextView textViewNptel = view.findViewById(R.id.fragment_resource_textview_nptel);
        textViewNptel.setOnClickListener(new ResourceUrlListener(resources.getString(
                R.string.resources_video_lectures_nptel_link)));
        TextView textViewre3Data = view.findViewById(R.id.fragment_resource_textview_re3data);
        textViewre3Data.setOnClickListener(new ResourceUrlListener(resources.getString(
                R.string.resources_re3data_link)));
        TextView textViewPlos = view.findViewById(R.id.fragment_resource_textview_plos);
        textViewPlos.setOnClickListener(new ResourceUrlListener(resources.getString(
                R.string.resources_plos_link)));
        TextView textViewScientific = view.findViewById(R.id.fragment_resource_textview_scientific);
        textViewScientific.setOnClickListener(new ResourceUrlListener(resources.getString(
                R.string.resources_scientific_link)));
        TextView textViewSimmons = view.findViewById(R.id.fragment_resource_textview_simmons);
        textViewSimmons.setOnClickListener(new ResourceUrlListener(resources.getString(
                R.string.resources_simmons_link)));
        TextView textViewMinnesota = view.findViewById(
                R.id.fragment_resource_textview_univ_of_minnesota);
        textViewMinnesota.setOnClickListener(new ResourceUrlListener(resources.getString(
                R.string.resources_university_of_minnesota_link)));
    }

    public static String getTitle(){
        return title;
    }
}

class ResourcesFragmentListener implements View.OnClickListener{

    private Fragment fragment = null;

    public ResourcesFragmentListener(Fragment fragment){
        this.fragment = fragment;
    }

    @Override
    public void onClick(View view) {
        Global.replaceFragment(fragment);
    }
}

class ResourceUrlListener implements View.OnClickListener{

    private String link = null;

    public ResourceUrlListener(String link){
        this.link = link;
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(link));
        Toast.makeText(Global.context, Global.context.getResources().getString(
                R.string.please_wait), Toast.LENGTH_SHORT).show();
        Global.context.startActivity(intent);
    }
}