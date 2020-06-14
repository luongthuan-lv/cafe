package vn.touchspace.example.ui.main.account.manager;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import vn.touchspace.example.data.DataManager;
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
        getCompositeDisposable().add(getDataManager()
                .getStaffs("manager")
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(list -> {
                    if (!isViewAttached()) {
                        return;
                    }
                    getMvpView().getManagerSuccess(list);
                }, throwable -> {
                    if (!isViewAttached()) {
                        return;
                    }
                    handleApiError(throwable);
                }));
    }
}
