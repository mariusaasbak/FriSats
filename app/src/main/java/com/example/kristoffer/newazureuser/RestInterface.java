package com.example.kristoffer.newazureuser;

import com.example.kristoffer.newazureuser.POJO.Services;
import com.example.kristoffer.newazureuser.POJO.Groups;
import com.example.kristoffer.newazureuser.POJO.Orders;
import com.example.kristoffer.newazureuser.POJO.Customers;
import com.example.kristoffer.newazureuser.POJO.Providers;

import java.util.List;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;

public interface RestInterface {
    @GET("/api/groups")
    List<Groups> getAllGroups();

    @GET("/api/groups")
    void getGroups(Callback<List<Groups>> response);

    @POST("/api/groups")
    void createNewGroup(@Body Groups newGroup, Callback<Groups> cb);



    @POST("/api/services")
    void createService(@Body Services newService, Callback<Services> cb);

    @GET("/api/services")
    void getServices(Callback<List<Services>> cb);



    @GET("/api/orders")
    void getOrders(Callback<List<Orders>> cb);

    @POST("/api/orders")
    void createNewOrder(@Body Orders newOrder, Callback<Orders> cb);


    @POST("/api/customers")
    void createNewCustomer(@Body Customers newCustomer, Callback<Customers> cb);

    @GET("/api/customers")
    void getCustomers(Callback<List<Customers>> response);



    @GET("/api/providers")
    void getProviders(Callback<List<Providers>> cb);

    @POST("/api/providers")
    void createNewProvider(@Body Providers newProvider, Callback<Providers> cb);













}
