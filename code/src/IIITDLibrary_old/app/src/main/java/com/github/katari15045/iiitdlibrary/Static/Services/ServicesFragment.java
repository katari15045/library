package com.github.katari15045.iiitdlibrary.Static.Services;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.katari15045.iiitdlibrary.Main.NavDrawer;
import com.github.katari15045.iiitdlibrary.Misc.Global;
import com.github.katari15045.iiitdlibrary.R;

import java.util.ArrayList;
import java.util.Arrays;

public class ServicesFragment extends Fragment implements
        ServicesRecyclerAdapter.serviceTextViewClickListener {

    private View view = null;
    private static String title = null;
    RecyclerView servicesRV;
    ServicesRecyclerAdapter servicesRecyclerAdapter;
    ArrayList<String> serviceNames;
    ArrayList<String> serviceDespcritions;

    public ServicesFragment(){
        title = Global.context.getResources().
                getString(R.string.nav_drawer_services_title);
    }

    @Override
    public void onResume() {
        Log.d("SAK", "ServicesFragment::onResume()");
        super.onResume();
        NavDrawer.hideItem(2);
        ((AppCompatActivity) Global.context).getSupportActionBar().setTitle(title);
    }

    @Override
    public void onPause() {
        Log.d("SAK", "ServicesFragment::onPause()");
        super.onPause();
        NavDrawer.showItem(2);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        Log.d("SAK", "ServicesFragment::onCreateView()");
        view = inflater.inflate(R.layout.fragment_services, null);
        servicesRV = view.findViewById(R.id.servicesRecyclerView);

        serviceNames = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.serviceNames)));
        serviceDespcritions = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.servicesDescriptions)));

        servicesRecyclerAdapter = new ServicesRecyclerAdapter(serviceNames,view.getContext());
        servicesRecyclerAdapter.setTextViewClickListener(this);

        servicesRV.setAdapter(servicesRecyclerAdapter);
        servicesRV.setLayoutManager(new LinearLayoutManager(Global.context));
        return view;
    }

    @Override
    public void onServiceTextClickListener(int position) {
        final Dialog dialog = new Dialog(Global.context);
        dialog.setContentView(R.layout.service_custom_dialog);

        TextView titleTextView = (TextView)dialog.findViewById(R.id.customDialogTitleTV);
        titleTextView.setText(serviceNames.get(position));

        TextView descTextView = (TextView)dialog.findViewById(R.id.customDialogDescriptionTV);
        descTextView.setText(serviceDespcritions.get(position));
        descTextView.setMovementMethod(LinkMovementMethod.getInstance());

        dialog.show();
    }

    public static String getTitle(){
        return title;
    }
}