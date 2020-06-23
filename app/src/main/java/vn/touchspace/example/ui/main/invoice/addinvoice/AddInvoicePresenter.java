package vn.touchspace.example.ui.main.invoice.addinvoice;

import android.os.Handler;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import vn.touchspace.example.data.DataManager;
import vn.touchspace.example.data.network.model.request.AddInvoiceRequest;
import vn.touchspace.example.data.network.model.request.UpdateCustomerRequest;
import vn.touchspace.example.data.network.model.response.ProductOfInvoice;
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

    @Override
    public void getStaffs() {
        getMvpView().showLoading();
        getCompositeDisposable().add(getDataManager()
                .getStaffs("staff")
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(list -> {
                    if (!isViewAttached()) {
                        return;
                    }
                    new Handler().postDelayed(() -> getMvpView().hideLoading(), 500);
                    getMvpView().getStaffsSuccess(list);
                }, throwable -> {
                    if (!isViewAttached()) {
                        return;
                    }
                    new Handler().postDelayed(() -> getMvpView().hideLoading(), 500);
                    handleApiError(throwable);
                }));
    }

    @Override
    public void getCustomers() {
        getMvpView().showLoading();
        getCompositeDisposable().add(getDataManager()
                .getCustomers()
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(list -> {
                    if (!isViewAttached()) {
                        return;
                    }
                    new Handler().postDelayed(() -> getMvpView().hideLoading(), 500);
                    getMvpView().getCustomersSuccess(list);
                }, throwable -> {
                    if (!isViewAttached()) {
                        return;
                    }
                    new Handler().postDelayed(() -> getMvpView().hideLoading(), 500);
                    handleApiError(throwable);
                }));
    }

    @Override
    public void getProducts() {
        getMvpView().showLoading();
        getCompositeDisposable().add(getDataManager()
                .getProducts("")
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(list -> {
                    if (!isViewAttached()) {
                        return;
                    }
                    new Handler().postDelayed(() -> getMvpView().hideLoading(), 500);
                    getMvpView().getProductsSuccess(list);
                }, throwable -> {
                    if (!isViewAttached()) {
                        return;
                    }
                    new Handler().postDelayed(() -> getMvpView().hideLoading(), 500);
                    handleApiError(throwable);
                }));
    }

    @Override
    public void addInvoice(String customerName, String staffName, String createDate, int discountPercentage, double totalValue, List<ProductOfInvoice> list) {
        getMvpView().showLoading();
        AddInvoiceRequest request = new AddInvoiceRequest();
        request.createDate = createDate;
        request.customerName = customerName;
        request.createStaff = staffName;
        request.discountPercentage = discountPercentage;
        request.totalValue = totalValue;
        request.state = "active";
        request.list = list;

        getCompositeDisposable().add(getDataManager()
                .addInvoice(request)
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

    @Override
    public void updateCustomer(String id, double total) {
        getMvpView().showLoading();
        UpdateCustomerRequest request = new UpdateCustomerRequest();
        request.customerInvoiceSum = total;
        request.id = id;

        getCompositeDisposable().add(getDataManager()
                .updateCustomer(request)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(message -> {
                    if (!isViewAttached()) {
                        return;
                    }
                    new Handler().postDelayed(() -> getMvpView().hideLoading(), 500);
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
