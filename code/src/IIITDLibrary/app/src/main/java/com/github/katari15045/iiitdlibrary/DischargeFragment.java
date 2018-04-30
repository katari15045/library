package com.github.katari15045.iiitdlibrary;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;


public class DischargeFragment extends Fragment {

    public DischargeFragment() {
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
        View view =  inflater.inflate(R.layout.fragment_discharge, container, false);

        Button dischargeButton = (Button) view.findViewById(R.id.discharge_submit_button);
        dischargeButton.setOnClickListener(new View.OnClickListener()
        {
            AlertDialog.Builder dialogBox;
            @Override
            public void onClick(View view)
            {
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
                {
                    dialogBox = new AlertDialog.Builder(getActivity(), android.R.style.Theme_Material_Dialog_Alert);
                }
                else
                {
                    dialogBox = new AlertDialog.Builder(getActivity());
                }

                dialogBox.setTitle("Ask for a discharge")
                        .setMessage("Are you sure?")
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener(){
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(getActivity(), "Request submitted", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        })
                        .show();
            }
        });

        return view;
    }
}
