package io.skfx.skfxschoolmobile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import java.util.Scanner;
import java.util.logging.Logger;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setContentView(R.layout.activity_main);
        Bundle extras = getIntent().getExtras();
        String code;
        Button qrb = (Button)findViewById(R.id.button);

        if (extras != null) {
            code = extras.getString("code");
            Toast.makeText(getApplicationContext(), "Code: "+code, Toast.LENGTH_LONG).show();
        }

        qrb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, QRScannerActivity.class));
                finish();
            }
        });

    }
    @Override
    public void onBackPressed() {
        return;
    }


}