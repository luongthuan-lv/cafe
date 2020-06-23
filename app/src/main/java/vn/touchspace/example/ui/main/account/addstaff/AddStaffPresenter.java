package vn.touchspace.example.ui.main.account.addstaff;

import android.os.Handler;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import vn.touchspace.example.data.DataManager;
import vn.touchspace.example.data.network.model.request.AddProductRequest;
import vn.touchspace.example.data.network.model.request.AddStaffRequest;
import vn.touchspace.example.ui.base.BasePresenter;
import vn.touchspace.example.utils.rx.SchedulerProvider;

public class AddStaffPresenter<V extends AddStaffMvpView> extends BasePresenter<V>
        implements AddStaffMvpPresenter<V> {

    private static final String TAG = "AddStaffPresenter";

    @Inject
    public AddStaffPresenter(DataManager dataManager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void addStaff(String username, String role) {
        getMvpView().showLoading();
        AddStaffRequest request = new AddStaffRequest();
        request.fullName = username;
        request.birthday = "00/00/0000";
        request.password = "12345";
        request.role = role;
        request.telephoneNumber = "0123456789";
        request.username = username;
        getCompositeDisposable().add(getDataManager()
                .createStaff(request)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(message -> {
                    if (!isViewAttached()) {
                        return;
                    }
                    new Handler().postDelayed(() -> getMvpView().hideLoading(), 500);
                    getMvpView().addSuccess(message.getMessage());
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
