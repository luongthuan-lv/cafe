package vn.touchspace.example.ui.main.product.addproduct;

import android.os.Handler;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import vn.touchspace.example.data.DataManager;
import vn.touchspace.example.data.network.model.request.AddProductRequest;
import vn.touchspace.example.ui.base.BasePresenter;
import vn.touchspace.example.utils.rx.SchedulerProvider;

public class AddProductPresenter<V extends AddProductMvpView> extends BasePresenter<V>
        implements AddProductMvpPresenter<V> {

    private static final String TAG = "AddProductPresenter";

    @Inject
    public AddProductPresenter(DataManager dataManager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void addProduct(String name, String price, String type, String image) {
        if (!getMvpView().isDoubleClick()) {
            AddProductRequest request = new AddProductRequest();
            request.productName = name;
            request.productPrice = price;
            request.productType = type;
            request.productImage = image;
            request.productExport = 0;

            getMvpView().showLoading();
            getCompositeDisposable().add(getDataManager()
                    .createProduct(request)
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
}
