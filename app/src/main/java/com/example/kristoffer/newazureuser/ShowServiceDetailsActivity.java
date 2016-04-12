package com.example.kristoffer.newazureuser;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class ShowServiceDetailsActivity extends AppCompatActivity {

    private TextView tjenesteNavnTextView,beskrivelseAvTjenesteTextView,unitPriceTextView,
                        tilgjengeligFraTextView,tilgjengeligTilTextView,tilbyderNavnTextView,tjenesteServiceGroupTextView;
    private ImageView tjenesteBilde;
    private Button kontaktInformasjonButton, bestillButton;
    private String tjenesteID,tjenesteNavn,tjenesteDescription,tjenesteGroupName,tjenesteUnitPrice,
                    tjenesteProviderID,tjenesteServiceGroupID,tjenesteAvailFrom,tjenesteAvailTo,tjenesteProvider;
    private String url = "http://frisats3.azurewebsites.net";
    private String imageFullURL,tjenesteBildeURL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_service_details);
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

        tjenesteNavnTextView =          (TextView) findViewById(R.id.tjenesteNavnTextView);
        beskrivelseAvTjenesteTextView = (TextView) findViewById(R.id.beskrivelse_av_tjenesten_textView);
        unitPriceTextView =             (TextView) findViewById(R.id.unit_price_textView);
        tilgjengeligFraTextView =       (TextView) findViewById(R.id.tilgjengelig_fra_textView);
        tilgjengeligTilTextView =       (TextView) findViewById(R.id.tilgjengelig_til_textView);
        tilbyderNavnTextView =          (TextView) findViewById(R.id.tilbyder_textView);
        tjenesteServiceGroupTextView =  (TextView) findViewById(R.id.tjenesteServiceGruppe_textView);

        tjenesteBilde =                 (ImageView) findViewById(R.id.tilbyder_imageView);
        kontaktInformasjonButton =      (Button) findViewById(R.id.kontakt_informasjons_btn);
        bestillButton =                 (Button) findViewById(R.id.bestill_btn);


        Intent intent = getIntent();

        tjenesteID = String.valueOf(intent.getIntExtra(NyServiceActivity.KEY_SERVICE_ID, 0));
        tjenesteNavn = intent.getStringExtra(NyServiceActivity.KEY_SERVICE_NAME);
        tjenesteDescription = intent.getStringExtra(NyServiceActivity.KEY_SERVICE_DESCRIPTION);
        tjenesteGroupName = intent.getStringExtra(NyServiceActivity.KEY_SERVICE_GROUP_NAME);
        tjenesteUnitPrice = intent.getStringExtra(NyServiceActivity.KEY_SERVICE_UNIT_PRICE);
        tjenesteBildeURL = String.valueOf(intent.getStringArrayExtra(NyServiceActivity.KEY_SERVICE_IMAGE_URL));
        tjenesteProviderID = intent.getStringExtra(NyServiceActivity.KEY_SERVICE_PROVIDER_ID);
        tjenesteServiceGroupID = intent.getStringExtra(NyServiceActivity.KEY_SERVICE_GROUP_ID);
        tjenesteAvailFrom = intent.getStringExtra(NyServiceActivity.KEY_SERVICE_AVAIL_FROM);
        tjenesteAvailTo = intent.getStringExtra(NyServiceActivity.KEY_SERVICE_AVAIL_TO);
        tjenesteProvider = intent.getStringExtra(NyServiceActivity.KEY_SERVICE_PROVIDER);


        tjenesteNavnTextView.setText(tjenesteNavn);
        unitPriceTextView.setText("Pris: " + tjenesteUnitPrice);
        beskrivelseAvTjenesteTextView.setText("Beskrivelse:\n" + tjenesteDescription);
        tilgjengeligFraTextView.setText("Tilgjengelig fra: " + tjenesteAvailFrom);
        tilgjengeligTilTextView.setText("Tilgjengelig til: " + tjenesteAvailTo);
        tilbyderNavnTextView.setText("Tilbyder: " + tjenesteProvider + " Provider ID: " + tjenesteProviderID);
        tjenesteServiceGroupTextView.setText("Gruppe: " + tjenesteGroupName);

        imageFullURL = url+tjenesteBildeURL;
        loadTjenesteImage();



    }

    public void loadTjenesteImage(){
        Picasso.with(ShowServiceDetailsActivity.this)
                .load(imageFullURL)
                .resize(100,100)
                .into(tjenesteBilde);
    }

}
