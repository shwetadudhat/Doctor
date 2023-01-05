package com.sparrow.doctor.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;

import com.sparrow.doctor.Model.Detail;
import com.sparrow.doctor.R;

import java.util.ArrayList;


public class ProcedureAdapter1 extends ArrayAdapter<Detail> {
    private final String MY_DEBUG_TAG = "CustomerAdapter";
    private ArrayList<Detail> items;
    private ArrayList<Detail> itemsAll;
    private ArrayList<Detail> suggestions;
    private int viewResourceId;

    public ProcedureAdapter1(Context context, int viewResourceId, ArrayList<Detail> items) {
        super(context, viewResourceId, items);
        this.items = items;
        this.itemsAll = (ArrayList<Detail>) items.clone();
        this.suggestions = new ArrayList<Detail>();
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
        Detail customer = items.get(position);
        if (customer != null) {
            TextView customerNameLabel = (TextView) v.findViewById(R.id.tv_name);
            if (customerNameLabel != null) {
//              Log.i(MY_DEBUG_TAG, "getView Customer Name:"+customer.getName());
                customerNameLabel.setText(customer.getProductName());
            }
        }
        return v;
    }

    @Override
    public Filter getFilter() {
        return nameFilter;
    }

    Filter nameFilter = new Filter() {
        @Override
        public String convertResultToString(Object resultValue) {
            String str = ((Detail)(resultValue)).getProductName();
            return str;
        }
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            if(constraint != null) {
                suggestions.clear();
                for (Detail customer : itemsAll) {
                    if(customer.getProductName().toLowerCase().startsWith(constraint.toString().toLowerCase())){
                        suggestions.add(customer);
                    }
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = suggestions;
                filterResults.count = suggestions.size();
                return filterResults;
            } else {
                return new FilterResults();
            }
        }
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            ArrayList<Detail> filteredList = (ArrayList<Detail>) results.values;
            if(results != null && results.count > 0) {
                clear();
                for (Detail c : filteredList) {
                    add(c);
                }
                notifyDataSetChanged();
            }
        }
    };

}