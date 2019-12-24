package com.hjq.demo.ui.activity;

import android.content.Context;
import android.content.Intent;

import androidx.viewpager.widget.ViewPager;

import com.gyf.immersionbar.BarHide;
import com.hjq.demo.R;
import com.hjq.demo.common.MyActivity;
import com.hjq.demo.other.IntentKey;
import com.hjq.demo.ui.adapter.ImagePagerAdapter;
import com.rd.PageIndicatorView;

import java.util.ArrayList;

import butterknife.BindView;

/**
 *    author : Android 轮子哥
 *    github : https://github.com/getActivity/AndroidProject
 *    time   : 2019/03/05
 *    desc   : 查看大图
 */
public final class ImageActivity extends MyActivity {

    @BindView(R.id.vp_image_pager)
    ViewPager mViewPager;
    @BindView(R.id.pv_image_indicator)
    PageIndicatorView mIndicatorView;

    public static void start(Context context, String url) {
        ArrayList<String> images = new ArrayList<>(1);
        images.add(url);
        start(context, images);
    }

    public static void start(Context context, ArrayList<String> urls) {
        start(context, urls, 0);
    }

    public static void start(Context context, ArrayList<String> urls, int index) {
        Intent intent = new Intent(context, ImageActivity.class);
        intent.putExtra(IntentKey.PICTURE, urls);
        intent.putExtra(IntentKey.INDEX, index);
        context.startActivity(intent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_image;
    }

    @Override
    protected void initView() {
        // 设置状态栏和导航栏参数
        getStatusBarConfig()
                // 有导航栏的情况下，activity全屏显示，也就是activity最下面被导航栏覆盖，不写默认非全屏
                .fullScreen(true)
                // 隐藏状态栏
                .hideBar(BarHide.FLAG_HIDE_STATUS_BAR)
                // 透明导航栏，不写默认黑色(设置此方法，fullScreen()方法自动为true)
                .transparentNavigationBar()
                .init();

        mIndicatorView.setViewPager(mViewPager);
    }

    @Override
    protected void initData() {
        ArrayList<String> images = getIntent().getStringArrayListExtra(IntentKey.PICTURE);
        int index = getIntent().getIntExtra(IntentKey.INDEX, 0);
        if (images != null && images.size() > 0) {
            mViewPager.setAdapter(new ImagePagerAdapter(this, images));
            if (index != 0 && index <= images.size()) {
                mViewPager.setCurrentItem(index);
            }
        } else {
            finish();
        }
    }

    @Override
    public boolean statusBarDarkFont() {
        return false;
    }
}