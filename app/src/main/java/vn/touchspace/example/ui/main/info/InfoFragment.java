package vn.touchspace.example.ui.main.info;

import android.os.Bundle;
import android.view.View;

import com.touchspace.example.R;

import vn.touchspace.example.ui.base.BaseFragment;

public class InfoFragment extends BaseFragment {

    public static InfoFragment newInstance() {

        Bundle args = new Bundle();

        InfoFragment fragment = new InfoFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_info;
    }

    @Override
    protected void init(Bundle saveInstanceState, View rootView) {

    }

    @Override
    protected void setUp(View view) {

    }
}
