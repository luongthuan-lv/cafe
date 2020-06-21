package vn.touchspace.example.ui.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.google.android.material.navigation.NavigationView;
import com.touchspace.example.R;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import vn.touchspace.example.data.network.model.response.User;
import vn.touchspace.example.ui.base.BaseActivity;
import vn.touchspace.example.ui.login.LoginActivity;
import vn.touchspace.example.ui.main.account.AccountFragment;
import vn.touchspace.example.ui.main.account.addstaff.AddStaffActivity;
import vn.touchspace.example.ui.main.customer.CustomerFragment;
import vn.touchspace.example.ui.main.customer.addcustomer.AddCustomerActivity;
import vn.touchspace.example.ui.main.info.InfoFragment;
import vn.touchspace.example.ui.main.invoice.InvoiceFragment;
import vn.touchspace.example.ui.main.invoice.InvoicesFragment;
import vn.touchspace.example.ui.main.product.ProductFragment;
import vn.touchspace.example.ui.main.product.addproduct.AddProductActivity;
import vn.touchspace.example.ui.main.statistical.StatisticalFragment;
import vn.touchspace.example.utils.AppUtils;

public class MainActivity extends BaseActivity implements MainMvpView {

    @Inject
    MainMvpPresenter<MainMvpView> mMainPresenter;

    private static final String TAG = "MainActivity";
    @BindView(R.id.iv_nav)
    ImageView ivNav;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.nav_view)
    NavigationView navView;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    TextView tvName, tvAvatar;
    TextView tvRole;
    @BindView(R.id.ic_add)
    ImageView icAdd;

    private long backPressedTime;
    private InvoicesFragment invoiceFragment = InvoicesFragment.newInstance();
    private String role = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        getActivityComponent().inject(this);

        mMainPresenter.onAttach(MainActivity.this);
        navigationDrawerClick();

        navView.getMenu().getItem(0).setChecked(true);

        View headerNav = navView.getHeaderView(0);

        tvName = headerNav.findViewById(R.id.tv_name);
        tvRole = headerNav.findViewById(R.id.tv_role);
        tvAvatar = headerNav.findViewById(R.id.tv_avatar);

        gotoFragment(invoiceFragment, getString(R.string.menu_invoice));
        icAdd.setVisibility(View.VISIBLE);

        mMainPresenter.getUserInfo();

        setUp();
    }

    private void navigationDrawerClick() {
        navView.setNavigationItemSelectedListener(menuItem -> {
            switch (menuItem.getItemId()) {
                case R.id.nav_invoice:
                    icAdd.setVisibility(View.VISIBLE);
                    gotoFragment(invoiceFragment, getString(R.string.menu_invoice));
                    break;
                case R.id.nav_customer:
                    icAdd.setVisibility(View.VISIBLE);
                    gotoFragment(CustomerFragment.newInstance(), getString(R.string.menu_customer));
                    icAdd.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, AddCustomerActivity.class)));
                    break;
                case R.id.nav_account:
                    if(role.equals("staff")){
                        icAdd.setVisibility(View.GONE);
                    }else{
                        icAdd.setVisibility(View.VISIBLE);
                    }
                    gotoFragment(AccountFragment.newInstance(), getString(R.string.menu_account));
                    icAdd.setOnClickListener(v -> {
                        Intent intent = new Intent(MainActivity.this, AddStaffActivity.class);
                        intent.putExtra("role", role);
                        startActivity(intent);
                    });
                    break;
                case R.id.nav_product:
                    icAdd.setVisibility(View.VISIBLE);
                    gotoFragment(ProductFragment.newInstance(), getString(R.string.menu_product));
                    icAdd.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, AddProductActivity.class)));
                    break;
                case R.id.nav_statistical:
                    icAdd.setVisibility(View.GONE);
                    gotoFragment(StatisticalFragment.newInstance(), getString(R.string.menu_statistical));
                    break;
                case R.id.nav_info:
                    icAdd.setVisibility(View.GONE);
                    gotoFragment(InfoFragment.newInstance(), getString(R.string.menu_info));
                    break;
                case R.id.nav_logout:
                    AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
                    alertDialog.setIcon(R.mipmap.ic_launcher);
                    alertDialog.setTitle("Thông báo");
                    alertDialog.setMessage("Bạn có chắc chắn muốn đăng xuất?");
                    alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Có",
                            (dialog, which) -> {
                                dialog.dismiss();
                                mMainPresenter.logout();
                                startActivity(new Intent(MainActivity.this, LoginActivity.class));
                                finish();
                            });
                    alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Không",
                            (dialog, which) -> dialog.dismiss());
                    alertDialog.show();
                    break;
            }
            drawerLayout.closeDrawers();
            return true;
        });
    }

    private void gotoFragment(Fragment fragment, String title) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.nav_host_fragment, fragment, fragment.getClass().getSimpleName())
                .commit();
        tvTitle.setText(title);
    }

    @OnClick(R.id.iv_nav)
    public void onViewClicked() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawers();
        } else {
            drawerLayout.openDrawer(GravityCompat.START);
            hideKeyBoard();
        }
    }

    @Override
    protected void setUp() {

    }

    @Override
    public void openLoginActivity() {
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }

    @Override
    public void getUserInfoSuccess(User user) {
        tvName.setText(user.getFullName());
        AppUtils.setDefaultAvatar(tvAvatar, user.getFullName());
        tvRole.setText(AppUtils.getRole(user.getRole()));
        role = user.getRole();
    }

    @Override
    protected void onDestroy() {
        mMainPresenter.onDetach();
        super.onDestroy();
    }

    private void hideKeyBoard() {
        // ẩn bàn phím
        InputMethodManager inputMethodManager = (InputMethodManager)
                navView.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (inputMethodManager != null) {
            inputMethodManager.hideSoftInputFromWindow(navView.getWindowToken(), 0);
        }
    }

    @Override
    public void onBackPressed() {
        if (backPressedTime + 2000 > System.currentTimeMillis()) {
            super.onBackPressed();
            return;
        } else {
            Toast.makeText(this, "Nhấn lần nữa để thoát", Toast.LENGTH_SHORT).show();
        }
        backPressedTime = System.currentTimeMillis();
    }


}
