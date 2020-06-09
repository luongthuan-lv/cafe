package vn.touchspace.example.ui.dialog;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import com.bumptech.glide.Glide;
import com.touchspace.example.R;

import butterknife.BindView;
import butterknife.OnClick;
import vn.touchspace.example.data.network.model.response.Product;
import vn.touchspace.example.ui.base.BaseDialog;
import vn.touchspace.example.ui.main.product.updateproduct.UpdateProductActivity;
import vn.touchspace.example.utils.AppUtils;

public class InfoProductDialog extends BaseDialog {

    @BindView(R.id.img_product)
    ImageView imgProduct;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_price)
    TextView tvPrice;
    @BindView(R.id.tv_type)
    TextView tvType;
    @BindView(R.id.tv_export)
    TextView tvExport;
    @BindView(R.id.btn_update)
    Button btnUpdate;
    @BindView(R.id.btn_remove)
    Button btnRemove;
    private Product product;

    private IOnClickListener mIOnClickListener;

    @Override
    protected int getLayoutResource() {
        return R.layout.dialog_info_product;
    }

    public static InfoProductDialog newInstance(Product product, IOnClickListener iOnClickListener) {
        InfoProductDialog fragment = new InfoProductDialog();
        fragment.mIOnClickListener = iOnClickListener;
        fragment.product = product;
        return fragment;
    }

    @Override
    protected void init(Bundle saveInstanceState, View view) {
        Glide.with(getBaseActivity()).load(product.getProductImage()).error(R.drawable.img_late).into(imgProduct);
        tvName.setText(product.getProductName());
        tvPrice.setText(String.format("Giá bán: %s VNĐ", AppUtils.formatNumber(product.getProductPrice())));
        tvType.setText(String.format("Loại sản phẩm: %s", product.getProductType()));
        tvExport.setText(String.format("Đã bán: %s", product.getProductExport()));
    }

    @Override
    protected void setUp(View view) {

    }

    @OnClick({R.id.btn_update, R.id.btn_remove})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_update:
                Intent intent = new Intent(getContext(), UpdateProductActivity.class);
                intent.putExtra("product", product);
                startActivity(intent);
                dismiss();
                break;
            case R.id.btn_remove:
                AlertDialog alertDialog = new AlertDialog.Builder(getBaseActivity()).create();
                alertDialog.setIcon(R.mipmap.ic_launcher);
                alertDialog.setMessage("Xác nhận xóa?");
                alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Có",
                        (dialog, which) -> {
                            mIOnClickListener.remove(product.getId());
                            dialog.dismiss();
                        });
                alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Không",
                        (dialog, which) -> dialog.dismiss());
                alertDialog.show();
                dismiss();
                break;
        }
    }

    public interface IOnClickListener{
        void remove(String id);
    }
}
