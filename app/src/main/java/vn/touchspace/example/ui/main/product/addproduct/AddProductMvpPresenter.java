package vn.touchspace.example.ui.main.product.addproduct;


import vn.touchspace.example.ui.base.MvpPresenter;
import vn.touchspace.example.ui.main.product.updateproduct.UpdateProductMvpView;

public interface AddProductMvpPresenter<V extends AddProductMvpView> extends MvpPresenter<V> {
    void addProduct(String name, String price, String type, String image);
}
