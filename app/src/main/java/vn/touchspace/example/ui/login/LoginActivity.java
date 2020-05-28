package vn.touchspace.example.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.touchspace.example.R;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import vn.touchspace.example.ui.base.BaseActivity;
import vn.touchspace.example.ui.main.MainActivity;

public class LoginActivity extends BaseActivity implements LoginMvpView {

    @BindView(R.id.edt_username)
    EditText edtUsername;
    @BindView(R.id.edt_password)
    EditText edtPassword;
//    @BindView(R.id.chk_remember_me)
//    CheckBox chkRememberMe;
    @BindView(R.id.btn_login)
    Button btnLogin;
    private long backPressedTime;


    @Inject
    LoginMvpPresenter<LoginMvpView> mLoginPresenter;

    private static final String TAG = "LoginActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        getActivityComponent().inject(this);

        mLoginPresenter.onAttach(LoginActivity.this);

        mLoginPresenter.checkLogin();

    }

    @Override
    protected void setUp() {

    }


    @Override
    public void openMainActivity() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }


    @Override
    protected void onDestroy() {
        mLoginPresenter.onDetach();
        super.onDestroy();
    }

    @OnClick(R.id.btn_login)
    public void onViewClicked() {
        if (edtUsername.getText().toString().trim().equals("") || edtPassword.getText().toString().trim().equals("")) {
            Toast.makeText(LoginActivity.this, "Vui lòng không để trống!", Toast.LENGTH_SHORT).show();
        } else {
            mLoginPresenter.onServerLoginClick(edtUsername.getText().toString().trim(), edtPassword.getText().toString().trim());
        }
        isDoubleClick(btnLogin);
    }


    public void isDoubleClick(View view) {
        view.setEnabled(false);
        new Handler().postDelayed(() -> view.setEnabled(true), 500);
    }

    @Override
    public void onBackPressed() {
        if(backPressedTime + 2000 > System.currentTimeMillis()) {
            finish();
            return;
        } else {
            Toast.makeText(this, "Nhấn lần nữa để thoát!", Toast.LENGTH_SHORT).show();
        }
        backPressedTime = System.currentTimeMillis();
    }
}
