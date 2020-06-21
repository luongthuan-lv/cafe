package vn.touchspace.example.data.network.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Invoice {
    @SerializedName("list")
    @Expose
    private List<ProductOfInvoice> list = null;
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("customerName")
    @Expose
    private String customerName;
    @SerializedName("createDate")
    @Expose
    private String createDate;
    @SerializedName("createStaff")
    @Expose
    private String createStaff;
    @SerializedName("discountPercentage")
    @Expose
    private Integer discountPercentage;
    @SerializedName("totalValue")
    @Expose
    private double totalValue;
    @SerializedName("state")
    @Expose
    private String state;
    @SerializedName("__v")
    @Expose
    private int v;

    public List<ProductOfInvoice> getList() {
        return list;
    }

    public void setList(List<ProductOfInvoice> list) {
        this.list = list;
    }

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

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getCreateStaff() {
        return createStaff;
    }

    public void setCreateStaff(String createStaff) {
        this.createStaff = createStaff;
    }

    public Integer getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(Integer discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    public double getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(double totalValue) {
        this.totalValue = totalValue;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getV() {
        return v;
    }

    public void setV(int v) {
        this.v = v;
    }
}
