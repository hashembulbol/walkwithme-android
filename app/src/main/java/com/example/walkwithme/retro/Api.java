package com.example.walkwithme.retro;

import com.example.walkwithme.models.DailyRoutine;
import com.example.walkwithme.models.FriendRequest;
import com.example.walkwithme.models.LoginResponse;
import com.example.walkwithme.models.Post;
import com.example.walkwithme.models.Profile;
import com.example.walkwithme.models.ProfileNonnested;
import com.example.walkwithme.models.RegistrationModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Api {

    String BASE_IP = "192.168.100.105:8000";
    //String BASE_IP = "10.0.2.2:8000";
    String BASE_URL = "http://" + BASE_IP + "/api/";

    @GET("profile/{username}")
    Call<Profile> getProfile(@Path("username") String username);
    
    @PUT("profile/{username}/")
    Call<Profile> updateProfile(@Path("username") String username, @Body Profile profile);

    @PUT("routine/{username}/")
    Call<DailyRoutine> updateRoutine(@Path("username") String username,  @Body DailyRoutine dailyRoutine);

    @GET("friendrequests/{sender}/{receiver}/")
    Call<Object> sendReq(@Path("sender") String sender, @Path("receiver") String receiver);

    @FormUrlEncoded
    @POST("routine/")
    Call<DailyRoutine> createRoutine(@Field("diet") String diet);

    @GET("getfriends/{username}")
    Call<List<ProfileNonnested>> getFriends(@Path("username") String username);

    @GET("friendrequests/")
    Call<List<FriendRequest>> getRequests();

    @GET("deletepost/{id}")
    Call<Object> deletePost(@Path("id") int id);

    @GET("acceptreq/{id}")
    Call<Object> acceptReq(@Path("id") int id);


    @GET("rejectreq/{id}")
    Call<Object> rejectreq(@Path("id") int id);

    @GET("posts/")
    Call<List<Post>> getPosts();

    @GET("createpost/{username}/{content}/")
    Call<Object> createPost(@Path("username") String un,@Path("content") String content);

    @GET("authenticate/")
    Call<LoginResponse> login(@Header("Authorization") String authHeader);

    @FormUrlEncoded
    @POST("profile/")
    Call<Profile> signup(@Field("username") String un, @Field("password") String pwd);

    @GET("deletefriend/{deleter}/{deleted}")
    Call<Object> deletefriend(@Path("deleter") String deleter, @Path("deleted") int deleted);

}
