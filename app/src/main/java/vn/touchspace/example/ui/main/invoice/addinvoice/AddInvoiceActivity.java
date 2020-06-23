package vn.touchspace.example.ui.main.invoice.addinvoice;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.touchspace.example.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import vn.touchspace.example.data.network.model.AddInvoiceModel;
import vn.touchspace.example.data.network.model.response.Customer;
import vn.touchspace.example.data.network.model.response.Product;
import vn.touchspace.example.data.network.model.response.ProductOfInvoice;
import vn.touchspace.example.data.network.model.response.User;
import vn.touchspace.example.ui.adapter.AddInvoiceAdapter;
import vn.touchspace.example.ui.base.BaseActivity;
import vn.touchspace.example.utils.AppUtils;
import vn.touchspace.example.utils.recycler.SetupRvUtils;

public class AddInvoiceActivity extends BaseActivity implements AddInvoiceMvpView {

    @Inject
    AddInvoiceMvpPresenter<AddInvoiceMvpView> mPresenter;

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.edt_customer_name)
    AutoCompleteTextView edtCustomerName;
    @BindView(R.id.edt_staff_name)
    AutoCompleteTextView edtStaffName;
    @BindView(R.id.tv_date)
    TextView tvDate;
    @BindView(R.id.edt_product_name)
    AutoCompleteTextView edtProductName;
    @BindView(R.id.edt_number_of_product)
    AutoCompleteTextView edtNumberOfProduct;
    @BindView(R.id.btn_add_product)
    Button btnAddProduct;
    @BindView(R.id.btn_add_invoice)
    Button btnAddInvoice;
    @BindView(R.id.tv_total_value)
    TextView tvTotalValue;
    @BindView(R.id.rcy_view)
    RecyclerView rcyView;
    @BindView(R.id.tv_discount_percentage)
    TextView tvDiscountPercentage;
    private List<String> customerList;
    private List<String> productList;
    private List<Double> priceList;
    private List<Integer> discountPercentageList;
    private List<AddInvoiceModel> list;
    private AddInvoiceAdapter adapter;
    private double totalValue = 0;
    private int discountPercentage = 0;
    private List<Customer> customers;
    String customerId = "";
    double customerInvoiceSum = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_invoice);
        ButterKnife.bind(this);

        getActivityComponent().inject(this);

        mPresenter.onAttach(this);

        tvTitle.setText(R.string.title_add_invoice);

        mPresenter.getCustomers();
        mPresenter.getProducts();
        mPresenter.getStaffs();

        setUp();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.onDetach();
    }


    @Override
    protected void setUp() {
        Calendar calendar = Calendar.getInstance();
        long date = calendar.getTimeInMillis();
        tvDate.setText(AppUtils.convertTime(date));

        list = new ArrayList<>();
        adapter = new AddInvoiceAdapter(list, this);

        adapter.setCallBack((total, position) -> {
            totalValue = 0;
            for (int i = 0; i < list.size(); i++) {
                totalValue += list.get(i).getProductPrice() * list.get(i).getNumberOfProduct();
            }
            totalValue = totalValue - total;
            totalValue = totalValue - (totalValue * discountPercentage / 100);
            tvTotalValue.setText(String.format("%s VNĐ", AppUtils.formatNumber(totalValue)));
            adapter.removeItem(position);
        });
        SetupRvUtils.setupLinearLayoutRecyclerView(this, rcyView);
        rcyView.setAdapter(adapter);

        edtCustomerName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                for (int i = 0; i < customerList.size(); i++) {
                    if (customerList.get(i).equals(edtCustomerName.getText().toString().trim())) {
                        discountPercentage = discountPercentageList.get(i);
                        customerId = customers.get(i).getId();
                        customerInvoiceSum = customers.get(i).getCustomerInvoiceSum();
                        tvDiscountPercentage.setText(String.format("Chiết khấu: %s%%", discountPercentage));
                    }
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    public void addSuccess(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void getCustomersSuccess(List<Customer> list) {
        customers = list;
        customerList = new ArrayList<>();
        discountPercentageList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            customerList.add(list.get(i).getCustomerName());
            if (list.get(i).getCustomerInvoiceSum() >= 700000) {
                discountPercentageList.add(10);
            } else {
                discountPercentageList.add(0);
            }

        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, customerList);
        edtCustomerName.setAdapter(adapter);
        edtCustomerName.setThreshold(1);
    }

    @Override
    public void getStaffsSuccess(List<User> list) {
        List<String> staffList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            staffList.add(list.get(i).getFullName());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, staffList);
        edtStaffName.setAdapter(adapter);
        edtStaffName.setThreshold(1);
    }

    @Override
    public void getProductsSuccess(List<Product> list) {
        priceList = new ArrayList<>();
        productList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            productList.add(list.get(i).getProductName());
            priceList.add(list.get(i).getProductPrice());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, productList);
        edtProductName.setAdapter(adapter);
        edtProductName.setThreshold(1);
    }

    @OnClick({R.id.iv_back, R.id.btn_add_product, R.id.btn_add_invoice})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.btn_add_product:
                if (edtProductName.getText().toString().trim().equals("") || edtNumberOfProduct.getText().toString().trim().equals("")) {
                    Toast.makeText(this, "Vui lòng không để trống", Toast.LENGTH_SHORT).show();
                } else {
                    AddInvoiceModel addInvoiceModel = new AddInvoiceModel();
                    addInvoiceModel.setProductName(edtProductName.getText().toString().trim());
                    addInvoiceModel.setNumberOfProduct(Integer.parseInt(edtNumberOfProduct.getText().toString().trim()));
                    for (int i = 0; i < productList.size(); i++) {
                        if (productList.get(i).equals(addInvoiceModel.getProductName())) {
                            addInvoiceModel.setProductPrice(priceList.get(i));
                        }
                    }
                    adapter.addItem(addInvoiceModel);
                    totalValue = 0;
                    for (int i = 0; i < list.size(); i++) {
                        totalValue += list.get(i).getProductPrice() * list.get(i).getNumberOfProduct();
                    }
                    totalValue = totalValue - (totalValue * discountPercentage / 100);
                    tvTotalValue.setText(String.format("%s VNĐ", AppUtils.formatNumber(totalValue)));
                    edtProductName.setText("");
                    edtNumberOfProduct.setText("");
                }
                InputMethodManager imm = (InputMethodManager) this.getSystemService(INPUT_METHOD_SERVICE);
                assert imm != null;
                imm.hideSoftInputFromWindow(btnAddProduct.getWindowToken(), 0);
                break;
            case R.id.btn_add_invoice:
                if (edtStaffName.getText().toString().trim().equals("") || edtCustomerName.getText().toString().trim().equals("")) {
                    Toast.makeText(this, "Vui lòng không để trống", Toast.LENGTH_SHORT).show();
                } else {
                    if (adapter.getDataSet().size() == 0) {
                        Toast.makeText(this, "Vui lòng thêm sản phẩm", Toast.LENGTH_SHORT).show();
                    } else {

                        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
                        alertDialog.setIcon(R.mipmap.ic_launcher);
                        alertDialog.setMessage("Xác nhận xóa?");
                        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Có",
                                (dialog, which) -> {
                                    customerInvoiceSum = totalValue + customerInvoiceSum;
                                    totalValue = totalValue - (totalValue * discountPercentage / 100);
                                    List<ProductOfInvoice> productOfInvoices = new ArrayList<>();
                                    for (int i = 0; i < adapter.getDataSet().size(); i++) {
                                        productOfInvoices.add(new ProductOfInvoice(adapter.getDataSet().get(i).getProductName(), adapter.getDataSet().get(i).getNumberOfProduct()));
                                    }
                                    mPresenter.addInvoice(edtCustomerName.getText().toString().trim(),
                                            edtStaffName.getText().toString().trim(),
                                            tvDate.getText().toString().trim(),
                                            discountPercentage,
                                            totalValue,
                                            productOfInvoices);
                                    mPresenter.updateCustomer(customerId, customerInvoiceSum);
                                    dialog.dismiss();
                                });
                        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Không",
                                (dialog, which) -> dialog.dismiss());
                        alertDialog.show();
                    }
                }
                break;
        }
    }
}