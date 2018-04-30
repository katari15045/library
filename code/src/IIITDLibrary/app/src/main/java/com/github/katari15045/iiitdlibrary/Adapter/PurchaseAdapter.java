package com.github.katari15045.iiitdlibrary.Adapter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.github.katari15045.iiitdlibrary.R;
import com.github.katari15045.iiitdlibrary.Util.PurchaseUtil;

import java.util.ArrayList;

/**
 * Created by Mayank on 3/16/2018.
 */

public class PurchaseAdapter extends ArrayAdapter<PurchaseUtil>
{

    public PurchaseAdapter(Activity context, ArrayList<PurchaseUtil> list)
    {
        super(context, 0, list);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View currentView = convertView;
        if(currentView == null)
        {
            currentView = LayoutInflater.from(getContext()).inflate(R.layout.suggestion_card_layout, parent, false);
        }

        PurchaseUtil item = getItem(position);

        TextView title = (TextView) currentView.findViewById(R.id.purchase_suggestion_title);
        TextView author = (TextView) currentView.findViewById(R.id.purchase_suggestion_author);
        TextView copyrightdate = (TextView) currentView.findViewById(R.id.purchase_suggestion_copyrightdate);
        TextView stdno = (TextView) currentView.findViewById(R.id.purchase_suggestion_standardnumber);
        TextView publisher = (TextView) currentView.findViewById(R.id.purchase_suggestion_publisher);
        TextView collectiontitle = (TextView) currentView.findViewById(R.id.purchase_suggestion_collectiontitle);
        TextView place = (TextView) currentView.findViewById(R.id.purchase_suggestion_publicationplace);

        title.setText(item.getTitle());
        author.setText(item.getAuthor());
        copyrightdate.setText(item.getCopyrightDate());
        stdno.setText(item.getStdNumber());
        publisher.setText(item.getPublisher());
        collectiontitle.setText(item.getCollectionTitle());
        place.setText(item.getPublicationPlace());

        return currentView;
    }
}
