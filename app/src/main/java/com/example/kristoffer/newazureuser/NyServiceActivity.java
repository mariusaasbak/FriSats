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
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.kristoffer.newazureuser.POJO.Groups;
import com.example.kristoffer.newazureuser.POJO.Services;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class NyServiceActivity extends AppCompatActivity implements ListView.OnItemClickListener {

    public static final String KEY_SERVICE_ID = "key_service_ID";
    public static final String KEY_SERVICE_NAME = "key_service_name";
    public static final String KEY_SERVICE_DESCRIPTION = "key_service_description";
    public static final String KEY_SERVICE_GROUP_NAME = "key_service_group_name";
    public static final String KEY_SERVICE_UNIT_PRICE = "key_service_unit_price";
    public static final String KEY_SERVICE_IMAGE_URL = "key_service_image_url";
    public static final String KEY_SERVICE_PROVIDER_ID = "key_service_provider_ID";
    public static final String KEY_SERVICE_GROUP_ID = "key_service_group_ID";
    public static final String KEY_SERVICE_AVAIL_FROM = "key_service_avail_from";
    public static final String KEY_SERVICE_AVAIL_TO = "key_service_avail_to";
    public static final String KEY_SERVICE_PROVIDER = "key_service_provider";

    private ListView serviceTextView;
    private String groupID,groupName,groupDescription,groupMembers;
    private String url = "http://frisats3.azurewebsites.net";
    private List<Services> serviceListe;
    private List myList = new ArrayList();
    private boolean valgt = false;
    private int nummer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ny_service);
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

        serviceTextView = (ListView) findViewById(R.id.service_listView);

        Intent intent = getIntent();

        groupID = String.valueOf(intent.getIntExtra(NyTjenesteActivity.KEY_GROUP_ID, 0));
        groupName = intent.getStringExtra(NyTjenesteActivity.KEY_GROUP_NAME);
        groupDescription = intent.getStringExtra(NyTjenesteActivity.KEY_GROUP_DESCRIPTION);
        groupMembers = String.valueOf(intent.getIntExtra(NyTjenesteActivity.KEY_GROUP_MEMBERS, 0));

        getTheServices();

        serviceTextView.setOnItemClickListener(this);




    }


    private void getTheServices(){
        final ProgressDialog loading = ProgressDialog.show(this, "Fetching Data", "Please wait...", false, false);

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

        String[] itemsGroup = new String[serviceListe.size()];

        int countServices = 0;
        for(int i=0; i<serviceListe.size(); i++){

            itemsGroup[i] = serviceListe.get(i).getGroupName();

            if (itemsGroup[i].equals(groupName)) {
                countServices++;
            }
        }

        String[] itemSHIT = new String[countServices];
        int counter;

        for (int i = 0; i < serviceListe.size(); i++) {

            itemsGroup[i] = serviceListe.get(i).getGroupName();

            if (itemsGroup[i].equals(groupName)) {
                counter=0;
                myList.add(itemSHIT[counter] = serviceListe.get(i).getServiceName());
                counter++;
                valgt = true;
            }
            if (valgt == true) {
                nummer = i;
                valgt = false;
            }
        }



        ArrayAdapter adapter = new ArrayAdapter<String>(this,R.layout.simple_list, myList);
        serviceTextView.setAdapter(adapter);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Intent intent = new Intent(this, ShowServiceDetailsActivity.class);
        Services service = serviceListe.get(position - position + nummer);

        intent.putExtra(KEY_SERVICE_ID, service.getId());
        intent.putExtra(KEY_SERVICE_NAME, service.getServiceName());
        intent.putExtra(KEY_SERVICE_DESCRIPTION, service.getDescription());
        intent.putExtra(KEY_SERVICE_GROUP_NAME, service.getGroupName());
        intent.putExtra(KEY_SERVICE_UNIT_PRICE, service.getUnitPrice());
        intent.putExtra(KEY_SERVICE_PROVIDER_ID, service.getProviderId());
        intent.putExtra(KEY_SERVICE_GROUP_ID, service.getServiceGroupId());
        intent.putExtra(KEY_SERVICE_AVAIL_FROM, service.getAvailFrom());
        intent.putExtra(KEY_SERVICE_AVAIL_TO, service.getAvailTo());
        intent.putExtra(KEY_SERVICE_PROVIDER, (String) service.getProvider());
        intent.putExtra(KEY_SERVICE_IMAGE_URL, (String) service.getImageUrl());

        startActivity(intent);
    }

}
