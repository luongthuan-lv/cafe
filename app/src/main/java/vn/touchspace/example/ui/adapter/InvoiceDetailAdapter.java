package vn.touchspace.example.ui.adapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.touchspace.example.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import vn.touchspace.example.data.network.model.response.ProductOfInvoice;

public class InvoiceDetailAdapter extends BaseRecyclerAdapter<ProductOfInvoice, InvoiceDetailAdapter.ViewHolder> {


    public InvoiceDetailAdapter(List<ProductOfInvoice> dataSet) {
        super(dataSet);
    }

    @Override
    protected int getLayoutResourceItem() {
        return R.layout.item_invoice_detail;
    }

    @Override
    public ViewHolder onCreateBasicViewHolder(View itemView) {
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindBasicItemView(ViewHolder holder, int position) {
        ProductOfInvoice product = getDataSet().get(position);
        holder.fillData(product);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_quantily)
        TextView tvQuantily;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void fillData(ProductOfInvoice product) {
            tvName.setText(product.getProductName());
            tvQuantily.setText(String.format("%s", product.getNumberOfProduct()));
        }
    }


}
