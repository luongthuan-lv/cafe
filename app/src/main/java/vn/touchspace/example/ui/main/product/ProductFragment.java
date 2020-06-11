package vn.touchspace.example.ui.main.product;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.touchspace.example.R;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import vn.touchspace.example.data.network.model.response.Product;
import vn.touchspace.example.ui.adapter.ProductAdapter;
import vn.touchspace.example.ui.base.BaseFragment;
import vn.touchspace.example.ui.dialog.InfoProductDialog;
import vn.touchspace.example.utils.recycler.SetupRvUtils;

public class ProductFragment extends BaseFragment implements ProductMvpView {

    @Inject
    ProductMvpPresenter<ProductMvpView> mPresenter;
    @BindView(R.id.rcy_view)
    RecyclerView rcyView;
    ProductAdapter adapter;
    @BindView(R.id.seach_bar)
    EditText seachBar;

    public static ProductFragment newInstance() {

        Bundle args = new Bundle();

        ProductFragment fragment = new ProductFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivityComponent().inject(this);
        mPresenter.onAttach(this);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mPresenter.onDetach();
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_product;
    }

    @Override
    protected void init(Bundle saveInstanceState, View rootView) {
        mPresenter.getProducts();
        seachBar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    protected void setUp(View view) {

    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.getProducts();
    }

    @Override
    public void getList(List<Product> list) {
        adapter = new ProductAdapter(list, getContext());
        adapter.setCallBack(position -> {
            InfoProductDialog dialog = InfoProductDialog.newInstance(list.get(position), id -> mPresenter.removeProduct(id));
            dialog.show(getChildFragmentManager(), null);
        });

        SetupRvUtils.setupGridLayoutRecyclerView(getContext(), rcyView, 2);
        rcyView.setAdapter(adapter);
    }

    @Override
    public void removeSuccess(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
        mPresenter.getProducts();
    }
}
