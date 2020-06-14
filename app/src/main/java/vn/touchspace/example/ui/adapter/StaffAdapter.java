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
import vn.touchspace.example.data.network.model.response.Product;
import vn.touchspace.example.data.network.model.response.User;
import vn.touchspace.example.utils.AppUtils;

public class StaffAdapter extends BaseRecyclerAdapter<User, StaffAdapter.ViewHolder> {

    CallBack callBack;


    private Context context;

    public StaffAdapter(List<User> dataSet, Context context) {
        super(dataSet);
        this.context = context;
    }

    public void setCallBack(CallBack callBack) {
        this.callBack = callBack;
    }

    @Override
    protected int getLayoutResourceItem() {
        return R.layout.item_staff;
    }

    @Override
    public ViewHolder onCreateBasicViewHolder(View itemView) {
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindBasicItemView(ViewHolder holder, int position) {
        User user = getDataSet().get(position);
        holder.fillData(user);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_avatar)
        TextView tvAvatar;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_role)
        TextView tvRole;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnLongClickListener(v -> {
                callBack.onLongClick(getAdapterPosition());
                return false;
            });
            itemView.setOnClickListener(v -> callBack.onClick(getAdapterPosition()));
        }

        void fillData(User user) {
            AppUtils.setDefaultAvatar(tvAvatar, user.getFullName());
            tvName.setText(user.getFullName());
            tvRole.setText(AppUtils.getRole(user.getRole()));
        }
    }

    public interface CallBack {
        void onLongClick(int position);
        void onClick(int position);
    }

}
