package vn.touchspace.example.ui.main.invoice.invoicefinish;

import android.os.Handler;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import vn.touchspace.example.data.DataManager;
import vn.touchspace.example.ui.base.BasePresenter;
import vn.touchspace.example.utils.rx.SchedulerProvider;

public class InvoicePresenter<V extends InvoiceMvpView> extends BasePresenter<V>
        implements InvoiceMvpPresenter<V> {

    private static final String TAG = "InvoicePresenter";

    @Inject
    public InvoicePresenter(DataManager dataManager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
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
