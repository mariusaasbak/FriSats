package com.example.kristoffer.newazureuser;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button loginButton,registrerButton,oversiktButton,opprettGruppeButton,customerOversiktButton,
                    providerOversiktButton,opprettProviderButton,serviceOversiktButton,opprettServiceButton,
                    ordreOversiktButton,opprettOrdreButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().permitAll().build());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Hjelp deg selv hahaha! \nMed denne appen får du nok den hjelpen du trenger.", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

            }
        });

        loginButton =               (Button) findViewById(R.id.logginn_btn);
        registrerButton =           (Button) findViewById(R.id.registrer_btn);
        oversiktButton =            (Button) findViewById(R.id.oversikt_btn);
        opprettGruppeButton =       (Button) findViewById(R.id.opprett_gruppe_btn);
        providerOversiktButton =    (Button) findViewById(R.id.provider_oversikt_btn);
        opprettProviderButton =     (Button) findViewById(R.id.opprett_provider_btn);
        serviceOversiktButton =     (Button) findViewById(R.id.service_oversikt_btn);
        opprettServiceButton =      (Button) findViewById(R.id.opprett_service_btn);
        customerOversiktButton =    (Button) findViewById(R.id.brukere_oversikt_btn);
        ordreOversiktButton =       (Button) findViewById(R.id.ordre_oversikt_btn);
        opprettOrdreButton =        (Button) findViewById(R.id.opprett_ordre_btn);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
            }
        });

        registrerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, RegisterNewCustomerActivity.class));
            }
        });

        oversiktButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, OversiktActivity.class));
            }
        });

        opprettGruppeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, OpprettGruppeActivity.class));
            }
        });

        providerOversiktButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, OversiktProviderActivity.class));
            }
        });

        opprettProviderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, OpprettProviderActivity.class));
            }
        });

        serviceOversiktButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, OversiktServiceActivity.class));
            }
        });

        opprettServiceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, OpprettServiceActivity.class));
            }
        });

        customerOversiktButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, OversiktCustumersActivity.class));
            }
        });

        ordreOversiktButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, OversiktOrdersActivity.class));
            }
        });

        opprettOrdreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, OpprettOrderActivity.class));
            }
        });
    }












    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
