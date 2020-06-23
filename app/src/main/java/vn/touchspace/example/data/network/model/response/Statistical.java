package vn.touchspace.example.data.network.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Statistical {
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("total")
    @Expose
    private int total;
    @SerializedName("count")
    @Expose
    private int count;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
