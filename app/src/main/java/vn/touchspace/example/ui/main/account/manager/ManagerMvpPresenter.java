package vn.touchspace.example.ui.main.account.manager;

import vn.touchspace.example.ui.base.MvpPresenter;
import vn.touchspace.example.ui.main.account.staff.StaffMvpView;

/**
 * Created by GNUD on 04/12/2017.
 */

public interface ManagerMvpPresenter<V extends ManagerMvpView> extends MvpPresenter<V> {
    void getManager();

    void deleteManager(String id);
}
