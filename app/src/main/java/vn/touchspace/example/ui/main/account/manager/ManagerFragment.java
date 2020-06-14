package vn.touchspace.example.ui.main.account.manager;

import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.touchspace.example.R;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import vn.touchspace.example.data.network.model.response.User;
import vn.touchspace.example.ui.adapter.StaffAdapter;
import vn.touchspace.example.ui.base.BaseFragment;
import vn.touchspace.example.utils.recycler.SetupRvUtils;

public class ManagerFragment extends BaseFragment implements ManagerMvpView {

    @Inject
    ManagerMvpPresenter<ManagerMvpView> mPresenter;
    @BindView(R.id.rcy_view)
    RecyclerView rcyView;
    private StaffAdapter adapter;


    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_manager;
    }

    @Override
    protected void init(Bundle saveInstanceState, View rootView) {
        getActivityComponent().inject(this);
        mPresenter.onAttach(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.getManager();
    }

    @Override
    protected void setUp(View view) {

    }

    @Override
    public void getManagerSuccess(List<User> list) {
        adapter = new StaffAdapter(list, getContext());
        adapter.setCallBack(new StaffAdapter.CallBack() {
            @Override
            public void onLongClick(int position) {

            }

            @Override
            public void onClick(int position) {

            }
        });

        SetupRvUtils.setupLinearLayoutRecyclerView(getContext(), rcyView);
        rcyView.setAdapter(adapter);
    }
}
