package vn.touchspace.example.ui.main.product;

import java.util.List;

import vn.touchspace.example.data.network.model.Message;
import vn.touchspace.example.data.network.model.response.Product;
import vn.touchspace.example.data.network.model.response.User;
import vn.touchspace.example.ui.base.MvpView;

public interface ProductMvpView extends MvpView {
    void getList(List<Product> list);

    void removeSuccess(String message);
}
