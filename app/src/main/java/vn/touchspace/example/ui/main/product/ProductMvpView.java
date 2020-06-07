package vn.touchspace.example.ui.main.info;

import vn.touchspace.example.data.network.model.response.User;
import vn.touchspace.example.ui.base.MvpView;

public interface InfoMvpView extends MvpView {
    void getInfoSuccess(User user);
}
