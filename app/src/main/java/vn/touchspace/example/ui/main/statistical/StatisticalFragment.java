package vn.touchspace.example.ui.main.statistical;

import android.os.Bundle;
import android.view.View;

import com.touchspace.example.R;

import vn.touchspace.example.ui.base.BaseFragment;
import vn.touchspace.example.ui.main.product.ProductFragment;

public class StatisticalFragment extends BaseFragment {

    public static StatisticalFragment newInstance() {

        Bundle args = new Bundle();

        StatisticalFragment fragment = new StatisticalFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_statistical;
    }

    @Override
    protected void init(Bundle saveInstanceState, View rootView) {

    }

    @Override
    protected void setUp(View view) {

    }
}
