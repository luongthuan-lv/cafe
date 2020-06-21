package vn.touchspace.example.ui.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.touchspace.example.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import vn.touchspace.example.data.network.model.response.Invoice;
import vn.touchspace.example.utils.AppUtils;

public class InvoiceAdapter extends BaseRecyclerAdapter<Invoice, InvoiceAdapter.ViewHolder> {

    CallBack callBack;


    public InvoiceAdapter(List<Invoice> dataSet) {
        super(dataSet);
    }

    public void setCallBack(CallBack callBack) {
        this.callBack = callBack;
    }

    @Override
    protected int getLayoutResourceItem() {
        return R.layout.item_invoice;
    }

    @Override
    public ViewHolder onCreateBasicViewHolder(View itemView) {
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindBasicItemView(ViewHolder holder, int position) {
        Invoice invoice = getDataSet().get(position);
        holder.fillData(invoice);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_price)
        TextView tvPrice;
        @BindView(R.id.tv_date)
        TextView tvDate;
        @BindView(R.id.img_state)
        ImageView imgState;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(v -> callBack.onClick(getAdapterPosition()));
        }

        void fillData(Invoice invoice) {
            tvName.setText(String.format("Tên khách hàng: %s", invoice.getCustomerName()));
            tvPrice.setText(String.format("Tổng hóa đơn: %s VNĐ", AppUtils.formatNumber(invoice.getTotalValue())));
            tvDate.setText(String.format("Ngày tạo: %s", invoice.getCreateDate()));
            switch (invoice.getState()) {
                case "cancel":
                    imgState.setImageResource(R.drawable.ic_cancel);
                    break;
                case "active":
                    imgState.setImageResource(R.drawable.ic_watch_later);
                    break;
                case "finish":
                    imgState.setImageResource(R.drawable.ic_finish);
                    break;
            }
        }
    }

    public interface CallBack {
        void onClick(int position);
    }

}
