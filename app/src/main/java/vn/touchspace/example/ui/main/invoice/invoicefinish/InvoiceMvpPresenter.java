package vn.touchspace.example.ui.main.invoice.invoicefinish;


import vn.touchspace.example.ui.base.MvpPresenter;

public interface InvoiceMvpPresenter<V extends InvoiceMvpView> extends MvpPresenter<V> {
    void getInvoices(String state);
}
