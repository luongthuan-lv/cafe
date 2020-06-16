package vn.touchspace.example.ui.main.account.manager;

import java.util.List;

import vn.touchspace.example.data.network.model.response.User;
import vn.touchspace.example.ui.base.MvpView;

/**
 * Created by GNUD on 04/12/2017.
 */

public interface ManagerMvpView extends MvpView {
        void getManagerSuccess(List<User> list);

        void removeSuccess(String message);
}
