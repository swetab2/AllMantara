package com.god.allmantara.Fragment.GodArati;


import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.god.allmantara.Adapter.Arti.ArtiAdapter;
import com.god.allmantara.Adapter.Mantra.AllGodAdapter;
import com.god.allmantara.Constant;
import com.god.allmantara.Model.Arti.ArtiModel;
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
public class ArtiFragment extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private ArtiAdapter adapterA;
    private Api apiAccess;
    private RetrofitClient retrofitClient;
    private ProgressDialog progressDialog;
    private GridLayoutManager gridLayoutManager;


    private AdView adView;
    private AdRequest adRequest;


    public ArtiFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_arti, container, false);


        adView = v.findViewById(R.id.ad_view);

        adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);

        recyclerView = v.findViewById(R.id.rcv);
        layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
       // gridLayoutManager = new GridLayoutManager(getContext(), 2, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(retrofitClient.BaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiAccess = retrofit.create(Api.class);
        arti_init();
        return v;
    }


    public void arti_init() {
        progressDialog = ProgressBarAccess.createProgressDialog(getActivity());
        Call<List<ArtiModel>> call = apiAccess.getAllArti("1");
        call.enqueue(new Callback<List<ArtiModel>>() {
            @Override
            public void onResponse(Call<List<ArtiModel>> call, Response<List<ArtiModel>> response) {
                progressDialog.dismiss();
                final List<ArtiModel> modelList = response.body();

                //  Log.d(Constant.TAGS, "List :" + modelList.size());
                Log.d(Constant.TAGS, "BrandId :" + modelList.size());
                adapterA = new ArtiAdapter(getContext(), (ArrayList<ArtiModel>) modelList);
                recyclerView.setAdapter(adapterA);

            }

            @Override
            public void onFailure(Call<List<ArtiModel>> call, Throwable t) {

            }
        });
    }


}
