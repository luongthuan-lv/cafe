package vn.touchspace.example.data.network.model.request;

import com.google.gson.annotations.SerializedName;

/**
 * Created by GNUD on 02/12/2017.
 */

public class UpdateInfoRequest {

    @SerializedName("id")
    public String id;
    @SerializedName("fullName")
    public String fullName;
    @SerializedName("birthday")
    public String birthday;
    @SerializedName("telephoneNumber")
    public String telephoneNumber;
}
