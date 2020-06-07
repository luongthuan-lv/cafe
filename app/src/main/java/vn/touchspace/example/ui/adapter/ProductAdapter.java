package vn.touchspace.example.ui.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.touchspace.example.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import vn.touchspace.example.data.network.model.response.Product;
import vn.touchspace.example.utils.AppUtils;

public class ProductAdapter extends BaseRecyclerAdapter<Product, ProductAdapter.ViewHolder> {

    CallBack callBack;

    private Context context;

    public ProductAdapter(List<Product> dataSet, Context context) {
        super(dataSet);
        this.context = context;
    }

    public void setCallBack(CallBack callBack) {
        this.callBack = callBack;
    }

    @Override
    protected int getLayoutResourceItem() {
        return R.layout.item_product;
    }

    @Override
    public ViewHolder onCreateBasicViewHolder(View itemView) {
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindBasicItemView(ViewHolder holder, int position) {
        Product product = getDataSet().get(position);
        holder.fillData(product);
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.img_product)
        ImageView imgProduct;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_price)
        TextView tvPrice;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(v -> callBack.onClick(getAdapterPosition()));
        }

        void fillData(Product product) {
            Glide.with(context).load(product.getProductImage()).error(R.drawable.img_late).into(imgProduct);
            tvName.setText(product.getProductName());
            tvPrice.setText(String.format("Giá: %s VNĐ", AppUtils.formatNumber(product.getProductPrice())));
        }
    }

    public interface CallBack {
        void onClick(int position);
    }

}
