package com.example.walkwithme;

import androidx.appcompat.app.AppCompatActivity;

import android.nfc.Tag;
import android.os.Bundle;
import android.service.autofill.SavedDatasetsInfo;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.walkwithme.helpers.SaveSharedPreference;
import com.example.walkwithme.retro.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostActivity extends AppCompatActivity {

    Button btn;
    EditText et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        btn = findViewById(R.id.btnNewPost);
        et = findViewById(R.id.etNewPost);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (et.getText().toString().isEmpty())
                    Toast.makeText(PostActivity.this, "Please enter the text of your post", Toast.LENGTH_LONG).show();

                else{

                    Call<Object> call = RetrofitClient.getInstance().getMyApi().createPost(SaveSharedPreference.getUserName(PostActivity.this), et.getText().toString());

                    call.enqueue(new Callback<Object>() {
                        @Override
                        public void onResponse(Call<Object> call, Response<Object> response) {
                            Toast.makeText(PostActivity.this, "Posted succesfully", Toast.LENGTH_LONG).show();
                            finish();

                        }

                        @Override
                        public void onFailure(Call<Object> call, Throwable t) {
                            //Toast.makeText(PostActivity.this, "Failed to post, check your connection", Toast.LENGTH_LONG).show();
                            finish();
                        }
                    });

                }

            }
        });

    }
}