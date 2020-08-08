package com.god.allmantara.Adapter.Arti;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import com.god.allmantara.Activity.VideoActivity;
import com.god.allmantara.Constant;
import com.god.allmantara.Fragment.GodArati.Arati_Content_Fragment;
import com.god.allmantara.Fragment.Mantra.SubCategoriesFragment;
import com.god.allmantara.Fragment.MpFourFragment;
import com.god.allmantara.Model.Arti.ArtiModel;
import com.god.allmantara.Model.Mantra.AllGodModel;
import com.god.allmantara.R;

import java.util.ArrayList;

public class ArtiAdapter extends RecyclerView.Adapter<ArtiAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<ArtiModel> modellist;
    private static String bitmap = "thumbnailUrl";


    public ArtiAdapter(Context context, ArrayList<ArtiModel> modellist) {
        this.context = context;
        this.modellist = modellist;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.arti_model, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        final ArtiModel tmodel = modellist.get(position);
        holder.artiname.setText(tmodel.getMgodname());


        final String strWorkoutVideo = tmodel.getGodvideo();


        Glide.with(context).load(tmodel.getMgodimage()).into(holder.godImage);


        holder.tvvideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //  Intent chestIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(strWorkoutVideo));

              /*  Intent i = new Intent(context, VideoActivity.class);
                i.putExtra(Constant.STR_URL, strWorkoutVideo);
                context.startActivity(i);
*/


                AppCompatActivity activity = (AppCompatActivity) view.getContext();
                MpFourFragment myFragment = new MpFourFragment();
                Bundle args = new Bundle();
                args.putString("id", strWorkoutVideo);


                bitmap = tmodel.getMgodimage();
            //    args.putString("images", bitmap);

              //  args.putString("url", bitmap.getStringExtra("images"));

                myFragment.setArguments(args);
                activity.getSupportFragmentManager().
                        beginTransaction().
                        replace(R.id.container, myFragment)
                        .addToBackStack(null).commit();


            }
        });


        holder.ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(Constant.TAGS, "BrandId is Here" + tmodel.getMgodid());
                int i = Integer.parseInt(tmodel.getMgodid());
                AppCompatActivity activity = (AppCompatActivity) view.getContext();
                //   Toast.makeText(context, "i count else - " + i, Toast.LENGTH_LONG).show();
                Arati_Content_Fragment myFragment = new Arati_Content_Fragment();
                Bundle args = new Bundle();
                args.putString("id", tmodel.getMgodid());
                myFragment.setArguments(args);
                activity.getSupportFragmentManager().
                        beginTransaction().
                        replace(R.id.container, myFragment)
                        .addToBackStack(null).commit();

                Toast.makeText(context, "Sucess" + tmodel.getMgodimage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return modellist.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView artiname, tvvideo;
        private ImageView godImage;
        private LinearLayout ll;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            godImage = itemView.findViewById(R.id.img_arti);
            artiname = itemView.findViewById(R.id.tv_arti);
            ll = itemView.findViewById(R.id.ll);
            tvvideo = itemView.findViewById(R.id.tv_video);

            //  videoView_two = itemView.findViewById(R.id.videoView)


        }
    }
}
