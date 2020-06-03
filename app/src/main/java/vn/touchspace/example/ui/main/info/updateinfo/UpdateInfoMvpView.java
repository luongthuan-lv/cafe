package vn.touchspace.example.ui.main.info.updateinfo;

import vn.touchspace.example.data.network.model.response.User;
import vn.touchspace.example.ui.base.MvpView;

public interface UpdateInfoMvpView extends MvpView {
    void getInfoSuccess(User user);
}
