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
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.kristoffer.newazureuser.POJO.Groups;
import com.example.kristoffer.newazureuser.POJO.Providers;
import com.example.kristoffer.newazureuser.POJO.Services;

import org.w3c.dom.Text;

import java.io.InvalidObjectException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class OpprettServiceActivity extends AppCompatActivity implements Spinner.OnItemSelectedListener {

    private Spinner spinnerServiceGroupName,spinnerServiceProvider;
    private EditText nyServiceNameEditText,nyServiceDescriptionEditText,nyServiceUnitPriceEditText;
    private EditText dateAvailFromEditText,dateAvailToEditText,timeAvailFromEditText,timeAvailToEditText;
    private String dateAvailFrom,dateAvailTo,timeAvailFrom,timeAvailTo;
    private Button opprettServiceButton;
    private String url = "http://frisats3.azurewebsites.net";

    private String providerFirstName,groupName;
    public int groupID,providerID;

    private String nyServiceName,nyServiceDescription,nyServiceGroupName,nyServiceProviderID,
            nyServiceGroupID,nyServiceUnitPrice,nyServiceAvailFrom,nyServiceAvailTo,nyServiceProvider;

    private List<Groups> gruppeListe;
    private List<Providers> providerListe;
    private final List<String> categoriesGroups = new ArrayList<String>();
    private final List<String> categoriesProviders = new ArrayList<String>();

    public final Calendar c = Calendar.getInstance();







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opprett_service);
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

        spinnerServiceGroupName = (Spinner) findViewById(R.id.spinner_service_group_name);
        spinnerServiceGroupName.setOnItemSelectedListener(this);
        getTheGroups();
        categoriesGroups.add("----------Velg en Gruppe----------");


        spinnerServiceProvider = (Spinner) findViewById(R.id.spinner_service_provider);
        getTheProviders();
        categoriesProviders.add("----------Velg en Provider----------");
        onProviderClick();


        nyServiceNameEditText =         (EditText) findViewById(R.id.ny_service_name_editText);
        nyServiceDescriptionEditText =  (EditText) findViewById(R.id.ny_service_description_editText);
        nyServiceUnitPriceEditText =    (EditText) findViewById(R.id.ny_service_unit_price_editText);

        dateAvailFromEditText =         (EditText) findViewById(R.id.date_avail_from);
        dateAvailToEditText =           (EditText) findViewById(R.id.date_avail_to);
        timeAvailFromEditText =         (EditText) findViewById(R.id.time_avail_from);
        timeAvailToEditText =           (EditText) findViewById(R.id.time_avail_to);

        opprettServiceButton =          (Button) findViewById(R.id.opprett_service_btn);





        //Er til gruppeAdapteret/gruppeSpinneren. Bruker den implementerte klassen med this...ikke rør!
        ArrayAdapter<String> dataGroupAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categoriesGroups);
        dataGroupAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerServiceGroupName.setAdapter(dataGroupAdapter);

        //setCurrentDateFromOnView();
        //setCurrentDateToOnView();
        //setCurrentTimeFrom();
        //setCurrentTimeTo();

        opprettServiceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    opprettServiceNow();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });


    }



    private void opprettServiceNow() {
        nyServiceName = nyServiceNameEditText.getText().toString();                 //String
        nyServiceDescription = nyServiceDescriptionEditText.getText().toString();   //String
        nyServiceGroupName = groupName;                                             //String
        nyServiceUnitPrice = nyServiceUnitPriceEditText.getText().toString();       //Double
        nyServiceProviderID = String.valueOf(providerID);                           //Integer
        nyServiceGroupID = String.valueOf(groupID);                                 //Integer
        //nyServiceAvailFrom = nyServiceAvailFromEditText.getText().toString();       //String
        //nyServiceAvailTo = nyServiceAvailToEditText.getText().toString();           //String
        nyServiceProvider = providerFirstName;                                      //Object == String

        final ProgressDialog loading = ProgressDialog.show(this, "Oppretter Tjeneste", "Vennligst vent...", false, false);

        RestAdapter adapter = new RestAdapter.Builder().setEndpoint(url).build();
        final RestInterface restInterface = adapter.create(RestInterface.class);

        final Services newService = new Services();

        newService.setServiceName(nyServiceName);
        newService.setDescription(nyServiceDescription);
        newService.setGroupName(nyServiceGroupName);
        newService.setUnitPrice(Double.parseDouble(nyServiceUnitPrice));
        newService.setProviderId(Integer.parseInt(nyServiceProviderID));
        newService.setServiceGroupId(Integer.parseInt(nyServiceGroupID));
        newService.setAvailFrom(nyServiceAvailFrom);
        newService.setAvailTo(nyServiceAvailTo);
        //newService.setProvider(nyServiceProvider);


        //newService.setAvailFrom("2016-02-28T07:59:59"); //2016-02-28T00:00:00 Denne formen!! Year-Month-Date Time-> Hour:Minute:Second
        //newService.setAvailTo("2016-03-28T23:30:59");   //2016-03-28T00:00:00 Denne forman!!
        //newService.setProvider("Nilsen Roger");// Funker ikke


        restInterface.createService(newService, new Callback<Services>() {
            @Override
            public void success(Services services, Response response) {
                loading.dismiss();
                Toast.makeText(getApplicationContext(), "Tjenesten er opprettet", Toast.LENGTH_LONG).show();
            }

            @Override
            public void failure(RetrofitError error) {
                throw new RuntimeException("Error! " + error.getMessage());
                //throw new NetworkException();
            }
        });

        nyServiceNameEditText.setText("");
        nyServiceDescriptionEditText.setText("");
        nyServiceUnitPriceEditText.setText("");

    }




    private void getTheGroups(){
        final ProgressDialog loading = ProgressDialog.show(this, "Sjekker grupper", "Vennligst vent...", false, false);
        RestAdapter adapter = new RestAdapter.Builder().setEndpoint(url).build();
        RestInterface restInterface = adapter.create(RestInterface.class);
        restInterface.getGroups(new Callback<List<Groups>>() {
            @Override
            public void success(List<Groups> list, Response response) {
                loading.dismiss();
                gruppeListe = list;
                for (int i = 0; i < gruppeListe.size(); i++) {
                    categoriesGroups.add(gruppeListe.get(i).getGroupName().toString());
                }
            }
            @Override
            public void failure(RetrofitError error) {
                //you can handle the errors here
            }
        });
    }




    private void getTheProviders(){
        final ProgressDialog loading = ProgressDialog.show(this, "Henter Provider-oversikt", "Vennligst vent...", false, false);
        RestAdapter adapter = new RestAdapter.Builder().setEndpoint(url).build();
        RestInterface restInterface = adapter.create(RestInterface.class);
        restInterface.getProviders(new Callback<List<Providers>>() {
            @Override
            public void success(List<Providers> list, Response response) {
                loading.dismiss();
                providerListe = list;
                for (int i = 0; i < providerListe.size(); i++) {
                    categoriesProviders.add((String) providerListe.get(i).getFirstName());
                }
            }

            @Override
            public void failure(RetrofitError error) {
                //you can handle the errors here
            }
        });
    }








    public void onProviderClick() {
        spinnerServiceProvider.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id)
            {
                final String item = parentView.getItemAtPosition(position).toString();

                RestAdapter adapter = new RestAdapter.Builder().setEndpoint(url).build();
                RestInterface restInterface = adapter.create(RestInterface.class);
                restInterface.getProviders(new Callback<List<Providers>>() {
                    @Override
                    public void success(List<Providers> list, Response response) {
                        providerListe = list;
                        providerID = 0;
                        providerFirstName = item;
                        String firstNameProvider;
                        for (int i = 0; i < providerListe.size(); i++) {
                            firstNameProvider = (String) providerListe.get(i).getFirstName();
                            if (String.valueOf(firstNameProvider).equals(String.valueOf(item))) { //Ingen navn..NULL POINT = fixet
                                providerID = providerListe.get(i).getId();
                                Toast.makeText(getApplicationContext(), "Provider: " + providerFirstName + ". ID: " + providerID, Toast.LENGTH_SHORT).show();
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

            public void onNothingSelected(AdapterView<?> parentView)
            {
                //spinnerServiceProvider = 0;
            }
        });
        ArrayAdapter<String> dataServiceProviderAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, categoriesProviders);
        dataServiceProviderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerServiceProvider.setAdapter(dataServiceProviderAdapter);
    }



    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        final String item = parent.getItemAtPosition(position).toString();

        RestAdapter adapter = new RestAdapter.Builder().setEndpoint(url).build();
        RestInterface restInterface = adapter.create(RestInterface.class);
        restInterface.getGroups(new Callback<List<Groups>>() {
            @Override
            public void success(List<Groups> list, Response response) {
                gruppeListe = list;
                groupID = 0;
                groupName = item;
                for (int i = 0; i < gruppeListe.size(); i++) {
                    if (gruppeListe.get(i).getGroupName().toString().equals(item)) {
                        groupID = gruppeListe.get(i).getId();
                        Toast.makeText(getApplicationContext(), "Gruppenavn: " + groupName + ". ID: " + groupID, Toast.LENGTH_SHORT).show();
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
    public void onNothingSelected(AdapterView<?> arg0) {
        Toast.makeText(getApplicationContext(), "Velg Gruppe: ", Toast.LENGTH_SHORT).show();
    }





    private void setCurrentDateFromOnView() {
        String dateFormat = "yyyy-MM-dd";
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat, Locale.UK);
        dateAvailFromEditText.setText(sdf.format(c.getTime()));
        dateAvailFrom = sdf.format(c.getTime());
        nyServiceAvailFrom = dateAvailFrom + "T" + timeAvailFrom;
        nyServiceAvailTo = dateAvailTo + "T" + timeAvailTo;
        Toast.makeText(getApplicationContext(), nyServiceAvailFrom + " - " + nyServiceAvailTo, Toast.LENGTH_LONG).show();
    }

    private void setCurrentDateToOnView() {
        String dateFormat = "yyyy-MM-dd";
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat, Locale.UK);
        dateAvailTo = sdf.format(c.getTime());
        dateAvailToEditText.setText(sdf.format(c.getTime()));
        nyServiceAvailFrom = dateAvailFrom + "T" + timeAvailFrom;
        nyServiceAvailTo = dateAvailTo + "T" + timeAvailTo;
        Toast.makeText(getApplicationContext(), nyServiceAvailFrom + " - " + nyServiceAvailTo, Toast.LENGTH_LONG).show();
    }

    private void setCurrentTimeFromOnView() {
        String timeFormat = "HH:mm:ss";
        SimpleDateFormat stf = new SimpleDateFormat(timeFormat, Locale.UK);
        timeAvailFromEditText.setText(stf.format(c.getTime()));
        timeAvailFrom = stf.format(c.getTime());
        nyServiceAvailFrom = dateAvailFrom + "T" + timeAvailFrom;
        nyServiceAvailTo = dateAvailTo + "T" + timeAvailTo;
        Toast.makeText(getApplicationContext(), nyServiceAvailFrom + " - " + nyServiceAvailTo, Toast.LENGTH_LONG).show();
    }

    private void setCurrentTimeToOnView() {
        String timeFormat = "HH:mm:ss";
        SimpleDateFormat stf = new SimpleDateFormat(timeFormat, Locale.UK);
        timeAvailToEditText.setText(stf.format(c.getTime()));
        timeAvailTo = stf.format(c.getTime());
        nyServiceAvailFrom = dateAvailFrom + "T" + timeAvailFrom;
        nyServiceAvailTo = dateAvailTo + "T" + timeAvailTo;
        Toast.makeText(getApplicationContext(), nyServiceAvailFrom + " - " + nyServiceAvailTo, Toast.LENGTH_LONG).show();
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











    public void dateFromOnClick(View v) {
        new DatePickerDialog(OpprettServiceActivity.this, dateFrom, c.get(Calendar.YEAR),
                c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH)).show();
    }
    public void dateToOnClick(View v) {
        new DatePickerDialog(OpprettServiceActivity.this, dateTo, c.get(Calendar.YEAR),
                c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH)).show();
    }



    public void timeFromOnClick(View v) { // Viser Dialogen på skjermen
        new TimePickerDialog(OpprettServiceActivity.this, timeFrom, c.get(Calendar.HOUR_OF_DAY),
                c.get(Calendar.MINUTE), true).show();
    }
    public void timeToOnClick(View v) { // Viser Dialogen på skjermen
        new TimePickerDialog(OpprettServiceActivity.this, timeTo, c.get(Calendar.HOUR_OF_DAY),
                c.get(Calendar.MINUTE), true).show();
    }



}
