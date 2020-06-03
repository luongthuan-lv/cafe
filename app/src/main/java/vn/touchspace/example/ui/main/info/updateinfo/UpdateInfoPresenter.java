package vn.touchspace.example.ui.main.info.updateinfo;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import vn.touchspace.example.data.DataManager;
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
}
