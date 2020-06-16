package vn.touchspace.example.ui.main.account.addstaff;


import vn.touchspace.example.ui.base.MvpPresenter;
import vn.touchspace.example.ui.main.product.addproduct.AddProductMvpView;

public interface AddStaffMvpPresenter<V extends AddStaffMvpView> extends MvpPresenter<V> {
    void addStaff(String username, String role);
}
