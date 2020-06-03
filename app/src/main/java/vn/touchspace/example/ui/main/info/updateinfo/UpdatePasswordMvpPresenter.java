package vn.touchspace.example.ui.main.info.updateinfo;


import vn.touchspace.example.ui.base.MvpPresenter;

public interface UpdatePasswordMvpPresenter<V extends UpdatePasswordMvpView> extends MvpPresenter<V> {

    void getInfo();

    void updatePassword(String password);
}
