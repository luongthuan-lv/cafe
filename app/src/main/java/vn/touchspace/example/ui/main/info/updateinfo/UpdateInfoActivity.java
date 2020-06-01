package vn.touchspace.example.ui.main.info.updateinfo;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.touchspace.example.R;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import vn.touchspace.example.ui.base.BaseActivity;
import vn.touchspace.example.utils.AppUtils;

public class UpdateInfoActivity extends BaseActivity implements UpdateInfoMvpView {

    @Inject
    UpdateInfoMvpPresenter<UpdateInfoMvpView> mvpPresenter;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_info);
        ButterKnife.bind(this);
        getActivityComponent().inject(this);

        mvpPresenter.onAttach(this);
        tvTitle.setText(R.string.title_update_info);
    }

    @Override
    protected void setUp() {

    }

    @OnClick(R.id.iv_back)
    public void onViewClicked() {
        finish();
        AppUtils.isDoubleClick(ivBack);
    }
}