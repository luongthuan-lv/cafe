package vn.touchspace.example.ui.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.touchspace.example.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import vn.touchspace.example.data.network.model.AddInvoiceModel;
import vn.touchspace.example.utils.AppUtils;

public class AddInvoiceAdapter extends BaseRecyclerAdapter<AddInvoiceModel, AddInvoiceAdapter.ViewHolder> {

    CallBack callBack;

    private Context context;

    public AddInvoiceAdapter(List<AddInvoiceModel> dataSet, Context context) {
        super(dataSet);
        this.context = context;
    }

    public void setCallBack(CallBack callBack) {
        this.callBack = callBack;
    }

    @Override
    protected int getLayoutResourceItem() {
        return R.layout.item_add_invoice;
    }

    @Override
    public ViewHolder onCreateBasicViewHolder(View itemView) {
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindBasicItemView(ViewHolder holder, int position) {
        AddInvoiceModel model = getDataSet().get(position);
        holder.fillData(model);
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_quantily)
        TextView tvQuantily;
        @BindView(R.id.tv_total)
        TextView tvTotal;
        @BindView(R.id.btn_delete)
        ImageButton btnDelete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void fillData(AddInvoiceModel model) {
            double total = model.getNumberOfProduct() * model.getProductPrice();
            tvName.setText(model.getProductName());
            tvQuantily.setText(String.format("%s VNĐ", String.format("%s", model.getNumberOfProduct())));
            tvTotal.setText(AppUtils.formatNumber(total));
            btnDelete.setOnClickListener(v -> callBack.onClick(total, getAdapterPosition()));

        }
    }

    public interface CallBack {
        void onClick(double total, int position);
    }

}
