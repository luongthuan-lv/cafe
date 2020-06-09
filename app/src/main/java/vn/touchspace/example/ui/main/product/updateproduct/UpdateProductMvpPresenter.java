package vn.touchspace.example.ui.main.product.updateproduct;


import vn.touchspace.example.ui.base.MvpPresenter;

public interface UpdateProductMvpPresenter<V extends UpdateProductMvpView> extends MvpPresenter<V> {
    void updateProduct(String id, String name, String price, String type, String image);
}
