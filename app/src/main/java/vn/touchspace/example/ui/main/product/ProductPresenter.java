package vn.touchspace.example.ui.main.product;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import vn.touchspace.example.data.DataManager;
import vn.touchspace.example.data.network.model.response.User;
import vn.touchspace.example.ui.base.BasePresenter;
import vn.touchspace.example.ui.main.info.InfoMvpPresenter;
import vn.touchspace.example.ui.main.info.InfoMvpView;
import vn.touchspace.example.utils.rx.SchedulerProvider;

public class ProductPresenter<V extends ProductMvpView> extends BasePresenter<V>
        implements ProductMvpPresenter<V> {

    private static final String TAG = "InfoPresenter";

    @Inject
    public ProductPresenter(DataManager dataManager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void getProducts() {
            getCompositeDisposable().add(getDataManager()
                    .getProducts()
                    .subscribeOn(getSchedulerProvider().io())
                    .observeOn(getSchedulerProvider().ui())
                    .subscribe(products -> {
                        if (!isViewAttached()) {
                            return;
                        }
                        getMvpView().getList(products);
                    }, throwable -> {
                        if (!isViewAttached()) {
                            return;
                        }
                        handleApiError(throwable);
                    }));
    }
}
