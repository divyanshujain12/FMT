package com.application.fmt.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CountriesModel {

    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("countries")
    @Expose
    private List<Country> countries = null;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public List<Country> getCountries() {
        return countries;
    }

    public void setCountries(List<Country> countries) {
        this.countries = countries;
    }
}
