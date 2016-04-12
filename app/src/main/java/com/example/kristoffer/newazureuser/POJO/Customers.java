package com.example.kristoffer.newazureuser.POJO;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class Customers {
    @SerializedName("CustomerId")
    @Expose
    private Integer CustomerId;
    @SerializedName("UserID")
    @Expose
    private Object UserID;
    @SerializedName("FirstName")
    @Expose
    private String FirstName;
    @SerializedName("LastName")
    @Expose
    private String LastName;
    @SerializedName("Address")
    @Expose
    private Object Address;
    @SerializedName("PostNo")
    @Expose
    private Object PostNo;
    @SerializedName("Location")
    @Expose
    private Object Location;
    @SerializedName("Telephone")
    @Expose
    private Object Telephone;
    @SerializedName("Latitude")
    @Expose
    private Double Latitude;
    @SerializedName("Longitude")
    @Expose
    private Double Longitude;
    @SerializedName("Email")
    @Expose
    private Object Email;
    @SerializedName("Rating")
    @Expose
    private Integer Rating;


    public Integer getCustomerId() {
        return CustomerId;
    }
    public void setCustomerId(Integer CustomerId) {
        this.CustomerId = CustomerId;
    }
    public Object getUserID() {
        return UserID;
    }
    public void setUserID(Object UserID) {
        this.UserID = UserID;
    }
    public String getFirstName() {
        return FirstName;
    }
    public void setFirstName(String FirstName) {
        this.FirstName = FirstName;
    }
    public String getLastName() {
        return LastName;
    }
    public void setLastName(String LastName) {
        this.LastName = LastName;
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
    public Object getLocation() {
        return Location;
    }
    public void setLocation(Object Location) {
        this.Location = Location;
    }
    public Object getTelephone() {
        return Telephone;
    }
    public void setTelephone(Object Telephone) {
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
    public Object getEmail() {
        return Email;
    }
    public void setEmail(Object Email) {
        this.Email = Email;
    }
    public Integer getRating() {
        return Rating;
    }
    public void setRating(Integer Rating) {this.Rating = Rating;}
}
