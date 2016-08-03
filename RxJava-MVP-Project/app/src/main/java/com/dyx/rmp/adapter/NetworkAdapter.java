package com.dyx.rmp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dyx.rmp.R;
import com.dyx.rmp.bean.json.NewsBean;
import com.dyx.rmp.constant.ApiConstant;
import com.dyx.rmp.util.TimeUtil;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * project name：RxJava-MVP-Project
 * class describe：
 * create person：dayongxin
 * create time：16/8/3 下午6:07
 * alter person：dayongxin
 * alter time：16/8/3 下午6:07
 * alter remark：
 */
public class NetworkAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<NewsBean.TngouEntity> tngouEntities;
    private Context context;
    private static final int TYPE_ITEM = 0;
    private static final int TYPE_FOOTER = 1;

    public NetworkAdapter(List<NewsBean.TngouEntity> tngouEntities, Context context) {
        this.tngouEntities = tngouEntities;
        this.context = context;
    }

    @Override
    public int getItemCount() {
        return tngouEntities.size() + 1;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_ITEM) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_item_layout, parent, false);
            return new ItemViewHolder(view);
        } else if (viewType == TYPE_FOOTER) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_footer_layout, parent, false);
            return new FooterViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ItemViewHolder) {
            Picasso.with(context).load(ApiConstant.IMG_PREFIX + tngouEntities.get(position).getImg()).into(((ItemViewHolder) holder).imageView);
            ((ItemViewHolder) holder).tvTitle.setText(tngouEntities.get(position).getTitle());
            ((ItemViewHolder) holder).tvTime.setText(TimeUtil.timeStamp2Date(tngouEntities.get(position).getTime() + ""));
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position + 1 == getItemCount()) {
            return TYPE_FOOTER;
        } else {
            return TYPE_ITEM;
        }
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView tvTitle;
        TextView tvTime;

        public ItemViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.id_iv);
            tvTitle = (TextView) itemView.findViewById(R.id.id_tv_title);
            tvTime = (TextView) itemView.findViewById(R.id.id_tv_time);
        }
    }


    public static class FooterViewHolder extends RecyclerView.ViewHolder {
        public FooterViewHolder(View itemView) {
            super(itemView);
        }
    }

}
