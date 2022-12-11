package com.example.walkwithme;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.walkwithme.helpers.SaveSharedPreference;
import com.example.walkwithme.models.DailyRoutine;
import com.example.walkwithme.models.LoginResponse;
import com.example.walkwithme.models.Profile;
import com.example.walkwithme.retro.RetrofitClient;

import java.time.Duration;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    Profile current;
    Profile updated;

    EditText etUn, etEmail, etFname, etLname, etHobbies, etJdate, etHeight, etWeight, etDiet, etMaintained, etDaily, etBdate;

    Button btnUpdate;

    public ProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_profile, container, false);

        etUn = root.findViewById(R.id.etProfileUn);
        etFname = root.findViewById(R.id.etProfileFname);
        etLname = root.findViewById(R.id.etProfileLname);
        etEmail = root.findViewById(R.id.etProfileEmail);
        etHobbies = root.findViewById(R.id.etProfileHobbies);
        etJdate = root.findViewById(R.id.etProfileJdate);
        etHeight = root.findViewById(R.id.etProfileHeight);
        etWeight = root.findViewById(R.id.etProfileWeight);
        etDiet = root.findViewById(R.id.etProfileDiet);
        etMaintained = root.findViewById(R.id.etProfileMaintained);
        etDaily = root.findViewById(R.id.etProfileSteps);
        etBdate = root.findViewById(R.id.etBdate);

        btnUpdate = root.findViewById(R.id.btnProfileUpdate);


        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Profile updated = new Profile();

                updated.setUsername(etUn.getText().toString());
                updated.setEmail(etEmail.getText().toString());
                updated.setFirstName(etFname.getText().toString());
                updated.setLastName(etLname.getText().toString());
                updated.setHobbies(etHobbies.getText().toString());
                if (!etHeight.getText().toString().isEmpty())
                    updated.setHeight(Integer.parseInt(etHeight.getText().toString()));
                if (!etWeight.getText().toString().isEmpty())
                    updated.setWeight(Integer.parseInt(etWeight.getText().toString()));

                if (!etDiet.getText().toString().isEmpty())
                    updated.setDiet(etDiet.getText().toString());
                if (!etMaintained.getText().toString().isEmpty())
                    updated.setMaintained(Integer.parseInt(etMaintained.getText().toString()));
                if (!etDaily.getText().toString().isEmpty())
                    updated.setSteps(Integer.parseInt(etDaily.getText().toString()));



                updated.setPassword(SaveSharedPreference.getPassword(getContext()));
                Log.d("PROFILEFRAGMENT", updated.toString());

                Call<Profile> call = RetrofitClient.getInstance().getMyApi().updateProfile(SaveSharedPreference.getUserName(getContext()), updated);
                call.enqueue(new Callback<Profile>() {
                    @Override
                    public void onResponse(Call<Profile> call, Response<Profile> response) {

                        if (response.isSuccessful()){
                            current = response.body();
                            populateProfile(current);

                            Toast.makeText(getActivity(),"Updated Succesfully", Toast.LENGTH_LONG).show();
                        }
                        else{
                            Toast.makeText(getActivity(),"Failed to update profile", Toast.LENGTH_LONG).show();
                            Log.d("TEST", response.message());
                            Log.d("TEST", response.errorBody().toString());

                        }

                    }

                    @Override
                    public void onFailure(Call<Profile> call, Throwable t) {
                        Log.d("TEST", t.getMessage());

                    }
                });


            }
        });

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



        Call<Profile> call = RetrofitClient.getInstance().getMyApi().getProfile(SaveSharedPreference.getUserName(getActivity()));
        call.enqueue(new Callback<Profile>() {
            @Override
            public void onResponse(Call<Profile> call, Response<Profile> response) {
                if (response.isSuccessful()){
                    populateProfile(response.body());
                }
                else{
                    Toast.makeText(getActivity(),"Failed to get profile data", Toast.LENGTH_LONG).show();
                    Log.d("TEST", response.message());
                }
            }

            @Override
            public void onFailure(Call<Profile> call, Throwable t) {
                Toast.makeText(getActivity(),"Failed to get profile data", Toast.LENGTH_LONG).show();
                Log.d("TEST", t.getMessage());
            }
        });


    }

    private void populateProfile(Profile profile){

        etUn.setText(profile.getUsername());
        etFname.setText(profile.getFirstName());
        etLname.setText(profile.getLastName());
        etEmail.setText(profile.getEmail());

        if (profile.getHeight() != null)
        etWeight.setText(profile.getHeight().toString());
        if (profile.getWeight() != null)
        etHeight.setText(profile.getWeight().toString());
        if (profile.getDate() != null)
        etBdate.setText(profile.getDate().toString());
        if (profile.getDateJoined() != null)
        etJdate.setText(profile.getDateJoined().substring(0,10));
        if (profile.getHobbies() != null)
        etHobbies.setText(profile.getHobbies().toString());



        etDaily.setText(profile.getSteps().toString());
        etDiet.setText(profile.getDiet().toString());
        etMaintained.setText(profile.getMaintained().toString());




    }
}