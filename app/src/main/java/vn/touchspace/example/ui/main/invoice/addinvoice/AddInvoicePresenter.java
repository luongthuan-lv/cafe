package vn.touchspace.example.ui.main.invoice.addinvoice;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import vn.touchspace.example.data.DataManager;
import vn.touchspace.example.ui.base.BasePresenter;
import vn.touchspace.example.ui.main.invoice.invoicedetail.InvoiceDetailMvpPresenter;
import vn.touchspace.example.ui.main.invoice.invoicedetail.InvoiceDetailMvpView;
import vn.touchspace.example.utils.rx.SchedulerProvider;

public class AddInvoicePresenter<V extends AddInvoiceMvpView> extends BasePresenter<V>
        implements AddInvoiceMvpPresenter<V> {

    private static final String TAG = "AddInvoicePresenter";

    @Inject
    public AddInvoicePresenter(DataManager dataManager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

}
