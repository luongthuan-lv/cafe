package vn.touchspace.example.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.touchspace.example.R;

import javax.inject.Inject;


import vn.touchspace.example.data.room.model.User;
import vn.touchspace.example.service.notify.NotifyService;
import vn.touchspace.example.ui.base.BaseActivity;
import vn.touchspace.example.ui.login.LoginActivity;

public class MainActivity extends BaseActivity implements MainMvpView {

    @Inject
    MainMvpPresenter<MainMvpView> mMainPresenter;

    @Inject
    NotifyService mNotifyService;


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
