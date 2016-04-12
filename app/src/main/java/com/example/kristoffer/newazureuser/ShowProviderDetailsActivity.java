package com.example.kristoffer.newazureuser;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class ShowProviderDetailsActivity extends AppCompatActivity {


    private TextView providerIDTextView,providerFirstNameTextView,providerLastNameTextView,providerCompanyNameTextView,
            providerAddressTextView,providerPostNumberTextView,providerLocationTextView,providerTelephoneTextView,
            providerLatitudeTextView,providerLongitudeTextView,providerEmailTextView,providerRatingTextView,providerDetaljerTextView;
    private String providerID,providerFirstName,providerLastName,providerCompanyName,providerAddress,providerPostNumber,
            providerLocation,providerTelephone,providerLatitude,providerLongitude,providerEmail,providerRating;
    //private Double providerLatitude,providerLongitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_provider_details);
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

        providerIDTextView =            (TextView) findViewById(R.id.provider_details_ID_textView);
        providerFirstNameTextView =     (TextView) findViewById(R.id.provider_details_first_name_textView);
        providerLastNameTextView =      (TextView) findViewById(R.id.provider_details_last_name_textView);
        providerCompanyNameTextView =   (TextView) findViewById(R.id.provider_details_company_name_textView);
        providerAddressTextView =       (TextView) findViewById(R.id.provider_details_address_textView);
        providerPostNumberTextView =    (TextView) findViewById(R.id.provider_details_post_number_textView);
        providerLocationTextView =      (TextView) findViewById(R.id.provider_details_location_textView);
        providerTelephoneTextView =     (TextView) findViewById(R.id.provider_details_telephone_textView);
        providerLatitudeTextView =      (TextView) findViewById(R.id.provider_details_latitude_textView);
        providerLongitudeTextView =     (TextView) findViewById(R.id.provider_details_longitude_textView);
        providerEmailTextView =         (TextView) findViewById(R.id.provider_details_email_textView);
        providerRatingTextView =        (TextView) findViewById(R.id.provider_details_rating_textView);
        providerDetaljerTextView =      (TextView) findViewById(R.id.provider_details_detaljer_textView);

        Intent intent = getIntent();

        providerID =            String.valueOf(intent.getIntExtra(OversiktProviderActivity.KEY_PROVIDER_ID, 0));
        providerFirstName =     String.valueOf(intent.getStringExtra(OversiktProviderActivity.KEY_PROVIDER_FIRST_NAME));
        providerLastName =      String.valueOf(intent.getStringExtra(OversiktProviderActivity.KEY_PROVIDER_LAST_NAME));
        providerCompanyName =   String.valueOf(intent.getStringExtra(OversiktProviderActivity.KEY_PROVIDER_COMPANY_NAME));
        providerAddress =       String.valueOf(intent.getStringExtra(OversiktProviderActivity.KEY_PROVIDER_ADDRESS));
        providerPostNumber =    String.valueOf(intent.getStringExtra(OversiktProviderActivity.KEY_PROVIDER_POST_NUMBER));
        providerLocation =      String.valueOf(intent.getStringExtra(OversiktProviderActivity.KEY_PROVIDER_LOCATION));
        providerTelephone =     String.valueOf(intent.getStringExtra(OversiktProviderActivity.KEY_PROVIDER_TELEPHONE));
        providerLatitude =      String.valueOf(intent.getDoubleExtra(OversiktProviderActivity.KEY_PROVIDER_LATITUDE, 0.0));
        providerLongitude =     String.valueOf(intent.getDoubleExtra(OversiktProviderActivity.KEY_PROVIDER_LONGITUDE, 0.0));
        providerEmail =         String.valueOf(intent.getStringExtra(OversiktProviderActivity.KEY_PROVIDER_EMAIL));
        providerRating =        String.valueOf(intent.getIntExtra(OversiktProviderActivity.KEY_PROVIDER_RATING, 0));


        providerDetaljerTextView.setText("Detaljer for " + providerFirstName + " " + providerLastName + ": ");

        providerIDTextView.         setText("ID:\r\r\r\r" + providerID);
        providerFirstNameTextView.  setText("Fornavn:\r\r" + providerFirstName);
        providerLastNameTextView.   setText("Etternavn:\r\r" + providerLastName);
        providerCompanyNameTextView.setText("Firmanavn:\r\r" + providerCompanyName);
        providerAddressTextView.    setText("Adresse:\r\r" + providerAddress);
        providerPostNumberTextView. setText("Postnummer:\r" + providerPostNumber);
        providerLocationTextView.   setText("Lokasjon:\r\r" + providerLocation);
        providerTelephoneTextView.  setText("Telefon:\r\r" + providerTelephone);
        providerLatitudeTextView.   setText("Latitude:\r\r" + providerLatitude);
        providerLongitudeTextView.  setText("Longitude:\r" + providerLongitude);
        providerEmailTextView.      setText("Epost:\r\r" + providerEmail);
        providerRatingTextView.     setText("Rating:\r\r" + providerRating);



    }

}
