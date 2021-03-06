package com.github.katari15045.iiitdlibrary.Biblio;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.github.katari15045.iiitdlibrary.Misc.Database;
import com.github.katari15045.iiitdlibrary.R;

import org.w3c.dom.Text;

import java.sql.ResultSet;
import java.util.HashSet;

/**
 * Created by Saketh Katari on 27-04-2018.
 */

public class BiblioDataFetcher extends AsyncTask<Void, Void, Void> {

    private View view = null;
    public Biblio biblio = null;

    public BiblioDataFetcher(View view){
        this.view = view;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Void doInBackground(Void... voids) {
        biblio = new Biblio();
        biblio.setBiblioNumber(BiblioOverviewFragment.biblioNumber);
        Database db = executeQuery("select author, title, notes, copyrightdate from biblio where biblionumber=" +
        BiblioOverviewFragment.biblioNumber + ";");
        parseBiblioQuery(db.getResultSet());
        db.close();
        db = executeQuery("select isbn, publishercode, editionstatement, pages from biblioitems where " +
                "biblionumber=" + BiblioOverviewFragment.biblioNumber + ";");
        parseBiblioItemQuery(db.getResultSet());
        db.close();
        handleNullFieldsInBiblio();
        StringBuilder sb = new StringBuilder();
        sb.append("biblionumber : ").append(biblio.getBiblioNumber()).append("\nauthor : ")
                .append(biblio.getAuthor()).append("\ntitle : ").append(biblio.getTitle())
                .append("\nnotes : ").append(biblio.getNotes()).append("\ncopyrightdate : ")
                .append(biblio.getCopyrightDate()).append("\nisbn : ").append(biblio.getIsbn())
                .append("\npublisher : ").append(biblio.getPublisher()).append("\nedition : ")
                .append(biblio.getEdition()).append("\npages : ").append(biblio.getPages())
                .append("\n");
        Log.d("SAK", "BiblioDataFetcher() results -> " + sb.toString());
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        TextView textViewTitle = view.findViewById(R.id.fragment_biblio_overview_textview_title);
        TextView textViewAuthor = view.findViewById(R.id.fragment_biblio_overview_textview_author);
        TextView textViewPublisher = view.findViewById(
                R.id.fragment_biblio_overview_textview_publisher);
        TextView textViewYear = view.findViewById(
                R.id.fragment_biblio_overview_textview_copyright_year);
        TextView textViewEdition = view.findViewById(R.id.fragment_biblio_overview_textview_edition);
        TextView textViewPages = view.findViewById(R.id.fragment_biblio_overview_textview_pages);
        TextView textViewIsbn = view.findViewById(R.id.fragment_biblio_overview_textview_isbn);
        TextView textViewNotes = view.findViewById(R.id.fragment_biblio_overview_textview_notes);
        textViewTitle.setText(biblio.getTitle());
        textViewAuthor.setText(biblio.getAuthor());
        textViewPublisher.setText(biblio.getPublisher());
        textViewYear.setText(biblio.getCopyrightDate());
        textViewEdition.setText(biblio.getEdition());
        textViewPages.setText(biblio.getPages());
        textViewIsbn.setText(biblio.getIsbn());
        textViewNotes.setText(biblio.getNotes());
    }

    private Database executeQuery(String command){
        try{
            Database db = new Database(command, true);
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
            biblio.setNotes(resultSet.getString(3));
            biblio.setCopyrightDate(resultSet.getString(4));
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

    private void handleNullFieldsInBiblio(){
        String notAvailable = view.getContext().getResources().getString(R.string.not_available);
        if(biblio.getBiblioNumber() == null || biblio.getBiblioNumber().equals("")){
            biblio.setBiblioNumber(notAvailable);
        }
        if(biblio.getAuthor() == null || biblio.getAuthor().equals("")){
            biblio.setAuthor(notAvailable);
        }
        if(biblio.getTitle() == null || biblio.getTitle().equals("")){
            biblio.setTitle(notAvailable);
        }
        if(biblio.getNotes() == null || biblio.getNotes().equals("")){
            biblio.setNotes(notAvailable);
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
