package com.example.xoaphm.smartdevicecontrol.control;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.xoaphm.smartdevicecontrol.R;
import com.example.xoaphm.smartdevicecontrol.models.Option;
import com.example.xoaphm.smartdevicecontrol.models.Title;

import java.util.List;


public class MenuAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int HEADER = 0;
    private static final int OPTION = 1;
    private static final int TITLE = 2;
    private List<Object> mObjects;
    private OnItemClickListener mOnItemClickListener;

    MenuAdapter(List<Object> objects, OnItemClickListener onItemClickListener) {
        mObjects = objects;
        mOnItemClickListener = onItemClickListener;
    }

    @Override
    public int getItemViewType(int position) {
        if (mObjects.get(position) instanceof Option) {
            return OPTION;
        } else if (mObjects.get(position) instanceof Title) {
            return TITLE;
        } else {
            return HEADER;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if (viewType == HEADER) {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_header, parent, false);
            return new Header(view);
        } else if (viewType == TITLE) {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_title, parent, false);
            return new TitleHolder(view);
        } else {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_option, parent, false);
            return new OptionHolder(view, mOnItemClickListener);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == HEADER) {
            ((Header) holder).onBindData();
        } else if (getItemViewType(position) == OPTION) {
            ((OptionHolder) holder).onBindData(mObjects.get(position));
        } else {
            ((TitleHolder) holder).onBindData(mObjects.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return mObjects.size();
    }

    public interface OnItemClickListener {
        void onClickItem(int position);
    }

    private static class OptionHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView mTvOption;
        private ImageView mImgIcon;
        private OnItemClickListener mOnItemClickListener;

        OptionHolder(final View itemView, OnItemClickListener onItemClickListener) {
            super(itemView);
            mOnItemClickListener = onItemClickListener;
            mTvOption = itemView.findViewById(R.id.tvOption);
            mImgIcon = itemView.findViewById(R.id.imgIcon);
            mTvOption.setOnClickListener(this);
            mImgIcon.setOnClickListener(this);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickListener.onClickItem(getAdapterPosition());
                }
            });
        }

        private void onBindData(Object object) {
            Option option = (Option) object;
            mTvOption.setText(option.getName());
            mImgIcon.setImageResource(option.getIcon());
            mTvOption.setTextColor(itemView.getResources().getColor(R.color.black));
            mImgIcon.setPressed(false);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.imgIcon:
                    mOnItemClickListener.onClickItem(getAdapterPosition());
                    break;
                case R.id.tvOption:
                    mOnItemClickListener.onClickItem(getAdapterPosition());
                    break;
            }
        }
    }

    private static class TitleHolder extends RecyclerView.ViewHolder {
        private TextView mTvTitle;

        TitleHolder(final View itemView) {
            super(itemView);
            mTvTitle = itemView.findViewById(R.id.tvTitle);
        }

        private void onBindData(Object object) {
            Title title = (Title) object;
            mTvTitle.setText(title.getName());
            mTvTitle.setTextColor(itemView.getResources().getColor(R.color.black));
        }
    }

    private static class Header extends RecyclerView.ViewHolder {

        Header(View itemView) {
            super(itemView);
        }

        private void onBindData() {
        }
    }
}