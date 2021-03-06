package vn.touchspace.example.ui.main.invoice.invoicecancel;

import android.os.Handler;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import vn.touchspace.example.data.DataManager;
import vn.touchspace.example.ui.base.BasePresenter;
import vn.touchspace.example.utils.rx.SchedulerProvider;

public class InvoiceCancelPresenter<V extends InvoiceCancelMvpView> extends BasePresenter<V>
        implements InvoiceCancelMvpPresenter<V> {

    private static final String TAG = "InvoiceCancelPresenter";

    @Inject
    public InvoiceCancelPresenter(DataManager dataManager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }


    @Override
    public void getInvoices(String state) {
        getMvpView().showLoading();
        getCompositeDisposable().add(getDataManager()
                .getInvoices(state)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(list -> {
                    if (!isViewAttached()) {
                        return;
                    }
                    new Handler().postDelayed(() -> getMvpView().hideLoading(), 500);
                    getMvpView().getInvoicesSuccess(list);
                }, throwable -> {
                    if (!isViewAttached()) {
                        return;
                    }
                    new Handler().postDelayed(() -> getMvpView().hideLoading(), 500);
                    handleApiError(throwable);
                }));
    }
}
