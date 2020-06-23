package vn.touchspace.example.data.network.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Product implements Serializable {

    @SerializedName("_id")
    @Expose
    public String id;

    @SerializedName("productName")
    @Expose
    public String productName;

    @SerializedName("productPrice")
    @Expose
    public double productPrice;

    @SerializedName("productImage")
    @Expose
    public String productImage;

    @SerializedName("productType")
    @Expose
    public String productType;

    @SerializedName("__v")
    @Expose
    public int __v;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

}
