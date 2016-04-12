package com.example.kristoffer.newazureuser.POJO;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class Providers {
    @SerializedName("Id")
    @Expose
    private Integer Id;
    @SerializedName("FirstName")
    @Expose
    private Object FirstName;
    @SerializedName("LastName")
    @Expose
    private Object LastName;
    @SerializedName("CompanyName")
    @Expose
    private String CompanyName;
    @SerializedName("Address")
    @Expose
    private Object Address;
    @SerializedName("PostNo")
    @Expose
    private Object PostNo;
    @SerializedName("Location")
    @Expose
    private String Location;
    @SerializedName("Telephone")
    @Expose
    private String Telephone;
    @SerializedName("Latitude")
    @Expose
    private Double Latitude;
    @SerializedName("Longitude")
    @Expose
    private Double Longitude;
    @SerializedName("Email")
    @Expose
    private String Email;
    @SerializedName("Rating")
    @Expose
    private Integer Rating;


    public Integer getId() {
        return Id;
    }
    public void setId(Integer Id) {
        this.Id = Id;
    }
    public Object getFirstName() {
        return FirstName;
    }
    public void setFirstName(Object FirstName) {
        this.FirstName = FirstName;
    }
    public Object getLastName() {
        return LastName;
    }
    public void setLastName(Object LastName) {
        this.LastName = LastName;
    }
    public String getCompanyName() {
        return CompanyName;
    }
    public void setCompanyName(String CompanyName) {
        this.CompanyName = CompanyName;
    }
    public Object getAddress() {
        return Address;
    }
    public void setAddress(Object Address) {
        this.Address = Address;
    }
    public Object getPostNo() {
        return PostNo;
    }
    public void setPostNo(Object PostNo) {
        this.PostNo = PostNo;
    }
    public String getLocation() {
        return Location;
    }
    public void setLocation(String Location) {
        this.Location = Location;
    }
    public String getTelephone() {
        return Telephone;
    }
    public void setTelephone(String Telephone) {
        this.Telephone = Telephone;
    }
    public Double getLatitude() {
        return Latitude;
    }
    public void setLatitude(Double Latitude) {
        this.Latitude = Latitude;
    }
    public Double getLongitude() {
        return Longitude;
    }
    public void setLongitude(Double Longitude) {
        this.Longitude = Longitude;
    }
    public String getEmail() {
        return Email;
    }
    public void setEmail(String Email) {
        this.Email = Email;
    }
    public Integer getRating() {
        return Rating;
    }
    public void setRating(Integer Rating) {this.Rating = Rating;}
}
