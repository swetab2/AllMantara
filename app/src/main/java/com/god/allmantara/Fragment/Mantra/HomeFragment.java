package com.god.allmantara.Fragment.Mantra;


import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.god.allmantara.Adapter.Arti.ArtiContentAdapter;
import com.god.allmantara.Adapter.Banner.BaseBannerAdapter;
import com.god.allmantara.Adapter.DailyPoojaAdapter.PoojaAdapter;
import com.god.allmantara.Adapter.Mantra.AllGodAdapter;
import com.god.allmantara.Constant;
import com.god.allmantara.Fragment.Festival.FestivalFragment;
import com.god.allmantara.Fragment.GodArati.ArtiFragment;
import com.god.allmantara.Model.Festival.FestivalModel;
import com.god.allmantara.Model.Mantra.AllGodModel;
import com.god.allmantara.Model.TodayPooja.PoojaModel;
import com.god.allmantara.NetworkLibrary.Api;
import com.god.allmantara.NetworkLibrary.RetrofitClient;
import com.god.allmantara.ProgressBarAccess;
import com.god.allmantara.R;
import com.yyydjk.library.BannerLayout;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {
    private TextView tv_mantra, tv_arti, tv_festival;
    private ImageView banner;
    private ViewFlipper viewFlipper;


    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private PoojaAdapter adapterA;
    private Api apiAccess;
    private RetrofitClient retrofitClient;
    private ProgressDialog progressDialog;
    private GridLayoutManager gridLayoutManager;


    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_mantra, container, false);
        tv_mantra = v.findViewById(R.id.mantra);
        tv_arti = v.findViewById(R.id.tv_arti);
        tv_festival = v.findViewById(R.id.festival);


        viewFlipper = v.findViewById(R.id.vf);

        tv_arti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArtiFragment artiFragment = new ArtiFragment();
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.container, artiFragment)
                        .addToBackStack(null)
                        .commit();
            }
        });


        tv_mantra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MantraFragment mantraFragment = new MantraFragment();
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.container, mantraFragment)
                        .addToBackStack(null)
                        .commit();
            }
        });

        tv_festival.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FestivalFragment festivalFragment = new FestivalFragment();
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.container, festivalFragment)
                        .addToBackStack(null)
                        .commit();
            }
        });


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(retrofitClient.BaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiAccess = retrofit.create(Api.class);
        todatMantra();

        recyclerView = v.findViewById(R.id.rcv);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, true));




        int[] images = {R.drawable.ganesh, R.drawable.hanumanjee, R.drawable.ganesh, R.drawable.hanumanjee, R.drawable.ganesh};

        for (int i = 0; i < images.length; i++) {
            // create the object of ImageView
            ImageView imageView = new ImageView(getContext());
            imageView.setImageResource(images[i]); // set image in ImageView
            viewFlipper.addView(imageView); // add the created ImageView in ViewFlipper
        }


        // Animation in = AnimationUtils.loadAnimation(getContext(), android.R.anim.slide_in_left);
        //  Animation out = AnimationUtils.loadAnimation(getContext(), android.R.anim.slide_out_right);
        // set the animation type's to ViewFlipper
        //  viewFlipper.setInAnimation(in);
        //  viewFlipper.setOutAnimation(out);
        // set interval time for flipping between views
        viewFlipper.setFlipInterval(10000);
        // set auto start for flipping between views
        viewFlipper.setAutoStart(true);


        return v;
    }


    public void todatMantra() {
      //  progressDialog = ProgressBarAccess.createProgressDialog(getActivity());
        Call<List<PoojaModel>> call = apiAccess.getToday("1");
        call.enqueue(new Callback<List<PoojaModel>>() {
            @Override
            public void onResponse(Call<List<PoojaModel>> call, Response<List<PoojaModel>> response) {
            //    progressDialog.dismiss();


                final List<PoojaModel> modelList = response.body();
                //     BrandSeriesModel brandModel = new BrandSeriesModel("1","sam","abc");
                //  Log.d(Constant.TAGS, "List :" + modelList.size());
                Log.d(Constant.TAGS, "BrandId :" + modelList.size());
                adapterA = new PoojaAdapter(getContext(), (ArrayList<PoojaModel>) modelList);
                recyclerView.setAdapter(adapterA);
            }

            @Override
            public void onFailure(Call<List<PoojaModel>> call, Throwable t) {

            }
        });
    }
}
