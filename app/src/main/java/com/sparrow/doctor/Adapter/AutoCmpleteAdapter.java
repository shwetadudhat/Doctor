package com.sparrow.doctor.Adapter;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.sparrow.doctor.Model.City;
import com.sparrow.doctor.R;

import java.util.ArrayList;


public class AutoCmpleteAdapter extends ArrayAdapter<City> implements Filterable {

    private LayoutInflater mInflater = null;
    private Activity activity;

    private final String MY_DEBUG_TAG = "CustomerAdapter";
    private ArrayList<City> items;
    private ArrayList<City> itemsAll;
    private ArrayList<City> suggestions;
    private int viewResourceId;



    public AutoCmpleteAdapter(Activity context, ArrayList<City> items) {
        super(context, 0, items);
        activity = context;
        mInflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        this.items = items;
        this.itemsAll = (ArrayList<City>) items.clone();
        this.suggestions = new ArrayList<City>();


    }



    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public City getItem(int position) {
        return items.get(position);
    }

    public static class ViewHolder {

        public TextView title;
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
//        return super.getView(position, convertView, parent);
        ViewHolder holder;
        City village1 = getItem(position);


        if (convertView == null) {

            holder = new ViewHolder();

            convertView = mInflater.inflate(R.layout.village_item,
                    parent, false);
            holder.title = (TextView) convertView
                    .findViewById(R.id.autoComplete);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }


        holder.title.setText(village1.getPatientName());
       /* holder.title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(activity, "villageName:--"+village1.getVillage_name()+"\nvillage_id:--"+village1.getId(), Toast.LENGTH_SHORT).show();
            }
        });*/

        return convertView;
    }


    @Override
    public Filter getFilter() {
        return nameFilter;
    }

    Filter nameFilter = new Filter() {
        @Override
        public String convertResultToString(Object resultValue) {
            String str = ((City)(resultValue)).getPatientName();
            return str;
        }
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            if(constraint != null) {
                suggestions.clear();
                String txt=constraint.toString().toLowerCase();
                Log.d(MY_DEBUG_TAG,"input_string:--"+txt);
                for (City customer : itemsAll) {
                    if(customer.getPatientName().toLowerCase().startsWith(txt)){
                        suggestions.add(customer);
                    }
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = suggestions;
                filterResults.count = suggestions.size();

                return filterResults;
            } else {


                /*my code for auto clear*/
                FilterResults filterResults = new FilterResults();
                filterResults.values = itemsAll;
                filterResults.count = itemsAll.size();
                ArrayList<City> filteredList = (ArrayList<City>) filterResults.values;

                Log.d(MY_DEBUG_TAG,"resultlistsize123:---"+itemsAll.size());

                return filterResults/*new FilterResults()*/;
            }
        }
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            ArrayList<City> filteredList = (ArrayList<City>) results.values;
            if(results != null && results.count > 0) {
                clear();
                for (City c : filteredList) {
                    add(c);
                }
                notifyDataSetChanged();
            }
            Log.d(MY_DEBUG_TAG, "publishResults2: "+filteredList);


        }
    };
}