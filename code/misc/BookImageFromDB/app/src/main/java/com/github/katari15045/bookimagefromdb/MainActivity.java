package com.github.katari15045.bookimagefromdb;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import java.net.URL;

public class MainActivity extends AppCompatActivity {
    private ImageFetcher imageFetcher = null;
    private ImageViewer imageViewer = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageViewer = new ImageViewer(this);
        imageFetcher = new ImageFetcher("9788177583724", imageViewer);
        imageFetcher.execute();
    }
}

class ImageViewer extends AsyncTask<Void, Void, Void>{

    private Context context = null;
    private AppCompatActivity activity = null;

    ImageViewer(Context context){
        this.context = context;
        activity = (AppCompatActivity)context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Void doInBackground(Void... voids) {
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        ImageView imageView = activity.findViewById(R.id.activity_main_image_view);
        imageView.setImageBitmap(ImageFetcher.getImageBitmap());
    }
}