package com.danielmaia.flights.views.adapters;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.danielmaia.flights.views.MainActivity;
import com.danielmaia.flights.views.fragments.OutboundFragment;
import com.danielmaia.flights.views.fragments.InboundFragment;

public class ViewPagerAdapter extends FragmentPagerAdapter implements ViewPager.OnPageChangeListener{

    public interface PageListener {
        void onPageSelected(int position);
        void onPageScrolled(float positionOffset);
    }

    private final MainActivity mContext;
    private PageListener mPageListener;

    public ViewPagerAdapter(MainActivity context, FragmentManager fm, PageListener listener) {
        super(fm);
        mContext = context;
        mPageListener = mPageListener;
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        if (position == 0) {
            return OutboundFragment.newInstance();
        } else {
            return InboundFragment.newInstance();
        }
    }

    @Override
    public int getCount() {
        // Show 2 total pages.
        return 2;
    }

    public void swipeToPrevious(int page) {
        mContext.viewPager.setCurrentItem(page, true);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}







