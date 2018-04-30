package com.github.katari15045.iiitdlibrary;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.github.katari15045.iiitdlibrary.Adapter.ReadingHistoryAdapter;
import com.github.katari15045.iiitdlibrary.Adapter.SearchHistoryAdapter2;
import com.github.katari15045.iiitdlibrary.Util.ReadingHistoryUtil;
import com.github.katari15045.iiitdlibrary.Util.SearchHistoryUtil;
import com.github.katari15045.iiitdlibrary.Util.UsernameUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


public class HistoryFragment extends Fragment {

    RadioGroup radioGroup;
    Button ok_button;
    LinearLayout history_linear_layout;
    private View view;
    private int flag = 0;
    private LinearLayout dropdown;
    private LinearLayout radiogrpLayout;
    private int isAddedSearchHistory = 0;
    private int isAddedReadingHistory = 0;

    private ResultSet searchResultSet;
    private ResultSet readingResultSet;

    private ArrayList<SearchHistoryUtil> listSearchHistory;
    private ArrayList<ReadingHistoryUtil> listReadingHistory;



    public HistoryFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_history, container, false);
        dropdown = view.findViewById(R.id.dropDownImageLayout);
        dropdown.setVisibility(View.GONE);
        radiogrpLayout = view.findViewById(R.id.radiogrp);

        dropdown.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                dropdown.setVisibility(View.GONE);
                radiogrpLayout.setVisibility(View.VISIBLE);
            }
        });

        history_linear_layout = view.findViewById(R.id.history_main_linear_layout);
        ok_button = (Button) view.findViewById(R.id.history_ok);
        radioGroup = (RadioGroup) view.findViewById(R.id.radio_group);
        ok_button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {

                int radioID = radioGroup.getCheckedRadioButtonId();
                View radioButton = radioGroup.findViewById(radioID);
                int itemIndex = radioGroup.indexOfChild(radioButton);

                Log.i(getActivity()+"", "idex - -------------- " + itemIndex);
                if(itemIndex == 0)
                {
                    history_linear_layout.removeAllViews();
                    history_linear_layout.addView(radiogrpLayout);
                    history_linear_layout.addView(dropdown);

                    dropdown.setVisibility(View.VISIBLE);
                    radiogrpLayout.setVisibility(View.GONE);


                    LinearLayout search_history_layout = (LinearLayout) getActivity().getLayoutInflater().inflate(R.layout.layout_search_history, null);

                    history_linear_layout.addView(search_history_layout);
                    searchHistoryUpdateScreen();


                }
                else if(itemIndex == 1)
                {

                    history_linear_layout.removeAllViews();
                    history_linear_layout.addView(radiogrpLayout);
                    history_linear_layout.addView(dropdown);

                    dropdown.setVisibility(View.VISIBLE);
                    radiogrpLayout.setVisibility(View.GONE);


                    LinearLayout reading_history_layout = (LinearLayout) getActivity().getLayoutInflater().inflate(R.layout.layout_reading_history, null);

                    history_linear_layout.addView(reading_history_layout);
                    searchReadingUpdateScreen();

                }
                else
                {
                    Toast.makeText(getActivity(), "Select an item to continue", Toast.LENGTH_SHORT).show();
                }
            }


       });

     return view;

    }

    private SearchHistoryAdapter2 adapterSearch;

    private void searchHistoryUpdateScreen()
    {
         Log.i(getActivity()+"", "***************** - " + (view == null));
         final ArrayList<SearchHistoryUtil> searchHistory = fetchSearchResult();
        adapterSearch = new SearchHistoryAdapter2(getActivity(), searchHistory);

         Button removeSelected = (Button) view.findViewById(R.id.button_remove_selected);
         removeSelected.setOnClickListener(new View.OnClickListener()
         {
            @Override
            public void onClick(View view) {
                //TODO -- remove selected item
                int changeFlag = 0;
                for(int i = 0 ; i <searchHistory.size(); i++)
                {
                    Log.i(getActivity()+"", "22222222222222222  -  "+i + "    " + searchHistory.get(i).getChecked());
                    if(searchHistory.get(i).getChecked() == true)
                    {
                        changeFlag = 1;
                        searchHistory.remove(i);
                    }
                }
                if(changeFlag == 1)
                {
                    adapterSearch.notifyDataSetChanged();
                }


            }
         });
        TextView selectAll = (TextView) view.findViewById(R.id.select_all_text);
        selectAll.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                //TODO -- select All listview items
                if(flag == 0) {
                    for (int i = 0; i < searchHistory.size(); i++) {
                        searchHistory.get(i).setChecked(true);
                    }
                    flag = 1;
                }
                else
                {
                    flag = 0;
                    for (int i = 0; i < searchHistory.size(); i++) {
                        searchHistory.get(i).setChecked(false);
                    }

                }
                adapterSearch.notifyDataSetChanged();


            }
        });
        //ListView listView = view.findViewById(R.id.search_history_listView);
        Log.i(getActivity()+"" , "size =  ========== "+ searchHistory.size());


        recyclerViewSearch = (RecyclerView) view.findViewById(R.id.search_history_recyclerView);
        recyclerViewSearch.setHasFixedSize(false);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerViewSearch.setLayoutManager(mLayoutManager);
        recyclerViewSearch.setItemAnimator(new DefaultItemAnimator());
        recyclerViewSearch.setAdapter(adapterSearch);

        //listView.setAdapter(adapter);
        //TODO -- add a background color



    }

    private RecyclerView recyclerViewSearch;
    private ArrayList<SearchHistoryUtil> fetchSearchResult()
    {
        listSearchHistory = new ArrayList<>();

        // TODO -- fetch data from the database
        SearchHistoryAsynTask searchHistoryAsynTask = new SearchHistoryAsynTask();
        searchHistoryAsynTask.execute(UsernameUtil.username);

        return listSearchHistory;
    }

    private ReadingHistoryAdapter adapterReading;

    private void searchReadingUpdateScreen()
    {

        final ArrayList<ReadingHistoryUtil> readingHistory = fetchReadingResult();
        adapterReading = new ReadingHistoryAdapter(getActivity(), readingHistory);

        Spinner reading_spinner = (Spinner) view.findViewById(R.id.spinner_reading_history);

        ArrayList<String> spinnerList = new ArrayList<>();
        spinnerList.add("Order by title");
        spinnerList.add("Order by date");

        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, spinnerList);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        reading_spinner.setAdapter(spinnerAdapter);
        reading_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(i == 0)
                {
                    //order by title
                    Collections.sort(readingHistory, new Comparator<ReadingHistoryUtil>() {
                        @Override
                        public int compare(ReadingHistoryUtil o1, ReadingHistoryUtil o2) {
                            return o1.getTitle().compareTo(o2.getTitle());
                        }
                    });

                }
                else if(i == 1)
                {
                    //order by date
                    Collections.sort(readingHistory, new Comparator<ReadingHistoryUtil>() {
                        @Override
                        public int compare(ReadingHistoryUtil o1, ReadingHistoryUtil o2) {
                            return o1.getDate().compareTo(o2.getDate());
                        }
                    });

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.reading_history_recyclerView);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapterReading);

        //listView.setAdapter(adapter);
        //TODO -- add a background color

    }


    private ArrayList<ReadingHistoryUtil> fetchReadingResult()
    {
        listReadingHistory = new ArrayList<>();

        ReadingHistoryAsynTask task = new ReadingHistoryAsynTask();
        task.execute(UsernameUtil.username);

        return listReadingHistory;
    }

    private class SearchHistoryAsynTask extends AsyncTask<String, Void,Void>
    {
        Database database;
        @Override
        protected Void doInBackground(String... ids) {
            String command="select time, query_desc, total from search_history where userid in (select borrowernumber from borrowers where userid =\"" + ids[0] + "\");";
            database = new Database(command, true);
            Thread dbThread = new Thread(database);
            dbThread.start();
            try {
                dbThread.join();
                searchResultSet = database.getResultSet();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {


            Log.i(getActivity()+"", "nnnnnnnnnnnnnnnnnnnnnnnnnn");
            Log.i(getActivity()+"", "***************** " + (searchResultSet == null));

            if(searchResultSet != null) {
                try {
                    while (searchResultSet.next()) {
                        Log.i(getActivity() + "", searchResultSet.getString("time") + " ---------------  ");
                        Log.i(getActivity() + "", searchResultSet.getString("query_desc") + " ---------------  ");
                        Log.i(getActivity() + "", searchResultSet.getString("total") + " ---------------  ");

                        String description = searchResultSet.getString("query_desc");
                        String resultCount = searchResultSet.getString("total");
                        String[] temp_date = searchResultSet.getString("time").split(" ");


                        String date = temp_date[0];
                        String time = temp_date[1];

                        SearchHistoryUtil tempSearchData = new SearchHistoryUtil(false, date, time, description, Integer.parseInt(resultCount));
                        listSearchHistory.add(tempSearchData);


                    }
                    adapterSearch.notifyDataSetChanged();

                    for (int i = 0; i < listSearchHistory.size(); i++) {
                        Log.i(getActivity() + "", "++++++++++++++++++++ " + listSearchHistory.get(i).getDate());
                        Log.i(getActivity() + "", "++++++++++++++++++++ " + listSearchHistory.get(i).getSearch());
                        Log.i(getActivity() + "", "++++++++++++++++++++ " + listSearchHistory.get(i).getTime());
                        Log.i(getActivity() + "", "++++++++++++++++++++ " + listSearchHistory.get(i).getChecked());
                        Log.i(getActivity() + "", "++++++++++++++++++++ " + listSearchHistory.get(i).getResult());
                    }
                    Log.i("" + getActivity(), "***************** ");


                } catch (SQLException e) {
                    Log.i(getActivity() + "", "FUCK -------------------");
                }
            }
            database.close();
        }
    }

    private class ReadingHistoryAsynTask extends AsyncTask<String, Void,Void>
    {
        Database database;
        @Override
        protected Void doInBackground(String... ids) {
            String command = "select old_issues.itemnumber, biblio.title, biblio.author, biblioitems.itemtype, items.itemcallnumber, old_issues.date_due from old_issues inner join items on old_issues.itemnumber=items.itemnumber inner join biblio on items.biblionumber = biblio.biblionumber inner join biblioitems on biblio.biblionumber = biblioitems.biblionumber where old_issues.borrowernumber in (select borrowernumber from borrowers where userid =\"" + ids[0] + "\");";

            database = new Database(command, true);
            Thread dbThread = new Thread(database);
            dbThread.start();
            try {
                dbThread.join();
                readingResultSet = database.getResultSet();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {


            Log.i(getActivity()+"", "nnnnnnnnnnnnnnnnnnnnnnnnnn");
            Log.i(getActivity()+"", "***************** " + (readingResultSet == null));
            if(readingResultSet != null) {
                try {
                    while (readingResultSet.next()) {
                        Log.i(getActivity() + "", readingResultSet.getString("biblio.title") + " ---------------  ");
                        Log.i(getActivity() + "", readingResultSet.getString("biblioitems.itemtype") + " ---------------  ");
                        Log.i(getActivity() + "", readingResultSet.getString("old_issues.date_due") + " ---------------  ");

                        String title = readingResultSet.getString("biblio.title");
                        //String itemtype = readingResultSet.getString("biblioitems.itemtype");
                        String itemtype = "Book";
                        String[] temp_date = readingResultSet.getString("old_issues.date_due").split(" ");
                        String date = temp_date[0];



                        ReadingHistoryUtil tempReadingData = new ReadingHistoryUtil(title, itemtype, date);
                        listReadingHistory.add(tempReadingData);


                    }
                    Log.i("" + getActivity(), "***************** ");


                } catch (SQLException e) {
                    Log.i(getActivity() + "", "FUCK -------------------");
                }

                adapterReading.notifyDataSetChanged();
                for (int i = 0; i < listReadingHistory.size(); i++) {
                    Log.i(getActivity() + "", "++++++++++++++++++++ " + listReadingHistory.get(i).getDate());
                    Log.i(getActivity() + "", "++++++++++++++++++++ " + listReadingHistory.get(i).getTitle());
                    Log.i(getActivity() + "", "++++++++++++++++++++ " + listReadingHistory.get(i).getItemtype());
                }

                Collections.sort(listReadingHistory, new Comparator<ReadingHistoryUtil>() {
                    @Override
                    public int compare(ReadingHistoryUtil o1, ReadingHistoryUtil o2) {
                        return o1.getTitle().compareTo(o2.getTitle());
                    }
                });
            }
            database.close();
        }
    }

}
