package com.github.katari15045.iiitdlibrary.Home;

import android.content.Context;

import com.github.katari15045.iiitdlibrary.Misc.Database;
import com.github.katari15045.iiitdlibrary.Misc.ImageFetcher;
import com.github.katari15045.iiitdlibrary.R;

import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * Created by Saketh Katari on 04-03-2018.
 */

public class NewArrivalsFetcher implements Runnable {

    private Context context = null;
    private int totalBooks = -1;
    private static ArrayList<NewArrivalCard> cards = null;

    public NewArrivalsFetcher(Context context, int totalBooks){
        this.context = context;
        this.totalBooks = totalBooks;
    }

    @Override
    public void run() {
        try{
            String command = context.getResources().getString(R.string.command_recent_books)
                    + " " + (totalBooks*3) + ";";
            Database database = new Database(command, true);
            Thread dbThread = new Thread(database);
            dbThread.start();
            dbThread.join();
            if(!Database.isConnected()){
                return;
            }
            cards = storeCards(database.getResultSet());
            database.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    private ArrayList<NewArrivalCard> storeCards(ResultSet resultSet){
        ArrayList<NewArrivalCard> cards = new ArrayList<>(totalBooks);
        try{
            while(resultSet.next()){
                NewArrivalCard card = new NewArrivalCard();
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

    public static ArrayList<NewArrivalCard> getCards(){
        return cards;
    }
}
