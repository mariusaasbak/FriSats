package com.example.kristoffer.newazureuser.POJO;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")

public class Services {
    @SerializedName("Id")
    @Expose
    private Integer Id;
    @SerializedName("ServiceName")
    @Expose
    private String ServiceName;
    @SerializedName("Description")
    @Expose
    private String Description;
    @SerializedName("GroupName")
    @Expose
    private String GroupName;
    @SerializedName("UnitPrice")
    @Expose
    private Double UnitPrice;
    @SerializedName("ImageUrl")
    @Expose
    private Object ImageUrl;
    @SerializedName("ProviderId")
    @Expose
    private Integer ProviderId;
    @SerializedName("ServiceGroupId")
    @Expose
    private Integer ServiceGroupId;
    @SerializedName("AvailFrom")
    @Expose
    private String AvailFrom;
    @SerializedName("AvailTo")
    @Expose
    private String AvailTo;
    @SerializedName("Provider")
    @Expose
    private Object Provider;


    public Integer getId() {return Id;}
    public void setId(Integer Id) {this.Id = Id;}
    public String getServiceName() {return ServiceName;}
    public void setServiceName(String ServiceName) {this.ServiceName = ServiceName;}
    public String getDescription() {return Description;}
    public void setDescription(String Description) {this.Description = Description;}
    public String getGroupName() {return GroupName;}
    public void setGroupName(String GroupName) {this.GroupName = GroupName;}
    public Double getUnitPrice() {return UnitPrice;}
    public void setUnitPrice(Double UnitPrice) {this.UnitPrice = UnitPrice;}
    public Object getImageUrl() {return ImageUrl;}
    public void setImageUrl(Object ImageUrl) {this.ImageUrl = ImageUrl;}
    public Integer getProviderId() {return ProviderId;}
    public void setProviderId(Integer ProviderId) {this.ProviderId = ProviderId;}
    public Integer getServiceGroupId() {return ServiceGroupId;}
    public void setServiceGroupId(Integer ServiceGroupId) {this.ServiceGroupId = ServiceGroupId;}
    public String getAvailFrom() {return AvailFrom;}
    public void setAvailFrom(String AvailFrom) {this.AvailFrom = AvailFrom;}
    public String getAvailTo() {return AvailTo;}
    public void setAvailTo(String AvailTo) {this.AvailTo = AvailTo;}
    public Object getProvider() {return Provider;}
    public void setProvider(Object Provider) {this.Provider = Provider;}
}
