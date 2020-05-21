package vn.touchspace.example.data.network.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by GNUD on 23/12/2017.
 */

public class Message {

    @SerializedName("message")
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
