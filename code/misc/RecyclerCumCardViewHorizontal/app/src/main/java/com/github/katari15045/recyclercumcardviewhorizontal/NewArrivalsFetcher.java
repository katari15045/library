package com.github.katari15045.recyclercumcardviewhorizontal;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * Created by Saketh Katari on 04-03-2018.
 */

public class NewArrivalsFetcher extends AsyncTask<Void, Void, Void> {

    private Context context = null;
    private AppCompatActivity activity = null;
    private int totalBooks = -1;
    private AlertDialog dialog = null;
    private static ArrayList<Card> cards = null;
    private AsyncTask<Void, Void, Void> postExecTask = null;

    NewArrivalsFetcher(Context context, int totalBooks, AsyncTask<Void, Void, Void> postExecTask){
        this.context = context;
        this.activity = (AppCompatActivity)context;
        this.totalBooks = totalBooks;
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
            String command = context.getResources().getString(R.string.command_recent_books)
                    + " " + (totalBooks*3) + ";";
            Database database = new Database(command, true);
            Thread dbThread = new Thread(database);
            dbThread.start();
            dbThread.join();
            cards = storeCards(database.getResultSet());
            database.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    private ArrayList<Card> storeCards(ResultSet resultSet){
        ArrayList<Card> cards = new ArrayList<>(totalBooks);
        try{
            while(resultSet.next()){
                Card card = new Card();
                card.setIsbn(resultSet.getString(1));
                card.setBiblionumber(resultSet.getString(2));
                ImageFetcher imageFetcher = new ImageFetcher(context, card.getIsbn(),
                        false);
                Thread imageFetcherThread = new Thread(imageFetcher);
                imageFetcherThread.start();
                imageFetcherThread.join();
                if(ImageFetcher.getImageBitmap() == null){
                    continue;
                }
                card.setImage(ImageFetcher.getImageBitmap());
                cards.add(card);
                if(cards.size() == totalBooks){
                    break;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return cards;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        postExecTask.execute();
        dialog.dismiss();
    }

    private void initProgressDialog(){
        View progressDialogView = activity.getLayoutInflater().inflate(R.layout.progress_dialog, null);
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
        dialogBuilder.setView(progressDialogView);
        dialog = dialogBuilder.create();
    }

    static ArrayList<Card> getCards(){
        return cards;
    }
}
