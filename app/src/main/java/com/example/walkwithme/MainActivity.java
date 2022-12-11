package com.example.walkwithme;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;

import com.example.walkwithme.databinding.ActivityMainBinding;
import com.example.walkwithme.helpers.SaveSharedPreference;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onResume() {
        super.onResume();
        checkLogin();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        checkLogin();


        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        replaceFragment(new HomeFragment());

        binding.bottomNavigationView.setOnItemSelectedListener(item -> {

            switch (item.getItemId()){

                case R.id.home:
                    replaceFragment(new HomeFragment());
                    break;
                case R.id.profile:
                    replaceFragment(new ProfileFragment());
                    break;
                case R.id.menu:
                    replaceFragment(new MenuFragment());
                    break;
                case R.id.friends:
                    replaceFragment(new FriendsFragment());
                    break;
            }

            return true;
        });
    }

    private void replaceFragment(Fragment fragment){

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.flFragment, fragment);
        fragmentTransaction.commit();


    }

    private void checkLogin(){

        if (SaveSharedPreference.getUserName(MainActivity.this).length() == 0){
            Intent i = new Intent(MainActivity.this, LoginActivity.class);
            finish();
            MainActivity.this.startActivity(i);
        }

    }

}