package com.sparrow.doctor.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.sparrow.doctor.Model.City;
import com.sparrow.doctor.R;

import java.util.List;

public class DeliveryMainAdapter extends RecyclerView.Adapter<DeliveryMainAdapter.ViewHolder>
{
    Context context;
    List<City> dataDisplays;
    String sms= "";//The message you want to text to the phone

    public DeliveryMainAdapter(Context context, List<City> dataDisplays) {
        this.context = context;
        this.dataDisplays = dataDisplays;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View listItem = inflater.inflate(R.layout.item_appointmentview, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
       holder.tv_starttime.setText(dataDisplays.get(position).getStartTime());
       holder.tv_starttime1.setText(dataDisplays.get(position).getStartTime());
       holder.tv_endtime.setText(dataDisplays.get(position).getEndTime());
       holder.tv_endtime1.setText(dataDisplays.get(position).getEndTime());
       holder.tv_prodname.setText(dataDisplays.get(position).getPatientName()   );
       holder.tv_procvname.setText(dataDisplays.get(position).getProcedure());


    }

    @Override
    public int getItemCount() {
        return dataDisplays.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tv_starttime, tv_starttime1, tv_endtime,
                tv_endtime1, tv_prodname,tv_procvname,
                tv_completed;
        ImageView iv_watch;



        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            this.tv_starttime = itemView.findViewById(R.id.tv_starttime);
            this.tv_starttime1 = itemView.findViewById(R.id.tv_starttime1);
            this.tv_endtime = itemView.findViewById(R.id.tv_endtime);
            this.tv_endtime1 = itemView.findViewById(R.id.tv_endtime1);
            this.tv_prodname = itemView.findViewById(R.id.tv_prodname);
            this.tv_procvname = itemView.findViewById(R.id.tv_procvname);
            this.tv_completed = itemView.findViewById(R.id.tv_completed);

            this.iv_watch=itemView.findViewById(R.id.iv_watch);
        }
    }
}
