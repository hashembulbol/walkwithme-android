package com.example.walkwithme;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.walkwithme.helpers.SaveSharedPreference;
import com.example.walkwithme.models.LoginResponse;
import com.example.walkwithme.retro.Api;
import com.example.walkwithme.retro.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    Button loginBtn;
    EditText unField, pwdField;
    TextView signupTv;

    TextView forgot;

    String un, pwd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginBtn = findViewById(R.id.loginButton);
        unField = findViewById(R.id.unField);
        pwdField = findViewById(R.id.pwdField);
        signupTv = findViewById(R.id.signupText);

        forgot = findViewById(R.id.forgotText);

        if(SaveSharedPreference.getUserName(LoginActivity.this).length() > 0)
        {
            Intent i = new Intent(LoginActivity.this, MainActivity.class);
            LoginActivity.this.startActivity(i);
        }


        forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://" + Api.BASE_IP + "/accounts/password_reset/"));
                LoginActivity.this.startActivity(browserIntent);
            }
        });

        signupTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LoginActivity.this, SignupActivity.class);
                LoginActivity.this.startActivity(i);
            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                un = unField.getText().toString();
                pwd = pwdField.getText().toString();

                if (un.isEmpty() || pwd.isEmpty()){
                    Toast.makeText(LoginActivity.this, "Kindly enter your credentials", Toast.LENGTH_LONG).show();
                }
                else {

                    ProgressDialog pd = new ProgressDialog(LoginActivity.this);
                    pd.setMessage("Loading");
                    pd.show();
                    String base = un + ":" + pwd;
                    String auth = "Basic " + Base64.encodeToString(base.getBytes(), Base64.NO_WRAP);
                    Call<LoginResponse> call = RetrofitClient.getInstance().getMyApi().login(auth);

                    call.enqueue(new Callback<LoginResponse>() {
                        @Override
                        public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                            Log.d("TEST", "1");
                            if (response.isSuccessful()) {
                                if (response.body() != null) {
                                    if (response.body().getUser() != null) {
                                        Log.d("TEST", response.body().toString());
                                        Toast.makeText(LoginActivity.this, "Login Succesful", Toast.LENGTH_LONG).show();
                                        SaveSharedPreference.setUserName(LoginActivity.this,response.body().getUser());
                                        SaveSharedPreference.setPassword(LoginActivity.this, pwd);
                                        pd.dismiss();
                                        Intent i = new Intent(LoginActivity.this, MainActivity.class);
                                        LoginActivity.this.startActivity(i);
                                    }
                                }


                            } else {
                                Toast.makeText(LoginActivity.this, "Wrong Username or Password", Toast.LENGTH_LONG).show();
                                pd.dismiss();
                            }
                        }

                        @Override
                        public void onFailure(Call<LoginResponse> call, Throwable t) {
                            Log.d("TEST", "1");
                            Toast.makeText(LoginActivity.this, "Network Error", Toast.LENGTH_LONG).show();
                            pd.dismiss();
                        }
                    });
                }
            }
        });

    }
}