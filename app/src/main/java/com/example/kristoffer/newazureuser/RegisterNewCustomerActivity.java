package com.example.kristoffer.newazureuser;

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

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class RegisterNewCustomerActivity extends AppCompatActivity {

    private EditText fornavnText,etternavnText,adresseText,postnummerText,stedText,telefonText,epostText;
    private String fornavn,etternavn,adresse,postnummer,sted,telefonnummer,epost;
    private String url = "http://frisats3.azurewebsites.net/";
    private Button registrerNyCustomerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_new_customer);
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

        fornavnText = (EditText) findViewById(R.id.nytt_fornavn_txt);
        etternavnText = (EditText) findViewById(R.id.nytt_etternavn_txt);
        adresseText = (EditText) findViewById(R.id.ny_addresse_txt);
        postnummerText = (EditText) findViewById(R.id.nytt_postnummer_txt);
        stedText = (EditText) findViewById(R.id.nytt_sted_txt);
        telefonText = (EditText) findViewById(R.id.nytt_telefonnummer_txt);
        epostText = (EditText) findViewById(R.id.ny_epostadresse_txt);

        registrerNyCustomerButton = (Button) findViewById(R.id.registrer_new_customer_btn);




        RestAdapter adapter = new RestAdapter.Builder().setEndpoint(url).build();
        final RestInterface restInterface = adapter.create(RestInterface.class);




        registrerNyCustomerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                fornavn = fornavnText.getText().toString();
                etternavn = etternavnText.getText().toString();
                adresse = adresseText.getText().toString();
                postnummer = postnummerText.getText().toString();
                sted = stedText.getText().toString();
                telefonnummer = telefonText.getText().toString();
                epost = epostText.getText().toString();


                final Customers newCustomer = new Customers();
                newCustomer.setFirstName(fornavn);
                newCustomer.setLastName(etternavn);
                newCustomer.setAddress(adresse);
                newCustomer.setPostNo(postnummer);
                newCustomer.setLocation(sted);
                newCustomer.setTelephone(telefonnummer);
                newCustomer.setEmail(epost);


                restInterface.createNewCustomer(newCustomer, new Callback<Customers>() {
                    @Override
                    public void success(Customers customers, Response response) {
                        Toast.makeText(getApplicationContext(), "Brukeren er opprettet", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        throw new RuntimeException("Error! " + error.getMessage());
                    }
                });
            }
        });




    }

}
