package vn.touchspace.example.ui.main.invoice;

import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.touchspace.example.R;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import vn.touchspace.example.data.network.model.response.Invoice;
import vn.touchspace.example.ui.adapter.InvoiceAdapter;
import vn.touchspace.example.ui.base.BaseFragment;
import vn.touchspace.example.utils.recycler.SetupRvUtils;

public class InvoiceActiveFragment extends BaseFragment implements InvoiceActiveMvpView{

    @BindView(R.id.rcy_view)
    RecyclerView rcyView;
    @Inject
    InvoiceActiveMvpPresenter<InvoiceActiveMvpView> mPresenter;
    private InvoiceAdapter adapter;


    public static InvoiceActiveFragment newInstance() {

        Bundle args = new Bundle();

        InvoiceActiveFragment fragment = new InvoiceActiveFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_invoice;
    }

    @Override
    protected void init(Bundle saveInstanceState, View rootView) {
        getActivityComponent().inject(this);
        mPresenter.onAttach(this);

    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.getInvoices("active");
    }


    @Override
    protected void setUp(View view) {

    }

    @Override
    public void getInvoicesSuccess(List<Invoice> list) {
        adapter = new InvoiceAdapter(list);
        SetupRvUtils.setupLinearLayoutRecyclerView(getContext(), rcyView);
        adapter.setCallBack(position -> {

        });
        rcyView.setAdapter(adapter);
    }
}
