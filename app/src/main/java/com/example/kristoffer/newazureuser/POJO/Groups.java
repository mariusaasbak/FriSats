package com.example.kristoffer.newazureuser.POJO;


import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")

public class Groups {
    @SerializedName("Id")
    @Expose
    private Integer Id;
    @SerializedName("GroupName")
    @Expose
    private String GroupName;
    @SerializedName("Description")
    @Expose
    private String Description;
    @SerializedName("ImageUrl")
    @Expose
    private String ImageUrl;
    @SerializedName("Members")
    @Expose
    private Object Members;


    public Integer getId() {return Id;}
    public void setId(Integer Id) {this.Id = Id;}
    public String getGroupName() {return GroupName;}
    public void setGroupName(String GroupName) { this.GroupName = GroupName;}
    public String getDescription() {return Description;}
    public void setDescription(String Description) {this.Description = Description;}
    public String getImageUrl() {return ImageUrl;}
    public void setImageUrl(String ImageUrl) {this.ImageUrl = ImageUrl;}
    public Object getMembers() {return Members;}
    public void setMembers(Object Members) {this.Members = Members;}


}
