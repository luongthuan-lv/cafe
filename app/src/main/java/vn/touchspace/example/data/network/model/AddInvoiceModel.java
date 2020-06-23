package vn.touchspace.example.data.network.model;

public class AddInvoiceModel {
    private String productName;
    private int numberOfProduct;
    private double productPrice;

    public AddInvoiceModel(String productName, int numberOfProduct, double productPrice) {
        this.productName = productName;
        this.numberOfProduct = numberOfProduct;
        this.productPrice = productPrice;
    }

    public AddInvoiceModel() {
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

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }
}
