package vn.touchspace.example.ui.main.invoice.invoicedetail;


import vn.touchspace.example.ui.base.MvpPresenter;
import vn.touchspace.example.ui.main.invoice.invoicefinish.InvoiceMvpView;

public interface InvoiceDetailMvpPresenter<V extends InvoiceDetailMvpView> extends MvpPresenter<V> {
    void changeState(String id, String state);
}
