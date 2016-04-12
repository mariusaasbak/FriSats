package com.example.kristoffer.newazureuser;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.kristoffer.newazureuser.POJO.Customers;


import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class LoginActivity extends AppCompatActivity {


    private String url = "http://frisats3.azurewebsites.net";
    private List<Customers> customersList;

    private TextView brukernavnTextView,passordTextView,errorText;
    private Button logInNowButton;

    private String fornavn,etternavn,adresse,postnummer,location,telefon,epost;
    private int brukerNummer;
    private boolean klar = false;
    private boolean riktig = false;

    public static final String KEY_CUSTOMER_FIRSTNAME = "key_customer_firstname";
    public static final String KEY_CUSTOMER_LASTNAME = "key_customer_lastname";
    public static final String KEY_CUSTOMER_ADRESSE = "key_customer_adresse";
    public static final String KEY_CUSTOMER_POSTNUMMER = "key_customer_postnummer";
    public static final String KEY_CUSTOMER_LOCATION = "key_customer_location";
    public static final String KEY_CUSTOMER_TELEFON = "key_customer_telefon";
    public static final String KEY_CUSTOMER_EPOST = "key_customer_email";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        brukernavnTextView = (TextView) findViewById(R.id.brukernavn_txt);
        passordTextView = (TextView) findViewById(R.id.passord_txt);
        logInNowButton = (Button) findViewById(R.id.logg_inn_btn);
        errorText = (TextView) findViewById(R.id.error_txt);

        logInNowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loggInn();
            }
        });
    }



    public void loggInn() {
        final ProgressDialog loading = ProgressDialog.show(this, "Logger inn", "Vennligst vent...", false, false);

        RestAdapter adapter = new RestAdapter.Builder().setEndpoint(url).build();
        RestInterface restInterface = adapter.create(RestInterface.class);

        restInterface.getCustomers(new Callback<List<Customers>>() {
            @Override
            public void success(List<Customers> list, Response response) {
                loading.dismiss();
                customersList = list;

                String[] items = new String[customersList.size()];
                String[] passordItems = new String[customersList.size()];

                for (int i = 0; i < customersList.size(); i++) {
                    //Storing names to string array
                    items[i] = customersList.get(i).getFirstName();
                    passordItems[i] = customersList.get(i).getLastName();

                    if (brukernavnTextView.getText().toString().equals(items[i])) {
                        brukerNummer = i;
                        klar = true;
                        break;
                    } else {
                        errorText.setText("Denne brukeren finnes ikke");
                        klar = false;
                    }
                }
                if (klar == true) {
                    if (passordTextView.getText().toString().equals(passordItems[brukerNummer])) {
                        Intent intent = new Intent(LoginActivity.this, HovedMenyActivity.class);
                        intent.putExtra(KEY_CUSTOMER_FIRSTNAME, customersList.get(brukerNummer).getFirstName());
                        intent.putExtra(KEY_CUSTOMER_LASTNAME, customersList.get(brukerNummer).getLastName());
                        intent.putExtra(KEY_CUSTOMER_ADRESSE, (String) customersList.get(brukerNummer).getAddress());
                        intent.putExtra(KEY_CUSTOMER_POSTNUMMER, (String) customersList.get(brukerNummer).getPostNo());
                        intent.putExtra(KEY_CUSTOMER_LOCATION, (String) customersList.get(brukerNummer).getLocation());
                        intent.putExtra(KEY_CUSTOMER_TELEFON, (String) customersList.get(brukerNummer).getTelephone());
                        intent.putExtra(KEY_CUSTOMER_EPOST, (String) customersList.get(brukerNummer).getEmail());

                        errorText.setText("Logget inn");
                        klar = false;
                        startActivity(intent);

                    } else {
                        errorText.setText("Feilt passord...");
                        klar = false;
                    }
                }
            }

            @Override
            public void failure(RetrofitError error) {
                errorText.setText("Får ikke lov...DUST");
            }
        });
    }








}
