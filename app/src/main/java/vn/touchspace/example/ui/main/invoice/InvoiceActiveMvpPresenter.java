package vn.touchspace.example.ui.main.invoice;


import vn.touchspace.example.ui.base.MvpPresenter;

public interface InvoiceActiveMvpPresenter<V extends InvoiceActiveMvpView> extends MvpPresenter<V> {
    void getInvoices(String state);
}
