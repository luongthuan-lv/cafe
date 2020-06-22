package vn.touchspace.example.ui.main.invoice.invoicecancel;


import vn.touchspace.example.ui.base.MvpPresenter;

public interface InvoiceCancelMvpPresenter<V extends InvoiceCancelMvpView> extends MvpPresenter<V> {
    void getInvoices(String state);
}
