package vn.touchspace.example.data.network.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ProductOfInvoice implements Serializable {

    @SerializedName("productName")
    @Expose
    private String productName;
    @SerializedName("numberOfProduct")
    @Expose
    private int numberOfProduct;

    public ProductOfInvoice(String productName, int numberOfProduct) {
        this.productName = productName;
        this.numberOfProduct = numberOfProduct;
    }

    public ProductOfInvoice() {
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getNumberOfProduct() {
        return numberOfProduct;
    }

    public void setNumberOfProduct(int numberOfProduct) {
        this.numberOfProduct = numberOfProduct;
    }
}
