package com.example.kristoffer.newazureuser;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HovedMenyActivity extends AppCompatActivity {

    public static final String KEY_CUSTOMER_FIRSTNAME = "key_customer_firstname";
    public static final String KEY_CUSTOMER_LASTNAME = "key_customer_lastname";
    public static final String KEY_CUSTOMER_ADRESSE = "key_customer_adresse";
    public static final String KEY_CUSTOMER_POSTNUMMER = "key_customer_postnummer";
    public static final String KEY_CUSTOMER_LOCATION = "key_customer_location";
    public static final String KEY_CUSTOMER_TELEFON = "key_customer_telefon";
    public static final String KEY_CUSTOMER_EPOST = "key_customer_email";

    private TextView loggetInnSomTextView;
    private String brukernavnLoggetInn,passordLoggetInn,adresseLoggetInn,postNummerLoggetInn,
                    locationLoggetInn,telefonNummerLoggetInn,epostLoggetInn;
    private Button profilButton,loggUtButton,nyTjenesteButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hoved_meny);
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

        loggetInnSomTextView = (TextView) findViewById(R.id.logget_inn_som_txt);
        profilButton = (Button) findViewById(R.id.profil_btn);
        loggUtButton = (Button) findViewById(R.id.logg_ut_btn);
        nyTjenesteButton = (Button) findViewById(R.id.ny_tjeneste_btn);


        Intent intent = getIntent();

        brukernavnLoggetInn = intent.getStringExtra(LoginActivity.KEY_CUSTOMER_FIRSTNAME);
        passordLoggetInn = intent.getStringExtra(LoginActivity.KEY_CUSTOMER_LASTNAME);
        adresseLoggetInn = intent.getStringExtra(LoginActivity.KEY_CUSTOMER_ADRESSE);
        postNummerLoggetInn = intent.getStringExtra(LoginActivity.KEY_CUSTOMER_POSTNUMMER);
        locationLoggetInn = intent.getStringExtra(LoginActivity.KEY_CUSTOMER_LOCATION);
        telefonNummerLoggetInn = intent.getStringExtra(LoginActivity.KEY_CUSTOMER_TELEFON);
        epostLoggetInn = intent.getStringExtra(LoginActivity.KEY_CUSTOMER_EPOST);

        loggetInnSomTextView.setText("Logget inn som:\n"+brukernavnLoggetInn+" "+passordLoggetInn);



        profilButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                visProfil();
            }
        });
        nyTjenesteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HovedMenyActivity.this, NyTjenesteActivity.class));
            }
        });

    }

    public void visProfil() {
        Intent intent = new Intent(HovedMenyActivity.this, ProfilActivity.class);
        intent.putExtra(KEY_CUSTOMER_FIRSTNAME, brukernavnLoggetInn);
        intent.putExtra(KEY_CUSTOMER_LASTNAME, passordLoggetInn);
        intent.putExtra(KEY_CUSTOMER_ADRESSE, adresseLoggetInn);
        intent.putExtra(KEY_CUSTOMER_POSTNUMMER, postNummerLoggetInn);
        intent.putExtra(KEY_CUSTOMER_LOCATION, locationLoggetInn);
        intent.putExtra(KEY_CUSTOMER_TELEFON, telefonNummerLoggetInn);
        intent.putExtra(KEY_CUSTOMER_EPOST, epostLoggetInn);

        startActivity(intent);
    }



}
