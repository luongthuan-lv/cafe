package vn.touchspace.example.ui.main.invoice;

import android.os.Bundle;
import android.view.View;

import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.touchspace.example.R;

import butterknife.BindView;
import vn.touchspace.example.ui.adapter.PagerFragmentAdapter;
import vn.touchspace.example.ui.base.BaseFragment;
import vn.touchspace.example.ui.main.invoice.invoiceactive.InvoiceActiveFragment;
import vn.touchspace.example.ui.main.invoice.invoicecancel.InvoiceCancelFragment;
import vn.touchspace.example.ui.main.invoice.invoicefinish.InvoiceFragment;

public class InvoicesFragment extends BaseFragment {


    @BindView(R.id.tablayout)
    TabLayout tablayout;
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    private PagerFragmentAdapter adapter;

    public static InvoicesFragment newInstance() {

        Bundle args = new Bundle();

        InvoicesFragment fragment = new InvoicesFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_invoices;
    }

    @Override
    protected void init(Bundle saveInstanceState, View rootView) {
        adapter = new PagerFragmentAdapter(getChildFragmentManager());

        adapter.addFragment(InvoiceActiveFragment.newInstance(), "Đang chờ");
        adapter.addFragment(InvoiceFragment.newInstance(), "Hoàn thành");
        adapter.addFragment(InvoiceCancelFragment.newInstance(), "Đã hủy");

        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(adapter.getCount());
        tablayout.setupWithViewPager(viewPager);
    }


    @Override
    protected void setUp(View view) {

    }

}
