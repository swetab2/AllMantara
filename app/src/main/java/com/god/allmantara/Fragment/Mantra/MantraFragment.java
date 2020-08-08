package com.god.allmantara.Fragment.Mantra;


import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.god.allmantara.Adapter.Mantra.AllGodAdapter;
import com.god.allmantara.Constant;
import com.god.allmantara.Model.Mantra.AllGodModel;
import com.god.allmantara.NetworkLibrary.Api;
import com.god.allmantara.NetworkLibrary.RetrofitClient;
import com.god.allmantara.ProgressBarAccess;
import com.god.allmantara.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

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
public class MantraFragment extends Fragment {


    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private AllGodAdapter adapterA;
    private Api apiAccess;
    private RetrofitClient retrofitClient;
    private ProgressDialog progressDialog;
    private GridLayoutManager gridLayoutManager;


    private AdView adView;
    private AdRequest adRequest;


    public MantraFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_mantra2, container, false);

        adView = v.findViewById(R.id.ad_view);

        adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);

        recyclerView = v.findViewById(R.id.rcv);
        //layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        gridLayoutManager = new GridLayoutManager(getContext(), 2, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(gridLayoutManager);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(retrofitClient.BaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiAccess = retrofit.create(Api.class);
        init();
        //   Log.d(Constant.TAGS, "Working outside working");
        return v;
    }

    public void init() {

        progressDialog = ProgressBarAccess.createProgressDialog(getActivity());
        Call<List<AllGodModel>> call = apiAccess.gellAll("1");
        call.enqueue(new Callback<List<AllGodModel>>() {
            @Override
            public void onResponse(Call<List<AllGodModel>> call, Response<List<AllGodModel>> response) {

                progressDialog.dismiss();


                final List<AllGodModel> modelList = response.body();
                //     BrandSeriesModel brandModel = new BrandSeriesModel("1","sam","abc");
                //  Log.d(Constant.TAGS, "List :" + modelList.size());
                Log.d(Constant.TAGS, "BrandId :" + modelList.size());
                adapterA = new AllGodAdapter(getContext(), (ArrayList<AllGodModel>) modelList);
                recyclerView.setAdapter(adapterA);


            }

            @Override
            public void onFailure(Call<List<AllGodModel>> call, Throwable t) {

            }
        });

    }


    @Override
    public void onPause() {
        if (adView != null) {
            adView.pause();
        }
        super.onPause();
    }


    @Override
    public void onResume() {
        if (adView != null) {
            adView.pause();
        }
        super.onResume();
    }


    @Override
    public void onDestroy() {
        if (adView != null) {
            adView.pause();
        }
        super.onDestroy();
    }
}
