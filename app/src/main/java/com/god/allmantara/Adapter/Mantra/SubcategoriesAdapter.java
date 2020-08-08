package com.god.allmantara.Adapter.Mantra;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.god.allmantara.Constant;
import com.god.allmantara.Fragment.Mantra.ContentFragment;
import com.god.allmantara.Model.Mantra.SubCategoriesModel;
import com.god.allmantara.R;

import java.util.ArrayList;

public class SubcategoriesAdapter extends RecyclerView.Adapter<SubcategoriesAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<SubCategoriesModel> modellist;


    public SubcategoriesAdapter(Context context, ArrayList<SubCategoriesModel> modellist) {
        this.context = context;
        this.modellist = modellist;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.subcategories_model, parent, false);

        MyViewHolder mv = new MyViewHolder(v);
        return mv;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final SubCategoriesModel tmodel = modellist.get(position);
        holder.tv_subcategories.setText(tmodel.getMantraspecficname());

        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.d(Constant.TAGS, "BrandId is Here" + tmodel.getRefmantaspecfic());
                int i = Integer.parseInt(tmodel.getRefmantaspecfic());
                AppCompatActivity activity = (AppCompatActivity) view.getContext();
                ContentFragment myFragment = new ContentFragment();
                Bundle args = new Bundle();
                args.putString("id", tmodel.getMantraspecficid());
                myFragment.setArguments(args);
                activity.getSupportFragmentManager().
                        beginTransaction().
                        replace(R.id.container, myFragment)
                        .addToBackStack(null).commit();


            }
        });
    }

    @Override
    public int getItemCount() {
        return modellist.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_subcategories;
        private RelativeLayout relativeLayout;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_subcategories = itemView.findViewById(R.id.tv_subcategories);
            relativeLayout = itemView.findViewById(R.id.relativeLayout);
        }
    }
}
