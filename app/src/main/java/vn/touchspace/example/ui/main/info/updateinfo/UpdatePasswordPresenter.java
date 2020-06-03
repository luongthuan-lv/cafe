package vn.touchspace.example.ui.main.info.updateinfo;

import android.os.Handler;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import vn.touchspace.example.data.DataManager;
import vn.touchspace.example.data.network.model.request.SignInRequest;
import vn.touchspace.example.data.network.model.request.UpdatePasswordRequest;
import vn.touchspace.example.data.network.model.response.User;
import vn.touchspace.example.ui.base.BasePresenter;
import vn.touchspace.example.utils.rx.SchedulerProvider;

import static vn.touchspace.example.data.prefs.AppPreferencesHelper.PREF_KEY_USER_ID;

public class UpdatePasswordPresenter<V extends UpdatePasswordMvpView> extends BasePresenter<V>
        implements UpdatePasswordMvpPresenter<V> {

    private static final String TAG = "UpdatePasswordPresenter";

    @Inject
    public UpdatePasswordPresenter(DataManager dataManager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void getInfo() {
        User user = getDataManager().findFirst(User.class);
        getMvpView().getInfoSuccess(user);
    }

    @Override
    public void updatePassword(String password) {
        if (!getMvpView().isDoubleClick()) {
            User user = getDataManager().findFirst(User.class);
            UpdatePasswordRequest request = new UpdatePasswordRequest();
            request.id = user.getId();
            request.password = password;

            getMvpView().showLoading();
            getCompositeDisposable().add(getDataManager()
                    .updatePassword(request)
                    .subscribeOn(getSchedulerProvider().io())
                    .observeOn(getSchedulerProvider().ui())
                    .subscribe(message -> {
                        if (!isViewAttached()) {
                            return;
                        }

                        user.getRealm().executeTransaction(realm -> user.setPassword(password));
                        new Handler().postDelayed(() -> getMvpView().hideLoading(), 500);
                        getMvpView().updatePassword(message.getMessage());
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
