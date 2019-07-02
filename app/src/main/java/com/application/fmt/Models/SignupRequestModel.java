package com.application.fmt.Models;

import android.app.Application;
import android.widget.Toast;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.application.fmt.BR;
import com.application.fmt.Constants.ErrorMessages;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.raywenderlich.android.validatetor.ValidateTor;

public class SignupRequestModel extends BaseObservable {

    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("mobile")
    @Expose
    private String mobile;
    @SerializedName("gender")
    @Expose
    private boolean gender;
    @SerializedName("age")
    @Expose
    private String age;
    @SerializedName("country_code")
    @Expose
    private String countryCode;
    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("otp")
    @Expose
    private String otp;


    @Bindable
    public String getEmail() {
        return email;

    }

    public void setEmail(String email) {
        this.email = email;
        notifyPropertyChanged(BR.email);
    }

    @Bindable
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
        notifyPropertyChanged(BR.password);
    }

    @Bindable
    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
        notifyPropertyChanged(BR.mobile);
    }

    @Bindable
    public Boolean getGender() {
        return gender;
    }

    public void setGender(Boolean gender) {
        this.gender = gender;
        notifyPropertyChanged(BR.gender);
    }

    @Bindable
    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
        notifyPropertyChanged(BR.age);
    }

    @Bindable
    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
        notifyPropertyChanged(BR.countryCode);
    }

    @Bindable
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
        notifyPropertyChanged(BR.country);
    }

    @Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(BR.name);

    }

    @Bindable
    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
        notifyPropertyChanged(BR.otp);
    }

    public boolean validationForSignUpStepOne(Application app) {
        ValidateTor validateTor = new ValidateTor();
        String errorMsg = "";
        if (validateTor.isEmpty(name)) {
            errorMsg = ErrorMessages.ERROR_NAME_EMPTY;
        } else if (validateTor.isEmpty(email)) {
            errorMsg = ErrorMessages.ERROR_EMAIL_EMPTY;
        } else if (!validateTor.isEmail(email)) {
            errorMsg = ErrorMessages.ERROR_INVALID_EMAIL;
        }
        if (errorMsg.length() > 0) {
            Toast.makeText(app.getApplicationContext(), errorMsg, Toast.LENGTH_SHORT).show();
        }
        return errorMsg.length() <= 0;
    }

    public boolean validationForSignUpStepTwo(Application app) {
        ValidateTor validateTor = new ValidateTor();
        String errorMsg = "";
        if (validateTor.isEmpty(name)) {
            errorMsg = ErrorMessages.ERROR_NAME_EMPTY;
        } else if (validateTor.isEmpty(email)) {
            errorMsg = ErrorMessages.ERROR_EMAIL_EMPTY;
        } else if (!validateTor.isEmail(email)) {
            errorMsg = ErrorMessages.ERROR_INVALID_EMAIL;
        } else if (!validateTor.isEmpty(mobile)) {
            errorMsg = ErrorMessages.ERROR_NUMBER_BLANK;
        } else if (!validateTor.isPhoneNumber(mobile)) {
            errorMsg = ErrorMessages.ERROR_INVALID_NUMBER;
        }
        if (errorMsg.length() > 0) {
            Toast.makeText(app.getApplicationContext(), errorMsg, Toast.LENGTH_SHORT).show();
        }
        return errorMsg.length() > 0;
    }
}
