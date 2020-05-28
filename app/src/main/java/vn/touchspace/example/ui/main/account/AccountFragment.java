package vn.touchspace.example.ui.main.account;

import android.os.Bundle;
import android.view.View;

import com.touchspace.example.R;

import vn.touchspace.example.ui.base.BaseFragment;

public class AccountFragment extends BaseFragment {

    public static AccountFragment newInstance() {

        Bundle args = new Bundle();

        AccountFragment fragment = new AccountFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_account;
    }

    @Override
    protected void init(Bundle saveInstanceState, View rootView) {

    }

    @Override
    protected void setUp(View view) {

    }
}
