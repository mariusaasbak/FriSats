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

import com.example.kristoffer.newazureuser.POJO.Orders;
import com.squareup.picasso.Picasso;

public class ShowOversiktDetailsActivity extends AppCompatActivity {

    private TextView textViewGroupId;
    private TextView textViewGroupName;
    private TextView textViewGroupDescription;
    private TextView textViewGroupMembers;
    private ImageView imageViewGroupDetails;
    private String imageStringURL;
    private String url = "http://frisats3.azurewebsites.net";
    private String imageFullURL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_oversikt_details);
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


        textViewGroupId = (TextView) findViewById(R.id.textView_group_ID_txt);
        textViewGroupName = (TextView) findViewById(R.id.textView_group_name_txt);
        textViewGroupDescription = (TextView) findViewById(R.id.textView_group_description_txt);
        textViewGroupMembers = (TextView) findViewById(R.id.textView_group_members_txt);
        imageViewGroupDetails = (ImageView) findViewById(R.id.imageView_group_details);

        Intent intent = getIntent();

        textViewGroupId.setText("ID: " + String.valueOf(intent.getIntExtra(OversiktActivity.KEY_GROUP_ID, 0)));
        textViewGroupName.setText("\nNavn: " + intent.getStringExtra(OversiktActivity.KEY_GROUP_NAME));
        textViewGroupDescription.setText("\nBeskrivelse: \n" + intent.getStringExtra(OversiktActivity.KEY_GROUP_DESCRIPTION));
        textViewGroupMembers.setText(String.valueOf("\nMedlemmer: " + intent.getIntExtra(OversiktActivity.KEY_GROUP_MEMBERS, 0)));

        imageStringURL = intent.getStringExtra(OversiktActivity.KEY_GROUP_IMAGE_URL);
        imageFullURL = url+imageStringURL;
        loadGroupImage();

    }


    public void loadGroupImage(){
        Picasso.with(ShowOversiktDetailsActivity.this)
                .load(imageFullURL)
                .resize(200,150)
                .into(imageViewGroupDetails);
    }

}
