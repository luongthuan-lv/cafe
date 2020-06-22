package vn.touchspace.example.ui.main.invoice.invoicedetail;


import android.os.Handler;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import vn.touchspace.example.data.DataManager;
import vn.touchspace.example.data.network.model.request.UpdateStateRequest;
import vn.touchspace.example.ui.base.BasePresenter;
import vn.touchspace.example.utils.rx.SchedulerProvider;

public class InvoiceDetailPresenter<V extends InvoiceDetailMvpView> extends BasePresenter<V>
        implements InvoiceDetailMvpPresenter<V> {

    private static final String TAG = "InvoiceDetailPresenter";

    @Inject
    public InvoiceDetailPresenter(DataManager dataManager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void changeState(String id, String state) {
        getMvpView().showLoading();
        UpdateStateRequest request = new UpdateStateRequest();
        request.id =  id;
        request.state = state;

        getCompositeDisposable().add(getDataManager()
                .updateState(request)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(message -> {
                    if (!isViewAttached()) {
                        return;
                    }
                    new Handler().postDelayed(() -> getMvpView().hideLoading(), 500);
                    getMvpView().success(message.getMessage());
                }, throwable -> {
                    if (!isViewAttached()) {
                        return;
                    }
                    new Handler().postDelayed(() -> getMvpView().hideLoading(), 500);
                    handleApiError(throwable);
                }));
    }
}
