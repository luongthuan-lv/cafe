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
import vn.touchspace.example.ui.main.info.InfoMvpPresenter;
import vn.touchspace.example.ui.main.info.InfoMvpView;
import vn.touchspace.example.utils.recycler.SetupRvUtils;

public class InvoiceFragment extends BaseFragment implements InvoiceMvpView{

    @BindView(R.id.rcy_view)
    RecyclerView rcyView;
    @Inject
    InvoiceMvpPresenter<InvoiceMvpView> mPresenter;
    private InvoiceAdapter adapter;


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
        getActivityComponent().inject(this);
        mPresenter.onAttach(this);

    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.getInvoices("finish");
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
