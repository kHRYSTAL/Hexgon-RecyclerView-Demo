package me.khrystal.hiverecyclerview;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import me.khrystal.widget.HiveLayout;
import me.khrystal.widget.HorizontalHiveImageView;

/**
 * usage:
 * author: kHRYSTAL
 * create time: 16/12/15
 * update time:
 * email: 723526676@qq.com
 */

public class ImagesAdapter extends RecyclerView.Adapter<ImagesAdapter.CustomViewHolder> {

    private List<Integer> images;
    private ViewHolderClicks clicksListener;

    public ImagesAdapter(List<Integer> images,
                         ViewHolderClicks clicksListener) {
        this.images = images;
        this.clicksListener = clicksListener;
    }


    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new CustomViewHolder(view, clicksListener);
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
        if (images == null || images.size() == 0) {
            return;
        }
        holder.setIsRecyclable(false);
        holder.setImage(images.get(position));
        holder.setText("" + position);

    }

    @Override
    public int getItemCount() {
        if (images == null) {
            return 0;
        }
        return images.size();
    }

    public void updateList(List<Integer> itemsList) {
        this.images = itemsList;
        notifyDataSetChanged();
    }

    public void addToList(List<Integer> itemsList) {
        this.images.addAll(itemsList);
        notifyDataSetChanged();
    }

    public void clear() {
        this.images.clear();
        notifyDataSetChanged();
    }


    public static class CustomViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {

        HiveLayout layout;
        ViewHolderClicks clicksListener;
        HorizontalHiveImageView imageView;
        TextView textView;
        int resId;

        public CustomViewHolder(View view, ViewHolderClicks clicksListener) {
            super(view);
            this.clicksListener = clicksListener;
            layout = (HiveLayout) view.findViewById(R.id.itemLayout);
            imageView = (HorizontalHiveImageView) layout.findViewById(R.id.itemIv);
            textView = (TextView) layout.findViewById(R.id.itemTv);
            layout.setOnClickListener(this);
        }


        public void setImage(int resId) {
            if (imageView != null && resId != 0) {
                this.resId = resId;
                imageView.setImageResource(resId);
            }
        }

        public void setText(String text) {
            if (textView!= null && !TextUtils.isEmpty(text)) {
                textView.setText(text);
            }
        }

        @Override
        public void onClick(View v) {
            if (clicksListener != null)
                this.clicksListener.onClick(v, getLayoutPosition(), resId);
        }
    }

    public interface ViewHolderClicks {
        void onClick(View view, int position, int resId);
    }
}
