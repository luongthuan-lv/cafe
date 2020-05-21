package vn.touchspace.example.ui.splash;

import android.content.Intent;
import android.os.Bundle;

import com.touchspace.example.R;
import vn.touchspace.example.ui.base.BaseActivity;
import vn.touchspace.example.ui.login.LoginActivity;
import vn.touchspace.example.ui.main.MainActivity;

import javax.inject.Inject;

public class SplashActivity extends BaseActivity implements SplashMvpView {

    @Inject
    SplashMvpPresenter<SplashMvpView> mSplashPresenter;

    @Override
    protected void setUp() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        getActivityComponent().inject(this);

        mSplashPresenter.onAttach(SplashActivity.this);

        mSplashPresenter.decideNextActivity();
    }

    @Override
    public void openLoginActivity() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void openMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }


    @Override
    protected void onDestroy() {
        mSplashPresenter.onDetach();
        super.onDestroy();
    }
}
