package vn.touchspace.example.ui.main.info;


import vn.touchspace.example.ui.base.MvpPresenter;

public interface InfoMvpPresenter<V extends InfoMvpView> extends MvpPresenter<V> {
    void getInfo();
}
