package vn.touchspace.example.data.network.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProductOfInvoice {

    @SerializedName("productName")
    @Expose
    private String productName;
    @SerializedName("numberOfProduct")
    @Expose
    private String numberOfProduct;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getNumberOfProduct() {
        return numberOfProduct;
    }

    public void setNumberOfProduct(String numberOfProduct) {
        this.numberOfProduct = numberOfProduct;
    }
}
