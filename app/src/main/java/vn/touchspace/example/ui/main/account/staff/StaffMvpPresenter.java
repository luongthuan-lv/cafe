package vn.touchspace.example.ui.main.account.staff;

import vn.touchspace.example.ui.base.MvpPresenter;
import vn.touchspace.example.ui.main.account.AccountMvpView;

/**
 * Created by GNUD on 04/12/2017.
 */

public interface StaffMvpPresenter<V extends StaffMvpView> extends MvpPresenter<V> {
    void getStaff();

    void deleteStaff(String id);
}
