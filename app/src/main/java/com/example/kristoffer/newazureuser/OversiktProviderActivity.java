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

import com.example.kristoffer.newazureuser.POJO.Groups;
import com.example.kristoffer.newazureuser.POJO.Providers;

import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class OversiktProviderActivity extends AppCompatActivity implements ListView.OnItemClickListener {

    public static final String KEY_PROVIDER_ID = "key_provider_id";
    public static final String KEY_PROVIDER_FIRST_NAME = "key_provider_first_name";
    public static final String KEY_PROVIDER_LAST_NAME = "key_provider_last_name";
    public static final String KEY_PROVIDER_COMPANY_NAME = "key_company_name";
    public static final String KEY_PROVIDER_ADDRESS = "key_provider_address";
    public static final String KEY_PROVIDER_POST_NUMBER = "key_provider_post_number";
    public static final String KEY_PROVIDER_LOCATION = "key_provider_location";
    public static final String KEY_PROVIDER_TELEPHONE = "key_provider_telephone";
    public static final String KEY_PROVIDER_LATITUDE = "key_provider_latitude";
    public static final String KEY_PROVIDER_LONGITUDE = "key_provider_logitude";
    public static final String KEY_PROVIDER_EMAIL = "key_provider_email";
    public static final String KEY_PROVIDER_RATING = "key_provider_rating";

    private ListView oversiktListe;
    private String url = "http://frisats3.azurewebsites.net";
    private List<Providers> providerListe;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oversikt_provider);
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

        oversiktListe = (ListView) findViewById(R.id.provider_list_view);
        getTheProviders();
        oversiktListe.setOnItemClickListener(this);
    }



    private void getTheProviders(){
        //While the app fetched data we are displaying a progress dialog
        final ProgressDialog loading = ProgressDialog.show(this, "Henter Provider-oversikt", "Vennligst vent...", false, false);

        RestAdapter adapter = new RestAdapter.Builder().setEndpoint(url).build();
        RestInterface restInterface = adapter.create(RestInterface.class);

        //Defining the method
        restInterface.getProviders(new Callback<List<Providers>>() {
            @Override
            public void success(List<Providers> list, Response response) {

                loading.dismiss();
                providerListe = list;
                showList();
            }

            @Override
            public void failure(RetrofitError error) {
                //you can handle the errors here
            }
        });
    }

    private void showList(){

        String[] items = new String[providerListe.size()];

        for(int i=0; i<providerListe.size(); i++){
            //Storing names to string array
            items[i] = (String) providerListe.get(i).getFirstName();
        }
        //Creating an array adapter for list view
        ArrayAdapter adapter = new ArrayAdapter<String>(this,R.layout.simple_list,items);

        //Setting adapter to listview
        oversiktListe.setAdapter(adapter);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //Creating an intent
        Intent intent = new Intent(this, ShowProviderDetailsActivity.class);

        //Getting the requested book from the list
        Providers provider = providerListe.get(position);

        //Adding book details to intent
        intent.putExtra(KEY_PROVIDER_ID, provider.getId());
        intent.putExtra(KEY_PROVIDER_FIRST_NAME, (String) provider.getFirstName());
        intent.putExtra(KEY_PROVIDER_LAST_NAME, (String) provider.getLastName());
        intent.putExtra(KEY_PROVIDER_COMPANY_NAME, provider.getCompanyName());
        intent.putExtra(KEY_PROVIDER_ADDRESS, (String) provider.getAddress());
        intent.putExtra(KEY_PROVIDER_POST_NUMBER, (String) provider.getPostNo());
        intent.putExtra(KEY_PROVIDER_LOCATION, provider.getLocation());
        intent.putExtra(KEY_PROVIDER_TELEPHONE, provider.getTelephone());
        intent.putExtra(KEY_PROVIDER_LATITUDE,  provider.getLatitude());
        intent.putExtra(KEY_PROVIDER_LONGITUDE, provider.getLongitude());
        intent.putExtra(KEY_PROVIDER_EMAIL, provider.getEmail());
        intent.putExtra(KEY_PROVIDER_RATING, provider.getRating());
        //Starting another activity to show book details
        startActivity(intent);
    }


}
