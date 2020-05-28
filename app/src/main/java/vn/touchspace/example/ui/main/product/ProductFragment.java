package vn.touchspace.example.ui.main.product;

import android.os.Bundle;
import android.view.View;

import com.touchspace.example.R;

import vn.touchspace.example.ui.base.BaseFragment;

public class ProductFragment extends BaseFragment {

    public static ProductFragment newInstance() {

        Bundle args = new Bundle();

        ProductFragment fragment = new ProductFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_product;
    }

    @Override
    protected void init(Bundle saveInstanceState, View rootView) {

    }

    @Override
    protected void setUp(View view) {

    }
}
