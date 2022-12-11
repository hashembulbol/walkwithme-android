package com.example.walkwithme.models.local;

import java.util.ArrayList;

public class FriendItem {

    private float id;
    private String password;
    private String last_login = null;
    private boolean is_superuser;
    private String username;
    private String first_name;
    private String last_name;
    private String email;
    private boolean is_staff;
    private boolean is_active;
    private String date_joined;
    private String height = null;
    private String weight = null;
    private String createddate;
    private String date = null;
    private String hobbies = null;
    private String dailyRoutine = null;
    ArrayList < Object > groups = new ArrayList < Object > ();
    ArrayList < Object > user_permissions = new ArrayList < Object > ();
    ArrayList < Object > friends = new ArrayList< Object >();


    // Getter Methods

    public float getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public String getLast_login() {
        return last_login;
    }

    public boolean getIs_superuser() {
        return is_superuser;
    }

    public String getUsername() {
        return username;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getEmail() {
        return email;
    }

    public boolean getIs_staff() {
        return is_staff;
    }

    public boolean getIs_active() {
        return is_active;
    }

    public String getDate_joined() {
        return date_joined;
    }

    public String getHeight() {
        return height;
    }

    public String getWeight() {
        return weight;
    }

    public String getCreateddate() {
        return createddate;
    }

    public String getDate() {
        return date;
    }

    public String getHobbies() {
        return hobbies;
    }

    public String getDailyRoutine() {
        return dailyRoutine;
    }

    // Setter Methods

    public void setId(float id) {
        this.id = id;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setLast_login(String last_login) {
        this.last_login = last_login;
    }

    public void setIs_superuser(boolean is_superuser) {
        this.is_superuser = is_superuser;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setIs_staff(boolean is_staff) {
        this.is_staff = is_staff;
    }

    public void setIs_active(boolean is_active) {
        this.is_active = is_active;
    }

    public void setDate_joined(String date_joined) {
        this.date_joined = date_joined;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public void setCreateddate(String createddate) {
        this.createddate = createddate;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setHobbies(String hobbies) {
        this.hobbies = hobbies;
    }

    public void setDailyRoutine(String dailyRoutine) {
        this.dailyRoutine = dailyRoutine;
    }
}
