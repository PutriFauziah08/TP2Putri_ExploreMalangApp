package com.putri.exploremalang.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.android.material.button.MaterialButton;
import com.putri.exploremalang.R;

public class SplashScreenActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView imgLogo;
    private Button nextButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen_page);

        imgLogo = (ImageView) findViewById(R.id.logo);
        nextButton = (Button) findViewById(R.id.nextButton);
        nextButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.nextButton){
            Intent intent = new Intent(this, HomeActivity.class);
            startActivity(intent);
        }
    }
}