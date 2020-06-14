package vn.touchspace.example.ui.main.account;

import vn.touchspace.example.ui.base.MvpPresenter;
import vn.touchspace.example.ui.main.MainMvpView;

/**
 * Created by GNUD on 04/12/2017.
 */

public interface AccountMvpPresenter<V extends AccountMvpView> extends MvpPresenter<V> {

    void getUserInfo();

}
