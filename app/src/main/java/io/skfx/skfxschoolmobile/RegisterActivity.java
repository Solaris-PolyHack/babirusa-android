package io.skfx.skfxschoolmobile;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public class RegisterActivity extends AppCompatActivity {
    public interface IHttpRequest{
        String BaseUrl = "https://back.babirusa.skifry.ru/";
        @Headers("Accept: application/json")
        @POST("reg_mb")
        Call<JsonElement> register(@Body HashMap registerApiPayload);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setContentView(R.layout.activity_register);
        EditText editTextA;
        EditText editTextL;
        EditText editTextP;
        EditText editTextE;
        EditText editTextC;

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofitRequest = new Retrofit.Builder()
                .baseUrl(IHttpRequest.BaseUrl)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();


        editTextA = findViewById(R.id.EditTextName);
        editTextL = findViewById(R.id.EditTextLastName);
        editTextP = findViewById(R.id.EditTextPass);
        editTextE = findViewById(R.id.EditTextEmail);
        editTextC = findViewById(R.id.EditTextClass);
        View view = (View)findViewById(R.id.backb);
        Button regb = (Button)findViewById(R.id.button2);


        final IHttpRequest request=retrofitRequest.create(IHttpRequest.class);


        regb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editTextA.getText().toString();
                String email = editTextE.getText().toString();
                String pass = editTextP.getText().toString();
                String classs = editTextC.getText().toString();
                String lastname = editTextL.getText().toString();

                SharedPreferences preferences =
                        getSharedPreferences("io.skfx.skfxschoolmobile", Context.MODE_PRIVATE);

                HashMap<String,String> SendData =new HashMap<>();
                SendData.put("name",name);
                SendData.put("e_mail",email);
                SendData.put("password",pass);
                SendData.put("surname",lastname);
                SendData.put("class",classs);
                if (name.equals("") || email.equals("") || pass.equals("") || lastname.equals("") || classs.equals("")){
                    AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(RegisterActivity.this);

                    dlgAlert.setMessage("Все поля должны быть заполнены");
                    dlgAlert.setTitle("Ошибка...");
                    dlgAlert.setPositiveButton("OK", null);
                    dlgAlert.setCancelable(true);
                    dlgAlert.create().show();

                    dlgAlert.setPositiveButton("Ok",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            });
                }
                else{
                    request.register(SendData).enqueue(new Callback<JsonElement>() {
                        @Override
                        public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                            if (response.isSuccessful()){
                                Toast.makeText(getApplicationContext(),response.body().toString(),Toast.LENGTH_LONG).show();
                                preferences.edit().putString("id", name).commit();
                                startActivity(new Intent(RegisterActivity.this, MainActivity.class).putExtra("name", name));
                                finish();
                            }
                        }

                        @Override
                        public void onFailure(Call<JsonElement> call, Throwable t) {
                            return;
                        }

                });
            }



            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editTextA.getText().toString();
                String email = editTextE.getText().toString();
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));

            }
        });
    }
    @Override
    public void onBackPressed() {
        return;
    }
}