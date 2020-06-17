package vn.touchspace.example.ui.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.touchspace.example.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import vn.touchspace.example.data.network.model.response.Customer;
import vn.touchspace.example.utils.AppUtils;

public class CustomerAdapter extends BaseRecyclerAdapter<Customer, CustomerAdapter.ViewHolder> {

    CallBack callBack;

    private Context context;

    public CustomerAdapter(List<Customer> dataSet, Context context) {
        super(dataSet);
        this.context = context;
    }

    public void setCallBack(CallBack callBack) {
        this.callBack = callBack;
    }

    @Override
    protected int getLayoutResourceItem() {
        return R.layout.item_customer;
    }

    @Override
    public ViewHolder onCreateBasicViewHolder(View itemView) {
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindBasicItemView(ViewHolder holder, int position) {
        Customer customer = getDataSet().get(position);
        holder.fillData(customer);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_avatar)
        TextView tvAvatar;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_telephone)
        TextView tvTelephone;
        @BindView(R.id.tv_invoiceSum)
        TextView tvInvoiceSum;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnLongClickListener(v -> {
                callBack.onLongClick(getAdapterPosition());
                return false;
            });
        }

        void fillData(Customer customer) {
            AppUtils.setDefaultAvatar(tvAvatar, customer.getCustomerName());
            tvName.setText(customer.getCustomerName());
            tvTelephone.setText(customer.getCustomerPhone());
            tvInvoiceSum.setText(String.format("%s VNĐ", AppUtils.formatNumber(customer.getCustomerInvoiceSum())));
        }
    }

    public interface CallBack {
        void onLongClick(int position);
    }

}
