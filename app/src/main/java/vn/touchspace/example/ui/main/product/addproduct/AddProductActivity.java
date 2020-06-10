package vn.touchspace.example.ui.main.product.addproduct;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.touchspace.example.R;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import vn.touchspace.example.data.network.model.response.Product;
import vn.touchspace.example.ui.base.BaseActivity;
import vn.touchspace.example.ui.main.product.updateproduct.UpdateProductMvpPresenter;
import vn.touchspace.example.ui.main.product.updateproduct.UpdateProductMvpView;
import vn.touchspace.example.utils.AppUtils;

public class AddProductActivity extends BaseActivity implements AddProductMvpView {

    @Inject
    AddProductMvpPresenter<AddProductMvpView> mPresenter;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.edt_name)
    EditText edtName;
    @BindView(R.id.edt_price)
    EditText edtPrice;
    @BindView(R.id.edt_type)
    EditText edtType;
    @BindView(R.id.btn_save)
    Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);
        ButterKnife.bind(this);

        getActivityComponent().inject(this);

        mPresenter.onAttach(this);

        tvTitle.setText(R.string.title_add_product);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.onDetach();
    }

    @Override
    protected void setUp() {

    }

    @OnClick({R.id.iv_back, R.id.img_product, R.id.btn_save})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.img_product:
                Toast.makeText(this, "Dev ngủ quên nên chưa làm phần chọn ảnh đâu!!!", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_save:
                if(edtName.getText().toString().trim().equals("") || edtPrice.getText().toString().trim().equals("") || edtPrice.getText().toString().trim().equals("")){
                    Toast.makeText(this, "Vui lòng không để trống!", Toast.LENGTH_SHORT).show();
                }else{
                    mPresenter.addProduct( edtName.getText().toString().trim(), edtPrice.getText().toString().trim(), edtType.getText().toString().trim(), "");
                }
                break;
        }
    }

    @Override
    public void addSuccess(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        finish();
    }
}