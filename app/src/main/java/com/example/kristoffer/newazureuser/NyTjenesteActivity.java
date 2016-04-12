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

import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class NyTjenesteActivity extends AppCompatActivity implements ListView.OnItemClickListener {

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
        setContentView(R.layout.activity_ny_tjeneste);
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

        oversiktListe = (ListView) findViewById(R.id.groups_oversikt_list_view);
        getTheGroups();
        oversiktListe.setOnItemClickListener(this);

    }


    private void getTheGroups(){
        final ProgressDialog loading = ProgressDialog.show(this, "Fetching Data", "Please wait...", false, false);

        RestAdapter adapter = new RestAdapter.Builder().setEndpoint(url).build();
        RestInterface restInterface = adapter.create(RestInterface.class);

        restInterface.getGroups(new Callback<List<Groups>>() {
            @Override
            public void success(List<Groups> list, Response response) {
                loading.dismiss();
                gruppeListe = list;
                showList();
            }

            @Override
            public void failure(RetrofitError error) {
                //you can handle the errors here
            }
        });
    }
    private void showList(){
        String[] items = new String[gruppeListe.size()];
        for(int i=0; i<gruppeListe.size(); i++){
            items[i] = gruppeListe.get(i).getGroupName();
        }
        ArrayAdapter adapter = new ArrayAdapter<String>(this,R.layout.simple_list,items);
        oversiktListe.setAdapter(adapter);
    }





    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //Creating an intent
        Intent intent = new Intent(this, NyServiceActivity.class);

        //Getting the requested book from the list
        Groups group = gruppeListe.get(position);

        //Adding book details to intent
        intent.putExtra(KEY_GROUP_ID, group.getId());
        intent.putExtra(KEY_GROUP_NAME, group.getGroupName());
        intent.putExtra(KEY_GROUP_DESCRIPTION, group.getDescription());
        intent.putExtra(KEY_GROUP_MEMBERS, (Integer) group.getMembers());
        intent.putExtra(KEY_GROUP_IMAGE_URL, group.getImageUrl());
        //Starting another activity to show book details
        startActivity(intent);
    }

}
