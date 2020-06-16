package vn.touchspace.example.ui.dialog;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.touchspace.example.R;

import butterknife.BindView;
import vn.touchspace.example.data.network.model.response.User;
import vn.touchspace.example.ui.base.BaseDialog;
import vn.touchspace.example.utils.AppUtils;

public class InfoStaffDialog extends BaseDialog {

    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_birthday)
    TextView tvBirthday;
    @BindView(R.id.tv_telephone)
    TextView tvTelephone;
    @BindView(R.id.tv_role)
    TextView tvRole;
    private User user;

    @Override
    protected int getLayoutResource() {
        return R.layout.dialog_info_staff;
    }

    public static InfoStaffDialog newInstance(User user) {
        InfoStaffDialog fragment = new InfoStaffDialog();
        fragment.user = user;
        return fragment;
    }

    @Override
    protected void init(Bundle saveInstanceState, View view) {
        tvName.setText(String.format("Họ và tên: %s", user.getFullName()));
        tvBirthday.setText(String.format("Ngày sinh: %s", user.getBirthday()));
        tvTelephone.setText(String.format("Số điện thoại: %s", user.getTelephoneNumber()));
        tvRole.setText(String.format("Chức vụ: %s", AppUtils.getRole(user.getRole())));
    }

    @Override
    protected void setUp(View view) {

    }


}
