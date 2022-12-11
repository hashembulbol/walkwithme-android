package com.example.walkwithme.models;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class FriendRequest {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("sender")
    @Expose
    private ProfileNonnested sender;
    @SerializedName("receiver")
    @Expose
    private ProfileNonnested receiver;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ProfileNonnested getSender() {
        return sender;
    }

    public void setSender(ProfileNonnested sender) {
        this.sender = sender;
    }

    public ProfileNonnested getReceiver() {
        return receiver;
    }

    public void setReceiver(ProfileNonnested receiver) {
        this.receiver = receiver;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(FriendRequest.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("id");
        sb.append('=');
        sb.append(((this.id == null)?"<null>":this.id));
        sb.append(',');
        sb.append("sender");
        sb.append('=');
        sb.append(((this.sender == null)?"<null>":this.sender));
        sb.append(',');
        sb.append("receiver");
        sb.append('=');
        sb.append(((this.receiver == null)?"<null>":this.receiver));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

}
