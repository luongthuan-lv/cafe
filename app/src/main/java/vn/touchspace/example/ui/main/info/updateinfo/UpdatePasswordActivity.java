package vn.touchspace.example.ui.main.info.updateinfo;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.touchspace.example.R;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import vn.touchspace.example.data.network.model.Message;
import vn.touchspace.example.data.network.model.response.User;
import vn.touchspace.example.ui.base.BaseActivity;
import vn.touchspace.example.utils.AppUtils;

public class UpdatePasswordActivity extends BaseActivity implements UpdatePasswordMvpView {

    @Inject
    UpdatePasswordMvpPresenter<UpdatePasswordMvpView> mvpPresenter;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.btn_save)
    Button btnSave;
    @BindView(R.id.edt_current_pass)
    EditText edtCurrentPass;
    @BindView(R.id.edt_new_pass)
    EditText edtNewPass;
    @BindView(R.id.edt_re_pass)
    EditText edtRePass;

    private String currentPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_password);
        ButterKnife.bind(this);
        getActivityComponent().inject(this);

        mvpPresenter.onAttach(this);
        tvTitle.setText(R.string.title_update_password);
        mvpPresenter.getInfo();
    }

    @Override
    protected void setUp() {

    }

    @OnClick({R.id.iv_back, R.id.btn_save})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                AppUtils.isDoubleClick(ivBack);
                break;
            case R.id.btn_save:
                if (edtCurrentPass.getText().toString().trim().equals("") || edtNewPass.getText().toString().trim().equals("")|| edtRePass.getText().toString().trim().equals("")) {
                    Toast.makeText(UpdatePasswordActivity.this, "Vui lòng không để trống!", Toast.LENGTH_SHORT).show();
                }else{
                    if(!currentPass.equals(edtCurrentPass.getText().toString().trim())){
                        Toast.makeText(UpdatePasswordActivity.this, "Mật khẩu hiện tại chưa đúng!", Toast.LENGTH_SHORT).show();
                    }else{
                        if(!edtRePass.getText().toString().trim().equals(edtNewPass.getText().toString().trim())){
                            Toast.makeText(UpdatePasswordActivity.this, "Mật khẩu mới chưa khớp!", Toast.LENGTH_SHORT).show();
                        }else{
                            mvpPresenter.updatePassword(edtNewPass.getText().toString().trim());
                        }
                    }
                }
                AppUtils.isDoubleClick(btnSave);
                break;
        }
    }

    @Override
    public void getInfoSuccess(User user) {
        currentPass = user.getPassword();
    }

    @Override
    public void updatePassword(String message) {
        Toast.makeText(UpdatePasswordActivity.this, message, Toast.LENGTH_SHORT).show();
        finish();
    }
}