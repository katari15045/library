package com.github.katari15045.iiitdlibrary.biblio;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.katari15045.iiitdlibrary.R;
import com.github.katari15045.iiitdlibrary.misc.ImageFetcher;

public class BiblioOverviewFragment extends Fragment{

    private View view = null;
    private ImageView imageView = null;
    private TextView textViewImage = null;
    private String debugTag = null;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable
            Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_biblio_overview, container, false);
        debug();
        imageView = view.findViewById(R.id.fragment_biblio_overview_imageview_book);
        textViewImage = view.findViewById(R.id.fragment_biblio_overview_textview_book);
        BiblioDataFetcher biblioDataFetcher = new BiblioDataFetcher(view.getContext(), imageView,
                textViewImage);
        biblioDataFetcher.execute();
        return view;
    }

    private void debug(){
        debugTag = view.getResources().getString(R.string.debug_tag);
        Log.d(debugTag, "BiblioOverviewFrag::onCreateView()");
    }
}