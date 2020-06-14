package vn.touchspace.example.ui.main.customer;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import vn.touchspace.example.data.DataManager;
import vn.touchspace.example.ui.base.BasePresenter;
import vn.touchspace.example.utils.rx.SchedulerProvider;

/**
 * Created by GNUD on 04/12/2017.
 */

public class CustomerPresenter<V extends CustomerMvpView> extends BasePresenter<V>
        implements CustomerMvpPresenter<V> {

    private static final String TAG = "CustomerPresenter";

    @Inject
    public CustomerPresenter(
            DataManager dataManager,
            SchedulerProvider schedulerProvider,
            CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

}
