package com.god.allmantara.Adapter.Arti;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.god.allmantara.Model.Arti.ArtiContentModel;
import com.god.allmantara.Model.Arti.ArtiModel;
import com.god.allmantara.R;

import java.util.ArrayList;

public class ArtiContentAdapter extends RecyclerView.Adapter<ArtiContentAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<ArtiContentModel> modellist;


    public ArtiContentAdapter(Context context, ArrayList<ArtiContentModel> modellist) {
        this.context = context;
        this.modellist = modellist;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.artimodel, parent, false);
        MyViewHolder mv = new MyViewHolder(v);
        return mv;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        final ArtiContentModel tmodel = modellist.get(position);
        holder.tvarticontent.setText(tmodel.getArticontent());
    }

    @Override
    public int getItemCount() {
        return modellist.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView tvarticontent;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvarticontent = itemView.findViewById(R.id.tv_arti_content);
        }
    }
}
