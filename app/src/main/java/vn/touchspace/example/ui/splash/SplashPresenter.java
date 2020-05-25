package vn.touchspace.example.ui.splash;

import android.text.TextUtils;

import vn.touchspace.example.data.DataManager;
import vn.touchspace.example.data.network.model.request.SignInRequest;
import vn.touchspace.example.data.network.model.response.SignIn;
import vn.touchspace.example.data.realm.model.Account;
import vn.touchspace.example.ui.base.BasePresenter;
import vn.touchspace.example.utils.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;

/**
 * Created by GNUD on 01/12/2017.
 */

public class SplashPresenter<V extends SplashMvpView> extends BasePresenter<V>
        implements SplashMvpPresenter<V> {

    @Inject
    public SplashPresenter(DataManager dataManager,
                           SchedulerProvider schedulerProvider,
                           CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }


    @Override
    public void onAttach(V mvpView) {
        super.onAttach(mvpView);
    }

    @Override
    public void decideNextActivity() {
        Account account = getDataManager().findFirst(Account.class);
        if (account != null) {
            SignInRequest signInRequest = new SignInRequest();
            signInRequest.username = account.username;
            signInRequest.password = account.password;
            getCompositeDisposable().add(getDataManager()
                    .signIn(signInRequest)
                    .subscribeOn(getSchedulerProvider().io())
                    .observeOn(getSchedulerProvider().ui())
                    .subscribe(signIn -> {
                        if (!isViewAttached()) {
                            return;
                        }
                        getHandler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                getMvpView().openMainActivity();
                            }
                        }, 2000);
                    }, throwable -> {
                        if (!isViewAttached()) {
                            return;
                        }
                        if (!TextUtils.isEmpty(throwable.getMessage())) {
                            getMvpView().showMessage(throwable.getMessage());
                        }
                    })
            );
        } else {
            getHandler().postDelayed(() -> getMvpView().openLoginActivity(), 5000);
        }
    }
}
