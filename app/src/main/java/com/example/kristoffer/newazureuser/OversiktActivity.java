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
import android.widget.TextView;

import com.example.kristoffer.newazureuser.POJO.Groups;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class OversiktActivity extends AppCompatActivity implements ListView.OnItemClickListener {

    private ListView oversiktListe;
    private String url = "http://frisats3.azurewebsites.net";

    public static final String KEY_GROUP_ID = "key_group_id";
    public static final String KEY_GROUP_NAME = "key_group_name";
    public static final String KEY_GROUP_DESCRIPTION = "key_group_description";
    public static final String KEY_GROUP_MEMBERS = "key_group_members";
    public static final String KEY_GROUP_IMAGE_URL = "key_group_imge_url";

    private List<Groups> gruppeListe;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oversikt);
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



        oversiktListe = (ListView) findViewById(R.id.group_list_view);
        getTheGroups();
        oversiktListe.setOnItemClickListener(this);


        //RestAdapter adapter = new RestAdapter.Builder().setEndpoint(url).build();
        //final RestInterface restInterface = adapter.create(RestInterface.class);
        //ArrayAdapter<Groups> arrayAdapter = new ArrayAdapter<Groups>(this, android.R.layout.simple_list_item_1, gruppeListe);
        //ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, monthsArray);
        //oversiktListe.setAdapter(arrayAdapter);
    }


    private void getTheGroups(){
        //While the app fetched data we are displaying a progress dialog
        final ProgressDialog loading = ProgressDialog.show(this, "Henter gruppeoversikt", "Vennligst vent...", false, false);

        RestAdapter adapter = new RestAdapter.Builder().setEndpoint(url).build();
        RestInterface restInterface = adapter.create(RestInterface.class);

        //Defining the method
        restInterface.getGroups(new Callback<List<Groups>>() {
            @Override
            public void success(List<Groups> list, Response response) {
                //Dismissing the loading progressbar
                loading.dismiss();

                //Storing the data in our list
                gruppeListe = list;

                //Calling a method to show the list
                showList();
            }
            @Override
            public void failure(RetrofitError error) {
                //you can handle the errors here
            }
        });
    }



    private void showList(){
        //String array to store all the book names
        String[] items = new String[gruppeListe.size()];

        //Traversing through the whole list to get all the names
        for(int i=0; i<gruppeListe.size(); i++){
            //Storing names to string array
            items[i] = gruppeListe.get(i).getGroupName();
        }
        //Creating an array adapter for list view
        ArrayAdapter adapter = new ArrayAdapter<String>(this,R.layout.simple_list,items);

        //Setting adapter to listview
        oversiktListe.setAdapter(adapter);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //Creating an intent
        Intent intent = new Intent(this, ShowOversiktDetailsActivity.class);

        //Getting the requested book from the list
        Groups group = gruppeListe.get(position);

        //Adding book details to intent
        intent.putExtra(KEY_GROUP_ID,group.getId());
        intent.putExtra(KEY_GROUP_NAME,group.getGroupName());
        intent.putExtra(KEY_GROUP_DESCRIPTION,group.getDescription());
        intent.putExtra(KEY_GROUP_MEMBERS, (Integer) group.getMembers());
        intent.putExtra(KEY_GROUP_IMAGE_URL,group.getImageUrl());
        //Starting another activity to show book details
        startActivity(intent);
    }



}
