package com.android.linkedviewpager2.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.linkedviewpager2.R;
import com.android.linkedviewpager2.entity.TopPagerBean;

import java.util.List;


public class TopPagerAdapter extends PagerAdapter {

    private List<TopPagerBean> mData;

    public TopPagerAdapter(List<TopPagerBean> mPageViews) {
        super();
        this.mData = mPageViews;
    }

    @Override
    public int getCount() {
        return 10000;
//        return mData.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }


    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.item_top_view, null);
        TextView tv_title = view.findViewById(R.id.tv_title);

        TopPagerBean bean = mData.get(position % mData.size());
//        TopPagerBean bean = mData.get(position);
        tv_title.setText(bean.getTitle());
        tv_title.setBackground(bean.getBackRes());
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        View view = (View) object;
        container.removeView(view);
    }
}