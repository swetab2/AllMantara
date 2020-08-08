package com.god.allmantara.Fragment.Festival;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.god.allmantara.Adapter.Arti.ArtiContentAdapter;
import com.god.allmantara.Adapter.Festival.FestivalAdapter;
import com.god.allmantara.Constant;
import com.god.allmantara.Model.Arti.ArtiContentModel;
import com.god.allmantara.Model.Festival.FestivalModel;
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
public class FestivalFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    private Spinner spinner;
    private Button button;


    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private FestivalAdapter adapterA;
    private Api apiAccess;
    private RetrofitClient retrofitClient;
    private ProgressDialog progressDialog;
    private GridLayoutManager gridLayoutManager;

    private Spinner mSpinner;

    private AdapterView view;

    private AdView adView;
    private AdRequest adRequest;
    private String userid;

    public FestivalFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_festival, container, false);
        recyclerView = v.findViewById(R.id.rcv);
        layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        //gridLayoutManager = new GridLayoutManager(getContext(), 2, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(retrofitClient.BaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiAccess = retrofit.create(Api.class);


        spinner = v.findViewById(R.id.spinner1);
        button = v.findViewById(R.id.button);

        spinner.setOnItemSelectedListener(this);

        // Spinner Drop down elements
        List<String> categories = new ArrayList<String>();
        categories.add("January");
        categories.add(" Febuary");
        categories.add(" March");
        categories.add(" April");
        categories.add(" May ");
        categories.add(" June");
        categories.add(" July");
        categories.add(" August");
        categories.add(" September ");
        categories.add(" October");
        categories.add(" November");
        categories.add(" December");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);
   /*     button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(MainActivity.this,SecondActivity.class);
                intent.putExtra("data", String.valueOf(spinner.getSelectedItem()));
                Toast.makeText(getContext(), "Selected: " + spinner.getSelectedItem(), Toast.LENGTH_LONG).show();
                int item = Integer.parseInt(spinner.getSelectedItem().toString());
                Toast.makeText(getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
                Log.d(Constant.TAGS, "position no : " + item);
            }
        });*/
        return v;
    }

    // Intent intent= new Intent(MainActivity.
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String item = adapterView.getItemAtPosition(i).toString();
        Toast.makeText(getContext(), "Selected: " + i, Toast.LENGTH_LONG).show();
        userid = Integer.toString(i);


        String s = new String(userid);

        //  Toast.makeText(getContext(), "userid: " + userid, Toast.LENGTH_LONG).show();


        Toast.makeText(getContext(), "userid: " + userid, Toast.LENGTH_LONG).show();
        progressDialog = ProgressBarAccess.createProgressDialog(getActivity());
        Call<List<FestivalModel>> call = apiAccess.getFestival(userid);
        call.enqueue(new Callback<List<FestivalModel>>() {
            @Override
            public void onResponse(Call<List<FestivalModel>> call, Response<List<FestivalModel>> response) {
                progressDialog.dismiss();
                final List<FestivalModel> modelList = response.body();
                Log.d(Constant.TAGS, "List :" + modelList.size());
                Log.d(Constant.TAGS, "BrandId :" + modelList.size());
                adapterA = new FestivalAdapter(getContext(), (ArrayList<FestivalModel>) modelList);
                recyclerView.setAdapter(adapterA);

                //  Toast.makeText(getContext(), "Selected: " + response, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<List<FestivalModel>> call, Throwable t) {
                Toast.makeText(getContext(), "Selected: " + t, Toast.LENGTH_LONG).show();
            }
        });


    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }


}


