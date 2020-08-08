package com.god.allmantara.Adapter.Festival;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.god.allmantara.Constant;
import com.god.allmantara.Model.Festival.FestivalModel;
import com.god.allmantara.R;

import java.util.ArrayList;

public class FestivalAdapter extends RecyclerView.Adapter<FestivalAdapter.MyviewHolder> {

    private Context context;
    private ArrayList<FestivalModel> modellist;


    public FestivalAdapter(Context context, ArrayList<FestivalModel> modellist) {
        this.context = context;
        this.modellist = modellist;
    }

    @NonNull
    @Override
    public MyviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.festival_model, parent, false);
        MyviewHolder myviewHolder = new MyviewHolder(v);
        return myviewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyviewHolder holder, int position) {
        final FestivalModel tmodel = modellist.get(position);

       // Log.d(Constant.TAGS, "BrandId :" + tmodel.getFname());


        holder.tvdaydate.setText(tmodel.getHindidate());
        holder.tvfes.setText(tmodel.getFname());
        holder.tvfesdes.setText(tmodel.getImpday());

    }

    @Override
    public int getItemCount() {
        return modellist.size();
    }

    public class MyviewHolder extends RecyclerView.ViewHolder {
        private TextView tvdaydate, tvfes, tvfesdes;

        public MyviewHolder(@NonNull View itemView) {
            super(itemView);
            tvdaydate = itemView.findViewById(R.id.tv_fes_day_date);
            tvfes = itemView.findViewById(R.id.tv_fes_name);
            tvfesdes = itemView.findViewById(R.id.tv_fes_des);
        }
    }
}
