package com.example.kristoffer.newazureuser;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.kristoffer.newazureuser.POJO.Customers;
import com.example.kristoffer.newazureuser.POJO.Groups;

import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class OpprettGruppeActivity extends AppCompatActivity {

    private EditText gruppeNavnEditText,gruppeBeskrivelseEditText;
    private String gruppeNavn,gruppeBeskrivelse;
    private Button registrerNyGruppeButton;
    private String url = "http://frisats3.azurewebsites.net";

    private List<Groups> gruppeListe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opprett_gruppe);
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

        gruppeNavnEditText = (EditText) findViewById(R.id.gruppe_navn_edit_text);
        gruppeBeskrivelseEditText = (EditText) findViewById(R.id.gruppeBeskrivelse_edit_text);
        registrerNyGruppeButton = (Button) findViewById(R.id.registrer_ny_gruppe_btn);



        registrerNyGruppeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                gruppeNavn = gruppeNavnEditText.getText().toString();
                gruppeBeskrivelse = gruppeBeskrivelseEditText.getText().toString();

                getTheGroups();

            }
        });

    }


    private void getTheGroups(){
        final ProgressDialog loading = ProgressDialog.show(this, "Oppretter gruppe", "Vennligst vent...", false, false);

        RestAdapter adapter = new RestAdapter.Builder().setEndpoint(url).build();
        RestInterface restInterface = adapter.create(RestInterface.class);

        restInterface.getGroups(new Callback<List<Groups>>() {
            @Override
            public void success(List<Groups> list, Response response) {
                loading.dismiss();

                gruppeListe = list;
                boolean klar = false;

                String[] itemsGroup = new String[gruppeListe.size()];

                for (int i = 0; i < gruppeListe.size(); i++) {

                    itemsGroup[i] = gruppeListe.get(i).getGroupName();

                    if (itemsGroup[i].equals(gruppeNavnEditText.getText().toString())) {
                        Toast.makeText(getApplicationContext(), "Error! Denne gruppen finnes fra før", Toast.LENGTH_LONG).show();
                        klar = false;
                        break;
                    } else {
                        klar = true;
                    }
                }
                if (klar) {
                    registrerDenNyeGruppen();
                }
            }

            @Override
            public void failure(RetrofitError error) {
                //you can handle the errors here
            }
        });
    }




    private void registrerDenNyeGruppen() {

        RestAdapter adapter = new RestAdapter.Builder().setEndpoint(url).build();
        final RestInterface restInterface = adapter.create(RestInterface.class);

        final Groups newGroup = new Groups();

        newGroup.setGroupName(gruppeNavn);
        newGroup.setDescription(gruppeBeskrivelse);

        restInterface.createNewGroup(newGroup, new Callback<Groups>() {
            @Override
            public void success(Groups groups, Response response) {

            }

            @Override
            public void failure(RetrofitError error) {
                throw new RuntimeException("Error! " + error.getMessage());
            }
        });

        gruppeNavnEditText.setText("");
        gruppeBeskrivelseEditText.setText("");

        Toast.makeText(getApplicationContext(), "Gruppen er opprettet", Toast.LENGTH_LONG).show();

    }



}
