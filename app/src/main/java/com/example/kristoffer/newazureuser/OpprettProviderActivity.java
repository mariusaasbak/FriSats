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

import com.example.kristoffer.newazureuser.POJO.Providers;

import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class OpprettProviderActivity extends AppCompatActivity {

    private EditText fornavnEditText,etternavnEditText,firmanavnEditText,adresseEditText,postnummerEditText,postAdresseEditText,telefonnummerEditText,epostEditText;
    private String fornavn,etternavn,firmaNavn,adresse,postnummer,postAdresse,telefonnummer,epost;
    private Button registrerProviderButton;
    private String url = "http://frisats3.azurewebsites.net";

    private List<Providers> providerListe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opprett_provider);
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

        fornavnEditText =           (EditText) findViewById(R.id.provider_fornavn_editText);
        etternavnEditText =         (EditText) findViewById(R.id.provider_etternavn_editText);
        firmanavnEditText =         (EditText) findViewById(R.id.provider_firmanavn_editText);
        adresseEditText =           (EditText) findViewById(R.id.provider_adresse_editText);
        postnummerEditText =        (EditText) findViewById(R.id.provider_postnummer_editText);
        postAdresseEditText =       (EditText) findViewById(R.id.provider_postadresse_editText);
        telefonnummerEditText =     (EditText) findViewById(R.id.provider_telefonnummer_editText);
        epostEditText =             (EditText) findViewById(R.id.provider_epost_editText);
        registrerProviderButton =   (Button) findViewById(R.id.registrer_provider_btn);


        registrerProviderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fornavn = fornavnEditText.getText().toString();
                etternavn = etternavnEditText.getText().toString();
                firmaNavn = firmanavnEditText.getText().toString();
                adresse = adresseEditText.getText().toString();
                postnummer = postnummerEditText.getText().toString();
                postAdresse = postAdresseEditText.getText().toString();
                telefonnummer = telefonnummerEditText.getText().toString();
                epost = epostEditText.getText().toString();

                try {
                    getTheProviders();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }


    private void getTheProviders(){
        final ProgressDialog loading = ProgressDialog.show(this, "Sjekker eksisterende hjelpere", "Vennligst vent...", false, false);

        RestAdapter adapter = new RestAdapter.Builder().setEndpoint(url).build();
        RestInterface restInterface = adapter.create(RestInterface.class);



        restInterface.getProviders(new Callback<List<Providers>>() {
            @Override
            public void success(List<Providers> list, Response response) {
                loading.dismiss();

                providerListe = list;
                boolean klar = false;

                String[] itemsTelefon = new String[providerListe.size()];
                String[] itemsEpost   = new String[providerListe.size()];

                //List<String> telefonItem = ;
                //List<Providers> epostItem = list;

                for (int i = 0; i < providerListe.size(); i++) {

                    itemsTelefon[i] = providerListe.get(i).getTelephone();
                    itemsEpost[i] = providerListe.get(i).getEmail();

                    //telefonItem.add(list.get(i).getTelephone().toString());

                    if (String.valueOf(itemsTelefon[i]).equals(telefonnummerEditText.getText().toString())) {
                        Toast.makeText(getApplicationContext(), "Error! Dette telefonnummeret er allerede i bruk", Toast.LENGTH_LONG).show();
                        //telefonnummerEditText.setText("");
                        klar = false;
                        break;
                    }
                    if (epostEditText.getText().toString().equals(String.valueOf(itemsEpost[i]))) {
                        Toast.makeText(getApplicationContext(), "Error! Denne eposten er allerede i bruk", Toast.LENGTH_LONG).show();
                        //epostEditText.setText("");
                        klar = false;
                        break;
                    }
                    else {
                        klar = true;
                    }
                }
                if (klar == true) {
                    registrerDenNyeProvideren();
                }
            }

            @Override
            public void failure(RetrofitError error) {
                //you can handle the errors here
            }
        });
    }


    private void registrerDenNyeProvideren() {
        final ProgressDialog loading = ProgressDialog.show(this, "Oppretter provider", "Vennligst vent...", false, false);

        RestAdapter adapter = new RestAdapter.Builder().setEndpoint(url).build();
        final RestInterface restInterface = adapter.create(RestInterface.class);

        final Providers newProvider = new Providers();

        newProvider.setFirstName(fornavn);
        newProvider.setLastName(etternavn);
        newProvider.setCompanyName(firmaNavn);
        newProvider.setAddress(adresse);
        newProvider.setPostNo(postnummer);
        newProvider.setLocation(postAdresse);
        newProvider.setTelephone(telefonnummer);
        newProvider.setEmail(epost);
        newProvider.setRating(100);

        restInterface.createNewProvider(newProvider, new Callback<Providers>() {
            @Override
            public void success(Providers providers, Response response) {
                loading.dismiss();
                Toast.makeText(getApplicationContext(), "Provideren er opprettet", Toast.LENGTH_LONG).show();
            }

            @Override
            public void failure(RetrofitError error) {
                throw new RuntimeException("Error! " + error.getMessage());
            }
        });

        fornavnEditText.setText("");
        etternavnEditText.setText("");
        firmanavnEditText.setText("");
        adresseEditText.setText("");
        postnummerEditText.setText("");
        postAdresseEditText.setText("");
        telefonnummerEditText.setText("");
        epostEditText.setText("");


    }

}
