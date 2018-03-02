package com.android.linkedviewpager2.activity;

import java.util.ArrayList;
import java.util.List;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.android.linkedviewpager2.R;
import com.android.linkedviewpager2.entity.TopPagerBean;
import com.android.linkedviewpager2.adapter.BottomPagerAdapter;
import com.android.linkedviewpager2.adapter.TopPagerAdapter;
import com.android.linkedviewpager2.fragment.Fragment1;
import com.android.linkedviewpager2.fragment.Fragment2;
import com.android.linkedviewpager2.fragment.Fragment3;
import com.android.linkedviewpager2.fragment.Fragment4;

public class MainActivity extends FragmentActivity {

    private LinearLayout container;
    private ViewPager bottomPager;

    private ViewPager topPager;
    private List<TopPagerBean> topPagerList;

    private ImageView[] images;
    private List<Fragment> fragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        initData();
        initPager();
    }

    private void initPager() {
        topPager.setAdapter(new TopPagerAdapter(topPagerList));
        topPager.addOnPageChangeListener(new OnPageChangeListener() {
            private int index = 0;

            @Override
            public void onPageSelected(int position) {
                index = position;
            }

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                int width = bottomPager.getWidth();
                bottomPager.scrollTo((int) (width * position + width * positionOffset), 0);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if (state == 0) {
                    bottomPager.setCurrentItem(index);
                }
            }
        });

        bottomPager.setAdapter(new BottomPagerAdapter(fragments, getSupportFragmentManager()));
        bottomPager.addOnPageChangeListener(new OnPageChangeListener() {
            private int index = 0;

            @Override
            public void onPageSelected(int position) {
                index = position;
            }

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                //arg0当前滑动界面
                //arg1当页面前滑动百分比
                //arg2当前页面滑动像素

                //Log.v("onPageScrolled", "arg0"+arg0+"arg1"+arg1+"arg2-"+arg2);
                int width = topPager.getWidth();

                topPager.scrollTo((int) (width * position + width * positionOffset), 0);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                Log.v("PageScrollStateChanged", state + "");
                if (state == 0) {
                    topPager.setCurrentItem(index);
                }
            }
        });

//        topPager.setCurrentItem(5000);
//        bottomPager.setCurrentItem(5000);
    }

    private void initData() {
        //顶部ViewPager
        topPagerList = new ArrayList<>();
        Drawable drawable1 = getResources().getDrawable(R.drawable.top_pager_1);
        Drawable drawable2 = getResources().getDrawable(R.drawable.top_pager_2);
        Drawable drawable3 = getResources().getDrawable(R.drawable.top_pager_3);
        Drawable drawable4 = getResources().getDrawable(R.drawable.top_pager_4);
        topPagerList.add(new TopPagerBean(drawable1, "春"));
        topPagerList.add(new TopPagerBean(drawable2, "夏"));
        topPagerList.add(new TopPagerBean(drawable3, "秋"));
        topPagerList.add(new TopPagerBean(drawable4, "冬"));

        //底部ViewPager
        fragments = new ArrayList<>();
        Fragment1 fragment1 = new Fragment1();
        Fragment2 fragment2 = new Fragment2();
        Fragment3 fragment3 = new Fragment3();
        Fragment4 fragment4 = new Fragment4();
        fragments.add(fragment1);
        fragments.add(fragment2);
        fragments.add(fragment3);
        fragments.add(fragment4);
    }

    private void initViews() {
        bottomPager = this.findViewById(R.id.bottomPager);
        topPager = this.findViewById(R.id.topPager);
        container = this.findViewById(R.id.container);

        // 1.设置幕后item的缓存数目
        bottomPager.setOffscreenPageLimit(3);
        topPager.setOffscreenPageLimit(3);

        container.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return bottomPager.dispatchTouchEvent(event);
            }
        });
    }
}
