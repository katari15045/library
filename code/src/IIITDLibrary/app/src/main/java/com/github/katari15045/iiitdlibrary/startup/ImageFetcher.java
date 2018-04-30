package com.github.katari15045.iiitdlibrary.startup;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.github.katari15045.iiitdlibrary.R;
import com.github.katari15045.iiitdlibrary.misc.Database;
import com.github.katari15045.iiitdlibrary.misc.JsonParser;
import com.github.katari15045.iiitdlibrary.misc.WebApi;

import java.io.InputStream;
import java.net.URL;
import java.sql.ResultSet;

/**
 * Created by Saketh Katari on 04-03-2018.
 */

/**
 * Given a biblio number or an ISBN number of a book, it fetches it's cover image from a Google API
 * if biblio number is given, it first fetches corresponding ISBN number from the database
 */
public class ImageFetcher implements Runnable {

    private Context context = null;
    private AppCompatActivity activity = null;
    private String isbn = null;
    private String biblioNumber = null;
    private static Bitmap imageBitmap = null;

    public ImageFetcher(Context context, String number, boolean isBiblioNumber){
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
            // At this stage, ISBN number is known;
            String urlString = context.getResources().getString(R.string.image_fetcher_api) + isbn;
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
    }

    // Queries the Database to get an ISBN number of a book, given it's biblio number
    private String getIsbnFromBiblioNumber(){
        try{
            String command = context.getResources().getString(R.string.command_isbn_from_biblio)
                    + biblioNumber + ";";
            Database database = new Database(context, command, true);
            Thread dbThread = new Thread(database);
            dbThread.start();
            // Wait until the database fetches the results
            dbThread.join();
            // Parse the results obtained from Database
            String isbn = parseDBQuery(database.getResultSet());
            // Make sure you close the resources of the database
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

    public static Bitmap getImageBitmap(){
        return  imageBitmap;
    }
}