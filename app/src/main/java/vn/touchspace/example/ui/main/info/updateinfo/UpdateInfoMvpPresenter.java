package vn.touchspace.example.ui.main.info.updateinfo;


import vn.touchspace.example.ui.base.MvpPresenter;
import vn.touchspace.example.ui.main.info.InfoMvpView;

public interface UpdateInfoMvpPresenter<V extends UpdateInfoMvpView> extends MvpPresenter<V> {
    void getInfo();

    void updateInfo(String name, String birthday, String telephoneNumber);
}
