package vn.touchspace.example.ui.main.customer;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import com.touchspace.example.R;

import javax.inject.Inject;

import vn.touchspace.example.ui.base.BaseFragment;

public class CustomerFragment extends BaseFragment implements CustomerMvpView {

    @Inject
    CustomerMvpPresenter<CustomerMvpView> mPresenter;

    public static CustomerFragment newInstance() {

        Bundle args = new Bundle();

        CustomerFragment fragment = new CustomerFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_customer;
    }

    @Override
    protected void init(Bundle saveInstanceState, View rootView) {
        getActivityComponent().inject(this);
        mPresenter.onAttach(this);
    }

    @Override
    protected void setUp(View view) {

    }
}
