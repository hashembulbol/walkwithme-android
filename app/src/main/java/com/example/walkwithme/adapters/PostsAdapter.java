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
import com.example.walkwithme.models.FriendRequest;
import com.example.walkwithme.models.Post;
import com.example.walkwithme.models.Profile;
import com.example.walkwithme.retro.RetrofitClient;
import com.google.gson.Gson;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.PostsViewHolder> {


    Context context;
    List<Post> items;

    public PostsAdapter(Context context, List<Post> items){

        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public PostsAdapter.PostsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_feed_layout, parent, false);

        return new PostsAdapter.PostsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostsAdapter.PostsViewHolder holder, int position) {

        Post currentpost = items.get(holder.getAdapterPosition());

        holder.tvContent.setText(currentpost.getContent());
        holder.tvUn.setText(currentpost.getAuthor().getUsername());

        holder.tvDate.setText(currentpost.getDate().substring(0,10));
        //Log.d("CHECKING", items.get(holder.getAdapterPosition()).getAuthor().getUsername() + " AND " + SaveSharedPreference.getUserName(context).toString());

        if (currentpost.getAuthor().getUsername().matches(SaveSharedPreference.getUserName(context).toString())){
            Log.d("MYPOST", "MYPOST");
            holder.tvDeletePost.setVisibility(View.VISIBLE);
            holder.tvDeletePost.setEnabled(true);
            holder.tvDeletePost.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Call<Object> call = RetrofitClient.getInstance().getMyApi().deletePost(currentpost.getId());
                    call.enqueue(new Callback<Object>() {
                        @Override
                        public void onResponse(Call<Object> call, Response<Object> response) {
                            Toast.makeText(context, "Deleted", Toast.LENGTH_LONG).show();
                        }

                        @Override
                        public void onFailure(Call<Object> call, Throwable t) {
                            Log.d("Deleting Post", t.getMessage());
                        }
                    });
                }
            });

        }

        holder.tvView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Call<Profile> call = RetrofitClient.getInstance().getMyApi().getProfile(items.get(position).getAuthor().getUsername());
                call.enqueue(new Callback<Profile>() {
                    @Override
                    public void onResponse(Call<Profile> call, Response<Profile> response) {
                        Gson gson = new Gson();
                        String displayedProfile = gson.toJson(response.body());
                        Intent i = new Intent(context, ProfileActivity.class);
                        i.putExtra("displayedProfile", displayedProfile);
                        context.startActivity(i);
                        Log.d("Getting Profile Success", response.message());
                        Toast.makeText(context, "Success", Toast.LENGTH_LONG).show();

                    }
                    @Override
                    public void onFailure(Call<Profile> call, Throwable t) {
                        Log.d("Getting Profile Failed", t.getMessage());
                        Toast.makeText(context, "Failed Getting Profile", Toast.LENGTH_LONG).show();
                    }
                });
            }
        });




    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class PostsViewHolder extends RecyclerView.ViewHolder{

        TextView tvContent;
        TextView tvUn;
        TextView tvDate;
        TextView tvView;
        TextView tvDeletePost;

        public PostsViewHolder(@NonNull View itemView) {
            super(itemView);
            tvContent = itemView.findViewById(R.id.tvItemPostContent);
            tvDate = itemView.findViewById(R.id.tvItemPostDate);
            tvUn = itemView.findViewById(R.id.tvItemPostUn);
            tvView = itemView.findViewById(R.id.tvItemPostView);
            tvDeletePost = itemView.findViewById(R.id.tvPostItemDeletePost);

        }
    }

}
