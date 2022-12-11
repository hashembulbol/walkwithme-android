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
import com.example.walkwithme.models.Profile;
import com.example.walkwithme.models.ProfileNonnested;
import com.example.walkwithme.retro.RetrofitClient;
import com.google.gson.Gson;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RequestsAdapter extends RecyclerView.Adapter<RequestsAdapter.RequestsViewHolder> {


    Context context;
    List<FriendRequest> items;

    public RequestsAdapter(Context context, List<FriendRequest> items){

        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public RequestsAdapter.RequestsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_request_layout, parent, false);

        return new RequestsAdapter.RequestsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RequestsAdapter.RequestsViewHolder holder, int position) {

        holder.tvName.setText(items.get(position).getSender().getUsername());
        holder.tvName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Call<Profile> call1 = RetrofitClient.getInstance().getMyApi().getProfile(items.get(position).getSender().getUsername());
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
        holder.btnAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Call<Object> call = RetrofitClient.getInstance().getMyApi().acceptReq(items.get(position).getId());
                call.enqueue(new Callback<Object>() {
                    @Override
                    public void onResponse(Call<Object> call, Response<Object> response) {
                        Log.d("Accepting req", response.message());
                        Toast.makeText(context, "Accepted", Toast.LENGTH_LONG).show();
                    }
                    @Override
                    public void onFailure(Call<Object> call, Throwable t) {
                        Log.d("FailedAccepting", t.getMessage());
                        Toast.makeText(context, "Failed Accepting", Toast.LENGTH_LONG).show();
                    }
                });
            }
        });

        holder.btnReject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Call<Object> call = RetrofitClient.getInstance().getMyApi().rejectreq(items.get(position).getId());
                call.enqueue(new Callback<Object>() {
                    @Override
                    public void onResponse(Call<Object> call, Response<Object> response) {
                        Log.d("Rejecting req", response.message());
                        Toast.makeText(context, "Rejected", Toast.LENGTH_LONG).show();
                    }
                    @Override
                    public void onFailure(Call<Object> call, Throwable t) {
                        Log.d("FailedRejecting", t.getMessage());
                        Toast.makeText(context, "Failed Rejecting", Toast.LENGTH_LONG).show();
                    }
                });
            }
        });


    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class RequestsViewHolder extends RecyclerView.ViewHolder{

        Button btnReject;
        Button btnAccept;
        TextView tvName;

        public RequestsViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvItemReqName);
            btnAccept = itemView.findViewById(R.id.btnItemAccept);
            btnReject = itemView.findViewById(R.id.btnItemReject);
        }
    }

}
