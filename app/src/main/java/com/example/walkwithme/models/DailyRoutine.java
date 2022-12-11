package com.example.walkwithme.models;


import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class DailyRoutine {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("steps")
    @Expose
    private Integer steps;
    @SerializedName("diet")
    @Expose
    private String diet;
    @SerializedName("maintained")
    @Expose
    private Integer maintained;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(DailyRoutine .class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("id");
        sb.append('=');
        sb.append(((this.id == null)?"<null>":this.id));
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
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

}