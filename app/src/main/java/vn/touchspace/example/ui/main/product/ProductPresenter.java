package vn.touchspace.example.ui.main.product;

import android.os.Handler;
import android.util.Log;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import vn.touchspace.example.data.DataManager;
import vn.touchspace.example.data.network.model.request.RemoveRequest;
import vn.touchspace.example.ui.base.BasePresenter;
import vn.touchspace.example.utils.rx.SchedulerProvider;

public class ProductPresenter<V extends ProductMvpView> extends BasePresenter<V>
        implements ProductMvpPresenter<V> {

    private static final String TAG = "ProductPresenter";

    @Inject
    public ProductPresenter(DataManager dataManager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void getProducts(String productName) {
        getMvpView().showLoading();
        getCompositeDisposable().add(getDataManager()
                .getProducts(productName)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(list -> {
                    if (!isViewAttached()) {
                        return;
                    }
                    new Handler().postDelayed(() -> getMvpView().hideLoading(), 500);
                    getMvpView().getList(list);
                }, throwable -> {
                    if (!isViewAttached()) {
                        return;
                    }
                    new Handler().postDelayed(() -> getMvpView().hideLoading(), 500);
                    handleApiError(throwable);
                }));
    }

    @Override
    public void removeProduct(String id) {
        getMvpView().showLoading();
        RemoveRequest request = new RemoveRequest();
        request.id = id;
        getCompositeDisposable().add(getDataManager()
                .removeProduct(request)
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
                })
        );
    }
}
