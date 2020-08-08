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
import android.widget.ImageView;

import com.god.allmantara.Adapter.Arti.ArtiAdapter;
import com.god.allmantara.Adapter.Arti.ArtiContentAdapter;
import com.god.allmantara.Constant;
import com.god.allmantara.Model.Arti.ArtiContentModel;
import com.god.allmantara.Model.Arti.ArtiModel;
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
public class Arati_Content_Fragment extends Fragment {
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private ArtiContentAdapter adapterA;
    private Api apiAccess;
    private RetrofitClient retrofitClient;
    private ProgressDialog progressDialog;
    private GridLayoutManager gridLayoutManager;


    private AdView adView;
    private AdRequest adRequest;


    private String articontent;
    private static String bitmap = "thumbnailUrl";
    private ImageView imageView;


    public Arati_Content_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_arati__content_, container, false);

        //  imageView = v.findViewById(R.id.img);
        articontent = getArguments().getString("id");


        Bundle bundle = this.getArguments();
        String page = bundle.getString("images", bitmap);

        //   ImageLoader imageLoader = AppController.getInstance().getImageLoader();
        //    imageView.setImageUrl(page, imageLoader);

        // ImageLoader imageLoader = AppController.getInstance().getImageLoader();


        recyclerView = v.findViewById(R.id.rcv);
        layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        //gridLayoutManager = new GridLayoutManager(getContext(), 2, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(retrofitClient.BaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiAccess = retrofit.create(Api.class);
        allContent();
        return v;
    }

    public void allContent() {
        progressDialog = ProgressBarAccess.createProgressDialog(getActivity());
        Call<List<ArtiContentModel>> call = apiAccess.getArtiContent(articontent);
        call.enqueue(new Callback<List<ArtiContentModel>>() {
            @Override
            public void onResponse(Call<List<ArtiContentModel>> call, Response<List<ArtiContentModel>> response) {

                progressDialog.dismiss();
                final List<ArtiContentModel> modelList = response.body();

                //  Log.d(Constant.TAGS, "List :" + modelList.size());
                Log.d(Constant.TAGS, "BrandId :" + modelList.size());
                adapterA = new ArtiContentAdapter(getContext(), (ArrayList<ArtiContentModel>) modelList);
                recyclerView.setAdapter(adapterA);

            }

            @Override
            public void onFailure(Call<List<ArtiContentModel>> call, Throwable t) {

            }
        });
    }


}
