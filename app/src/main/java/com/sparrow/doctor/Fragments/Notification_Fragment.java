package com.sparrow.doctor.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.sparrow.doctor.R;

public class Notification_Fragment extends BaseFragment{

    public Notification_Fragment() {
        // Required empty public -constructor

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);




        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        llSearch=view.findViewById(R.id.llSearch);
//
//        if (ConnectivityReceiver.isConnected()) {
//            showDialog("");
//            LoadHomeData();
//
//        } else {
////            CommonFunctions.showInternetConnectionDialog(getContext());
//
//        }

    }
}
