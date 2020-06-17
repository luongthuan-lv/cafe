package vn.touchspace.example.ui.main.customer;

import java.util.List;

import vn.touchspace.example.data.network.model.response.Customer;
import vn.touchspace.example.ui.base.MvpView;

/**
 * Created by GNUD on 04/12/2017.
 */

public interface CustomerMvpView extends MvpView {
        void getListSuccess(List<Customer> list);
        void removeSuccess(String message);
}
