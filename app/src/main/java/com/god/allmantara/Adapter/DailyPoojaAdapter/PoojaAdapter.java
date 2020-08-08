package com.god.allmantara.Adapter.DailyPoojaAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.god.allmantara.Model.TodayPooja.PoojaModel;
import com.god.allmantara.R;

import java.util.ArrayList;

public class PoojaAdapter extends RecyclerView.Adapter<PoojaAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<PoojaModel> modellist;


    public PoojaAdapter(Context context, ArrayList<PoojaModel> modellist) {
        this.context = context;
        this.modellist = modellist;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.poohja_day_model, parent, false);
        return new PoojaAdapter.MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final PoojaModel tmodel = modellist.get(position);
        holder.panchang.setText(tmodel.getTodayday());
        holder.fest.setText(tmodel.getTodayfestival());
        holder.special.setText(tmodel.getTodayfestival());
    }

    @Override
    public int getItemCount() {
        return modellist.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView panchang, fest, special;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            panchang = itemView.findViewById(R.id.tv_day_month);
            fest = itemView.findViewById(R.id.tv_festival);
            special = itemView.findViewById(R.id.tv_specialday);
        }
    }
}
