package io.skfx.skfxschoolmobile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;


import org.w3c.dom.Text;

import java.util.Scanner;
import java.util.logging.Logger;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setContentView(R.layout.activity_main);
        Bundle extras = getIntent().getExtras();

        ImageView image = (ImageView)findViewById(R.id.imageView2);
        String code;
        SharedPreferences preferences =
                getSharedPreferences("io.skfx.skfxschoolmobile", Context.MODE_PRIVATE);
        TextView welcometext = (TextView)findViewById(R.id.textView5);
        Button qrb = (Button)findViewById(R.id.button);
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                preferences.edit().remove("id").commit();
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
                finish();
            }
        });
        if (extras != null) {
            if (!(extras.getString("code") == null)){
                code = extras.getString("code");
                Toast.makeText(getApplicationContext(), "Code: "+code, Toast.LENGTH_LONG).show();
            }
            if(!(extras.getString("name") == null)){
                welcometext.setText("Здравствуйте, " + extras.getString("name"));
            }

        }

        qrb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, QRScannerActivity.class));

            }
        });

    }
    @Override
    public void onBackPressed() {
        return;
    }


}