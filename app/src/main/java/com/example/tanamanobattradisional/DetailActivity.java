package com.example.tanamanobattradisional;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class DetailActivity extends AppCompatActivity {

    TextView tvDetailNama;
    ImageView imageDetail;
    TextView tvDetailDeskripsi;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        tvDetailNama = findViewById(R.id.tvDetailNama);
        imageDetail = findViewById(R.id.imageDetail);
        tvDetailDeskripsi =findViewById(R.id.tvDetailDeskripsi);
        toolbar = findViewById(R.id.toolbar);

        tvDetailNama.setText(getIntent().getStringExtra("nama"));
        tvDetailDeskripsi.setText(getIntent().getStringExtra("deskripsi"));
        Glide.with(this).load(getIntent().getStringExtra("image_url")).into(imageDetail);

        toolbar.setOnClickListener(view -> finish());
    }
}