package com.example.walkwithme.adapters;


import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.walkwithme.ProfileActivity;
import com.example.walkwithme.R;
import com.example.walkwithme.helpers.SaveSharedPreference;
import com.example.walkwithme.models.Profile;
import com.example.walkwithme.models.ProfileNonnested;
import com.example.walkwithme.retro.RetrofitClient;
import com.google.gson.Gson;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileFriendsAdapter extends RecyclerView.Adapter<ProfileFriendsAdapter.ProfileFriendsViewHolder> {


    Context context;
    List<ProfileNonnested> items;

    public ProfileFriendsAdapter(Context context, List<ProfileNonnested> items){

        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public ProfileFriendsAdapter.ProfileFriendsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_profilefriends_layout, parent, false);

        return new ProfileFriendsAdapter.ProfileFriendsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProfileFriendsAdapter.ProfileFriendsViewHolder holder, int position) {

        holder.tvUn.setText(items.get(position).getUsername());

        holder.btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Call<Profile> call1 = RetrofitClient.getInstance().getMyApi().getProfile(items.get(position).getUsername());
                call1.enqueue(new Callback<Profile>() {
                    @Override
                    public void onResponse(Call<Profile> call, Response<Profile> response) {

                        Gson gson = new Gson();
                        String displayedProfile = gson.toJson(response.body());
                        Intent i = new Intent(context, ProfileActivity.class);
                        i.putExtra("displayedProfile", displayedProfile);
                        context.startActivity(i);




                    }

                    @Override
                    public void onFailure(Call<Profile> call, Throwable t) {

                    }
                });



            }
        });


    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class ProfileFriendsViewHolder extends RecyclerView.ViewHolder{

        Button btnView;
        TextView tvUn;


        public ProfileFriendsViewHolder(@NonNull View itemView) {
            super(itemView);
            tvUn = itemView.findViewById(R.id.tvProfileFriendName);
            btnView = itemView.findViewById(R.id.btnProfileFriendView);
        }
    }

}
