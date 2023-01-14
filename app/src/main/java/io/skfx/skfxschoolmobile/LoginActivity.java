package io.skfx.skfxschoolmobile;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;

import java.util.HashMap;
import java.util.Scanner;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public class LoginActivity extends AppCompatActivity {
    public interface IHttpRequestl{
        String BaseUrl = "https://back.babirusa.skifry.ru/";
        @Headers("Accept: application/json")
        @POST("log_mb")
        Call<JsonElement> login(@Body HashMap registerApiPayload);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setContentView(R.layout.activity_login);
        Scanner sc = null;
        SharedPreferences preferences =
                getSharedPreferences("io.skfx.skfxschoolmobile", Context.MODE_PRIVATE);
        String name = preferences.getString("id", "");
        if (!name.equals("")){
            startActivity(new Intent(LoginActivity.this, MainActivity.class).putExtra("name", name));
            finish();
        }
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofitRequest = new Retrofit.Builder()
                .baseUrl(LoginActivity.IHttpRequestl.BaseUrl)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        System.out.println(sc);
        Button btn = (Button)findViewById(R.id.button2);
        Button btnl = (Button)findViewById(R.id.button);
        EditText EditTextEmail;
        EditText EditTextPass;
        final LoginActivity.IHttpRequestl request=retrofitRequest.create(LoginActivity.IHttpRequestl.class);
        EditTextEmail = findViewById(R.id.EditTextEmail);
        EditTextPass = findViewById(R.id.EditTextPass);






        TextView forgetb = (TextView)findViewById(R.id.textView3);
        btnl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = EditTextEmail.getText().toString();
                String pass = EditTextPass.getText().toString();

                HashMap<String,String> SendData =new HashMap<>();
                SendData.put("password",pass);
                SendData.put("e_mail", email);

                request.login(SendData).enqueue(new Callback<JsonElement>() {
                    @Override
                    public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                        if (response.body().toString().equals("\"log_fall\"")){
                            Toast.makeText(getApplicationContext(),"Пароль или почта не правильная",Toast.LENGTH_LONG).show();

                        }
                        else{
                            if (response.isSuccessful()){
                                Toast.makeText(getApplicationContext(),response.body().toString(),Toast.LENGTH_LONG).show();
                                startActivity(new Intent(LoginActivity.this, MainActivity.class).putExtra("name", name));
                                finish();
                            }
                        }

                    }

                    @Override
                    public void onFailure(Call<JsonElement> call, Throwable t) {
                        return;
                    }

                });


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