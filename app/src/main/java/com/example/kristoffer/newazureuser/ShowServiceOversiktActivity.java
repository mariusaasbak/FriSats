package com.example.kristoffer.newazureuser;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class ShowServiceOversiktActivity extends AppCompatActivity {

    private TextView showServiceIDTextView,showServiceNameTextView,showServiceDescriptionTextView,showServiceGroupNameTextView,
            showServiceUnitPriceTextView,showServiceProviderIDTextView,showServiceGroupIDTextView,
            showServiceAvailFromTextView,showServiceAvailToTextView,showServiceProviderTextView;

    private String serviceID,serviceName,serviceDescription,serviceGroupName,serviceUnitPrice,serviceProviderID,
            serviceGroupID,serviceAvailFrom,serviceAvailTo,serviceProvider;

    private ImageView serviceBilde;
    private String url = "http://frisats3.azurewebsites.net";
    private String imageFullURL,serviceBildeURL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_service_oversikt);
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

        showServiceIDTextView =             (TextView) findViewById(R.id.show_service_ID_textView);
        showServiceNameTextView =           (TextView) findViewById(R.id.show_service_navn_textView);
        showServiceDescriptionTextView =    (TextView) findViewById(R.id.show_service_beskrivelse_textView);
        showServiceGroupNameTextView =      (TextView) findViewById(R.id.show_service_gruppe_navn_textView);
        showServiceUnitPriceTextView =      (TextView) findViewById(R.id.show_service_unit_price_textView);
        showServiceProviderIDTextView =     (TextView) findViewById(R.id.show_service_provider_ID_textView);
        showServiceGroupIDTextView =        (TextView) findViewById(R.id.show_service_gruppe_ID_textView);
        showServiceAvailFromTextView =      (TextView) findViewById(R.id.show_service_avail_from_textView);
        showServiceAvailToTextView =        (TextView) findViewById(R.id.show_service_avail_to_textView);
        showServiceProviderTextView =       (TextView) findViewById(R.id.show_service_provider_textView);

        serviceBilde =                      (ImageView) findViewById(R.id.show_service_image_view);

        Intent intent = getIntent();

        serviceID = String.valueOf(intent.getIntExtra(OversiktServiceActivity.KEY_SERVICE_OVERSIKT_ID, 0));
        serviceName = intent.getStringExtra(OversiktServiceActivity.KEY_SERVICE_OVERSIKT_SERVICE_NAME);
        serviceDescription = intent.getStringExtra(OversiktServiceActivity.KEY_SERVICE_OVERSIKT_DESCRIPTION);
        serviceGroupName = intent.getStringExtra(OversiktServiceActivity.KEY_SERVICE_OVERSIKT_GROUP_NAME);
        serviceUnitPrice = String.valueOf(intent.getDoubleExtra(OversiktServiceActivity.KEY_SERVICE_OVERSIKT_UNIT_PRICE, 0.0));
        serviceBildeURL = String.valueOf(intent.getStringArrayExtra(OversiktServiceActivity.KEY_SERVICE_OVERISKT_IMAGE_URL));
        serviceProviderID = String.valueOf(intent.getIntExtra(OversiktServiceActivity.KEY_SERVICE_OVERSIKT_PROVIDER_ID, 0));
        serviceGroupID = String.valueOf(intent.getIntExtra(OversiktServiceActivity.KEY_SERVICE_OVERSIKT_SERVICE_GROUP_ID, 0));
        serviceAvailFrom = intent.getStringExtra(OversiktServiceActivity.KEY_SERVICE_OVERSIKT_AVAIL_FROM);
        serviceAvailTo = intent.getStringExtra(OversiktServiceActivity.KEY_SERVICE_OVERSIKT_AVAIL_TO);
        serviceProvider = intent.getStringExtra(OversiktServiceActivity.KEY_SERVICE_OVERSIKT_PROVIDER);

        showServiceIDTextView.setText("Service ID: " + serviceID);
        showServiceNameTextView.setText("Service Name: " + serviceName);
        showServiceDescriptionTextView.setText("Description: " + serviceDescription);
        showServiceGroupNameTextView.setText("Service Group Name: " + serviceGroupName);
        showServiceUnitPriceTextView.setText("Service Unit Price: " + serviceUnitPrice + ",-");
        showServiceProviderIDTextView.setText("Service Provider ID: " + serviceProviderID);
        showServiceGroupIDTextView.setText("Service Group ID: " + serviceGroupID);
        showServiceAvailFromTextView.setText("Avail from: " + serviceAvailFrom);
        showServiceAvailToTextView.setText("Avail to: " + serviceAvailTo);
        showServiceProviderTextView.setText("Service Provider: " + serviceProvider);

        imageFullURL = url+serviceBildeURL;
        loadServiceImage();
    }

    public void loadServiceImage(){
        Picasso.with(ShowServiceOversiktActivity.this)
                .load(imageFullURL)
                .resize(100,100)
                .into(serviceBilde);
    }

}
