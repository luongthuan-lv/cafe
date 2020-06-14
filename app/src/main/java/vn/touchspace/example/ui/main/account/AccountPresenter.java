package vn.touchspace.example.ui.main.account;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import vn.touchspace.example.data.DataManager;
import vn.touchspace.example.data.network.model.response.User;
import vn.touchspace.example.ui.base.BasePresenter;
import vn.touchspace.example.ui.main.MainMvpPresenter;
import vn.touchspace.example.ui.main.MainMvpView;
import vn.touchspace.example.utils.AppLogger;
import vn.touchspace.example.utils.rx.SchedulerProvider;

import static vn.touchspace.example.data.prefs.AppPreferencesHelper.PREF_KEY_PASSWORD;
import static vn.touchspace.example.data.prefs.AppPreferencesHelper.PREF_KEY_USERNAME;
import static vn.touchspace.example.data.prefs.AppPreferencesHelper.PREF_KEY_USER_ID;

/**
 * Created by GNUD on 04/12/2017.
 */

public class AccountPresenter<V extends AccountMvpView> extends BasePresenter<V>
        implements AccountMvpPresenter<V> {

    private static final String TAG = "AccountPresenter";

    @Inject
    public AccountPresenter(
            DataManager dataManager,
            SchedulerProvider schedulerProvider,
            CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }


    @Override
    public void getUserInfo() {
        User user = getDataManager().findFirst(User.class);
        getMvpView().getUserInfoSuccess(user.getRole());
    }
}
