package vn.touchspace.example.data.network.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Customer {
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("customerName")
    @Expose
    private String customerName;
    @SerializedName("customerPhone")
    @Expose
    private String customerPhone;
    @SerializedName("customerInvoiceSum")
    @Expose
    private double customerInvoiceSum;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public double getCustomerInvoiceSum() {
        return customerInvoiceSum;
    }

    public void setCustomerInvoiceSum(double customerInvoiceSum) {
        this.customerInvoiceSum = customerInvoiceSum;
    }
}
