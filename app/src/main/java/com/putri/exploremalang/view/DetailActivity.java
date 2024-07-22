package com.putri.exploremalang.view;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.putri.exploremalang.R;
import com.putri.exploremalang.model.Wisata;

public class DetailActivity extends AppCompatActivity {

    private ImageView detailImage;
    private TextView detailTitle;
    private TextView detailDescription;
    private TextView detailJam;
    private TextView detailTiket;
    private ImageView mapButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_page);

        detailImage = findViewById(R.id.detailImage);
        detailTitle = findViewById(R.id.detailTitle);
        detailDescription = findViewById(R.id.detailDescription);
        detailJam = findViewById(R.id.detailJam);
        detailTiket = findViewById(R.id.detailTiket);
        mapButton = findViewById(R.id.mapButton);

        Wisata wisata = getIntent().getParcelableExtra("wisata");

        if (wisata != null) {
            Glide.with(this).load(wisata.getGambarUrl()).into(detailImage);
            detailTitle.setText(wisata.getNama());
            detailDescription.setText(wisata.getDeskripsi());
            detailJam.setText("Jam Operasional: " + wisata.getJamBuka());
            if (wisata.getHarga().equals("0")) {
                detailTiket.setText("-");
            }else{
                detailTiket.setText("Harga: " + wisata.getHarga());
            }

            mapButton.setOnClickListener(v -> {
                String locationUrl = wisata.getLokasi();
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(locationUrl));

                if (mapIntent.resolveActivity(getPackageManager()) != null) {
                    startActivity(mapIntent);
                } else {
                    Toast.makeText(DetailActivity.this, "Tidak Bisa Membuka Maps", Toast.LENGTH_SHORT).show();
                }
            });
        }

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Detail");
        }

        toolbar.setNavigationOnClickListener(v -> onBackPressed());
    }
}
