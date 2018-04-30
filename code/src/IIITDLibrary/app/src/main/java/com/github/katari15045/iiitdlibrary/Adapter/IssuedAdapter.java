package com.github.katari15045.iiitdlibrary.Adapter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.github.katari15045.iiitdlibrary.R;
import com.github.katari15045.iiitdlibrary.Util.IssuedItemUtil;

import java.util.ArrayList;

/**
 * Created by Mayank on 3/16/2018.
 */

public class IssuedAdapter extends ArrayAdapter<IssuedItemUtil>
{
    public IssuedAdapter(Activity context, ArrayList<IssuedItemUtil> list)
    {
        super(context, 0, list);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View currentView = convertView;
        if(currentView == null)
        {
            currentView = LayoutInflater.from(getContext()).inflate(R.layout.issuedlist_layout_cardview, parent, false);
        }

        IssuedItemUtil item = getItem(position);

        TextView titleTextView = (TextView) currentView.findViewById(R.id.issued_book_title);
        TextView barcodeTextView = (TextView) currentView.findViewById(R.id.issued_book_barcode);
        TextView callnoTextView = (TextView) currentView.findViewById(R.id.issued_book_callno);
        TextView duedateTextView = (TextView) currentView.findViewById(R.id.issued_book_duedate);
        TextView fineTextView = (TextView) currentView.findViewById(R.id.issued_book_isFine);
        CheckBox renewCheckBox = (CheckBox) currentView.findViewById(R.id.issued_book_checkbox);

        titleTextView.setText(item.getTitle());
        barcodeTextView.setText(item.getBarcode());
        callnoTextView.setText(item.getCallno());
        duedateTextView.setText(item.getDuedate());
        fineTextView.setText(item.getFine());
        renewCheckBox.setChecked(false);

        return currentView;


    }
}
