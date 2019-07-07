package com.application.fmt.Models;

import android.app.Application;

import androidx.databinding.BaseObservable;

import com.application.fmt.Constants.ErrorMessages;
import com.application.fmt.utils.CommonFunctions;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.raywenderlich.android.validatetor.ValidateTor;

public class User extends BaseObservable {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("profile_image")
    @Expose
    private ProfileImage profileImage;
    @SerializedName("mobile")
    @Expose
    private String mobile;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("age")
    @Expose
    private Integer age;
    @SerializedName("authentication_token")
    @Expose
    private String authenticationToken;
    @SerializedName("otp")
    @Expose
    private String otp;



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ProfileImage getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(ProfileImage profileImage) {
        this.profileImage = profileImage;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getAuthenticationToken() {
        return authenticationToken;
    }

    public void setAuthenticationToken(String authenticationToken) {
        this.authenticationToken = authenticationToken;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public boolean loginOtpValidation(Application app) {
        ValidateTor validateTor = new ValidateTor();
        String errorMsg = "";
        if (validateTor.isEmpty(getEmail())) {
            errorMsg = ErrorMessages.ERROR_EMAIL_EMPTY;
        } else if (!validateTor.isEmail(getEmail())) {
            errorMsg = ErrorMessages.ERROR_INVALID_EMAIL;
        }
        if (errorMsg.length() > 0) {
            CommonFunctions.getInstance().showErrorMessage(app, errorMsg);
        }

        return errorMsg.length() == 0;
    }

    public boolean loginValidation(Application app) {
        ValidateTor validateTor = new ValidateTor();
        String errorMsg = "";

        if (validateTor.isEmpty(getOtp())) {
            errorMsg = ErrorMessages.ERROR_OTP_EMPTY;
        } else if (!validateTor.isAtleastLength(getOtp(), 4)) {
            errorMsg = ErrorMessages.ERROR_INVALID_OTP;
        }
        if (errorMsg.length() > 0) {
            CommonFunctions.getInstance().showErrorMessage(app, errorMsg);
        }

        return errorMsg.length() == 0;
    }
}