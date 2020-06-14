package vn.touchspace.example.ui.main.account;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.touchspace.example.R;

import javax.inject.Inject;

import butterknife.BindView;
import vn.touchspace.example.ui.adapter.PagerFragmentAdapter;
import vn.touchspace.example.ui.base.BaseFragment;
import vn.touchspace.example.ui.main.account.manager.ManagerFragment;
import vn.touchspace.example.ui.main.account.staff.StaffFragment;

public class AccountFragment extends BaseFragment implements AccountMvpView {

    @Inject
    AccountMvpPresenter<AccountMvpView> mPresenter;
    @BindView(R.id.tablayout)
    TabLayout tablayout;
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    @BindView(R.id.layout_staff)
    LinearLayout layoutStaff;
    @BindView(R.id.tv_staff)
    TextView tvStaff;
    private PagerFragmentAdapter adapter;

    public static AccountFragment newInstance() {

        Bundle args = new Bundle();

        AccountFragment fragment = new AccountFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mPresenter.onDetach();
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_account;
    }

    @Override
    protected void init(Bundle saveInstanceState, View rootView) {
        getActivityComponent().inject(this);
        mPresenter.onAttach(this);

        adapter = new PagerFragmentAdapter(getChildFragmentManager());

        mPresenter.getUserInfo();
    }

    @Override
    protected void setUp(View view) {

    }

    @Override
    public void getUserInfoSuccess(String role) {
        if (role.equals("director")) {
            layoutStaff.setVisibility(View.VISIBLE);
            tvStaff.setVisibility(View.GONE);
            adapter.addFragment(new StaffFragment(), "Nhân Viên");
            adapter.addFragment(new ManagerFragment(), "Quản lý");
        } else if (role.equals("manager")) {
            layoutStaff.setVisibility(View.VISIBLE);
            tvStaff.setVisibility(View.GONE);
            adapter.addFragment(new StaffFragment(), "Nhân Viên");
        } else {
            layoutStaff.setVisibility(View.GONE);
            tvStaff.setVisibility(View.VISIBLE);
        }


        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(adapter.getCount());
        tablayout.setupWithViewPager(viewPager);
    }
}
