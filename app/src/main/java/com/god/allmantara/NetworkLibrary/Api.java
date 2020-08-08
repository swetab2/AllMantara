package com.god.allmantara.NetworkLibrary;

import com.god.allmantara.Model.Arti.ArtiContentModel;
import com.god.allmantara.Model.Arti.ArtiModel;
import com.god.allmantara.Model.Festival.FestivalModel;
import com.god.allmantara.Model.Mantra.AllGodModel;
import com.god.allmantara.Model.Mantra.ContentModel;
import com.god.allmantara.Model.Mantra.SubCategoriesModel;
import com.god.allmantara.Model.TodayPooja.PoojaModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface Api {
    @FormUrlEncoded
    @POST("godmantra/godmantra.php")
    Call<List<AllGodModel>> gellAll(
            @Field("hindugodid") String hindugodid
    );


    @FormUrlEncoded
    @POST("godmantra/mspecfic.php")
    Call<List<SubCategoriesModel>> getSubCateories(
            @Field("godid") String godid
    );

    @FormUrlEncoded
    @POST("godmantra/content.php")
    Call<List<ContentModel>> getAllContent(
            @Field("allcontent") String allcontent
    );

    @FormUrlEncoded
    @POST("godmantra/agodname.php")
    Call<List<ArtiModel>> getAllArti(
            @Field("artiid") String artiid
    );

    @FormUrlEncoded
    @POST("godmantra/articontent.php")
    Call<List<ArtiContentModel>> getArtiContent(
            @Field("articontent") String articontent
    );

    @FormUrlEncoded
    @POST("godmantra/festival.php")
    Call<List<FestivalModel>> getFestival(
            @Field("fesproductionid") String fesproductionid
    );

    @FormUrlEncoded
    @POST("godmantra/today.php")
    Call<List<PoojaModel>> getToday(
            @Field("dayproductionid") String dayproductionid
    );


}
