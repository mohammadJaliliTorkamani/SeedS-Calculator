package com.seeds.seeds_calculator.Network;

import com.seeds.seeds_calculator.Model.MoreApp;
import com.seeds.seeds_calculator.Model.NagScreen;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ClientApi {
    @GET("more_apps.php")
    Call<List<MoreApp>> getMoreAppLiost();

    @GET("nag_Screen.php")
    Call<NagScreen> getNagScreen();
}