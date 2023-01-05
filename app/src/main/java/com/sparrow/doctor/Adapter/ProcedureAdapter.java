package com.sparrow.doctor.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.sparrow.doctor.R;

import java.util.ArrayList;


public class ProcedureAdapter extends ArrayAdapter<String> {

    String[] items;
    private int viewResourceId;

    public ProcedureAdapter(Context context, int viewResourceId, String[] items) {
        super(context, viewResourceId, items);
        this.items = items;
        this.viewResourceId = viewResourceId;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater vi = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = vi.inflate(R.layout.design_product, null);
//            v = vi.inflate(viewResourceId, null);
//            vi = vi.inflate(R.layout.customerNameLabel, null);
        }


            TextView customerNameLabel = (TextView) v.findViewById(R.id.tv_name);
            if (customerNameLabel != null) {
//              Log.i(MY_DEBUG_TAG, "getView Customer Name:"+customer.getName());
                customerNameLabel.setText(items[position]);
            }

        return v;
    }

}