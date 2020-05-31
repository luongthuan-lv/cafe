package vn.touchspace.example.data.network.model.request;

import com.google.gson.annotations.SerializedName;

/**
 * Created by GNUD on 02/12/2017.
 */

public class UpdatePasswordRequest {

    @SerializedName("id")
    public String id;
    @SerializedName("password")
    public String password;
}
