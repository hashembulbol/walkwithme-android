package com.example.walkwithme.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RegistrationModel {
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("dailyRoutine")
    @Expose
    private DailyRoutine dailyRoutine;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public DailyRoutine getDailyRoutine() {
        return dailyRoutine;
    }

    public void setDailyRoutine(DailyRoutine dailyRoutine) {
        this.dailyRoutine = dailyRoutine;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(DailyRoutine.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("username");
        sb.append('=');
        sb.append(((this.username == null)?"<null>":this.username));
        sb.append(',');
        sb.append("password");
        sb.append('=');
        sb.append(((this.password == null)?"<null>":this.password));
        sb.append(',');
        sb.append("dailyRoutine");
        sb.append('=');
        sb.append(((this.dailyRoutine == null)?"<null>":this.dailyRoutine));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }


}
