package com.github.katari15045.iiitdlibrary;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;


public class MessagingFragment extends Fragment {

    private CheckBox checkBox_itemDue;
    private CheckBox checkBox_advancenotice;
    private CheckBox checkBox_holdfilled;
    private CheckBox checkBox_itemcheckin;
    private CheckBox checkBox_itemcheckout;

    private Spinner messagingSpinner;
    public MessagingFragment() {
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
        View view =  inflater.inflate(R.layout.fragment_messaging, container, false);

        messagingSpinner = (Spinner) view.findViewById(R.id.fragment_messaging_spinner);
        ArrayList<String> spinnerList = new ArrayList<>();
        for(int i = 1; i < 15; i++)
        {
            spinnerList.add(""+i);
        }

        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, spinnerList);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        messagingSpinner.setAdapter(spinnerAdapter);

        checkBox_itemDue = (CheckBox) view.findViewById(R.id.messaging_itemdue_checkbox);
        checkBox_advancenotice = (CheckBox) view.findViewById(R.id.messaging_advancenotice_checkbox);
        checkBox_holdfilled = (CheckBox) view.findViewById(R.id.messaging_holdfilled_checkbox);
        checkBox_itemcheckin = (CheckBox) view.findViewById(R.id.messaging_itemcheckin_checkbox);
        checkBox_itemcheckout = (CheckBox) view.findViewById(R.id.messaging_itemcheckout_checkbox);

        checkBox_itemDue.setChecked(true);
        checkBox_advancenotice.setChecked(true);
        checkBox_holdfilled.setChecked(true);
        checkBox_itemcheckin.setChecked(true);
        checkBox_itemcheckout.setChecked(true);

        Button submitButton = (Button) view.findViewById(R.id.messaging_submit_button);
        Button cancelButton = (Button) view.findViewById(R.id.messaging_cancel_button);

        submitButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Changes submitted", Toast.LENGTH_SHORT).show();
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {

                Toast.makeText(getActivity(), "Cancel clicked", Toast.LENGTH_SHORT).show();
            }
        });


        return view;
    }

}
