package vn.touchspace.example.ui.main.invoice;


import vn.touchspace.example.ui.base.MvpPresenter;
import vn.touchspace.example.ui.main.info.InfoMvpView;

public interface InvoiceMvpPresenter<V extends InvoiceMvpView> extends MvpPresenter<V> {
    void getInvoices(String state);
}
