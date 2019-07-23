package com.application.fmt.ApiUtils;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import rx.Observable;

public interface GetDataService {

    @POST("users/email_exists")
    Observable<JsonElement> checkEmailExist(@Body JsonObject requestJson);

    @POST("users/mobile_exists")
    Observable<JsonElement> checkMobileExist(@Body JsonObject requestJson);

    @GET("users/country_list")
    Observable<JsonElement> getCountries();

    @POST("users/otp")
    Observable<JsonElement> sendOtp(@Body JsonObject requestJson);

    @POST("users")
    Observable<JsonElement> register(@Body JsonObject requestJson);

    @POST("users/sign_in")
    Observable<JsonElement> login(@Body JsonObject requestJson);

    @POST("users/login_otp")
    Observable<JsonElement> loginOtp(@Body JsonObject requestJson);

    @Multipart
    @POST("users/face_recognition")
    Observable<JsonElement> uploadFile(@Part MultipartBody.Part part, @Part("mobile") RequestBody user);
}