package com.example.kristoffer.newazureuser;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.kristoffer.newazureuser.POJO.Customers;
import com.example.kristoffer.newazureuser.POJO.Providers;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class OversiktCustumersActivity extends AppCompatActivity implements ListView.OnItemClickListener {

    /*
    private Integer CustomerId;
    private Object UserID;
    private String FirstName;
    private String LastName;
    private Object Address;
    private Object PostNo;
    private Object Location;
    private Object Telephone;
    private Double Latitude;
    private Double Longitude;
    private Object Email;
    private Integer Rating;*/

    public static final String KEY_CUSTOMER_ID = "key_customer_ID";
    public static final String KEY_CUSTOMER_USER_ID = "key_customer_user_ID";
    public static final String KEY_CUSTOMER_FIRST_NAME = "key_customer_first_name";
    public static final String KEY_CUSTOMER_LAST_NAME = "key_customer_last_name";
    public static final String KEY_CUSTOMER_ADDRESS = "key_customer_address";
    public static final String KEY_CUSTOMER_POST_NUMBER = "key_customer_post_number";
    public static final String KEY_CUSTOMER_LOCATION = "key_customer_location";
    public static final String KEY_CUSTOMER_TELEPHONE = "key_customer_telephone";
    public static final String KEY_CUSTOMER_LATITUDE = "key_customer_latitude";
    public static final String KEY_CUSTOMER_LONGITUDE = "key_customer_logitude";
    public static final String KEY_CUSTOMER_EMAIL = "key_customer_email";
    public static final String KEY_CUSTOMER_RATING = "key_customer_rating";

    private ListView oversiktListe;
    private String url = "http://frisats3.azurewebsites.net";
    private List<Customers> customerListe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oversikt_custumers);
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


        oversiktListe = (ListView) findViewById(R.id.oversikt_customers_list_view);
        getTheCustomers();
        oversiktListe.setOnItemClickListener(this);

    }

    private void getTheCustomers(){
        final ProgressDialog loading = ProgressDialog.show(this, "Henter Brukere-oversikt", "Vennligst vent...", false, false);
        RestAdapter adapter = new RestAdapter.Builder().setEndpoint(url).build();
        RestInterface restInterface = adapter.create(RestInterface.class);
        restInterface.getCustomers(new Callback<List<Customers>>() {
            @Override
            public void success(List<Customers> list, Response response) {
                loading.dismiss();
                customerListe = list;
                showList();
            }
            @Override
            public void failure(RetrofitError error) {
                //you can handle the errors here
            }
        });
    }

    private void showList() {
        String[] items = new String[customerListe.size()];
        for (int i = 0; i < customerListe.size(); i++) {
            items[i] = customerListe.get(i).getFirstName();
        }
        ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.simple_list, items);
        oversiktListe.setAdapter(adapter);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this, ShowCustomerDetailsActivity.class);
        Customers customer = customerListe.get(position);
        intent.putExtra(KEY_CUSTOMER_ID, customer.getCustomerId());
        intent.putExtra(KEY_CUSTOMER_USER_ID, (String) customer.getUserID());
        intent.putExtra(KEY_CUSTOMER_FIRST_NAME, customer.getFirstName());
        intent.putExtra(KEY_CUSTOMER_LAST_NAME, customer.getLastName());
        intent.putExtra(KEY_CUSTOMER_ADDRESS, (String) customer.getAddress());
        intent.putExtra(KEY_CUSTOMER_POST_NUMBER, (String) customer.getPostNo());
        intent.putExtra(KEY_CUSTOMER_LOCATION, (String) customer.getLocation());
        intent.putExtra(KEY_CUSTOMER_TELEPHONE, (String) customer.getTelephone());
        intent.putExtra(KEY_CUSTOMER_LATITUDE, String.valueOf(customer.getLatitude()));
        intent.putExtra(KEY_CUSTOMER_LONGITUDE, String.valueOf(customer.getLongitude()));
        intent.putExtra(KEY_CUSTOMER_EMAIL, (String) customer.getEmail());
        intent.putExtra(KEY_CUSTOMER_RATING, String.valueOf(customer.getRating()));
        startActivity(intent);
    }

}
