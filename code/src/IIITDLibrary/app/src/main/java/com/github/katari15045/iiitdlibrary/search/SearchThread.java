package com.github.katari15045.iiitdlibrary.search;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.github.katari15045.iiitdlibrary.Misc.Database;
import com.github.katari15045.iiitdlibrary.R;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.HashSet;

/**
 * Created by Saketh Katari on 27-04-2018.
 */

public class SearchThread extends AsyncTask<Void, Void, Void> {

    private Context context = null;
    private String query = null;
    private static HashSet<SearchResult> results = null;

    public SearchThread(Context context, String query){
        this.context = context;
        this.query = query;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        results = new HashSet<>();
        handleQuery("select title, author from biblio where title like '%" + query + "%';");
        handleQuery("select title, author from biblio where author like '%" + query + "%';");
        handleQuery("select title, author from biblio where notes like '%" + query + "%';");
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        SearchActivity.resultsFetched = true;
        SearchActivity.addRecyclerView(context);
    }

    private void handleQuery(String command){
        try{
            Database db = new Database(command, true);
            Thread dbThread = new Thread(db);
            dbThread.start();
            dbThread.join();
            parse(db.getResultSet());
            db.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void parse(ResultSet resultSet){
        try{
            while(resultSet.next()){
                SearchResult searchResult = new SearchResult();
                searchResult.setTitle(resultSet.getString(1));
                searchResult.setAuthor(resultSet.getString(2));
                results.add(searchResult);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static HashSet<SearchResult> getResults(){
        return results;
    }
}
