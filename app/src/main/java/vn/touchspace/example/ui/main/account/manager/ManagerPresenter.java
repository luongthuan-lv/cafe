package vn.touchspace.example.ui.main.account.manager;

import android.os.Handler;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import vn.touchspace.example.data.DataManager;
import vn.touchspace.example.data.network.model.request.RemoveRequest;
import vn.touchspace.example.ui.base.BasePresenter;
import vn.touchspace.example.ui.main.account.staff.StaffMvpPresenter;
import vn.touchspace.example.ui.main.account.staff.StaffMvpView;
import vn.touchspace.example.utils.rx.SchedulerProvider;

/**
 * Created by GNUD on 04/12/2017.
 */

public class ManagerPresenter<V extends ManagerMvpView> extends BasePresenter<V>
        implements ManagerMvpPresenter<V> {

    private static final String TAG = "ManagerPresenter";

    @Inject
    public ManagerPresenter(
            DataManager dataManager,
            SchedulerProvider schedulerProvider,
            CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void getManager() {
        getMvpView().showLoading();
        getCompositeDisposable().add(getDataManager()
                .getStaffs("manager")
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(list -> {
                    if (!isViewAttached()) {
                        return;
                    }
                    new Handler().postDelayed(() -> getMvpView().hideLoading(), 500);
                    getMvpView().getManagerSuccess(list);
                }, throwable -> {
                    if (!isViewAttached()) {
                        return;
                    }
                    new Handler().postDelayed(() -> getMvpView().hideLoading(), 500);
                    handleApiError(throwable);
                }));
    }

    @Override
    public void deleteManager(String id) {
        getMvpView().showLoading();
        RemoveRequest request = new RemoveRequest();
        request.id = id;
        getCompositeDisposable().add(getDataManager()
                .removeStaff(request)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(message -> {
                    if (!isViewAttached()) {
                        return;
                    }
                    new Handler().postDelayed(() -> getMvpView().hideLoading(), 500);
                    getMvpView().removeSuccess(message.getMessage());
                }, throwable -> {
                    if (!isViewAttached()) {
                        return;
                    }
                    new Handler().postDelayed(() -> getMvpView().hideLoading(), 500);
                    handleApiError(throwable);
                }));
    }
}
