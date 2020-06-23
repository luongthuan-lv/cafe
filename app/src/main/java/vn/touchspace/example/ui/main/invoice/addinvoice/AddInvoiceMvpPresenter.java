package vn.touchspace.example.ui.main.invoice.addinvoice;


import java.util.List;

import vn.touchspace.example.data.network.model.response.ProductOfInvoice;
import vn.touchspace.example.ui.base.MvpPresenter;
import vn.touchspace.example.ui.main.invoice.invoicedetail.InvoiceDetailMvpView;

public interface AddInvoiceMvpPresenter<V extends AddInvoiceMvpView> extends MvpPresenter<V> {

    void getStaffs();

    void getCustomers();

    void getProducts();

    void addInvoice(String customerName, String staffName, String createDate, int discountPercentage, double totalValue, List<ProductOfInvoice> list);

    void updateCustomer(String id, double total);
}
