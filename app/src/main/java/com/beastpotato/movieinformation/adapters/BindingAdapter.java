package com.beastpotato.movieinformation.adapters;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Oleksiy on 1/31/2016.
 */
public class BindingAdapter<T> extends RecyclerView.Adapter<BindingAdapter.BindingViewHolder> {
    private List<T> data;
    private ItemBinder<T> binding;

    public BindingAdapter(List data, ItemBinder<T> binding) {
        this.data = data;
        this.binding = binding;
    }

    public void setData(List<T> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @Override
    public BindingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new BindingViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), binding.getLayoutItemId(), parent, false).getRoot());
    }

    @Override
    public void onBindViewHolder(BindingViewHolder holder, int position) {
        holder.getBinding().setVariable(binding.getBoundVariableId(), data.get(position));
        holder.getBinding().executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return data != null ? data.size() : 0;
    }

    public static class BindingViewHolder extends RecyclerView.ViewHolder {
        public BindingViewHolder(View itemView) {
            super(itemView);
        }

        public ViewDataBinding getBinding() {
            return DataBindingUtil.getBinding(itemView);
        }
    }
}
