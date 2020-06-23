package vn.touchspace.example.ui.main.invoice.invoicedetail;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.touchspace.example.R;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import vn.touchspace.example.data.network.model.response.Invoice;
import vn.touchspace.example.ui.adapter.InvoiceDetailAdapter;
import vn.touchspace.example.ui.base.BaseActivity;
import vn.touchspace.example.utils.AppUtils;
import vn.touchspace.example.utils.recycler.SetupRvUtils;

public class InvoiceDetailActivity extends BaseActivity implements InvoiceDetailMvpView {

    @Inject
    InvoiceDetailMvpPresenter<InvoiceDetailMvpView> mPresenter;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_customer_name)
    TextView tvCustomerName;
    @BindView(R.id.tv_staff_name)
    TextView tvStaffName;
    @BindView(R.id.tv_date)
    TextView tvDate;
    @BindView(R.id.tv_value)
    TextView tvValue;
    @BindView(R.id.tv_discount_percentage)
    TextView tvDiscountPercentage;
    @BindView(R.id.tv_state)
    TextView tvState;
    @BindView(R.id.rcy_view)
    RecyclerView rcyView;
    @BindView(R.id.btn_finish)
    Button btnFinish;
    @BindView(R.id.btn_cancel)
    Button btnCancel;
    @BindView(R.id.layout_bottom)
    LinearLayout layoutBottom;
    private Invoice invoice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invoice_detail);
        ButterKnife.bind(this);

        getActivityComponent().inject(this);

        mPresenter.onAttach(this);

        tvTitle.setText(R.string.title_invoice_detail);

        invoice = (Invoice) getIntent().getSerializableExtra("invoice_model");

        setUp();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.onDetach();
    }

    @Override
    protected void setUp() {
        tvCustomerName.setText(String.format("Tên khách hàng: %s", invoice.getCustomerName()));
        tvStaffName.setText(String.format("Nhân viên tạo: %s", invoice.getCreateStaff()));
        tvValue.setText(String.format("Tổng giá trị: %s VNĐ", AppUtils.formatNumber(invoice.getTotalValue())));
        tvDate.setText(String.format("Ngày tạo: %s", invoice.getCreateDate()));
        tvDiscountPercentage.setText(String.format("Chiết khấu: %s%%", invoice.getDiscountPercentage()));

        switch (invoice.getState()) {
            case "cancel":
                tvState.setText(R.string.str_state_cancel);
                layoutBottom.setVisibility(View.GONE);
                rcyView.setPadding(0, 0, 0, 0);

                break;
            case "finish":
                tvState.setText(R.string.str_state_finish);
                btnFinish.setVisibility(View.GONE);
                rcyView.setPadding(0, 0, 0, 72);
                break;
            case "active":
                tvState.setText(R.string.str_state_active);
                rcyView.setPadding(0, 0, 0, 72);
                break;
        }


        InvoiceDetailAdapter adapter = new InvoiceDetailAdapter(invoice.getList());
        SetupRvUtils.setupLinearLayoutRecyclerView(this, rcyView);
        rcyView.setAdapter(adapter);
        
    }

    @OnClick({R.id.iv_back, R.id.btn_finish, R.id.btn_cancel})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.btn_finish:
                AlertDialog alertDialog = new AlertDialog.Builder(this).create();
                alertDialog.setIcon(R.mipmap.ic_launcher);
                alertDialog.setMessage("Hoàn thành đơn?");
                alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Xong",
                        (dialog, which) -> {
                            mPresenter.changeState(invoice.getId(), "finish");
                            dialog.dismiss();
                        });
                alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Không",
                        (dialog, which) -> dialog.dismiss());
                alertDialog.show();
                break;
            case R.id.btn_cancel:
                AlertDialog alertDialogCancel = new AlertDialog.Builder(this).create();
                alertDialogCancel.setIcon(R.mipmap.ic_launcher);
                alertDialogCancel.setMessage("Hủy đơn?");
                alertDialogCancel.setButton(AlertDialog.BUTTON_POSITIVE, "Hủy",
                        (dialog, which) -> {
                            mPresenter.changeState(invoice.getId(), "cancel");
                            dialog.dismiss();
                        });
                alertDialogCancel.setButton(AlertDialog.BUTTON_NEGATIVE, "Không",
                        (dialog, which) -> dialog.dismiss());
                alertDialogCancel.show();
                break;
        }
    }

    @Override
    public void success(String mesage) {
        Toast.makeText(this, mesage, Toast.LENGTH_SHORT).show();
        finish();
    }
}