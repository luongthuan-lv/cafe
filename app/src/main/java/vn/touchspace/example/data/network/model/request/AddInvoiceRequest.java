package vn.touchspace.example.data.network.model.request;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import vn.touchspace.example.data.network.model.response.ProductOfInvoice;

/**
 * Created by GNUD on 02/12/2017.
 */

public class AddInvoiceRequest {
    @SerializedName("list")
    public List<ProductOfInvoice> list;
    @SerializedName("customerName")
    public String customerName;
    @SerializedName("createDate")
    public String createDate;
    @SerializedName("createStaff")
    public String createStaff;
    @SerializedName("discountPercentage")
    public int discountPercentage;
    @SerializedName("totalValue")
    public double totalValue;
    @SerializedName("state")
    public String state;
}
