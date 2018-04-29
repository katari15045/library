package com.github.katari15045.iiitdlibrary.biblio;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.github.katari15045.iiitdlibrary.R;
import com.github.katari15045.iiitdlibrary.misc.Database;

import java.sql.ResultSet;

/**
 * Created by Saketh Katari on 29-04-2018.
 */

public class BiblioCopiesDataFetcher extends AsyncTask<Void, Void, Void> {

    private Context context = null;
    private String debugTag = null;
    private String availForLoan = null;
    private String takenForLoan = null;
    private String notForLoan = null;

    public BiblioCopiesDataFetcher(Context context){
        this.context = context;
        debugTag = context.getResources().getString(R.string.debug_tag);
    }

    @Override
    protected Void doInBackground(Void... voids) {
        Database db = executeQuery("select count(*) from items where biblionumber="
                + BiblioActivity.biblioNumber + " and onloan is NULL;");
        availForLoan = parse(db.getResultSet());
        db.close();
        db = executeQuery("select count(*) from items where biblionumber="
                + BiblioActivity.biblioNumber + " and onloan is not NULL;");
        takenForLoan = parse(db.getResultSet());
        db.close();
        db = executeQuery("select count(*) from items where notforloan=1 and biblionumber=" +
                BiblioActivity.biblioNumber + ";");
        notForLoan = parse(db.getResultSet());
        db.close();
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        AppCompatActivity activity = (AppCompatActivity)context;
        TextView tvAvailForLoan = activity.findViewById(R.id.
                frag_biblio_copies_tv_available_for_loan);
        TextView tvTakenForLoan = activity.findViewById(R.id.frag_biblio_copies_tv_taken_for_loan);
        TextView tvNotForLoan = activity.findViewById(R.id.frag_biblio_copies_tv_not_for_loan);
        tvAvailForLoan.setText(availForLoan);
        tvTakenForLoan.setText(takenForLoan);
        tvNotForLoan.setText(notForLoan);
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

    private String parse(ResultSet resultSet){
        try{
            resultSet.next();
            return resultSet.getString(1);
        }catch(Exception e){
            e.printStackTrace();
        }
        return context.getResources().getString(R.string.not_available);
    }
}
