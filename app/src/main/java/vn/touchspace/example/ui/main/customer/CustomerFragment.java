package vn.touchspace.example.ui.main.customer;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.touchspace.example.R;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import vn.touchspace.example.data.network.model.response.Customer;
import vn.touchspace.example.ui.adapter.CustomerAdapter;
import vn.touchspace.example.ui.adapter.StaffAdapter;
import vn.touchspace.example.ui.base.BaseFragment;
import vn.touchspace.example.ui.dialog.InfoStaffDialog;
import vn.touchspace.example.utils.recycler.SetupRvUtils;

public class CustomerFragment extends BaseFragment implements CustomerMvpView {

    @Inject
    CustomerMvpPresenter<CustomerMvpView> mPresenter;
    @BindView(R.id.rcy_view)
    RecyclerView rcyView;
    private CustomerAdapter adapter;

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
    public void onResume() {
        super.onResume();
        mPresenter.getCustomers();
    }

    @Override
    protected void setUp(View view) {

    }

    @Override
    public void getListSuccess(List<Customer> list) {
        adapter = new CustomerAdapter(list, getContext());
        adapter.setCallBack(position -> {
            AlertDialog alertDialog = new AlertDialog.Builder(getBaseActivity()).create();
            alertDialog.setIcon(R.mipmap.ic_launcher);
            alertDialog.setMessage("Xóa khách hàng này?");
            alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Xóa",
                    (dialog, which) -> {
                        mPresenter.removeCustomer(list.get(position).getId());
                        dialog.dismiss();
                    });
            alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Không",
                    (dialog, which) -> dialog.dismiss());
            alertDialog.show();
        });

        SetupRvUtils.setupLinearLayoutRecyclerView(getContext(), rcyView);
        rcyView.setAdapter(adapter);
    }

    @Override
    public void removeSuccess(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
        mPresenter.getCustomers();
    }
}
