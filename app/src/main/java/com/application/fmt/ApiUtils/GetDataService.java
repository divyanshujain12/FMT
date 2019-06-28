package com.application.fmt.ApiUtils;

import com.application.fmt.Models.CheckOnlyModel;
import com.application.fmt.Models.CountriesModel;
import com.application.fmt.Models.LoginUserProfileModel;
import com.application.fmt.Models.SignupUserProfileModel;


import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import rx.Observable;

public interface GetDataService {
    @Headers("Content-Type: application/json")
    @POST("email_exists")
    Observable<CheckOnlyModel> checkEmailExist(@Body String requestJson);

    @Headers("Content-Type: application/json")
    @POST("mobile_exists")
    Observable<CheckOnlyModel> checkMobileExist(@Body String requestJson);

    @GET("country_list")
    Observable<CountriesModel> getCountries();

    @Headers("Content-Type: application/json")
    @POST("otp")
    Observable<CheckOnlyModel> sendOtp(@Body String requestJson);

    @Headers("Content-Type: application/json")
    @POST("users")
    Observable<SignupUserProfileModel> register(@Body String requestJson);

    @Headers("Content-Type: application/json")
    @POST("sign_in")
    Observable<LoginUserProfileModel> login(@Body String requestJson);
}