package vn.touchspace.example.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.touchspace.example.R;

import javax.inject.Inject;

import butterknife.BindDimen;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import vn.touchspace.example.data.room.model.User;
import vn.touchspace.example.service.notify.NotifyService;
import vn.touchspace.example.ui.base.BaseActivity;
import vn.touchspace.example.ui.login.LoginActivity;
import vn.touchspace.example.ui.rate.RateDialog;

public class MainActivity extends BaseActivity implements MainMvpView {

    @Inject
    MainMvpPresenter<MainMvpView> mMainPresenter;

    @Inject
    NotifyService mNotifyService;

    @BindView(R.id.edt_user)
    EditText edtUser;
    @BindView(R.id.btn_save)
    Button btnSave;
    @BindView(R.id.btn_getAll)
    Button btnGetAll;
    @BindView(R.id.btn_find)
    Button btnFind;

    @BindDimen(R.dimen.dp24)
    int dp24;

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getActivityComponent().inject(this);

        mMainPresenter.onAttach(MainActivity.this);


        setUp();
    }

    @Override
    protected void setUp() {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @OnClick(R.id.btn_logout)
    public void logout() {
        mMainPresenter.logout();
    }

    @Override
    public void openLoginActivity() {
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }

    @Override
    public void findUserByName(User user) {
        Log.i(TAG, "findUserByName: " + user.getName());
    }

    @Override
    protected void onDestroy() {
        mMainPresenter.onDetach();
        super.onDestroy();
    }


}
