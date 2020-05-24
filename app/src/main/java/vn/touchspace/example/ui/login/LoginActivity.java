package vn.touchspace.example.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;

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
    @BindView(R.id.chk_remember_me)
    CheckBox chkRememberMe;
    @BindView(R.id.btn_login)
    Button btnLogin;

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
    }
}
