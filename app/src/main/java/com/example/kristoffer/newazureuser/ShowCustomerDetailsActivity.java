package com.example.kristoffer.newazureuser;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class ShowCustomerDetailsActivity extends AppCompatActivity {

    private TextView customerCustomerIDTextView,customerUserIDTextView,customerFirstNameTextView,customerLastNameTextView,
            customerAddressTextView,customerPostNumberTextView,customerLocationTextView,customerTelephoneTextView,
            customerLatitudeTextView,customerLongitudeTextView,customerEmailTextView,customerRatingTextView;

    private String customerID,customerUserID,customerFirstName,customerLastName,customerAddress,customerPostNumber,
            customerLocation,customerTelephone,customerLatitude,customerLongitude,customerEmail,customerRating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_customer_details);
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

        customerCustomerIDTextView =    (TextView) findViewById(R.id.customer_details_customer_ID_textView);
        customerUserIDTextView =        (TextView) findViewById(R.id.customer_details_user_ID_textView);
        customerFirstNameTextView =     (TextView) findViewById(R.id.customer_details_first_name_textView);
        customerLastNameTextView =      (TextView) findViewById(R.id.customer_details_last_name_textView);
        customerAddressTextView =       (TextView) findViewById(R.id.customer_details_address_textView);
        customerPostNumberTextView =    (TextView) findViewById(R.id.customer_details_post_number_textView);
        customerLocationTextView =      (TextView) findViewById(R.id.customer_details_location_textView);
        customerTelephoneTextView =     (TextView) findViewById(R.id.customer_details_telephone_textView);
        customerLatitudeTextView =      (TextView) findViewById(R.id.customer_details_latitude_textView);
        customerLongitudeTextView =     (TextView) findViewById(R.id.customer_details_longitude_textView);
        customerEmailTextView =         (TextView) findViewById(R.id.customer_details_email_textView);
        customerRatingTextView =        (TextView) findViewById(R.id.customer_details_rating_textView);

        Intent intent = getIntent();

        customerID = String.valueOf(intent.getIntExtra(OversiktCustumersActivity.KEY_CUSTOMER_ID, 0));
        customerUserID = String.valueOf(intent.getStringExtra(OversiktCustumersActivity.KEY_CUSTOMER_USER_ID));
        customerFirstName = String.valueOf(intent.getStringExtra(OversiktCustumersActivity.KEY_CUSTOMER_FIRST_NAME));
        customerLastName = intent.getStringExtra(OversiktCustumersActivity.KEY_CUSTOMER_LAST_NAME);
        customerAddress = String.valueOf(intent.getStringExtra(OversiktCustumersActivity.KEY_CUSTOMER_ADDRESS));
        customerPostNumber = String.valueOf(intent.getStringExtra(OversiktCustumersActivity.KEY_CUSTOMER_POST_NUMBER));
        customerLocation = String.valueOf(intent.getStringExtra(OversiktCustumersActivity.KEY_CUSTOMER_LOCATION));
        customerTelephone = String.valueOf(intent.getStringExtra(OversiktCustumersActivity.KEY_CUSTOMER_TELEPHONE));
        customerLatitude = String.valueOf(intent.getStringExtra(OversiktCustumersActivity.KEY_CUSTOMER_LATITUDE));
        customerLongitude = String.valueOf(intent.getStringExtra(OversiktCustumersActivity.KEY_CUSTOMER_LONGITUDE));
        customerEmail = String.valueOf(intent.getStringExtra(OversiktCustumersActivity.KEY_CUSTOMER_EMAIL));
        customerRating = String.valueOf(intent.getStringExtra(OversiktCustumersActivity.KEY_CUSTOMER_RATING));

        customerCustomerIDTextView.setText("Kunde ID: " + customerID);
        customerUserIDTextView.setText("Bruker ID: " + customerUserID);
        customerFirstNameTextView.setText("Fornavn: " + customerFirstName);
        customerLastNameTextView.setText("Etternavn: " + customerLastName);
        customerAddressTextView.setText("Adresse: " + customerAddress);
        customerPostNumberTextView.setText("Postnummer: " + customerPostNumber);
        customerLocationTextView.setText("Lokasjon: " + customerLocation);
        customerTelephoneTextView.setText("Telefonnummer: " + customerTelephone);
        customerLatitudeTextView.setText("Latitude: " + customerLatitude);
        customerLongitudeTextView.setText("Longitude: " + customerLongitude);
        customerEmailTextView.setText("E-post: " + customerEmail);
        customerRatingTextView.setText("Rating: " + customerRating);

    }

}
