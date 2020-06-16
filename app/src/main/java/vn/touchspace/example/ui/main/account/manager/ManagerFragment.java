package vn.touchspace.example.ui.main.account.manager;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.touchspace.example.R;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import vn.touchspace.example.data.network.model.response.User;
import vn.touchspace.example.ui.adapter.StaffAdapter;
import vn.touchspace.example.ui.base.BaseFragment;
import vn.touchspace.example.ui.dialog.InfoStaffDialog;
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
                AlertDialog alertDialog = new AlertDialog.Builder(getBaseActivity()).create();
                alertDialog.setIcon(R.mipmap.ic_launcher);
                alertDialog.setMessage("Xóa quản lý này?");
                alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Xóa",
                        (dialog, which) -> {
                            mPresenter.deleteManager(list.get(position).getId());
                            dialog.dismiss();
                        });
                alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Không",
                        (dialog, which) -> dialog.dismiss());
                alertDialog.show();
            }

            @Override
            public void onClick(int position) {
                InfoStaffDialog dialog = InfoStaffDialog.newInstance(list.get(position));
                dialog.show(getChildFragmentManager(), null);
            }
        });

        SetupRvUtils.setupLinearLayoutRecyclerView(getContext(), rcyView);
        rcyView.setAdapter(adapter);
    }

    @Override
    public void removeSuccess(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
        mPresenter.getManager();
    }
}
