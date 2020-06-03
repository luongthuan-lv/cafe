package vn.touchspace.example.ui.main.info.updateinfo;

import android.os.Handler;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.realm.Realm;
import vn.touchspace.example.data.DataManager;
import vn.touchspace.example.data.network.model.request.UpdateInfoRequest;
import vn.touchspace.example.data.network.model.request.UpdatePasswordRequest;
import vn.touchspace.example.data.network.model.response.User;
import vn.touchspace.example.ui.base.BasePresenter;
import vn.touchspace.example.ui.main.info.InfoMvpPresenter;
import vn.touchspace.example.ui.main.info.InfoMvpView;
import vn.touchspace.example.utils.rx.SchedulerProvider;

public class UpdateInfoPresenter<V extends UpdateInfoMvpView> extends BasePresenter<V>
        implements UpdateInfoMvpPresenter<V> {

    private static final String TAG = "UpdateInfoPresenter";

    @Inject
    public UpdateInfoPresenter(DataManager dataManager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void getInfo() {
        User user = getDataManager().findFirst(User.class);
        getMvpView().getInfoSuccess(user);
    }

    @Override
    public void updateInfo(String name, String birthday, String telephoneNumber) {
        User user = getDataManager().findFirst(User.class);

        if (!getMvpView().isDoubleClick()) {
            UpdateInfoRequest request = new UpdateInfoRequest();
            request.id = user.getId();
            request.fullName = name;
            request.birthday = birthday;
            request.telephoneNumber = telephoneNumber;

            getMvpView().showLoading();
            getCompositeDisposable().add(getDataManager()
                    .updateInfo(request)
                    .subscribeOn(getSchedulerProvider().io())
                    .observeOn(getSchedulerProvider().ui())
                    .subscribe(message -> {
                        if (!isViewAttached()) {
                            return;
                        }

                        user.getRealm().executeTransaction(realm -> {
                            user.setFullName(name);
                            user.setBirthday(birthday);
                            user.setTelephoneNumber(telephoneNumber);
                        });
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
