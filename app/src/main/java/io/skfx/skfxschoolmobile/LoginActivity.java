package io.skfx.skfxschoolmobile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Scanner;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setContentView(R.layout.activity_login);
        Scanner sc = null;
        System.out.println(sc);
        Button btn = (Button)findViewById(R.id.button2);
        Button btnl = (Button)findViewById(R.id.button);
        TextView forgetb = (TextView)findViewById(R.id.textView3);
        btnl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                finish();
            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                finish();
            }
        });
        forgetb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, ResetActivity.class));
                finish();
            }
        });

    }
    @Override
    public void onBackPressed() {
        return;
    }
    //TODO:Create function of save session
    //example:
    //    SharedPreferences preferences =
    //            getSharedPreferences("com.blabla.yourapp", Context.MODE_PRIVATE);
    //preferences.edit().putString("session", <yoursessiontoken>).commit();
    //preferences.getString("session", "");
}