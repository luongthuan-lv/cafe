package vn.touchspace.example.ui.main.customer.addcustomer;


import vn.touchspace.example.ui.base.MvpPresenter;
import vn.touchspace.example.ui.main.account.addstaff.AddStaffMvpView;

public interface AddCustomerMvpPresenter<V extends AddCustomerMvpView> extends MvpPresenter<V> {
    void addCustomer(String name, String tel);
}
