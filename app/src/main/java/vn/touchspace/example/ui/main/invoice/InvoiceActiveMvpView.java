package vn.touchspace.example.ui.main.invoice;

import java.util.List;

import vn.touchspace.example.data.network.model.response.Invoice;
import vn.touchspace.example.ui.base.MvpView;

public interface InvoiceActiveMvpView extends MvpView {
    void getInvoicesSuccess(List<Invoice> list);
}
