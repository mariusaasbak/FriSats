package com.example.kristoffer.newazureuser;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class ShowOrdersDetailsActivity extends AppCompatActivity {

    private TextView orderDetailsIDTextView,orderDetailsStartTimeTextView,orderDetailsEndTimeTextView,orderDetailsLocationTextView,
            orderDetailsPriceTextView,ordersServiceImageURLTextView,orderDetailsServiceIDTextView,orderDetailsUserIDTextView,
                    ordersServiceIDTextView,ordersServiceNameTextView,ordersServiceDescriptionTextView,ordersServiceGroupNameTextView,
                    ordersServiceUnitPriceTextView,ordersServiceProviderIDTextView,ordersServiceGroupIDTextView,
                    ordersServiceAvailFromTextView,ordersServiceAvailToTextView,ordersServiceProviderTextView,
            orderDetailsCustomerTextView;

    private String orderDetailsID,orderDetailsStartTime,orderDetailsEndTime,orderDetailsLocation,
                   orderDetailsPrice,orderDetailsServiceID,orderDetailsUserID,
                        ordersServiceID,ordersServiceName,ordersServiceDescription,ordersServiceGroupName,
                        ordersServiceUnitPrice,ordersServiceImageURL,ordersServiceProviderID,ordersServiceGroupID,
                        ordersServiceAvailFrom,ordersServiceAvailTo,ordersServiceProvider,
            orderDetailsCustomer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_orders_details);
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

        orderDetailsIDTextView =            (TextView) findViewById(R.id.order_details_ID);
        orderDetailsStartTimeTextView =     (TextView) findViewById(R.id.order_details_start_time);
        orderDetailsEndTimeTextView =       (TextView) findViewById(R.id.order_details_end_time);
        orderDetailsLocationTextView =      (TextView) findViewById(R.id.order_details_location);
        orderDetailsPriceTextView =         (TextView) findViewById(R.id.order_details_price);
        orderDetailsServiceIDTextView =     (TextView) findViewById(R.id.orders_details_service_ID);
        orderDetailsUserIDTextView =        (TextView) findViewById(R.id.orders_details_user_ID);
         ordersServiceIDTextView =           (TextView) findViewById(R.id.orders_service_ID);
         ordersServiceNameTextView =         (TextView) findViewById(R.id.orders_service_name);
         ordersServiceDescriptionTextView =  (TextView) findViewById(R.id.orders_service_description);
         ordersServiceGroupNameTextView =    (TextView) findViewById(R.id.orders_service_group_name);
         ordersServiceUnitPriceTextView =    (TextView) findViewById(R.id.orders_service_unit_price);
         ordersServiceImageURLTextView =     (TextView) findViewById(R.id.orders_service_image_URL);
         ordersServiceProviderIDTextView =   (TextView) findViewById(R.id.orders_service_provider_ID);
         ordersServiceGroupIDTextView =      (TextView) findViewById(R.id.orders_service_group_ID);
         ordersServiceAvailFromTextView =    (TextView) findViewById(R.id.orders_service_avail_from);
         ordersServiceAvailToTextView =      (TextView) findViewById(R.id.orders_service_avail_to);
         ordersServiceProviderTextView =     (TextView) findViewById(R.id.orders_service_provider);
        orderDetailsCustomerTextView =      (TextView) findViewById(R.id.order_details_customer);


        Intent intent = getIntent();
        orderDetailsID = String.valueOf(intent.getIntExtra(OversiktOrdersActivity.KEY_ORDERS_OVERSIKT_ID, 0));
        orderDetailsStartTime = intent.getStringExtra(OversiktOrdersActivity.KEY_ORDERS_OVERSIKT_START_TIME);
        orderDetailsEndTime = intent.getStringExtra(OversiktOrdersActivity.KEY_ORDERS_OVERSIKT_END_TIME);
        orderDetailsLocation = String.valueOf(intent.getStringExtra(OversiktOrdersActivity.KEY_ORDERS_OVERSIKT_LOCATION));
        orderDetailsPrice = String.valueOf(intent.getDoubleExtra(OversiktOrdersActivity.KEY_ORDERS_OVERSIKT_PRICE, 0.0));
        orderDetailsServiceID = String.valueOf(intent.getIntExtra(OversiktOrdersActivity.KEY_ORDERS_OVERSIKT_SERVICE_ID, 0));
        orderDetailsUserID = intent.getStringExtra(OversiktOrdersActivity.KEY_ORDERS_OVERSIKT_USER_ID);
         ordersServiceID = String.valueOf(intent.getIntExtra(OversiktOrdersActivity.KEY_SERVICE_OVERSIKT_ID, 0));
         ordersServiceName = intent.getStringExtra(OversiktOrdersActivity.KEY_SERVICE_OVERSIKT_SERVICE_NAME);
         ordersServiceDescription = intent.getStringExtra(OversiktOrdersActivity.KEY_SERVICE_OVERSIKT_DESCRIPTION);
         ordersServiceGroupName = intent.getStringExtra(OversiktOrdersActivity.KEY_SERVICE_OVERSIKT_GROUP_NAME);
         ordersServiceUnitPrice = String.valueOf(intent.getDoubleExtra(OversiktOrdersActivity.KEY_SERVICE_OVERSIKT_UNIT_PRICE, 0.0));
         ordersServiceImageURL = String.valueOf(intent.getStringArrayExtra(OversiktOrdersActivity.KEY_SERVICE_OVERISKT_IMAGE_URL));
         ordersServiceProviderID = String.valueOf(intent.getIntExtra(OversiktOrdersActivity.KEY_SERVICE_OVERSIKT_PROVIDER_ID, 0));
         ordersServiceGroupID = String.valueOf(intent.getIntExtra(OversiktOrdersActivity.KEY_SERVICE_OVERSIKT_SERVICE_GROUP_ID, 0));
         ordersServiceAvailFrom = intent.getStringExtra(OversiktOrdersActivity.KEY_SERVICE_OVERSIKT_AVAIL_FROM);
         ordersServiceAvailTo = intent.getStringExtra(OversiktOrdersActivity.KEY_SERVICE_OVERSIKT_AVAIL_TO);
         ordersServiceProvider = intent.getStringExtra(OversiktOrdersActivity.KEY_SERVICE_OVERSIKT_PROVIDER);
        orderDetailsCustomer = String.valueOf(intent.getStringExtra(OversiktOrdersActivity.KEY_ORDERS_OVERSIKT_CUSTOMER));


        orderDetailsIDTextView.setText("Order ID: " + orderDetailsID);
        orderDetailsStartTimeTextView.setText("Start time: " + orderDetailsStartTime);
        orderDetailsEndTimeTextView.setText("End time: " + orderDetailsEndTime);
        orderDetailsLocationTextView.setText("Location: " + orderDetailsLocation);
        orderDetailsPriceTextView.setText("Pris: " + orderDetailsPrice);
        orderDetailsServiceIDTextView.setText("Order Service ID: " + orderDetailsServiceID);
        orderDetailsUserIDTextView.setText("User ID: " + orderDetailsUserID);
         ordersServiceIDTextView.setText("Service ID: " + ordersServiceID);
         ordersServiceNameTextView.setText("Service Name: " + ordersServiceName);
         ordersServiceDescriptionTextView.setText("Service Description: " + ordersServiceDescription);
         ordersServiceGroupNameTextView.setText("Service Group Name: " + ordersServiceGroupName);
         ordersServiceUnitPriceTextView.setText("Service Unit Price: " + ordersServiceUnitPrice);
         ordersServiceImageURLTextView.setText("Service Image URL: " + ordersServiceImageURL);
         ordersServiceProviderIDTextView.setText("Service Provider ID: " + ordersServiceProviderID);
         ordersServiceGroupIDTextView.setText("Service Group ID: " + ordersServiceGroupID);
         ordersServiceAvailFromTextView.setText("Service Avail From: " + ordersServiceAvailFrom);
         ordersServiceAvailToTextView.setText("Service Avail To: " + ordersServiceAvailTo);
         ordersServiceProviderTextView.setText("Service Provider: " + ordersServiceProvider);
        orderDetailsCustomerTextView.setText("Customer: " + orderDetailsCustomer);
    }
}
