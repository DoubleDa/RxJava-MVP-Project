package com.dyx.rmp.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dyx.rmp.R;

import java.util.List;

/**
 * project name：RxJava-MVP-Project
 * class describe：
 * create person：dayongxin
 * create time：16/8/3 下午4:03
 * alter person：dayongxin
 * alter time：16/8/3 下午4:03
 * alter remark：
 */
public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MenuViewHolder> {
    private List<String> mDataList;
    private OnMyClickListener onMyClickListener;

    public interface OnMyClickListener {
        void onItemClick(int pos);
    }

    public void setOnMyClickListener(OnMyClickListener onMyClickListener) {
        this.onMyClickListener = onMyClickListener;
    }

    public MenuAdapter(List<String> mDataList) {
        this.mDataList = mDataList;
    }

    @Override
    public MenuViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_item, parent, false);
        return new MenuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MenuViewHolder holder, int position) {
        holder.textView.setText(mDataList.get(position));
        if (onMyClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = holder.getLayoutPosition();
                    onMyClickListener.onItemClick(pos);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    public static class MenuViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        public MenuViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.id_tv_item);
        }
    }
}
