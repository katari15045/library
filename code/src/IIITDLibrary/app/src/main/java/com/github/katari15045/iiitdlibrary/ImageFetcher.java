package com.github.katari15045.iiitdlibrary;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.io.InputStream;
import java.net.URL;
import java.sql.ResultSet;

/**
 * Created by Saketh Katari on 04-03-2018.
 */

public class ImageFetcher implements Runnable {

    private Context context = null;
    private AppCompatActivity activity = null;
    private String isbn = null;
    private String biblioNumber = null;
    private static Bitmap imageBitmap = null;

    ImageFetcher(Context context, String number, boolean isBiblioNumber){
        this.context = context;
        activity = (AppCompatActivity)context;
        if(isBiblioNumber){
            this.biblioNumber = number;
        }else{
            this.isbn = number;
        }
    }

    @Override
    public void run() {
        try{
            if(isbn == null){
                isbn = getIsbnFromBiblioNumber();
                Log.d("SAK", "biblionumber -> " + biblioNumber + "; isbn -> " + isbn);
            }
            String urlString = "https://www.googleapis.com/books/v1/volumes?q=isbn:" + isbn;
            WebAPI webAPIImageURL = new WebAPI(urlString);
            Thread threadImageURL = new Thread(webAPIImageURL);
            threadImageURL.start();
            threadImageURL.join();
            String imageUrl = JsonParser.parse(webAPIImageURL.getDataFetched());
            imageBitmap = getImageFromUrl(imageUrl);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private String getIsbnFromBiblioNumber(){
        try{
            String command = context.getResources().getString(R.string.command_isbn_from_biblio)
                    + biblioNumber + ";";
            Database database = new Database(command, true);
            Thread dbThread = new Thread(database);
            dbThread.start();
            dbThread.join();
            String isbn = parseDBQuery(database.getResultSet());
            database.close();
            return isbn;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    private String parseDBQuery(ResultSet resultSet){
        try{
            resultSet.next();
            return  resultSet.getString(1);
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

    static Bitmap getImageBitmap(){
        return  imageBitmap;
    }
}
