package vn.touchspace.example.ui.main.account.staff;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import vn.touchspace.example.data.DataManager;
import vn.touchspace.example.data.network.model.response.User;
import vn.touchspace.example.ui.base.BasePresenter;
import vn.touchspace.example.ui.main.account.AccountMvpPresenter;
import vn.touchspace.example.ui.main.account.AccountMvpView;
import vn.touchspace.example.utils.rx.SchedulerProvider;

/**
 * Created by GNUD on 04/12/2017.
 */

public class StaffPresenter<V extends StaffMvpView> extends BasePresenter<V>
        implements StaffMvpPresenter<V> {

    private static final String TAG = "StaffPresenter";

    @Inject
    public StaffPresenter(
            DataManager dataManager,
            SchedulerProvider schedulerProvider,
            CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void getStaff() {
        getCompositeDisposable().add(getDataManager()
                .getStaffs("staff")
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(list -> {
                    if (!isViewAttached()) {
                        return;
                    }
                    getMvpView().getStaffSuccess(list);
                }, throwable -> {
                    if (!isViewAttached()) {
                        return;
                    }
                    handleApiError(throwable);
                }));
    }
}
