package com.github.katari15045.iiitdlibrary;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.github.katari15045.iiitdlibrary.Util.PurchaseUtil;

import java.util.ArrayList;

public class PurchaseSuggestionActivity extends AppCompatActivity {

    private Toolbar mActionBarToolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase_suggestion);

        mActionBarToolbar = (Toolbar) findViewById(R.id.suggestionToolbar);
        setSupportActionBar(mActionBarToolbar);
        getSupportActionBar().setTitle("New Purchase Suggestion");
        getSupportActionBar().setHomeButtonEnabled(true);



        final TextView titleEdittext = (TextView) findViewById(R.id.suggestion_title_edittext);
        final TextView authorEdittext = (TextView) findViewById(R.id.suggestion_author_edittext);
        final TextView copyrightEdittext = (TextView) findViewById(R.id.suggestion_copyrightdate_edittext);

        final TextView standardNumberEdittext = (TextView) findViewById(R.id.suggestion_standardnumber_edittext);
        final TextView publisherEdittext = (TextView) findViewById(R.id.suggestion_publisher_edittext);
        final TextView collectionTitleEdittext = (TextView) findViewById(R.id.suggestion_collectiontitle_edittext);
        final TextView publicationPlaceEdittext = (TextView) findViewById(R.id.suggestion_publicationplace_edittext);
        final TextView notesEdittext = (TextView) findViewById(R.id.suggestion_notes_edittext);

        final Spinner itemType_Spinner = (Spinner) findViewById(R.id.spinner_itemType);
        final Spinner reasonForSuggestion_spinner = (Spinner) findViewById(R.id.spinner_reasonForSuggestion);

        ArrayList<String> itemTypeList = fetchItemType();
        ArrayAdapter<String> itemTypeSpinnerAdapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_spinner_item, itemTypeList);
        itemTypeSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        itemType_Spinner.setAdapter(itemTypeSpinnerAdapter);

        ArrayList<String> reasonList = fetchReasonList();
        ArrayAdapter<String> reasonAdapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_spinner_item, reasonList);
        reasonAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        reasonForSuggestion_spinner.setAdapter(reasonAdapter);

        final Button submit = (Button) findViewById(R.id.newSuggestionSubmit);
        Button cancel = (Button) findViewById(R.id.suggestionCancel);

        submit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String title = titleEdittext.getText().toString().trim();
                String author = authorEdittext.getText().toString().trim();
                String copyright = copyrightEdittext.getText().toString();
                String standardNumber = standardNumberEdittext.getText().toString().trim();
                String publisher = publisherEdittext.getText().toString().trim();
                String collectionTitle = collectionTitleEdittext.getText().toString().trim();
                String publicationPlace = publicationPlaceEdittext.getText().toString().trim();

                String item = itemType_Spinner.getSelectedItem().toString();
                String reason = reasonForSuggestion_spinner.getSelectedItem().toString();
                String notes = notesEdittext.getText().toString().trim();

               /* SuggestionUtil suggestionObject = new SuggestionUtil(title, author, copyright,
                                standardNumber, publisher, collectionTitle,
                                publicationPlace, item, reason,
                                notes);
                                */
               if(title.length() <=2)
               {
                   Toast.makeText(PurchaseSuggestionActivity.this, "Enter a valid title", Toast.LENGTH_SHORT).show();
               }
               else {
                   PurchaseUtil purchaseObject = new PurchaseUtil(title, author, copyright,
                           standardNumber, publisher, collectionTitle, publicationPlace);
                   //TODO -- send data back as intent

                   Intent intent = new Intent();

                   Bundle bundle = new Bundle();
                   bundle.putSerializable("value", purchaseObject);
                   intent.putExtras(bundle);
                   setResult(200, intent);
                   finish();
               }
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


    }

    private ArrayList<String> fetchItemType()
    {
        ArrayList<String> listItemType = new ArrayList<>();
        listItemType.add("Books");
        listItemType.add("CDs");
        listItemType.add("DVDs");
        listItemType.add("e-Book");
        listItemType.add("Kindle eBook");
        listItemType.add("Kindle eBook Reader");
        listItemType.add("Maps");
        listItemType.add("Mixed Materials");
        listItemType.add("Music");
        listItemType.add("Periodicals");
        listItemType.add("Reference");
        listItemType.add("Thesis");

        return listItemType;
    }

    private ArrayList<String> fetchReasonList()
    {
        ArrayList<String> listReason = new ArrayList<>();
        listReason.add("Increase the number of copies of this book in the library");
        listReason.add("This book is not available in the library");

        return listReason;
    }
}
