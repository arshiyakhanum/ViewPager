package com.arshiya.viewpager;

import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewParent;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {

    private static final String TAG = MainActivity.class.getSimpleName();
    private static final int NUM_PAGES = 5;
    private ViewPager mViewPager;
    private PagerAdapter mPagerAdapter;
    private Handler mHandler;
    private Runnable mRunnable;
    private final int SLIDE_DELAY = 3 * 1000;
    private int mIndex;
    private ImageView mScreen1;
    private ImageView mScreen2;
    private ImageView mScreen3;
    private ImageView mScreen4;
    private ImageView mScreen5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mScreen1 = (ImageView) findViewById(R.id.screen_1);
        mScreen2 = (ImageView) findViewById(R.id.screen_2);
        mScreen3 = (ImageView) findViewById(R.id.screen_3);
        mScreen4 = (ImageView) findViewById(R.id.screen_4);
        mScreen5 = (ImageView) findViewById(R.id.screen_5);

        highlightButton(0);

        mViewPager = (ViewPager) findViewById(R.id.view_pager);
        mViewPager.addOnPageChangeListener(this);

        mHandler = new Handler(getMainLooper());
        mRunnable = new Runnable() {
            @Override
            public void run() {
                Log.d(TAG, "run() : " + mIndex);

                if (mIndex > 4) {
                    mIndex = 0;
                }
                mViewPager.setCurrentItem(mIndex++, true);
                mHandler.postDelayed(mRunnable, SLIDE_DELAY);
            }
        };

        mPagerAdapter = new ScreenSliderAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mPagerAdapter);

    }

    @Override
    protected void onResume() {
        super.onResume();
        mIndex = 0;
        mHandler.postDelayed(mRunnable, SLIDE_DELAY);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mHandler.removeCallbacks(mRunnable);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        Log.d(TAG, "onPageScrolled : " + position);
        highlightButton(position);
    }

    @Override
    public void onPageSelected(int position) {
        Log.d(TAG, "onPageSelected : position = " + position);

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    class ScreenSliderAdapter extends FragmentStatePagerAdapter {

        public ScreenSliderAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            Log.d(TAG, "slide : " + i);
            return SlideFragment.newInstance(i);
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }
    }

    private void highlightButton(int i) {
        mIndex = i;
        Log.d(TAG, "slide : " + i);

        Drawable rect_blue = getResources().getDrawable(R.drawable.circle_blue);
        Drawable rect_gray = getResources().getDrawable(R.drawable.circle_gray);

        mScreen1.setImageDrawable(rect_gray);
        mScreen2.setImageDrawable(rect_gray);
        mScreen3.setImageDrawable(rect_gray);
        mScreen4.setImageDrawable(rect_gray);
        mScreen5.setImageDrawable(rect_gray);

        switch (i) {
            case 0:
                mScreen1.setImageDrawable(rect_blue);
                break;

            case 1:
                mScreen2.setImageDrawable(rect_blue);
                break;

            case 2:
                mScreen3.setImageDrawable(rect_blue);
                break;

            case 3:
                mScreen4.setImageDrawable(rect_blue);
                break;

            case 4:
                mScreen5.setImageDrawable(rect_blue);
                break;

            case 5:
                break;
        }


    }
}
