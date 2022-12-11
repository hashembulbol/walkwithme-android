package com.example.walkwithme;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.walkwithme.adapters.FriendsPostsAdapter;
import com.example.walkwithme.adapters.PostsAdapter;
import com.example.walkwithme.adapters.ProfileFriendsAdapter;
import com.example.walkwithme.helpers.SaveSharedPreference;
import com.example.walkwithme.models.FriendRequest;
import com.example.walkwithme.models.Post;
import com.example.walkwithme.models.Profile;
import com.example.walkwithme.models.ProfileNonnested;
import com.example.walkwithme.retro.RetrofitClient;
import com.google.gson.Gson;

import java.util.List;
import java.util.Vector;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileActivity extends AppCompatActivity {

    TextView tvUn, tvFname, tvLname, tvEmail, tvHobbies, tvJdate, tvHeight, tvWeight, tvDaily, tvMaintained, tvDiet;
    RecyclerView rvFriends, rvPosts;
    Button btnSendReq;
    TextView tvHeaderPosts, tvHeaderFriends, tvHeaderDaily;
    Profile profile;

    List<ProfileNonnested> myFriends;
    List<FriendRequest> myRes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        tvUn = findViewById(R.id.tvFriendpUn);
        tvFname = findViewById(R.id.tvFriendpFname);
        tvLname = findViewById(R.id.tvFreindpLname);
        tvEmail = findViewById(R.id.tvFriendpEmail);
        tvHobbies = findViewById(R.id.tvFriendpHobbies);
        tvJdate = findViewById(R.id.tvFriendpJdate);
        tvHeight = findViewById(R.id.tvFriendpHeight);
        tvWeight = findViewById(R.id.tvFriendpWeight);
        tvDaily = findViewById(R.id.tvFriendpDaily);
        tvMaintained = findViewById(R.id.tvFriendpMaintained);
        tvDiet = findViewById(R.id.tvFriendpDiet);

        rvFriends = findViewById(R.id.rvFreindspFriends);
        rvPosts = findViewById(R.id.rvFriendspPosts);

        tvHeaderFriends = findViewById(R.id.tvProfileHeaderFriends);
        tvHeaderPosts = findViewById(R.id.tvProfileHeaderPosts);

        btnSendReq = findViewById(R.id.btnSendReq);




        Gson gson = new Gson();
        profile = gson.fromJson(getIntent().getStringExtra("displayedProfile"), Profile.class);

        populateProfile(profile);

        btnSendReq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call<Object> call = RetrofitClient.getInstance().getMyApi().sendReq(SaveSharedPreference.getUserName(ProfileActivity.this), profile.getUsername());
                call.enqueue(new Callback<Object>() {
                    @Override
                    public void onResponse(Call<Object> call, Response<Object> response) {

                        Toast.makeText(ProfileActivity.this,"Sent", Toast.LENGTH_LONG).show();

                    }

                    @Override
                    public void onFailure(Call<Object> call, Throwable t) {

                    }
                });
            }
        });

        Call<List<Post>> call = RetrofitClient.getInstance().getMyApi().getPosts();
        call.enqueue(new Callback<List<Post>>() {

            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {


                List<Post> filtered = new Vector<>();
                for (Post post: response.body()
                ) {
                    if (post.getAuthor().getUsername().matches(profile.getUsername())) filtered.add(post);
                }

                if (filtered.size() == 0) tvHeaderPosts.append(" (No posts currently) ");

                FriendsPostsAdapter adapter = new FriendsPostsAdapter(ProfileActivity.this, filtered);
                rvPosts.setAdapter(adapter);
                rvPosts.setLayoutManager(new LinearLayoutManager(ProfileActivity.this));

                isAlreadyRequested();
                isAlreadyFriend();
                isMe();


            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {

            }
        });


    }

    private void populateProfile(Profile profile){

        tvUn.append(profile.getUsername());
        tvFname.append(profile.getFirstName());
        tvLname.append(profile.getLastName());
        tvEmail.append(profile.getEmail());

        if (profile.getHeight() != null)
            tvHeight.append(profile.getHeight().toString());
        if (profile.getWeight() != null)
            tvWeight.append(profile.getWeight().toString());
        if (profile.getDateJoined() != null)
            tvJdate.append(profile.getDateJoined().substring(0,10));
        if (profile.getHobbies() != null)
            tvHobbies.append(profile.getHobbies().toString());


            tvDaily.append(profile.getSteps().toString());
            tvDiet.append(profile.getDiet().toString());
            tvMaintained.append(profile.getMaintained().toString());


        ProfileFriendsAdapter adapter = new ProfileFriendsAdapter(ProfileActivity.this, profile.getFriends());
        rvFriends.setAdapter(adapter);
        rvFriends.setLayoutManager(new LinearLayoutManager(ProfileActivity.this));

        if (profile.getFriends().size() == 0)
            tvHeaderFriends.append(" (No Friends) ");

    }


    private void isAlreadyFriend(){

        Call<Profile> myprofilecall = RetrofitClient.getInstance().getMyApi().getProfile(SaveSharedPreference.getUserName(ProfileActivity.this));
        myprofilecall.enqueue(new Callback<Profile>() {
            @Override
            public void onResponse(Call<Profile> call, Response<Profile> response) {

                myFriends = response.body().getFriends();
                for (ProfileNonnested friend : myFriends){
                    if (profile.getUsername().matches(friend.getUsername())){
                        btnSendReq.setEnabled(false);
                        btnSendReq.setText("ALREADY FRIENDS");
                    }

                }

            }

            @Override
            public void onFailure(Call<Profile> call, Throwable t) {

            }
        });


    }

    private void isAlreadyRequested(){

        Call<List<FriendRequest>> call2 = RetrofitClient.getInstance().getMyApi().getRequests();
        call2.enqueue(new Callback<List<FriendRequest>>() {
            @Override
            public void onResponse(Call<List<FriendRequest>> call, Response<List<FriendRequest>> response) {

                myRes = response.body();
                for (FriendRequest req : myRes){
                    if (profile.getUsername().matches(req.getReceiver().getUsername()) || profile.getUsername().matches(req.getSender().getUsername())){
                        btnSendReq.setEnabled(false);
                        btnSendReq.setText("ALREADY REQUESTED");
                    }

                }

            }

            @Override
            public void onFailure(Call<List<FriendRequest>> call, Throwable t) {

            }
        });

    }

    private void isMe(){

        if (SaveSharedPreference.getUserName(ProfileActivity.this).matches(profile.getUsername()))
            btnSendReq.setVisibility(View.INVISIBLE);

    }



}