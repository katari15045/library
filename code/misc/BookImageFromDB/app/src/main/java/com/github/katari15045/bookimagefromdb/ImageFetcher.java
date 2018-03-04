package com.github.katari15045.bookimagefromdb;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import java.io.InputStream;
import java.net.URL;
import java.sql.ResultSet;

/**
 * Created by Saketh Katari on 04-03-2018.
 */

public class ImageFetcher extends AsyncTask<Void, Void, Void> {

    private Context context = null;
    private AppCompatActivity activity = null;
    private String isbn = null;
    private String biblioNumber = null;
    private AsyncTask<Void, Void, Void> postExecTask = null;
    private static Bitmap imageBitmap = null;
    private AlertDialog dialog = null;

    ImageFetcher(Context context, String number, AsyncTask<Void, Void, Void> postExecTask, boolean isBiblioNumber){
        this.context = context;
        activity = (AppCompatActivity)context;
        if(isBiblioNumber){
            this.biblioNumber = number;
        }else{
            this.isbn = number;
        }
        this.postExecTask = postExecTask;
        initProgressDialog();
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        dialog.show();
    }

    @Override
    protected Void doInBackground(Void... voids) {
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

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        dialog.dismiss();
        postExecTask.execute();
    }

    static Bitmap getImageBitmap(){
        return  imageBitmap;
    }

    private void initProgressDialog(){
        View progressDialogView = activity.getLayoutInflater().inflate(R.layout.progress_dialog, null);
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
        dialogBuilder.setView(progressDialogView);
        dialog = dialogBuilder.create();
    }
}
