package com.github.katari15045.bookimagefromdb;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;

/**
 * Created by Saketh Katari on 04-03-2018.
 */

public class ImageFetcher extends AsyncTask<Void, Void, Void> {

    private String isbn = null;
    private AsyncTask<Void, Void, Void> postExecTask = null;
    private static Bitmap imageBitmap = null;

    ImageFetcher(String isbn, AsyncTask<Void, Void, Void> postExecTask){
        this.isbn = isbn;
        this.postExecTask = postExecTask;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Void doInBackground(Void... voids) {
        try{
            String urlString = "https://www.googleapis.com/books/v1/volumes?q=isbn:" + isbn;
            WebAPI webAPIImageURL = new WebAPI(urlString);
            Thread threadImageURL = new Thread(webAPIImageURL);
            threadImageURL.start();
            threadImageURL.join();
            Log.d("SAK", "WebAPI -> " + webAPIImageURL.getDataFetched());
            String imageUrl = JsonParser.parse(webAPIImageURL.getDataFetched());
            Log.d("SAK", "Json Parser -> " + imageUrl);
            imageBitmap = getImageFromUrl(imageUrl);
            if(imageBitmap == null){
                Log.d("SAK", "Bitmap is null");
            }else{
                Log.d("SAK", "Bitmap is available!");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
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

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        postExecTask.execute();
    }

    static Bitmap getImageBitmap(){
        return  imageBitmap;
    }
}
