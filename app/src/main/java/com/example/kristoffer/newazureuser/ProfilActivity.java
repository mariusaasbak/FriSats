package com.example.kristoffer.newazureuser;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class ProfilActivity extends AppCompatActivity {

    private TextView profilFornavnTextView,profilEtternavnTextView,profilAdresseTextView,
            profilPostNummerTextView,profilLocationTextView,profilTelefonNummerTextView,profilEpostTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);
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

        profilFornavnTextView = (TextView) findViewById(R.id.textView_profil_fornavn_txt);
        profilEtternavnTextView = (TextView) findViewById(R.id.textView_profil_etternavn_txt);
        profilAdresseTextView = (TextView) findViewById(R.id.textView_profil_adresse_txt);
        profilPostNummerTextView = (TextView) findViewById(R.id.textView_profil_post_nummer_txt);
        profilLocationTextView = (TextView) findViewById(R.id.textView_profil_location_txt);
        profilTelefonNummerTextView = (TextView) findViewById(R.id.textView_profil_telefon_nummer_txt);
        profilEpostTextView = (TextView) findViewById(R.id.textView_profil_epost_txt);

        Intent intent = getIntent();

        profilFornavnTextView.setText(intent.getStringExtra(HovedMenyActivity.KEY_CUSTOMER_FIRSTNAME));
        profilEtternavnTextView.setText(intent.getStringExtra(HovedMenyActivity.KEY_CUSTOMER_LASTNAME));
        profilAdresseTextView.setText(intent.getStringExtra(HovedMenyActivity.KEY_CUSTOMER_ADRESSE));
        profilPostNummerTextView.setText(intent.getStringExtra(HovedMenyActivity.KEY_CUSTOMER_POSTNUMMER));
        profilLocationTextView.setText(intent.getStringExtra(HovedMenyActivity.KEY_CUSTOMER_LOCATION));
        profilTelefonNummerTextView.setText(intent.getStringExtra(HovedMenyActivity.KEY_CUSTOMER_TELEFON));
        profilEpostTextView.setText(intent.getStringExtra(HovedMenyActivity.KEY_CUSTOMER_EPOST));

    }

}
