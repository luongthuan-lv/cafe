package vn.touchspace.example.ui.main.invoice.invoicedetail;

import java.util.List;

import vn.touchspace.example.data.network.model.response.Invoice;
import vn.touchspace.example.ui.base.MvpView;

public interface InvoiceDetailMvpView extends MvpView {
    void success(String mesage);
}
