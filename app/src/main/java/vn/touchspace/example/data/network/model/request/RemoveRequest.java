package vn.touchspace.example.data.network.model.request;

import com.google.gson.annotations.SerializedName;

/**
 * Created by GNUD on 02/12/2017.
 */

public class SignInRequest {

    @SerializedName("username")
    public String username;
    @SerializedName("password")
    public String password;
}
