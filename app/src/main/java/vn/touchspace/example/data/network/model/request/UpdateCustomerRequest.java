package vn.touchspace.example.data.network.model.request;

import com.google.gson.annotations.SerializedName;

/**
 * Created by GNUD on 02/12/2017.
 */

public class UpdateCustomerRequest {

    @SerializedName("customerName")
    public String customerName;
    @SerializedName("customerPhone")
    public String customerPhone;
}
