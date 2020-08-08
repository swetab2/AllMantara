package com.god.allmantara.Fragment.Mantra;


import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.god.allmantara.Adapter.Mantra.AllGodAdapter;
import com.god.allmantara.Adapter.Mantra.ContentAdapter;
import com.god.allmantara.Constant;
import com.god.allmantara.Model.Arti.ArtiModel;
import com.god.allmantara.Model.Mantra.ContentModel;
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
public class ContentFragment extends Fragment {


    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private AllGodAdapter adapterA;
    private Api apiAccess;
    private RetrofitClient retrofitClient;
    private ProgressDialog progressDialog;
    private GridLayoutManager gridLayoutManager;


    private ArtiModel mContentModel;
    private AdView mAdView;
    private AdRequest adRequest;
    private String contentid;

    public ContentFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_content, container, false);
        contentid = getArguments().getString("id");

        // layout view operation
        recyclerView = v.findViewById(R.id.rcv);
        gridLayoutManager = new GridLayoutManager(getContext(), 1, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(gridLayoutManager);


      //  ImageView imageView = v.findViewById(R.id.img);


        //ads
        MobileAds.initialize(getActivity(), new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        mAdView = v.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);


        //web call
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(retrofitClient.BaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        apiAccess = retrofit.create(Api.class);

        try {
            contentMAin();
        } catch (Exception e) {


        }

        return v;
    }


    public void contentMAin() {

        progressDialog = ProgressBarAccess.createProgressDialog(getActivity());
        Call<List<ContentModel>> call = apiAccess.getAllContent(contentid);
        call.enqueue(new Callback<List<ContentModel>>() {
            @Override
            public void onResponse(Call<List<ContentModel>> call, Response<List<ContentModel>> response) {

                final Handler h = new Handler() {
                    @Override
                    public void handleMessage(Message message) {
                        progressDialog.dismiss();
                        Toast.makeText(getContext(), "Please Check your network Connectivityz", Toast.LENGTH_SHORT).show();
                    }
                };

                h.sendMessageDelayed(new Message(), 1000);


                final List<ContentModel> modelList = response.body();
                ContentAdapter contentAdapter = new ContentAdapter(getContext(), (ArrayList<ContentModel>) modelList);
                recyclerView.setAdapter(contentAdapter);
                //   Toast.makeText(getContext(), "Sucess" + modelList, Toast.LENGTH_SHORT).show();
                Log.d(Constant.TAGS, "ModelList" + modelList);

            }

            @Override
            public void onFailure(Call<List<ContentModel>> call, Throwable t) {

            }
        });


    }

}
