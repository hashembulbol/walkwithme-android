package com.example.walkwithme.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.walkwithme.LoginActivity;
import com.example.walkwithme.R;
import com.example.walkwithme.helpers.SaveSharedPreference;
import com.example.walkwithme.models.local.MenuItem;
import com.example.walkwithme.retro.Api;

import java.util.ArrayList;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MenuViewHolder> {


    Context context;
    ArrayList<MenuItem> items;

    public MenuAdapter(Context context, ArrayList<MenuItem> items){

        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public MenuAdapter.MenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_menu_layout, parent, false);

        return new MenuAdapter.MenuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MenuAdapter.MenuViewHolder holder, int position) {

        holder.tv.setText(items.get(position).getText());
        holder.iv.setImageResource(items.get(position).getImage());

        if (position == 1){

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent i = new Intent(view.getContext(), LoginActivity.class);
                    SaveSharedPreference.clear(view.getContext());
                    view.getContext().startActivity(i);


                }
            });

        }
        else if (position == 0){

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://" + Api.BASE_IP + "/accounts/password_reset/"));
                    context.startActivity(browserIntent);


                }
            });

        }

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class MenuViewHolder extends RecyclerView.ViewHolder{

        ImageView iv;
        TextView tv;

        public MenuViewHolder(@NonNull View itemView) {
            super(itemView);
            iv = itemView.findViewById(R.id.ivItemMenu);
            tv = itemView.findViewById(R.id.tvItemMenu);
        }
    }

}
