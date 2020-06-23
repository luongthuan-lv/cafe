package vn.touchspace.example.ui.main.statistical;

import android.os.Handler;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import vn.touchspace.example.data.DataManager;
import vn.touchspace.example.data.network.model.request.AddProductRequest;
import vn.touchspace.example.data.network.model.request.AddStaffRequest;
import vn.touchspace.example.ui.base.BasePresenter;
import vn.touchspace.example.ui.main.product.addproduct.AddProductMvpPresenter;
import vn.touchspace.example.ui.main.product.addproduct.AddProductMvpView;
import vn.touchspace.example.utils.rx.SchedulerProvider;

public class StatisticalPresenter<V extends StatisticalMvpView> extends BasePresenter<V>
        implements StatisticalMvpPresenter<V> {

    private static final String TAG = "StatisticalPresenter";

    @Inject
    public StatisticalPresenter(DataManager dataManager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void getFinish(String startDate, String endDate) {
        getCompositeDisposable().add(getDataManager()
                .getStatistical(startDate, endDate, "finish")
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(list -> {
                    if (!isViewAttached()) {
                        return;
                    }
                    new Handler().postDelayed(() -> getMvpView().hideLoading(), 500);
                    getMvpView().finishSuccess(list);
                }, throwable -> {
                    if (!isViewAttached()) {
                        return;
                    }
                    new Handler().postDelayed(() -> getMvpView().hideLoading(), 500);
                    handleApiError(throwable);
                })
        );
    }

    @Override
    public void getActive(String startDate, String endDate) {
        getCompositeDisposable().add(getDataManager()
                .getStatistical(startDate, endDate, "active")
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(list -> {
                    if (!isViewAttached()) {
                        return;
                    }
                    new Handler().postDelayed(() -> getMvpView().hideLoading(), 500);
                    getMvpView().activeSuccess(list);
                }, throwable -> {
                    if (!isViewAttached()) {
                        return;
                    }
                    new Handler().postDelayed(() -> getMvpView().hideLoading(), 500);
                    handleApiError(throwable);
                })
        );
    }

    @Override
    public void getCancel(String startDate, String endDate) {
        getCompositeDisposable().add(getDataManager()
                .getStatistical(startDate, endDate, "cancel")
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(list -> {
                    if (!isViewAttached()) {
                        return;
                    }
                    new Handler().postDelayed(() -> getMvpView().hideLoading(), 500);
                    getMvpView().cancelSuccess(list);
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
