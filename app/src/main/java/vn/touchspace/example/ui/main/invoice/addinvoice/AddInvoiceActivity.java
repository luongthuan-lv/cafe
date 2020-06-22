package vn.touchspace.example.ui.main.invoice.addinvoice;

import android.os.Bundle;
import android.widget.TextView;

import com.touchspace.example.R;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import vn.touchspace.example.ui.base.BaseActivity;
import vn.touchspace.example.ui.main.account.addstaff.AddStaffMvpPresenter;
import vn.touchspace.example.ui.main.account.addstaff.AddStaffMvpView;
import vn.touchspace.example.ui.main.invoice.invoicedetail.InvoiceDetailMvpView;

public class AddInvoiceActivity extends BaseActivity implements AddInvoiceMvpView{

    @Inject
    AddInvoiceMvpPresenter<AddInvoiceMvpView> mPresenter;

    @BindView(R.id.tv_title)
    TextView tvTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_invoice);
        ButterKnife.bind(this);

        getActivityComponent().inject(this);

        mPresenter.onAttach(this);

        tvTitle.setText(R.string.title_add_staff);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.onDetach();
    }


    @Override
    protected void setUp() {

    }
}