package vn.touchspace.example.ui.login;

import android.content.Intent;
import android.os.Bundle;
import com.touchspace.example.R;
import vn.touchspace.example.ui.base.BaseActivity;
import vn.touchspace.example.ui.main.MainActivity;

import javax.inject.Inject;

public class LoginActivity extends BaseActivity implements LoginMvpView {

    @Inject
    LoginMvpPresenter<LoginMvpView> mLoginPresenter;

    private static final String TAG = "LoginActivity";

    @Override
    protected void setUp() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getActivityComponent().inject(this);

        mLoginPresenter.onAttach(LoginActivity.this);

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

}
