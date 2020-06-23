package vn.touchspace.example.ui.main.invoice.addinvoice;

import java.util.List;

import vn.touchspace.example.data.network.model.response.Customer;
import vn.touchspace.example.data.network.model.response.Product;
import vn.touchspace.example.data.network.model.response.User;
import vn.touchspace.example.ui.base.MvpView;

public interface AddInvoiceMvpView extends MvpView {
    void addSuccess(String message);

    void getCustomersSuccess(List<Customer> list);

    void getStaffsSuccess(List<User> list);

    void getProductsSuccess(List<Product> list);
}
