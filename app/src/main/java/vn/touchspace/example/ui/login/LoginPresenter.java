package vn.touchspace.example.ui.login;

import android.os.Handler;
import android.text.TextUtils;

import com.touchspace.example.R;
import vn.touchspace.example.data.DataManager;
import vn.touchspace.example.data.network.model.request.SignInRequest;
import vn.touchspace.example.data.network.model.response.SignIn;
import vn.touchspace.example.data.realm.model.Account;
import vn.touchspace.example.ui.base.BasePresenter;
import vn.touchspace.example.utils.AppLogger;
import vn.touchspace.example.utils.CommonUtils;
import vn.touchspace.example.utils.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;

import static vn.touchspace.example.data.prefs.AppPreferencesHelper.PREF_KEY_PASSWORD;
import static vn.touchspace.example.data.prefs.AppPreferencesHelper.PREF_KEY_USERNAME;
import static vn.touchspace.example.data.prefs.AppPreferencesHelper.PREF_KEY_USER_ID;

/**
 * Created by GNUD on 02/12/2017.
 */

public class LoginPresenter<V extends LoginMvpView> extends BasePresenter<V>
        implements LoginMvpPresenter<V> {

    @Inject
    public LoginPresenter(
            DataManager dataManager,
            SchedulerProvider schedulerProvider,
            CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    private static final String TAG = "LoginPresenter";

    @Override
    public void onServerLoginClick(final String username, final String password) {
        if (!getMvpView().isDoubleClick()) {
            SignInRequest signInRequest = new SignInRequest();
            signInRequest.username = username;
            signInRequest.username = password;

            getMvpView().showLoading();
            getCompositeDisposable().add(getDataManager()
                    .signIn(signInRequest)
                    .subscribeOn(getSchedulerProvider().io())
                    .observeOn(getSchedulerProvider().ui())
                    .subscribe(user -> {
                        if (!isViewAttached()) {
                            return;
                        }

                        new Handler().postDelayed(() -> getMvpView().hideLoading(), 500);
                        getDataManager().clear(Account.class);
                        getDataManager().save(new Account(username, password));
                        getDataManager().save(PREF_KEY_USER_ID, user.getId());
                        getMvpView().openMainActivity();

                    }, throwable -> {
                        if (!isViewAttached()) {
                            return;
                        }
                        new Handler().postDelayed(() -> getMvpView().hideLoading(), 500);
                        handleApiError(throwable);
                    })
            );
        }
    }
}
