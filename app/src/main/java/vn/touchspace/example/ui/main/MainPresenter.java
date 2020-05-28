package vn.touchspace.example.ui.main;

import android.util.Log;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import vn.touchspace.example.data.DataManager;
import vn.touchspace.example.data.network.model.response.User;
import vn.touchspace.example.data.realm.model.Account;
import vn.touchspace.example.ui.base.BasePresenter;
import vn.touchspace.example.utils.AppLogger;
import vn.touchspace.example.utils.GsonUtils;
import vn.touchspace.example.utils.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

import static vn.touchspace.example.data.prefs.AppPreferencesHelper.PREF_KEY_PASSWORD;
import static vn.touchspace.example.data.prefs.AppPreferencesHelper.PREF_KEY_USERNAME;
import static vn.touchspace.example.data.prefs.AppPreferencesHelper.PREF_KEY_USER_ID;

/**
 * Created by GNUD on 04/12/2017.
 */

public class MainPresenter<V extends MainMvpView> extends BasePresenter<V>
        implements MainMvpPresenter<V> {

    private static final String TAG = "MainPresenter";

    @Inject
    public MainPresenter(
            DataManager dataManager,
            SchedulerProvider schedulerProvider,
            CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void logout() {
        getDataManager().clear(User.class);
        getDataManager().remove(PREF_KEY_USERNAME);
        getDataManager().remove(PREF_KEY_PASSWORD);
        getDataManager().remove(PREF_KEY_USER_ID);
        getMvpView().openLoginActivity();
        AppLogger.i("logout");
    }

    @Override
    public void getUserInfo() {
        User user = getDataManager().findFirst(User.class);
        getMvpView().getUserInfoSuccess(user);
    }
}
