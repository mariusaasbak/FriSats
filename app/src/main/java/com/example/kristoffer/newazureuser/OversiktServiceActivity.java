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

import com.example.kristoffer.newazureuser.POJO.Providers;
import com.example.kristoffer.newazureuser.POJO.Services;

import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class OversiktServiceActivity extends AppCompatActivity  implements ListView.OnItemClickListener {

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
    private List<Services> serviceListe;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oversikt_service);
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

        oversiktListe = (ListView) findViewById(R.id.service_oversikt_list_view);
        getTheServices();
        oversiktListe.setOnItemClickListener(this);
    }

    private void getTheServices(){
        final ProgressDialog loading = ProgressDialog.show(this, "Henter Service-oversikt", "Vennligst vent...", false, false);

        RestAdapter adapter = new RestAdapter.Builder().setEndpoint(url).build();
        RestInterface restInterface = adapter.create(RestInterface.class);

        restInterface.getServices(new Callback<List<Services>>() {
            @Override
            public void success(List<Services> list, Response response) {
                loading.dismiss();
                serviceListe = list;
                showList();
            }

            @Override
            public void failure(RetrofitError error) {
                //you can handle the errors here
            }
        });
    }

    private void showList(){

        String[] items = new String[serviceListe.size()];

        for(int i=0; i<serviceListe.size(); i++){
            items[i] = serviceListe.get(i).getServiceName();
        }

        ArrayAdapter adapter = new ArrayAdapter<String>(this,R.layout.simple_list,items);
        oversiktListe.setAdapter(adapter);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //Creating an intent
        Intent intent = new Intent(this, ShowServiceOversiktActivity.class);

        //Getting the requested book from the list
        Services service = serviceListe.get(position);

        intent.putExtra(KEY_SERVICE_OVERSIKT_ID, service.getId());
        intent.putExtra(KEY_SERVICE_OVERSIKT_SERVICE_NAME, service.getServiceName());
        intent.putExtra(KEY_SERVICE_OVERSIKT_DESCRIPTION, service.getDescription());
        intent.putExtra(KEY_SERVICE_OVERSIKT_GROUP_NAME, service.getGroupName());
        intent.putExtra(KEY_SERVICE_OVERSIKT_UNIT_PRICE, service.getUnitPrice());
        intent.putExtra(KEY_SERVICE_OVERSIKT_PROVIDER_ID, service.getProviderId());
        intent.putExtra(KEY_SERVICE_OVERSIKT_SERVICE_GROUP_ID, service.getServiceGroupId());
        intent.putExtra(KEY_SERVICE_OVERSIKT_AVAIL_FROM, service.getAvailFrom());
        intent.putExtra(KEY_SERVICE_OVERSIKT_AVAIL_TO, service.getAvailTo());
        intent.putExtra(KEY_SERVICE_OVERSIKT_PROVIDER, (String) service.getProvider());
        intent.putExtra(KEY_SERVICE_OVERISKT_IMAGE_URL, (String) service.getImageUrl());
        //Starting another activity to show book details
        startActivity(intent);
    }

}
