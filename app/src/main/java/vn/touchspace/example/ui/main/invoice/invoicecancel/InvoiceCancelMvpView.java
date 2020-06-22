package vn.touchspace.example.ui.main.invoice.invoicecancel;

import java.util.List;

import vn.touchspace.example.data.network.model.response.Invoice;
import vn.touchspace.example.ui.base.MvpView;

public interface InvoiceCancelMvpView extends MvpView {
    void getInvoicesSuccess(List<Invoice> list);
}
