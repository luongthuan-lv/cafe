package vn.touchspace.example.ui.main.product;


import vn.touchspace.example.ui.base.MvpPresenter;
import vn.touchspace.example.ui.main.info.InfoMvpView;

public interface ProductMvpPresenter<V extends ProductMvpView> extends MvpPresenter<V> {
    void getProducts(String productName);

    void removeProduct(String id);

}
