package com.example.walkwithme.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProfileNonnested {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("last_login")
    @Expose
    private Object lastLogin;
    @SerializedName("is_superuser")
    @Expose
    private Boolean isSuperuser;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("first_name")
    @Expose
    private String firstName;
    @SerializedName("last_name")
    @Expose
    private String lastName;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("is_staff")
    @Expose
    private Boolean isStaff;
    @SerializedName("is_active")
    @Expose
    private Boolean isActive;
    @SerializedName("date_joined")
    @Expose
    private String dateJoined;
    @SerializedName("height")
    @Expose
    private Integer height;
    @SerializedName("weight")
    @Expose
    private Integer weight;
    @SerializedName("createddate")
    @Expose
    private String createddate;
    @SerializedName("date")
    @Expose
    private Object date;
    @SerializedName("hobbies")
    @Expose
    private Object hobbies;

    @SerializedName("groups")
    @Expose
    private List<Object> groups = null;
    @SerializedName("user_permissions")
    @Expose
    private List<Object> userPermissions = null;
    @SerializedName("friends")
    @Expose
    private List<Object> friends = null;


    @SerializedName("steps")
    @Expose
    private Integer steps;
    @SerializedName("diet")
    @Expose
    private String diet;
    @SerializedName("maintained")
    @Expose
    private Integer maintained;


    public Integer getSteps() {
        return steps;
    }

    public void setSteps(Integer steps) {
        this.steps = steps;
    }

    public String getDiet() {
        return diet;
    }

    public void setDiet(String diet) {
        this.diet = diet;
    }

    public Integer getMaintained() {
        return maintained;
    }

    public void setMaintained(Integer maintained) {
        this.maintained = maintained;
    }



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Object getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Object lastLogin) {
        this.lastLogin = lastLogin;
    }

    public Boolean getIsSuperuser() {
        return isSuperuser;
    }

    public void setIsSuperuser(Boolean isSuperuser) {
        this.isSuperuser = isSuperuser;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getIsStaff() {
        return isStaff;
    }

    public void setIsStaff(Boolean isStaff) {
        this.isStaff = isStaff;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public String getDateJoined() {
        return dateJoined;
    }

    public void setDateJoined(String dateJoined) {
        this.dateJoined = dateJoined;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public String getCreateddate() {
        return createddate;
    }

    public void setCreateddate(String createddate) {
        this.createddate = createddate;
    }

    public Object getDate() {
        return date;
    }

    public void setDate(Object date) {
        this.date = date;
    }

    public Object getHobbies() {
        return hobbies;
    }

    public void setHobbies(Object hobbies) {
        this.hobbies = hobbies;
    }



    public List<Object> getGroups() {
        return groups;
    }

    public void setGroups(List<Object> groups) {
        this.groups = groups;
    }

    public List<Object> getUserPermissions() {
        return userPermissions;
    }

    public void setUserPermissions(List<Object> userPermissions) {
        this.userPermissions = userPermissions;
    }

    public List<Object> getFriends() {
        return friends;
    }

    public void setFriends(List<Object> friends) {
        this.friends = friends;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(ProfileNonnested.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("id");
        sb.append('=');
        sb.append(((this.id == null)?"<null>":this.id));
        sb.append(',');
        sb.append("password");
        sb.append('=');
        sb.append(((this.password == null)?"<null>":this.password));
        sb.append(',');
        sb.append("lastLogin");
        sb.append('=');
        sb.append(((this.lastLogin == null)?"<null>":this.lastLogin));
        sb.append(',');
        sb.append("isSuperuser");
        sb.append('=');
        sb.append(((this.isSuperuser == null)?"<null>":this.isSuperuser));
        sb.append(',');
        sb.append("username");
        sb.append('=');
        sb.append(((this.username == null)?"<null>":this.username));
        sb.append(',');
        sb.append("firstName");
        sb.append('=');
        sb.append(((this.firstName == null)?"<null>":this.firstName));
        sb.append(',');
        sb.append("lastName");
        sb.append('=');
        sb.append(((this.lastName == null)?"<null>":this.lastName));
        sb.append(',');
        sb.append("email");
        sb.append('=');
        sb.append(((this.email == null)?"<null>":this.email));
        sb.append(',');
        sb.append("isStaff");
        sb.append('=');
        sb.append(((this.isStaff == null)?"<null>":this.isStaff));
        sb.append(',');
        sb.append("isActive");
        sb.append('=');
        sb.append(((this.isActive == null)?"<null>":this.isActive));
        sb.append(',');
        sb.append("dateJoined");
        sb.append('=');
        sb.append(((this.dateJoined == null)?"<null>":this.dateJoined));
        sb.append(',');
        sb.append("height");
        sb.append('=');
        sb.append(((this.height == null)?"<null>":this.height));
        sb.append(',');
        sb.append("weight");
        sb.append('=');
        sb.append(((this.weight == null)?"<null>":this.weight));
        sb.append(',');
        sb.append("createddate");
        sb.append('=');
        sb.append(((this.createddate == null)?"<null>":this.createddate));
        sb.append(',');
        sb.append("date");
        sb.append('=');
        sb.append(((this.date == null)?"<null>":this.date));
        sb.append(',');
        sb.append("hobbies");
        sb.append('=');
        sb.append(((this.hobbies == null)?"<null>":this.hobbies));
        sb.append(',');
        sb.append("steps");
        sb.append('=');
        sb.append(((this.steps == null)?"<null>":this.steps));
        sb.append(',');
        sb.append("diet");
        sb.append('=');
        sb.append(((this.diet == null)?"<null>":this.diet));
        sb.append(',');
        sb.append("maintained");
        sb.append('=');
        sb.append(((this.maintained == null)?"<null>":this.maintained));
        sb.append(',');
        sb.append("groups");
        sb.append('=');
        sb.append(((this.groups == null)?"<null>":this.groups));
        sb.append(',');
        sb.append("userPermissions");
        sb.append('=');
        sb.append(((this.userPermissions == null)?"<null>":this.userPermissions));
        sb.append(',');
        sb.append("friends");
        sb.append('=');
        sb.append(((this.friends == null)?"<null>":this.friends));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

}
