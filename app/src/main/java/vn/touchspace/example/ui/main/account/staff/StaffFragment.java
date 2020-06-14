package vn.touchspace.example.ui.main.account.staff;

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

public class StaffFragment extends BaseFragment implements StaffMvpView {

    @Inject
    StaffMvpPresenter<StaffMvpView> mPresenter;
    @BindView(R.id.rcy_view)
    RecyclerView rcyView;
    private StaffAdapter adapter;

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_staff;
    }

    @Override
    protected void init(Bundle saveInstanceState, View rootView) {
        getActivityComponent().inject(this);
        mPresenter.onAttach(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.getStaff();
    }

    @Override
    protected void setUp(View view) {

    }

    @Override
    public void getStaffSuccess(List<User> list) {
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
