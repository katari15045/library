package com.github.katari15045.iiitdlibrary;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.github.katari15045.iiitdlibrary.Adapter.FineAdapter;
import com.github.katari15045.iiitdlibrary.Adapter.IssuedAdapter;
import com.github.katari15045.iiitdlibrary.Util.FineUtil;
import com.github.katari15045.iiitdlibrary.Util.IssuedItemUtil;
import com.github.katari15045.iiitdlibrary.Util.UsernameUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class SummaryFragment extends Fragment {


    private CardView fine_card;
    private ResultSet fineResultSet;

    private TextView fine_total_summary_textView;

    private ArrayList<FineUtil> fineList;
    private FineAdapter adapterFineDetails;

    private IssuedAdapter issuedItemAdapter;
    private ArrayList<IssuedItemUtil> issuedList;
    private ResultSet issuedResultSet;

    private  ListView issuedListView;

    public SummaryFragment() {
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
        View view =  inflater.inflate(R.layout.fragment_summary, container, false);

        //issued
        issuedList = new ArrayList<>();
        issuedList =  fetchIssuedList();
        issuedListView = (ListView) view.findViewById(R.id.issued_linear_layout);
        issuedItemAdapter = new IssuedAdapter(getActivity(), issuedList);
        issuedListView.setAdapter(issuedItemAdapter);
        setListViewHeightBasedOnItems(issuedListView);
        setLongClick();

        Button renewButton = (Button) view.findViewById(R.id.issuedList_renew_button);
        renewButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Item renewed", Toast.LENGTH_SHORT).show();
            }
        });



        ListView listView = (ListView) view.findViewById(R.id.wishlistListView);
        ArrayList<String> wishList = new ArrayList<>();
        wishList.add("Sheldon Ross");
        wishList.add("Cormen");
        wishList.add("Lord of the rings");
        wishList.add("Breief history of time by Stephen Hawkings");


        ArrayAdapter<String> wishLishAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, wishList);
        listView.setAdapter(wishLishAdapter);

        fine_card = (CardView) view.findViewById(R.id.profile_summary_fine_view);
        fine_card.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                Intent fineDetailsIntent = new Intent(getActivity(), FineDetailsActivity.class);
                fineDetailsIntent.putExtra("totalFine", fine_total_summary_textView.getText());
                startActivity(fineDetailsIntent);
            }
        });

        fine_total_summary_textView = (TextView) view.findViewById(R.id.summary_total_fine_textview);
        FineDetailsAsynTask fineDetailsAsynTask = new FineDetailsAsynTask();
        fineDetailsAsynTask.execute(UsernameUtil.username, "0");

        return view;
    }

    private ArrayList<IssuedItemUtil> fetchIssuedList()
    {
        // TODO -- update the query in IssuedDetails Task

        IssuedDetailsAsynTask task = new IssuedDetailsAsynTask();
        task.execute(UsernameUtil.username);

        return issuedList;
    }

    private class FineDetailsAsynTask extends AsyncTask<String, Void,Void>
    {
        Database database;

        @Override
        protected Void doInBackground(String... ids) {
            String command = "select sum(amountoutstanding) from accountlines where borrowernumber in (select borrowernumber from borrowers where userid =\"" + ids[0] + "\");";

            database = new Database(command, true);
            Thread dbThread = new Thread(database);
            dbThread.start();
            try {
                dbThread.join();
                fineResultSet = database.getResultSet();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {


            String fineTotal = "";
            if (fineResultSet != null)
            {
                try {
                    while (fineResultSet.next()) {
                        Log.i(getActivity() + "", fineResultSet.getString("sum(amountoutstanding)") + " ---------------  ");
                        fineTotal = fineResultSet.getString("sum(amountoutstanding)");

                        break;
                    }
                    Log.i("" + getActivity(), "***************** ");

                } catch (SQLException e) {
                    Log.i(getActivity() + "", "FUCK -------------------");
                }
                if(fineTotal != null && fineTotal.length() > 2) {
                    fine_total_summary_textView.setText(fineTotal.substring(0, 4));
                }
            }


            database.close();

        }
    }

    public static boolean setListViewHeightBasedOnItems(ListView listView) {

        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter != null) {

            int numberOfItems = listAdapter.getCount();

            // Get total height of all items.
            int totalItemsHeight = 0;
            for (int itemPos = 0; itemPos < numberOfItems; itemPos++) {
                View item = listAdapter.getView(itemPos, null, listView);
                float px = 500 * (listView.getResources().getDisplayMetrics().density);
                item.measure(View.MeasureSpec.makeMeasureSpec((int)px, View.MeasureSpec.AT_MOST), View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
                totalItemsHeight += item.getMeasuredHeight();
            }

            // Get total height of all item dividers.
            int totalDividersHeight = listView.getDividerHeight() *
                    (numberOfItems - 1);
            // Get padding
            int totalPadding = listView.getPaddingTop() + listView.getPaddingBottom();

            // Set list height.
            ViewGroup.LayoutParams params = listView.getLayoutParams();
            params.height = totalItemsHeight + totalDividersHeight + totalPadding;
            listView.setLayoutParams(params);
            listView.requestLayout();
            return true;

        } else {
            return false;
        }

    }


    private void setLongClick()
    {
        issuedListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

                Log.i(getActivity()+"", "******++++++++++********");

                String message = "Title: " + issuedList.get(i).getTitle() + "\n"
                        + "Call no.: " +  issuedList.get(i).getCallno() + "\n";
                createWhatsppIntent(message);

                return true;
            }
        });
    }




    private void createWhatsppIntent(String bookTitle)
    {
        Intent whatsappIntent = new Intent(Intent.ACTION_SEND);
        whatsappIntent.setType("text/plain");
        whatsappIntent.setPackage("com.whatsapp");
        whatsappIntent.putExtra(Intent.EXTRA_TEXT, bookTitle);
        try
        {
            startActivity(whatsappIntent);
        }
        catch (android.content.ActivityNotFoundException ex)
        {
            Toast.makeText(getActivity(), "Whatsapp is not installed.", Toast.LENGTH_SHORT).show();
        }
    }


    private class IssuedDetailsAsynTask extends AsyncTask<String, Void,Void>
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
               issuedResultSet = database.getResultSet();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {

        if(issuedResultSet != null){
            try {
                while (issuedResultSet.next()) {
                    Log.i(getActivity() + "", issuedResultSet.getString("old_issues.itemnumber") + " ---------------  ");
                    Log.i(getActivity() + "", issuedResultSet.getString("biblio.title") + " ---------------  ");
                    Log.i(getActivity() + "", issuedResultSet.getString("biblio.author") + " ---------------  ");
                    Log.i(getActivity() + "", issuedResultSet.getString("biblioitems.itemtype") + " ---------------  ");
                    Log.i(getActivity() + "", issuedResultSet.getString("items.itemcallnumber") + " ---------------  ");
                    Log.i(getActivity() + "", issuedResultSet.getString("old_issues.date_due") + " ---------------  ");

                    String title = issuedResultSet.getString("biblio.title");
                    String barcode = issuedResultSet.getString("old_issues.itemnumber");
                    String callno = issuedResultSet.getString("items.itemcallnumber");
                    String due = issuedResultSet.getString("old_issues.date_due");
                    String fine = "No";


                    issuedList.add(new IssuedItemUtil(title, barcode, callno, due, fine));
                }
                Log.i("" + getActivity(), "***************** ");


            } catch (SQLException e) {
                Log.i(getActivity() + "", "FUCK -------------------");
            }

        }

            issuedItemAdapter.notifyDataSetChanged();

            setListViewHeightBasedOnItems(issuedListView);
            setLongClick();
            database.close();

        }
    }



}
