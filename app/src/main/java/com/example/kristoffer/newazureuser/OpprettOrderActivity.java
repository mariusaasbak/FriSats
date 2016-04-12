package com.example.kristoffer.newazureuser;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.kristoffer.newazureuser.POJO.Customers;
import com.example.kristoffer.newazureuser.POJO.Groups;
import com.example.kristoffer.newazureuser.POJO.Orders;
import com.example.kristoffer.newazureuser.POJO.Providers;
import com.example.kristoffer.newazureuser.POJO.Services;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class OpprettOrderActivity extends AppCompatActivity {

    private Spinner spinnerOrderCustomer,spinnerOrderService;
    private String url = "http://frisats3.azurewebsites.net";
    private EditText opprettOrderLocationEditText,opprettOrderPriceEditText;
    private EditText dateAvailFromEditText,dateAvailToEditText,timeAvailFromEditText,timeAvailToEditText;
    private String dateAvailFrom,dateAvailTo,timeAvailFrom,timeAvailTo;
    private Button opprettOrderButton;
    public int customerID,serviceID;
    private String customerFirstName,serviceName;

    private String nyOrderID,nyOrderAvailFrom,nyOrderAvailTo,nyOrderLocation,nyOrderPrice;

    private List<Customers> customerListe;
    private List<Services> serviceListe;
    private final List<String> categoriesCustomer = new ArrayList<String>();
    private final List<String> categoriesService = new ArrayList<String>();

    private final Orders newOrder = new Orders();
    private final Services theService = new Services();
    private final Customers theCustomer = new Customers();

    public final Calendar c = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opprett_order);
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

        spinnerOrderCustomer = (Spinner) findViewById(R.id.spinner_order_customer);
        getTheCustomers();
        categoriesCustomer.add("-------------Velg en Customer-------------");
        onCustomerClick();

        spinnerOrderService = (Spinner) findViewById(R.id.spinner_order_service);
        getTheServices();
         categoriesService.add("-------------Velg en Tjeneste---------------");
        onServiceClick();

        opprettOrderLocationEditText = (EditText) findViewById(R.id.opprett_order_location);
        opprettOrderPriceEditText = (EditText) findViewById(R.id.opprett_order_price);
        dateAvailFromEditText = (EditText) findViewById(R.id.order_date_avail_from);
        dateAvailToEditText = (EditText) findViewById(R.id.order_date_avail_to);
        timeAvailFromEditText = (EditText) findViewById(R.id.order_time_avail_from);
        timeAvailToEditText = (EditText) findViewById(R.id.order_time_avail_to);
        opprettOrderButton = (Button) findViewById(R.id.opprett_order_btn);

        opprettOrderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opprettOrderNow();
            }
        });
    }


    private void opprettOrderNow() {
        nyOrderLocation = opprettOrderLocationEditText.getText().toString();
        nyOrderPrice = String.valueOf(opprettOrderPriceEditText.getText());


        final ProgressDialog loading = ProgressDialog.show(this, "Oppretter Order", "Vennligst vent...", false, false);

        RestAdapter adapter = new RestAdapter.Builder().setEndpoint(url).build();
        final RestInterface restInterface = adapter.create(RestInterface.class);



        newOrder.setStartTime(nyOrderAvailFrom);
        newOrder.setEndTime(nyOrderAvailTo);
        newOrder.setLocation(nyOrderLocation);
        newOrder.setPrice(Double.parseDouble(nyOrderPrice));
        newOrder.setServiceId(serviceID);
        newOrder.setUserID("9af174e1-918e-4e01-88ba-425fd20dda8d");
        //newOrder.setService(newOrder.getService());
        //newOrder.setCustomer(newOrder.getCustomer());

        //Toast.makeText(getApplicationContext(), newOrder.getService().toString(), Toast.LENGTH_LONG).show();

        restInterface.createNewOrder(newOrder, new Callback<Orders>() {
            @Override
            public void success(Orders orders, Response response) {
                loading.dismiss();
                Toast.makeText(getApplicationContext(), "Orderen er opprettet", Toast.LENGTH_LONG).show();
            }

            @Override
            public void failure(RetrofitError error) {
                throw new RuntimeException("Error! " + error.getMessage());
                //throw new NetworkException();
            }
        });

        opprettOrderLocationEditText.setText("");
        opprettOrderPriceEditText.setText("");
        dateAvailFromEditText.setText("");
        dateAvailToEditText.setText("");
        timeAvailFromEditText.setText("");
        timeAvailToEditText.setText("");

    }


    private void getTheCustomers(){
        final ProgressDialog loading = ProgressDialog.show(this, "Henter Customer-oversikt", "Vennligst vent...", false, false);
        RestAdapter adapter = new RestAdapter.Builder().setEndpoint(url).build();
        RestInterface restInterface = adapter.create(RestInterface.class);
        restInterface.getCustomers(new Callback<List<Customers>>() {
            @Override
            public void success(List<Customers> list, Response response) {
                loading.dismiss();
                customerListe = list;
                for (int i = 0; i < customerListe.size(); i++) {
                    categoriesCustomer.add(customerListe.get(i).getFirstName());
                }
            }
            @Override
            public void failure(RetrofitError error) {
                //you can handle the errors here
            }
        });
    }

    private void getTheServices(){
        final ProgressDialog loading = ProgressDialog.show(this, "Henter Service-oversikt", "Vennligst vent...", false, false);
        RestAdapter adapter = new RestAdapter.Builder().setEndpoint(url).build();
        RestInterface restInterface = adapter.create(RestInterface.class);
        restInterface.getServices(new Callback<List<Services>>() {
            @Override
            public void success(List<Services> list, Response response) {
                loading.dismiss();
                serviceListe = list;
                for (int i = 0; i < serviceListe.size(); i++) {
                    categoriesService.add(serviceListe.get(i).getServiceName());
                }
            }
            @Override
            public void failure(RetrofitError error) {
                //you can handle the errors here
            }
        });
    }



    public void onCustomerClick() {
        spinnerOrderCustomer.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id)
            {
                final String item = parentView.getItemAtPosition(position).toString();
                RestAdapter adapter = new RestAdapter.Builder().setEndpoint(url).build();
                RestInterface restInterface = adapter.create(RestInterface.class);
                restInterface.getCustomers(new Callback<List<Customers>>() {
                    @Override
                    public void success(List<Customers> list, Response response) {
                        customerListe = list;
                        customerID = 0;
                        customerFirstName = item;
                        String firstNameCustomer;
                        for (int i = 0; i < customerListe.size(); i++) {
                            firstNameCustomer = customerListe.get(i).getFirstName();
                            if (String.valueOf(firstNameCustomer).equals(String.valueOf(item))) { //Ingen navn..NULL POINT = fixet
                                customerID = customerListe.get(i).getCustomerId();

                                theCustomer.setCustomerId(customerListe.get(i).getCustomerId());
                                theCustomer.setFirstName(customerListe.get(i).getFirstName());
                                theCustomer.setLastName(customerListe.get(i).getLastName());
                                theCustomer.setAddress(customerListe.get(i).getAddress());
                                theCustomer.setPostNo(customerListe.get(i).getPostNo());
                                theCustomer.setLocation(customerListe.get(i).getLocation());
                                theCustomer.setTelephone(customerListe.get(i).getTelephone());
                                theCustomer.setLatitude(customerListe.get(i).getLatitude());
                                theCustomer.setLongitude(customerListe.get(i).getLongitude());
                                theCustomer.setEmail(customerListe.get(i).getEmail());
                                theCustomer.setRating(customerListe.get(i).getRating());

                                //newOrder.setCustomer(theCustomer);

                                Toast.makeText(getApplicationContext(), "Customer: " + customerFirstName + ". ID: " + customerID, Toast.LENGTH_SHORT).show();
                                break;
                            }
                        }
                    }
                    @Override
                    public void failure(RetrofitError error) {
                        //you can handle the errors here
                    }
                });
            }
            public void onNothingSelected(AdapterView<?> parentView) {
                //spinnerServiceProvider = 0;
            }
        });
        ArrayAdapter<String> dataCustomerAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, categoriesCustomer);
        dataCustomerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerOrderCustomer.setAdapter(dataCustomerAdapter);
    }

    public void onServiceClick() {
        spinnerOrderService.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id)
            {
                final String item = parentView.getItemAtPosition(position).toString();
                RestAdapter adapter = new RestAdapter.Builder().setEndpoint(url).build();
                RestInterface restInterface = adapter.create(RestInterface.class);
                restInterface.getServices(new Callback<List<Services>>() {
                    @Override
                    public void success(List<Services> list, Response response) {
                        serviceListe = list;
                        serviceID = 0;
                        serviceName = item;
                        String nameOfService;
                        for (int i = 0; i < serviceListe.size(); i++) {
                            nameOfService = serviceListe.get(i).getServiceName();
                            if (String.valueOf(nameOfService).equals(String.valueOf(item))) {
                                serviceID = serviceListe.get(i).getId();
                                /*newOrder.getService().setId(i);
                                newOrder.getService().setServiceName(serviceListe.get(i).getServiceName());
                                newOrder.getService().setDescription(serviceListe.get(i).getDescription());
                                newOrder.getService().setGroupName(serviceListe.get(i).getGroupName());
                                newOrder.getService().setUnitPrice(serviceListe.get(i).getUnitPrice());
                                newOrder.getService().setImageUrl(serviceListe.get(i).getImageUrl());
                                newOrder.getService().setProviderId(serviceListe.get(i).getProviderId());
                                newOrder.getService().setServiceGroupId(serviceListe.get(i).getServiceGroupId());
                                newOrder.getService().setAvailFrom(serviceListe.get(i).getAvailFrom());
                                newOrder.getService().setAvailTo(serviceListe.get(i).getAvailTo());
                                newOrder.getService().setProvider(serviceListe.get(i).getProvider());*/

                                theService.setId(i);
                                theService.setServiceName(serviceListe.get(i).getServiceName());
                                theService.setDescription(serviceListe.get(i).getDescription());
                                theService.setGroupName(serviceListe.get(i).getGroupName());
                                theService.setUnitPrice(serviceListe.get(i).getUnitPrice());
                                theService.setImageUrl(serviceListe.get(i).getImageUrl());
                                theService.setProviderId(serviceListe.get(i).getProviderId());
                                theService.setServiceGroupId(serviceListe.get(i).getServiceGroupId());
                                theService.setAvailFrom(serviceListe.get(i).getAvailFrom());
                                theService.setAvailTo(serviceListe.get(i).getAvailTo());
                                theService.setProvider(serviceListe.get(i).getProvider());

                                //newOrder.setService(theService);

                                Toast.makeText(getApplicationContext(), "Service: " + serviceName + ". ID: " + serviceID, Toast.LENGTH_SHORT).show();
                                break;
                            }
                        }
                    }
                    @Override
                    public void failure(RetrofitError error) {
                        //you can handle the errors here
                    }
                });
            }
            public void onNothingSelected(AdapterView<?> parentView) {
                //spinnerServiceProvider = 0;
            }
        });
        ArrayAdapter<String> dataServiceAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, categoriesService);
        dataServiceAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerOrderService.setAdapter(dataServiceAdapter);
    }




    private void setCurrentDateFromOnView() {
        String dateFormat = "yyyy-MM-dd";
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat, Locale.UK);
        dateAvailFromEditText.setText(sdf.format(c.getTime()));
        dateAvailFrom = sdf.format(c.getTime());
        nyOrderAvailFrom = dateAvailFrom + "T" + timeAvailFrom;
        nyOrderAvailTo = dateAvailTo + "T" + timeAvailTo;
        Toast.makeText(getApplicationContext(), nyOrderAvailFrom + " - " + nyOrderAvailTo, Toast.LENGTH_LONG).show();
    }

    private void setCurrentDateToOnView() {
        String dateFormat = "yyyy-MM-dd";
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat, Locale.UK);
        dateAvailTo = sdf.format(c.getTime());
        dateAvailToEditText.setText(sdf.format(c.getTime()));
        nyOrderAvailFrom = dateAvailFrom + "T" + timeAvailFrom;
        nyOrderAvailTo = dateAvailTo + "T" + timeAvailTo;
        Toast.makeText(getApplicationContext(), nyOrderAvailFrom + " - " + nyOrderAvailTo, Toast.LENGTH_LONG).show();
    }

    private void setCurrentTimeFromOnView() {
        String timeFormat = "HH:mm:ss";
        SimpleDateFormat stf = new SimpleDateFormat(timeFormat, Locale.UK);
        timeAvailFromEditText.setText(stf.format(c.getTime()));
        timeAvailFrom = stf.format(c.getTime());
        nyOrderAvailFrom = dateAvailFrom + "T" + timeAvailFrom;
        nyOrderAvailTo = dateAvailTo + "T" + timeAvailTo;
        Toast.makeText(getApplicationContext(), nyOrderAvailFrom + " - " + nyOrderAvailTo, Toast.LENGTH_LONG).show();
    }

    private void setCurrentTimeToOnView() {
        String timeFormat = "HH:mm:ss";
        SimpleDateFormat stf = new SimpleDateFormat(timeFormat, Locale.UK);
        timeAvailToEditText.setText(stf.format(c.getTime()));
        timeAvailTo = stf.format(c.getTime());
        nyOrderAvailFrom = dateAvailFrom + "T" + timeAvailFrom;
        nyOrderAvailTo = dateAvailTo + "T" + timeAvailTo;
        Toast.makeText(getApplicationContext(), nyOrderAvailFrom + " - " + nyOrderAvailTo, Toast.LENGTH_LONG).show();
    }





    DatePickerDialog.OnDateSetListener dateFrom = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            c.set(Calendar.YEAR, year);
            c.set(Calendar.MONTH, monthOfYear);
            c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            setCurrentDateFromOnView();
        }
    };
    DatePickerDialog.OnDateSetListener dateTo = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            c.set(Calendar.YEAR, year);
            c.set(Calendar.MONTH, monthOfYear);
            c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            setCurrentDateToOnView();
        }
    };
    TimePickerDialog.OnTimeSetListener timeFrom = new TimePickerDialog.OnTimeSetListener() {
        @Override   // Tar verdiene fra Dialogen og setter tid
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            c.set(Calendar.HOUR_OF_DAY, hourOfDay);
            c.set(Calendar.MINUTE, minute);
            setCurrentTimeFromOnView();
        }
    };
    TimePickerDialog.OnTimeSetListener timeTo = new TimePickerDialog.OnTimeSetListener() {
        @Override   // Tar verdiene fra Dialogen og setter tid
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            c.set(Calendar.HOUR_OF_DAY, hourOfDay);
            c.set(Calendar.MINUTE, minute);
            setCurrentTimeToOnView();
        }
    };











    public void orderDateFromOnClick(View v) {
        new DatePickerDialog(OpprettOrderActivity.this, dateFrom, c.get(Calendar.YEAR),
                c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH)).show();
    }
    public void orderDateToOnClick(View v) {
        new DatePickerDialog(OpprettOrderActivity.this, dateTo, c.get(Calendar.YEAR),
                c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH)).show();
    }



    public void orderTimeFromOnClick(View v) { // Viser Dialogen på skjermen
        new TimePickerDialog(OpprettOrderActivity.this, timeFrom, c.get(Calendar.HOUR_OF_DAY),
                c.get(Calendar.MINUTE), true).show();
    }
    public void orderTimeToOnClick(View v) { // Viser Dialogen på skjermen
        new TimePickerDialog(OpprettOrderActivity.this, timeTo, c.get(Calendar.HOUR_OF_DAY),
                c.get(Calendar.MINUTE), true).show();
    }



}
