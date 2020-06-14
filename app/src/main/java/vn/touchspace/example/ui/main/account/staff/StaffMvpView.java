package vn.touchspace.example.ui.main.account.staff;

import java.util.List;

import vn.touchspace.example.data.network.model.response.User;
import vn.touchspace.example.ui.base.MvpView;

/**
 * Created by GNUD on 04/12/2017.
 */

public interface StaffMvpView extends MvpView {
    void getStaffSuccess(List<User> list);
}
