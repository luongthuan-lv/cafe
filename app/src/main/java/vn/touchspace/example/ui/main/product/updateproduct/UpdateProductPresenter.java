package vn.touchspace.example.ui.main.product.updateproduct;

import android.os.Handler;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import vn.touchspace.example.data.DataManager;
import vn.touchspace.example.data.network.model.request.UpdatePasswordRequest;
import vn.touchspace.example.data.network.model.request.UpdateProductRequest;
import vn.touchspace.example.data.network.model.response.User;
import vn.touchspace.example.ui.base.BasePresenter;
import vn.touchspace.example.utils.rx.SchedulerProvider;

public class UpdateProductPresenter<V extends UpdateProductMvpView> extends BasePresenter<V>
        implements UpdateProductMvpPresenter<V> {

    private static final String TAG = "InfoPresenter";

    @Inject
    public UpdateProductPresenter(DataManager dataManager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void updateProduct(String id, String name, String price, String type, String image) {
        if (!getMvpView().isDoubleClick()) {
            UpdateProductRequest request = new UpdateProductRequest();
            request.id = id;
            request.productName = name;
            request.productPrice = price;
            request.productType = type;
            request.productImage = image;

            getMvpView().showLoading();
            getCompositeDisposable().add(getDataManager()
                    .updateProduct(request)
                    .subscribeOn(getSchedulerProvider().io())
                    .observeOn(getSchedulerProvider().ui())
                    .subscribe(message -> {
                        if (!isViewAttached()) {
                            return;
                        }
                        new Handler().postDelayed(() -> getMvpView().hideLoading(), 500);
                        getMvpView().updateSuccess(message.getMessage());
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
