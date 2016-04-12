package com.example.kristoffer.newazureuser;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.kristoffer.newazureuser.POJO.Orders;
import com.example.kristoffer.newazureuser.POJO.Services;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class OversiktOrdersActivity extends AppCompatActivity implements ListView.OnItemClickListener {

    public static final String KEY_ORDERS_OVERSIKT_ID = "key_orders_oversikt_ID"; //Integer
    public static final String KEY_ORDERS_OVERSIKT_START_TIME = "key_orders_oversikt_start_time"; //String
    public static final String KEY_ORDERS_OVERSIKT_END_TIME = "key_orders_oversikt_end_time"; //String
    public static final String KEY_ORDERS_OVERSIKT_LOCATION = "key_orders_oversikt_location"; //Object
    public static final String KEY_ORDERS_OVERSIKT_PRICE = "key_orders_oversikt_price"; // Double
    public static final String KEY_ORDERS_OVERSIKT_SERVICE_ID = "key_orders_oversikt_service_ID"; //Integer
    public static final String KEY_ORDERS_OVERSIKT_USER_ID = "key_orders_oversikt_user_ID"; // String
    public static final String KEY_ORDERS_OVERSIKT_SERVICE = "key_orders_oversikt_service"; // Services
    public static final String KEY_ORDERS_OVERSIKT_CUSTOMER = "key_orders_oversikt_customer"; // Object

    public static final String KEY_SERVICE_OVERSIKT_ID = "key_service_oversikt_ID";
    public static final String KEY_SERVICE_OVERSIKT_SERVICE_NAME = "key_service_oversikt_service_name";
    public static final String KEY_SERVICE_OVERSIKT_DESCRIPTION = "key_service_oversikt_description";
    public static final String KEY_SERVICE_OVERSIKT_GROUP_NAME = "key_service_oversikt_group_name";
    public static final String KEY_SERVICE_OVERSIKT_UNIT_PRICE = "key_service_oversikt_unit_price";
    public static final String KEY_SERVICE_OVERISKT_IMAGE_URL = "key_service_oversikt_image_url";
    public static final String KEY_SERVICE_OVERSIKT_PROVIDER_ID = "key_service_oversikt_provider_ID";
    public static final String KEY_SERVICE_OVERSIKT_SERVICE_GROUP_ID = "key_service_oversikt_service_group_ID";
    public static final String KEY_SERVICE_OVERSIKT_AVAIL_FROM = "key_service_oversikt_avail_from";
    public static final String KEY_SERVICE_OVERSIKT_AVAIL_TO = "key_service_avail_to";
    public static final String KEY_SERVICE_OVERSIKT_PROVIDER = "key_service_oversikt_provider";

    private ListView oversiktListe;
    private String url = "http://frisats3.azurewebsites.net";
    private List<Orders> ordersListe;


    private Integer Id;
    private String StartTime;
    private String EndTime;
    private Object Location;
    private Double Price;
    private Integer ServiceId;
    private String UserID;
    private Services Service;
    private Object Customer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oversikt_orders);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        oversiktListe = (ListView) findViewById(R.id.orders_list_view);
        getTheOrders();
        oversiktListe.setOnItemClickListener(this);

    }


    private void getTheOrders(){
        final ProgressDialog loading = ProgressDialog.show(this, "Henter Orders-oversikt", "Vennligst vent...", false, false);

        RestAdapter adapter = new RestAdapter.Builder().setEndpoint(url).build();
        RestInterface restInterface = adapter.create(RestInterface.class);

        restInterface.getOrders(new Callback<List<Orders>>() {
            @Override
            public void success(List<Orders> list, Response response) {
                loading.dismiss();
                ordersListe = list;
                showList();
            }
            @Override
            public void failure(RetrofitError error) {
                //you can handle the errors here
            }
        });
    }

    private void showList(){
        String[] items = new String[ordersListe.size()];
        for(int i=0; i<ordersListe.size(); i++){
            items[i] = ordersListe.get(i).getId().toString();
        }
        ArrayAdapter adapter = new ArrayAdapter<String>(this,R.layout.simple_list,items);
        oversiktListe.setAdapter(adapter);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //Creating an intent
        Intent intent = new Intent(this, ShowOrdersDetailsActivity.class);

        //Getting the requested book from the list
        Orders order = ordersListe.get(position);


        intent.putExtra(KEY_ORDERS_OVERSIKT_ID, order.getId());
        intent.putExtra(KEY_ORDERS_OVERSIKT_START_TIME, order.getStartTime());
        intent.putExtra(KEY_ORDERS_OVERSIKT_END_TIME, order.getEndTime());
        intent.putExtra(KEY_ORDERS_OVERSIKT_LOCATION, (String) order.getLocation());
        intent.putExtra(KEY_ORDERS_OVERSIKT_PRICE, order.getPrice());
        intent.putExtra(KEY_ORDERS_OVERSIKT_SERVICE_ID, order.getServiceId());
        intent.putExtra(KEY_ORDERS_OVERSIKT_USER_ID, order.getUserID());
        //intent.putExtra(KEY_ORDERS_OVERSIKT_SERVICE, order.getService()); //Services
        intent.putExtra(KEY_ORDERS_OVERSIKT_CUSTOMER, (String) order.getCustomer()); //Object

        //Services service = serviceList.get(position);

        intent.putExtra(KEY_SERVICE_OVERSIKT_ID, order.getService().getId());
        intent.putExtra(KEY_SERVICE_OVERSIKT_SERVICE_NAME, order.getService().getServiceName());
        intent.putExtra(KEY_SERVICE_OVERSIKT_DESCRIPTION, order.getService().getDescription());
        intent.putExtra(KEY_SERVICE_OVERSIKT_GROUP_NAME, order.getService().getGroupName());
        intent.putExtra(KEY_SERVICE_OVERSIKT_UNIT_PRICE, order.getService().getUnitPrice());
        intent.putExtra(KEY_SERVICE_OVERSIKT_PROVIDER_ID, order.getService().getProviderId());
        intent.putExtra(KEY_SERVICE_OVERSIKT_SERVICE_GROUP_ID, order.getService().getServiceGroupId());
        intent.putExtra(KEY_SERVICE_OVERSIKT_AVAIL_FROM, order.getService().getAvailFrom());
        intent.putExtra(KEY_SERVICE_OVERSIKT_AVAIL_TO, order.getService().getAvailTo());
        intent.putExtra(KEY_SERVICE_OVERSIKT_PROVIDER, (String) order.getService().getProvider());
        intent.putExtra(KEY_SERVICE_OVERISKT_IMAGE_URL, (String) order.getService().getImageUrl());


        startActivity(intent);


    }

}
