package vn.touchspace.example.ui.login;

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
    public void onServerLoginClick(final String email, final String password) {
        //validate email and password
        if (!getMvpView().isDoubleClick()) {
            if (TextUtils.isEmpty(email)) {
                getMvpView().onError(R.string.empty_email);
                return;
            }
            if (!CommonUtils.isEmailValid(email)) {
                getMvpView().onError(R.string.invalid_email);
                return;
            }
            if (TextUtils.isEmpty(password)) {
                getMvpView().onError(R.string.empty_password);
                return;
            }
            AppLogger.d("email: " + email);
            AppLogger.d("password: " + password);
            SignInRequest signInRequest = new SignInRequest();
            signInRequest.email = email;
            signInRequest.password = password;
            getMvpView().showLoading();
            getCompositeDisposable().add(getDataManager()
                    .signIn(signInRequest)
                    .subscribeOn(getSchedulerProvider().io())
                    .observeOn(getSchedulerProvider().ui())
                    .subscribe(signIn -> {
                        if (!isViewAttached()) {
                            return;
                        }
                        getMvpView().hideLoading();
                        getDataManager().clear(Account.class);
                        getDataManager().save(new Account(email, password));
                        getDataManager().save(PREF_KEY_USERNAME, email);
                        getDataManager().save(PREF_KEY_PASSWORD, password);
                        getMvpView().openMainActivity();

                    }, throwable -> {
                        if (!isViewAttached()) {
                            return;
                        }
                        getMvpView().hideLoading();
                        handleApiError(throwable);
                    })
            );
        }
    }
}
