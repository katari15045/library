package com.github.katari15045.iiitdlibrary;

import android.content.Context;

import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * Created by Saketh Katari on 04-03-2018.
 */

public class NewArrivalsFetcher implements Runnable {

    private Context context = null;
    private int totalBooks = -1;
    private static ArrayList<Card> cards = null;

    NewArrivalsFetcher(Context context, int totalBooks){
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
            cards = storeCards(database.getResultSet());
            database.close();
        }catch(Exception e){
            e.printStackTrace();
        }
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

    static ArrayList<Card> getCards(){
        return cards;
    }
}
