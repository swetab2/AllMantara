package com.god.allmantara.Fragment.Mantra;


import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.god.allmantara.Adapter.Mantra.SubcategoriesAdapter;
import com.god.allmantara.Constant;
import com.god.allmantara.Model.Mantra.SubCategoriesModel;
import com.god.allmantara.NetworkLibrary.Api;
import com.god.allmantara.NetworkLibrary.RetrofitClient;
import com.god.allmantara.ProgressBarAccess;
import com.god.allmantara.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

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
public class SubCategoriesFragment extends Fragment {


    //  private AdView adView;
    private AdRequest adRequest;
    private Context mContext;

    private ProgressDialog progressDialog;

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private AdView mAdView;
    private String godid;
    private Api apiAccess;
    private RetrofitClient retrofitClient;
    private GridLayoutManager gridLayoutManager;
    private ProgressBarAccess progressBarAccess;

    public SubCategoriesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_sub_categories, container, false);
        recyclerView = v.findViewById(R.id.rcv);
        gridLayoutManager = new GridLayoutManager(getContext(), 2, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(gridLayoutManager);

        godid = getArguments().getString("id");


        //ads
        MobileAds.initialize(getActivity(), new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        mAdView = v.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);


        //webcall

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(retrofitClient.BaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        apiAccess = retrofit.create(Api.class);

        Log.d(Constant.TAGS, "Working outside working");

        try {
            subCategories();
        } catch (Exception e) {


        }
        return v;
    }

    public void subCategories() {

        progressDialog = ProgressBarAccess.createProgressDialog(getActivity());
        progressDialog.setTitle("Loading");
        progressDialog.setMessage("Glad to Wait");

        Call<List<SubCategoriesModel>> call = apiAccess.getSubCateories(godid);
        call.enqueue(new Callback<List<SubCategoriesModel>>() {
            @Override
            public void onResponse(Call<List<SubCategoriesModel>> call, Response<List<SubCategoriesModel>> response) {

                progressDialog.dismiss();


                final List<SubCategoriesModel> modelList = response.body();

                SubcategoriesAdapter subcategoriesAdapter = new SubcategoriesAdapter(getContext(), (ArrayList<SubCategoriesModel>) modelList);
                recyclerView.setAdapter(subcategoriesAdapter);
              //  Toast.makeText(getContext(), "Sucess" + modelList, Toast.LENGTH_SHORT).show();
                Log.d(Constant.TAGS, "ModelList" + modelList);
            }

            @Override
            public void onFailure(Call<List<SubCategoriesModel>> call, Throwable t) {
                Toast.makeText(getContext(), "Failed" + t, Toast.LENGTH_SHORT).show();
            }
        });

    }

}
