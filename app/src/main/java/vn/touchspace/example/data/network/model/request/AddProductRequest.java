package vn.touchspace.example.data.network.model.request;

import com.google.gson.annotations.SerializedName;

/**
 * Created by GNUD on 02/12/2017.
 */

public class AddProductRequest {

    @SerializedName("productName")
    public String productName;
    @SerializedName("productPrice")
    public String productPrice;
    @SerializedName("productImage")
    public String productImage;
    @SerializedName("productType")
    public String productType;
    @SerializedName("productExport")
    public int productExport;
}
