package vn.touchspace.example.ui.main.info;

import android.util.Log;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import vn.touchspace.example.data.DataManager;
import vn.touchspace.example.data.network.model.response.User;
import vn.touchspace.example.ui.base.BasePresenter;
import vn.touchspace.example.utils.rx.SchedulerProvider;

public class InfoPresenter <V extends InfoMvpView> extends BasePresenter<V>
        implements InfoMvpPresenter<V> {

    private static final String TAG = "InfoPresenter";

    @Inject
    public InfoPresenter(DataManager dataManager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void getInfo() {
        User user = getDataManager().findFirst(User.class);
        getMvpView().getInfoSuccess(user);
    }
}
