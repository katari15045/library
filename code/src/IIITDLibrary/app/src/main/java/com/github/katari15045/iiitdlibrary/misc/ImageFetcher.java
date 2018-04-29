package com.github.katari15045.iiitdlibrary.misc;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.katari15045.iiitdlibrary.R;

import org.w3c.dom.Text;

import java.io.InputStream;
import java.net.URL;

/**
 * Created by Saketh Katari on 29-04-2018.
 */

public class ImageFetcher extends AsyncTask<Void, Void, Void> {

    private Context context = null;
    private String isbn = null;
    private ImageView imageView = null;
    private TextView textView = null;
    private static Bitmap imageBitmap = null;
    private String debugTag = null;

    public ImageFetcher(Context context, String isbn, ImageView imageView, TextView textView){
        this.context = context;
        this.isbn = isbn;
        debugTag = context.getResources().getString(R.string.debug_tag);
        this.imageView = imageView;
        this.textView = textView;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        try{
            String urlString = context.getResources().getString(R.string.image_fetcher_api) + isbn;
            Log.d(debugTag, "Image Fetcher -> " + urlString);
            WebApi webAPIImageURL = new WebApi(context, urlString);
            Thread threadImageURL = new Thread(webAPIImageURL);
            threadImageURL.start();
            // Wait until you get a JSON string from Google API that contains the URL of cover image
            threadImageURL.join();
            // Parse the Google API Json String to extract the URL of cover image
            String imageUrl = JsonParser.parse(webAPIImageURL.getDataFetched());
            imageBitmap = getImageFromUrl(imageUrl);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        if(imageView == null){
            Log.d("Mars", "imageView is null!");
        }
        if(imageBitmap == null){
            Log.d("Mars", "imageBitmap is null!");
            textView.setText(context.getResources().getString(R.string.no_image));
        } else{
            imageView.setImageBitmap(imageBitmap);
        }
    }

    private Bitmap getImageFromUrl(String urlString){
        try{
            URL url = new URL(urlString);
            InputStream inputStream = url.openStream();
            return BitmapFactory.decodeStream(inputStream);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
