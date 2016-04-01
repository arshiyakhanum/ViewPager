package com.arshiya.viewpager;

import android.graphics.drawable.Drawable;
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        highlightButton(0);

        mViewPager = (ViewPager) findViewById(R.id.view_pager);
        mViewPager.addOnPageChangeListener(this);

        mPagerAdapter = new ScreenSliderAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mPagerAdapter);

    }

    @Override
    protected void onResume() {
        super.onResume();
        //start scrolling page programmatically
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        Log.d(TAG, "onPageScrolled : " + position);
        highlightButton(position);
    }

    @Override
    public void onPageSelected(int position) {
        Log.d(TAG, "onPageSelected : position = " + position);
//        Toast.makeText(this, "Selected page : " + position, Toast.LENGTH_SHORT).show();

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
        Log.d(TAG, "slide : " + i);
        ImageView screen_1 = (ImageView) findViewById(R.id.screen_1);
        ImageView screen_2 = (ImageView) findViewById(R.id.screen_2);
        ImageView screen_3 = (ImageView) findViewById(R.id.screen_3);
        ImageView screen_4 = (ImageView) findViewById(R.id.screen_4);
        ImageView screen_5 = (ImageView) findViewById(R.id.screen_5);

        Drawable rect_blue = getResources().getDrawable(R.drawable.circle_blue);
        Drawable rect_gray = getResources().getDrawable(R.drawable.circle_gray);

        screen_1.setImageDrawable(rect_gray);
        screen_2.setImageDrawable(rect_gray);
        screen_3.setImageDrawable(rect_gray);
        screen_4.setImageDrawable(rect_gray);
        screen_5.setImageDrawable(rect_gray);

        switch (i) {
            case 0:
                screen_1.setImageDrawable(rect_blue);
                break;

            case 1:
                screen_2.setImageDrawable(rect_blue);
                break;

            case 2:
                screen_3.setImageDrawable(rect_blue);
                break;

            case 3:
                screen_4.setImageDrawable(rect_blue);
                break;

            case 4:
                screen_5.setImageDrawable(rect_blue);
                break;

            case 5:
                break;
        }


    }
}
