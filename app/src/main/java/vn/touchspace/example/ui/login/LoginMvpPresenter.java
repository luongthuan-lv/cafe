package vn.touchspace.example.ui.login;

import vn.touchspace.example.ui.base.MvpPresenter;

/**
 * Created by GNUD on 02/12/2017.
 */

public interface LoginMvpPresenter<V extends LoginMvpView> extends MvpPresenter<V> {

    void onServerLoginClick(String email, String password);

    void checkLogin();
}
