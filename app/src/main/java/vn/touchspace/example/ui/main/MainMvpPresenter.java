package vn.touchspace.example.ui.main;

import vn.touchspace.example.data.room.model.User;
import vn.touchspace.example.ui.base.MvpPresenter;

/**
 * Created by GNUD on 04/12/2017.
 */

public interface MainMvpPresenter<V extends MainMvpView> extends MvpPresenter<V> {

    void logout();

    void getUserInfo();

}
