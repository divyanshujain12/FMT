package com.application.fmt.ApiUtils;

import androidx.databinding.BaseObservable;

import com.application.fmt.Models.CheckOnlyModel;
import com.application.fmt.Models.CountriesModel;
import com.application.fmt.Models.LoginUserProfileModel;
import com.application.fmt.Models.SignupUserProfileModel;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;


import org.json.JSONObject;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import rx.Observable;

public interface GetDataService {
    @Headers("Content-Type: application/json")
    @POST("email_exists")
    Observable<JsonElement> checkEmailExist(@Body JsonObject requestJson);

    @Headers("Content-Type: application/json")
    @POST("mobile_exists")
    Observable<JsonElement> checkMobileExist(@Body JsonObject requestJson);

    @GET("country_list")
    Observable<JsonElement> getCountries();

    @Headers("Content-Type: application/json")
    @POST("otp")
    Observable<JsonElement> sendOtp(@Body JsonObject requestJson);

    @Headers("Content-Type: application/json")
    @POST("users")
    Observable<JsonElement> register(@Body JsonObject requestJson);

    @Headers("Content-Type: application/json")
    @POST("sign_in")
    Observable<JsonElement> login(@Body JsonObject requestJson);
}