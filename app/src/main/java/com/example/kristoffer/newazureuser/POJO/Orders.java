package com.example.kristoffer.newazureuser.POJO;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")

public class Orders {
    @SerializedName("Id")
    @Expose
    private Integer Id;
    @SerializedName("StartTime")
    @Expose
    private String StartTime;
    @SerializedName("EndTime")
    @Expose
    private String EndTime;
    @SerializedName("Location")
    @Expose
    private Object Location;
    @SerializedName("Price")
    @Expose
    private Double Price;
    @SerializedName("ServiceId")
    @Expose
    private Integer ServiceId;
    @SerializedName("UserID")
    @Expose
    private String UserID;
    @SerializedName("Service")
    @Expose
    private Services Service;
    @SerializedName("Customer")
    @Expose
    private Object Customer;


    public Integer getId() {return Id;}
    public void setId(Integer Id) {this.Id = Id;}
    public String getStartTime() {return StartTime;}
    public void setStartTime(String StartTime) {this.StartTime = StartTime;}
    public String getEndTime() {return EndTime;}
    public void setEndTime(String EndTime) {this.EndTime = EndTime;}
    public Object getLocation() {return Location;}
    public void setLocation(Object Location) {this.Location = Location;}
    public Double getPrice() {return Price;}
    public void setPrice(Double Price) {this.Price = Price;}
    public Integer getServiceId() {return ServiceId;}
    public void setServiceId(Integer ServiceId) {this.ServiceId = ServiceId;}
    public String getUserID() {return UserID;}
    public void setUserID(String UserID) {this.UserID = UserID;}
    public Services getService() {return Service;}
    public void setService(Services Service) {this.Service = Service;}
    public Object getCustomer() {return Customer;}
    public void setCustomer(Object Customer) {this.Customer = Customer;}
}
