package com.github.katari15045.iiitdlibrary.Adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import com.github.katari15045.iiitdlibrary.R;
import com.github.katari15045.iiitdlibrary.Util.FineUtil;

import java.util.ArrayList;

/**
 * Created by Mayank on 3/11/2018.
 */

public class FineAdapter extends ArrayAdapter<FineUtil>
{
    public FineAdapter(Context context, ArrayList<FineUtil> adapterList)
    {
        super(context, 0, adapterList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View currentItemView = convertView;
        if(currentItemView == null)
        {
            currentItemView = LayoutInflater.from(getContext()).inflate(R.layout.view_fine_adapter, parent, false);
        }
        FineUtil fineData = getItem(position);

        TextView dateTextView = currentItemView.findViewById(R.id.fine_date);
        TextView descriptionTextView = currentItemView.findViewById(R.id.fine_description);
        TextView amountTextView = currentItemView.findViewById(R.id.fine_amount);
        TextView amountOutstandingTextView = currentItemView.findViewById(R.id.fine_amount_outstanding);

        dateTextView.setText(fineData.getDate());
        descriptionTextView.setText(fineData.getDescription());
        amountTextView.setText(fineData.getFineAmount());
        amountOutstandingTextView.setText(fineData.getAmountOutstanding());
        if(position%2 == 0)
        {
            dateTextView.setBackgroundColor(Color.parseColor("#E0E0E0"));
            descriptionTextView.setBackgroundColor(Color.parseColor("#E0E0E0"));
            amountTextView.setBackgroundColor(Color.parseColor("#E0E0E0"));
            amountOutstandingTextView.setBackgroundColor(Color.parseColor("#E0E0E0"));
        }


        return currentItemView;
    }
}
