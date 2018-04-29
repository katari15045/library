package com.github.katari15045.iiitdlibrary.biblio;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import com.github.katari15045.iiitdlibrary.R;
import com.github.katari15045.iiitdlibrary.misc.Database;
import com.github.katari15045.iiitdlibrary.misc.ImageFetcher;

import java.sql.ResultSet;

/**
 * Created by Saketh Katari on 27-04-2018.
 */

public class BiblioDataFetcher extends AsyncTask<Void, Void, Void> {

    private Context context = null;
    private ImageView imageView = null;
    private TextView textView = null;
    private AppCompatActivity activity = null;
    public Biblio biblio = null;

    public BiblioDataFetcher(Context context, ImageView imageView, TextView textView){
        this.context = context;
        activity = (AppCompatActivity)context;
        this.imageView = imageView;
        this.textView = textView;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        biblio = new Biblio();
        biblio.setBiblioNumber(BiblioActivity.biblioNumber);
        Database db = executeQuery("select author, title, copyrightdate from biblio where biblionumber=" +
                BiblioActivity.biblioNumber + ";");
        parseBiblioQuery(db.getResultSet());
        db.close();
        db = executeQuery("select isbn, publishercode, editionstatement, pages from biblioitems where " +
                "biblionumber=" + BiblioActivity.biblioNumber + ";");
        parseBiblioItemQuery(db.getResultSet());
        db.close();
        db = executeQuery("select max(price) from items where biblionumber=" +
                BiblioActivity.biblioNumber + ";");
        parsePrice(db.getResultSet());
        db.close();
        handleNullFieldsInBiblio();
        StringBuilder sb = new StringBuilder();
        sb.append("biblionumber : ").append(biblio.getBiblioNumber()).append("\nauthor : ")
                .append(biblio.getAuthor()).append("\ntitle : ").append(biblio.getTitle())
                .append("\nprice : ").append(biblio.getPrice()).append("\ncopyrightdate : ")
                .append(biblio.getCopyrightDate()).append("\nisbn : ").append(biblio.getIsbn())
                .append("\npublisher : ").append(biblio.getPublisher()).append("\nedition : ")
                .append(biblio.getEdition()).append("\npages : ").append(biblio.getPages())
                .append("\n");
        Log.d("SAK", "BiblioDataFetcher() results -> " + sb.toString());
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        TextView textViewTitle = activity.findViewById(R.id.fragment_biblio_overview_textview_title);
        TextView textViewAuthor = activity.findViewById(R.id.fragment_biblio_overview_textview_author);
        TextView textViewPublisher = activity.findViewById(
                R.id.fragment_biblio_overview_textview_publisher);
        TextView textViewYear = activity.findViewById(
                R.id.fragment_biblio_overview_textview_copyright_year);
        TextView textViewEdition = activity.findViewById(R.id.fragment_biblio_overview_textview_edition);
        TextView textViewPages = activity.findViewById(R.id.fragment_biblio_overview_textview_pages);
        TextView textViewIsbn = activity.findViewById(R.id.fragment_biblio_overview_textview_isbn);
        TextView textViewPrice = activity.findViewById(R.id.fragment_biblio_overview_textview_price);
        textViewTitle.setText(biblio.getTitle());
        textViewAuthor.setText(biblio.getAuthor());
        textViewPublisher.setText(biblio.getPublisher());
        textViewYear.setText(biblio.getCopyrightDate());
        textViewEdition.setText(biblio.getEdition());
        textViewPages.setText(biblio.getPages());
        textViewIsbn.setText(biblio.getIsbn());
        textViewPrice.setText(biblio.getPrice());
        // Fetch Image
        ImageFetcher imageFetcher = new ImageFetcher(context,
                biblio.getIsbn(), imageView, textView);
        imageFetcher.execute();
    }

    private Database executeQuery(String command){
        try{
            Database db = new Database(context, command, true);
            Thread dbThread = new Thread(db);
            dbThread.start();
            dbThread.join();
            return db;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    private void parseBiblioQuery(ResultSet resultSet){
        try{
            resultSet.next();
            biblio.setAuthor(resultSet.getString(1));
            biblio.setTitle(resultSet.getString(2));
            biblio.setCopyrightDate(resultSet.getString(3));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void parseBiblioItemQuery(ResultSet resultSet){
        try{
            resultSet.next();
            biblio.setIsbn(resultSet.getString(1));
            biblio.setPublisher(resultSet.getString(2));
            biblio.setEdition(resultSet.getString(3));
            biblio.setPages(resultSet.getString(4));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void parsePrice(ResultSet resultSet){
        try{
            resultSet.next();
            biblio.setPrice("INR " + resultSet.getString(1));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void handleNullFieldsInBiblio(){
        String notAvailable = context.getResources().getString(R.string.not_available);
        if(biblio.getBiblioNumber() == null || biblio.getBiblioNumber().equals("")){
            biblio.setBiblioNumber(notAvailable);
        }
        if(biblio.getAuthor() == null || biblio.getAuthor().equals("")){
            biblio.setAuthor(notAvailable);
        }
        if(biblio.getTitle() == null || biblio.getTitle().equals("")){
            biblio.setTitle(notAvailable);
        }
        if(biblio.getPrice() == null || biblio.getPrice().equals("")){
            biblio.setPrice(notAvailable);
        }
        if(biblio.getCopyrightDate() == null || biblio.getCopyrightDate().equals("")){
            biblio.setCopyrightDate(notAvailable);
        }
        if(biblio.getIsbn() == null || biblio.getIsbn().equals("")){
            biblio.setIsbn(notAvailable);
        }
        if(biblio.getPublisher() == null || biblio.getPublisher().equals("")){
            biblio.setPublisher(notAvailable);
        }
        if(biblio.getEdition() == null || biblio.getEdition().equals("")){
            biblio.setEdition(notAvailable);
        }
        if(biblio.getPages() == null || biblio.getPages().equals("")){
            biblio.setPages(notAvailable);
        }
    }
}