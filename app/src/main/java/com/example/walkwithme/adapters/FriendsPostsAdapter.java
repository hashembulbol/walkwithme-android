package com.example.walkwithme.adapters;


import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.walkwithme.R;
import com.example.walkwithme.models.Post;
import com.example.walkwithme.models.Profile;
import com.example.walkwithme.retro.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FriendsPostsAdapter extends RecyclerView.Adapter<FriendsPostsAdapter.FriendsPostsViewHolder> {


    Context context;
    List<Post> items;

    public FriendsPostsAdapter(Context context, List<Post> items){

        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public FriendsPostsAdapter.FriendsPostsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_friendsfeed_layout, parent, false);

        return new FriendsPostsAdapter.FriendsPostsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FriendsPostsAdapter.FriendsPostsViewHolder holder, int position) {

        holder.tvContent.setText(items.get(position).getContent());
        holder.tvDate.setText(items.get(position).getDate().substring(0,10));


    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class FriendsPostsViewHolder extends RecyclerView.ViewHolder{

        TextView tvContent;
        TextView tvDate;

        public FriendsPostsViewHolder(@NonNull View itemView) {
            super(itemView);
            tvContent = itemView.findViewById(R.id.tvFriendfeedContent);
            tvDate = itemView.findViewById(R.id.tvFriendsfeedDate);

        }
    }

}

