package vn.touchspace.example.ui.main.customer.addcustomer;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.touchspace.example.R;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import vn.touchspace.example.ui.base.BaseActivity;
import vn.touchspace.example.ui.main.info.updateinfo.UpdateInfoActivity;

public class AddCustomerActivity extends BaseActivity implements AddCustomerMvpView {

    @Inject
    AddCustomerMvpPresenter<AddCustomerMvpView> mPresenter;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.edt_name)
    EditText edtName;
    @BindView(R.id.edt_tel)
    EditText edtTel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_customer);
        ButterKnife.bind(this);

        getActivityComponent().inject(this);

        mPresenter.onAttach(this);

        tvTitle.setText(R.string.title_add_customer);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.onDetach();
    }


    @Override
    protected void setUp() {
    }


    @OnClick({R.id.iv_back, R.id.btn_save})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.btn_save:
                if (edtName.getText().toString().trim().equals("") || edtTel.getText().toString().trim().equals("")) {
                    Toast.makeText(this, "Vui lòng không để trống!", Toast.LENGTH_SHORT).show();
                } else {
                    if (edtTel.getText().toString().trim().length() != 10) {
                        Toast.makeText(this, "Số điện thoại phải có 10 chữ số!", Toast.LENGTH_SHORT).show();
                    }else{
                        mPresenter.addCustomer(edtName.getText().toString().trim(), edtTel.getText().toString().trim());
                    }
                }
                break;
        }
    }

    @Override
    public void addCustomer(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        finish();
    }
}