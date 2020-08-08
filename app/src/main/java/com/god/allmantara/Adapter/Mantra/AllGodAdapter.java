package com.god.allmantara.Adapter.Mantra;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.god.allmantara.Constant;
import com.god.allmantara.Fragment.Mantra.SubCategoriesFragment;
import com.god.allmantara.Model.Mantra.AllGodModel;
import com.god.allmantara.R;

import java.util.ArrayList;

public class AllGodAdapter extends RecyclerView.Adapter<AllGodAdapter.MyviewHolder> {


    //  private BrandSeries brandSeries;
    private Context context;
    private ArrayList<AllGodModel> modellist;


    public AllGodAdapter(Context context, ArrayList<AllGodModel> modellist) {
        this.context = context;
        this.modellist = modellist;
    }


    @NonNull
    @Override
    public MyviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.allgodmodel, parent, false);
        MyviewHolder mv = new MyviewHolder(v);
        return mv;
    }

    @Override
    public void onBindViewHolder(@NonNull MyviewHolder holder, int position) {
        final AllGodModel tmodel = modellist.get(position);
        holder.godname.setText(tmodel.getGodname());

        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(Constant.TAGS, "BrandId is Here" + tmodel.getGodid());
                int i = Integer.parseInt(tmodel.getGodid());

                AppCompatActivity activity = (AppCompatActivity) view.getContext();

                Toast.makeText(context, "i count else - " + i, Toast.LENGTH_LONG).show();
                SubCategoriesFragment myFragment = new SubCategoriesFragment();
                Bundle args = new Bundle();

                args.putString("id", tmodel.getGodid());

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

    public class MyviewHolder extends RecyclerView.ViewHolder {

        private TextView godname;
        private ImageView godImage;
        private RelativeLayout relativeLayout;

        public MyviewHolder(@NonNull View itemView) {
            super(itemView);

            godname = itemView.findViewById(R.id.tv_brand_model);
            godImage = itemView.findViewById(R.id.image_brand_model);
            relativeLayout = itemView.findViewById(R.id.relativeLayout);

        }
    }
}
