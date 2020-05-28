package vn.touchspace.example.ui.main;

import vn.touchspace.example.data.network.model.response.User;
import vn.touchspace.example.ui.base.MvpView;

/**
 * Created by GNUD on 04/12/2017.
 */

public interface MainMvpView extends MvpView {

    void openLoginActivity();

    void getUserInfoSuccess(User user);

}
