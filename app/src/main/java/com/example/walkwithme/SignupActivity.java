package com.example.walkwithme;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.walkwithme.helpers.SaveSharedPreference;
import com.example.walkwithme.models.DailyRoutine;
import com.example.walkwithme.models.LoginResponse;
import com.example.walkwithme.models.Profile;
import com.example.walkwithme.models.RegistrationModel;
import com.example.walkwithme.retro.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignupActivity extends AppCompatActivity {

    EditText etUn;
    EditText etPwd;
    EditText etPwd2;
    Button btnSignup;

    TextView tvAlready;
    DailyRoutine routine;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        etUn = (EditText) findViewById(R.id.unSignupField);
        etPwd = (EditText) findViewById(R.id.pwdSignupField);
        etPwd2 = (EditText) findViewById(R.id.pwd2SignupField);
        tvAlready = findViewById(R.id.alreadyText);

        btnSignup = findViewById(R.id.signupButton);

        tvAlready.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(SignupActivity.this, LoginActivity.class);
                SignupActivity.this.startActivity(i);
            }
        });

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String un = etUn.getText().toString();
                String pwd = etPwd.getText().toString();
                String pwd2 = etPwd2.getText().toString();


                if (un.matches("") || pwd.matches("") || pwd2.matches("")){
                    Toast.makeText(SignupActivity.this, "Please fill all fields", Toast.LENGTH_LONG).show();
                }
                else
                {
                    if (!pwd.equals(pwd2)){
                        Toast.makeText(SignupActivity.this, "Passwords doesn't match", Toast.LENGTH_LONG).show();
                    }
                    else{
                        signup(un, pwd);
                    }
                }
            }
        });


    }

    private void signup(String un, String pwd){


        Call<Profile> createprofile = RetrofitClient.getInstance().getMyApi().signup(un, pwd);
        ProgressDialog pd = new ProgressDialog(SignupActivity.this);
        pd.setMessage("Loading");
        pd.show();

        createprofile.enqueue(new Callback<Profile>() {
            @Override
            public void onResponse(Call<Profile> call, Response<Profile> response) {

                if(response.isSuccessful() && response.body() !=null && response.body().getUsername() != null){
                    Toast.makeText(SignupActivity.this, "Created successfully", Toast.LENGTH_LONG).show();
                    SaveSharedPreference.setUserName(SignupActivity.this,response.body().getUsername());
                    SaveSharedPreference.setPassword(SignupActivity.this, pwd);
                    Intent i = new Intent(SignupActivity.this, MainActivity.class);
                    SignupActivity.this.startActivity(i);
                }

                else{
                    Toast.makeText(SignupActivity.this, "Invalid username or password", Toast.LENGTH_LONG).show();
                }

                pd.dismiss();

            }

            @Override
            public void onFailure(Call<Profile> call, Throwable t) {
                Log.d("TEST", t.getMessage());
                Toast.makeText(SignupActivity.this, t.getMessage().toString(), Toast.LENGTH_LONG).show();
                pd.dismiss();

            }
        });


    }
}