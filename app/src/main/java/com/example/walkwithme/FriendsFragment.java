package com.example.walkwithme;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.walkwithme.adapters.FriendsAdapter;
import com.example.walkwithme.adapters.MenuAdapter;
import com.example.walkwithme.adapters.RequestsAdapter;
import com.example.walkwithme.helpers.SaveSharedPreference;
import com.example.walkwithme.models.FriendRequest;
import com.example.walkwithme.models.Profile;
import com.example.walkwithme.models.ProfileNonnested;
import com.example.walkwithme.retro.RetrofitClient;

import java.util.List;
import java.util.Vector;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FriendsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FriendsFragment extends Fragment {


    TextView tvReqsHeader, tvFriendsHeader;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FriendsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FriendsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FriendsFragment newInstance(String param1, String param2) {
        FriendsFragment fragment = new FriendsFragment();
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
        View root = inflater.inflate(R.layout.fragment_friends, container, false);

        RecyclerView rv = root.findViewById(R.id.rvMyfriends);
        RecyclerView rv2 = root.findViewById(R.id.rvMyreqs);

        tvFriendsHeader = root.findViewById(R.id.tvMyfriendsHeader);
        tvReqsHeader = root.findViewById(R.id.tvMyReqsHeader);

        Call<List<ProfileNonnested>> call = RetrofitClient.getInstance().getMyApi().getFriends(SaveSharedPreference.getUserName(getContext()));

        call.enqueue(new Callback<List<ProfileNonnested>>() {
            @Override
            public void onResponse(Call<List<ProfileNonnested>> call, Response<List<ProfileNonnested>> response) {
                Log.d("TEST1", response.body().toString());

                tvFriendsHeader.append(" (" + response.body().size() + " Friends) ");


                FriendsAdapter adapter = new FriendsAdapter(getActivity(), response.body());
                rv.setAdapter(adapter);
                rv.setLayoutManager(new LinearLayoutManager(getActivity()));

                Call<List<FriendRequest>> call2 = RetrofitClient.getInstance().getMyApi().getRequests();
                call2.enqueue(new Callback<List<FriendRequest>>() {
                    @Override
                    public void onResponse(Call<List<FriendRequest>> call, Response<List<FriendRequest>> response) {
                        Log.d("TEST1", response.body().toString());



                        List<FriendRequest> filtered = new Vector<>();
                        for (FriendRequest req: response.body()
                             ) {
                            if (req.getReceiver().getUsername().matches(SaveSharedPreference.getUserName(getContext()))) filtered.add(req);
                        }

                        tvReqsHeader.append(" (" + filtered.size() + " Requests)");

                        RequestsAdapter adapter = new RequestsAdapter(getActivity(), filtered);
                        rv2.setAdapter(adapter);
                        rv2.setLayoutManager(new LinearLayoutManager(getActivity()));

                    }

                    @Override
                    public void onFailure(Call<List<FriendRequest>> call, Throwable t) {
                        Log.d("FailedFriendReqs", t.getMessage());

                    }
                });
            }

            @Override
            public void onFailure(Call<List<ProfileNonnested>> call, Throwable t) {
                Log.d("FailedFriends", t.getMessage());

            }
        });

        return root;
    }
}