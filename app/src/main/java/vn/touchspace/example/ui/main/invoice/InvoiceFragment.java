package vn.touchspace.example.ui.main.invoice;

import android.os.Bundle;
import android.view.View;

import com.touchspace.example.R;

import vn.touchspace.example.ui.base.BaseFragment;

public class InvoiceFragment extends BaseFragment {

    public static InvoiceFragment newInstance() {

        Bundle args = new Bundle();

        InvoiceFragment fragment = new InvoiceFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_invoice;
    }

    @Override
    protected void init(Bundle saveInstanceState, View rootView) {

    }

    @Override
    protected void setUp(View view) {

    }
}
