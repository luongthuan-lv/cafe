package vn.touchspace.example.ui.main.customer.addcustomer;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import vn.touchspace.example.data.DataManager;
import vn.touchspace.example.data.network.model.request.AddCustomerRequest;
import vn.touchspace.example.data.network.model.request.AddStaffRequest;
import vn.touchspace.example.ui.base.BasePresenter;
import vn.touchspace.example.ui.main.account.addstaff.AddStaffMvpPresenter;
import vn.touchspace.example.ui.main.account.addstaff.AddStaffMvpView;
import vn.touchspace.example.utils.rx.SchedulerProvider;

public class AddCustomerPresenter<V extends AddCustomerMvpView> extends BasePresenter<V>
        implements AddCustomerMvpPresenter<V> {

    private static final String TAG = "AddCustomerPresenter";

    @Inject
    public AddCustomerPresenter(DataManager dataManager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }


    @Override
    public void addCustomer(String name, String tel) {
        AddCustomerRequest request = new AddCustomerRequest();
        request.customerName = name;
        request.customerPhone = tel;
        request.customerInvoiceSum = "0";


        getMvpView().showLoading();
        getCompositeDisposable().add(getDataManager()
                .createCustomer(request)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(message -> {
                    if (!isViewAttached()) {
                        return;
                    }
                    getMvpView().hideLoading();
                    getMvpView().addCustomer(message.getMessage());
                }, throwable -> {
                    if (!isViewAttached()) {
                        return;
                    }
                    getMvpView().hideLoading();
                    handleApiError(throwable);
                })
        );
    }
}
