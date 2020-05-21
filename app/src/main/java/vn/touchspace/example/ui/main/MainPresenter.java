package vn.touchspace.example.ui.main;

import android.util.Log;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import vn.touchspace.example.data.DataManager;
import vn.touchspace.example.data.realm.model.Account;
import vn.touchspace.example.data.room.model.User;
import vn.touchspace.example.ui.base.BasePresenter;
import vn.touchspace.example.utils.AppLogger;
import vn.touchspace.example.utils.GsonUtils;
import vn.touchspace.example.utils.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

import static vn.touchspace.example.data.prefs.AppPreferencesHelper.PREF_KEY_PASSWORD;
import static vn.touchspace.example.data.prefs.AppPreferencesHelper.PREF_KEY_USERNAME;

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
        getDataManager().remove(PREF_KEY_USERNAME);
        getDataManager().remove(PREF_KEY_PASSWORD);
        getMvpView().openLoginActivity();
        AppLogger.i("logout");
    }

    @Override
    public void saveUser(User user) {
        getCompositeDisposable().add(getDataManager().saveUser(user)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(aBoolean ->
                        getCompositeDisposable().add(getDataManager().getAllUsers()
                                .subscribeOn(getSchedulerProvider().io())
                                .observeOn(getSchedulerProvider().ui())
                                .subscribe(users -> {
                                    for (User userItem : users) {
                                        Log.i(TAG, "user: " + userItem.getName());
                                    }
                                }, this::handleApiError)))
        );
    }

    @Override
    public void getAll() {
        getDataManager().getAllUsers();
        getCompositeDisposable().add(getDataManager().getAllUsers()
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(users -> {
                    for (User userItem : users) {
                        Log.i(TAG, "user: " + userItem.getName());
                    }
                }, throwable -> {
                    Log.i(TAG, "throwable: " + throwable.getMessage());
                }));
    }

    @Override
    public void findUser(String name) {
        getCompositeDisposable().add(getDataManager().findUser(name)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui()).subscribe(user -> {
                    if (!isViewAttached()) {
                        return;
                    }
                    getMvpView().findUserByName(user);
                }, throwable -> Log.i(TAG, "throwable: " + throwable.getMessage()))
        );
    }

    @Override
    public void getRealm() {
        Account account = getDataManager().findFirst(Account.class);
        if(account != null) {
            Log.i(TAG, "getRealm: " + account.username + "\n" + account.password);
        }
    }
}
