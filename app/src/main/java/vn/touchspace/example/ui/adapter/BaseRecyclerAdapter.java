package vn.touchspace.example.ui.adapter;



import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public abstract class BaseRecyclerAdapter<T, V extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<V> {

    private List<T> dataSet;

    public BaseRecyclerAdapter(List<T> dataSet) {
        this.dataSet = dataSet;
    }

    @LayoutRes
    protected abstract int getLayoutResourceItem();

    public abstract V onCreateBasicViewHolder(View itemView);

    public abstract void onBindBasicItemView(V holder, int position);

    @NonNull
    @Override
    public V onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(getLayoutResourceItem(), parent, false);
        return onCreateBasicViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull V holder, int position) {
        onBindBasicItemView(holder, position);
    }

    @Override
    public int getItemCount() {
        return dataSet == null ? 0 : dataSet.size();
    }

    public void addItem(T item) {
        if (!dataSet.contains(item)) {
            dataSet.add(item);
            notifyItemInserted(dataSet.size() - 1);
        }
    }

    public void addItems(@NonNull List<T> newDataSetItems) {
        if (newDataSetItems.size() == 0) return;
        int positionStart = getItemCount();
        int itemCount = newDataSetItems.size();
        dataSet.addAll(newDataSetItems);
        notifyItemRangeInserted(positionStart, itemCount);
    }

    public void removeItem(T item) {
        int indexOfItem = dataSet.indexOf(item);
        removeItem(indexOfItem);
    }

    public void removeItem(int indexOfItem) {
        if (indexOfItem != -1) {
            this.dataSet.remove(indexOfItem);
            notifyItemRemoved(indexOfItem);
        }
    }

    public T getItem(int index) {
        if (dataSet != null && dataSet.get(index) != null) {
            return dataSet.get(index);
        } else {
            throw new IllegalArgumentException("Item with index " + index + " doesn't exist, dataSet is " + dataSet);
        }
    }

    public List<T> getDataSet() {
        if(dataSet != null) {
            return dataSet;
        }
        return null;
    }

    public void setDataSet(@NonNull List<T> newDataSet) {
        dataSet = newDataSet;
        notifyDataSetChanged();
    }

    public void resetItems(@NonNull List<T> newDataSet) {
        dataSet.clear();
        addItems(newDataSet);
    }

    public void clear() {
        dataSet.clear();
        notifyDataSetChanged();
    }

    public boolean isEmpty() {
        return getItemCount() == 0;
    }

    public boolean isValidPos(int position) {
        return position >= 0 && position < getItemCount();
    }
}
