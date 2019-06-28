package com.application.fmt.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Country {

@SerializedName("country")
@Expose
private String country;
@SerializedName("country_code")
@Expose
private String countryCode;
@SerializedName("selected")
@Expose
private Boolean selected;

public String getCountry() {
return country;
}

public void setCountry(String country) {
this.country = country;
}

public String getCountryCode() {
return countryCode;
}

public void setCountryCode(String countryCode) {
this.countryCode = countryCode;
}

public Boolean getSelected() {
return selected;
}

public void setSelected(Boolean selected) {
this.selected = selected;
}

}