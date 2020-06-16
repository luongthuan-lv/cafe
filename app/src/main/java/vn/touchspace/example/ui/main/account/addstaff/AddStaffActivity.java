package vn.touchspace.example.ui.main.account.addstaff;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.touchspace.example.R;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import vn.touchspace.example.ui.base.BaseActivity;

public class AddStaffActivity extends BaseActivity implements AddStaffMvpView {

    @Inject
    AddStaffMvpPresenter<AddStaffMvpView> mPresenter;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.edt_name)
    EditText edtName;
    @BindView(R.id.rd_staff)
    RadioButton rdStaff;
    @BindView(R.id.rd_manager)
    RadioButton rdManager;

    private String role, roleV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);
        ButterKnife.bind(this);

        getActivityComponent().inject(this);

        mPresenter.onAttach(this);

        tvTitle.setText(R.string.title_add_staff);

        role = getIntent().getStringExtra("role");

        rdStaff.setChecked(true);
        roleV = "staff";

        if(role.equals("director")){
            rdManager.setVisibility(View.VISIBLE);
        }else{
            rdManager.setVisibility(View.GONE);
            roleV = "staff";
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.onDetach();
    }


    @Override
    protected void setUp() {
    }


    @Override
    public void addSuccess(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        finish();
    }

    @OnClick({R.id.iv_back, R.id.rd_staff, R.id.rd_manager, R.id.btn_save})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.rd_staff:
                roleV = "staff";
                break;
            case R.id.rd_manager:
                roleV = "manager";
                break;
            case R.id.btn_save:
                if (edtName.getText().toString().trim().equals("")) {
                    Toast.makeText(this, "Vui lòng không để trống!", Toast.LENGTH_SHORT).show();
                } else {
                    mPresenter.addStaff(edtName.getText().toString().trim(), roleV);
                }
                break;
        }
    }
}