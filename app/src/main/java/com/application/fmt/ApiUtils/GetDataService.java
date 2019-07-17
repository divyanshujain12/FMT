package com.application.fmt.ApiUtils;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import rx.Observable;

public interface GetDataService {
    @Headers({"Content-Type: application/json",
            "device-type:android"})
    @POST("users/email_exists")
    Observable<JsonElement> checkEmailExist(@Body JsonObject requestJson);

    @Headers({"Content-Type: application/json",
            "device-type:android"})
    @POST("users/mobile_exists")
    Observable<JsonElement> checkMobileExist(@Body JsonObject requestJson);

    @Headers({"Content-Type: application/json",
            "device-type:android"})
    @GET("users/country_list")
    Observable<JsonElement> getCountries();

    @Headers({"Content-Type: application/json",
            "device-type:android"})
    @POST("users/otp")
    Observable<JsonElement> sendOtp(@Body JsonObject requestJson);

    @Headers({"Content-Type: application/json",
            "device-type:android"})
    @POST("users")
    Observable<JsonElement> register(@Body JsonObject requestJson);

    @Headers({"Content-Type: application/json",
            "device-type:android"})
    @POST("users/sign_in")
    Observable<JsonElement> login(@Body JsonObject requestJson);

    @Headers({"Content-Type: application/json",
            "device-type:android"})
    @POST("users/login_otp")
    Observable<JsonElement> loginOtp(@Body JsonObject requestJson);
}