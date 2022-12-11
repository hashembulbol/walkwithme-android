package com.example.walkwithme;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.walkwithme.adapters.FriendsAdapter;
import com.example.walkwithme.adapters.PostsAdapter;
import com.example.walkwithme.helpers.SaveSharedPreference;
import com.example.walkwithme.models.FriendRequest;
import com.example.walkwithme.models.Post;
import com.example.walkwithme.models.ProfileNonnested;
import com.example.walkwithme.retro.RetrofitClient;

import java.util.Collections;
import java.util.List;
import java.util.Vector;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    Button btnPost;
    Button btnWalking;

    TextView tvWelcome;

    List<Post> allposts;
    List<ProfileNonnested> friends;
    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
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

        View root = inflater.inflate(R.layout.fragment_home, container, false);

        allposts = null;

        btnPost = root.findViewById(R.id.btnHomeWritePost);
        btnWalking = root.findViewById(R.id.btnWalking);

        tvWelcome = root.findViewById(R.id.tvWelcome);

        tvWelcome.append(" " + SaveSharedPreference.getUserName(getActivity()));

        btnWalking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(), WalkingActivity.class);
                getContext().startActivity(i);
            }
        });

        btnPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(getContext(), PostActivity.class);
                getContext().startActivity(i);

            }
        });

        RecyclerView rv = root.findViewById(R.id.rvFeed);
        Call<List<Post>> call = RetrofitClient.getInstance().getMyApi().getPosts();
        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {

                allposts = response.body();

                Call<List<ProfileNonnested>> call2 = RetrofitClient.getInstance().getMyApi().getFriends(SaveSharedPreference.getUserName(getContext()));
                call2.enqueue(new Callback<List<ProfileNonnested>>() {
                    @Override
                    public void onResponse(Call<List<ProfileNonnested>> call, Response<List<ProfileNonnested>> response) {

                        friends = response.body();
                        PostsAdapter adapter = new PostsAdapter(getActivity(), filterPosts());
                        rv.setAdapter(adapter);
                        rv.setLayoutManager(new LinearLayoutManager(getActivity()));



                    }

                    @Override
                    public void onFailure(Call<List<ProfileNonnested>> call, Throwable t) {

                    }
                });






            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {

            }
        });


        return root;
    }

    private List<Post> filterPosts(){

        List<Post> filtered = new Vector<>();

        for (Post post: allposts) {
            Log.d("CheckingPosts", post.toString());

            for (ProfileNonnested friend : friends)
                if (post.getAuthor().getUsername().matches(friend.getUsername()) ) {
                    Log.d("CheckingPosts", friend.toString());
                    filtered.add(post);
                }
        }

        for (Post post: allposts) {
            if (post.getAuthor().getUsername().matches(SaveSharedPreference.getUserName(getActivity()))){
                filtered.add(post);
            }

        }

        List<Post> shallowCopy = filtered.subList(0, filtered.size());
        Collections.reverse(shallowCopy);
        return shallowCopy;


    }
}