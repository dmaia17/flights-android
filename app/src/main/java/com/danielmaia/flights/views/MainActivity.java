package com.danielmaia.flights.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.danielmaia.flights.R;
import com.danielmaia.flights.databinding.ActivityMainBinding;
import com.danielmaia.flights.viewModels.HandlerMainActivityViewModel;
import com.danielmaia.flights.viewModels.MainActivityViewModel;
import com.danielmaia.flights.views.adapters.ViewPagerAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import info.androidhive.fontawesome.FontDrawable;

public class MainActivity extends AppCompatActivity implements ViewPagerAdapter.PageListener{

    private final String TAG = "MainActivity";
    private final int TAB_OUTBOUND = 0;
    private final int TAB_INBOUND = 1;

    @BindView(R.id.view_pager)
    public ViewPager viewPager;

    @BindView(R.id.rlTab1)
    RelativeLayout rlTab1;

    @BindView(R.id.txtTitle1)
    TextView txtTitle1;

    @BindView(R.id.vwSelected1)
    View vwSelected1;

    @BindView(R.id.rlTab2)
    RelativeLayout rlTab2;

    @BindView(R.id.txtTitle2)
    TextView txtTitle2;

    @BindView(R.id.vwSelected2)
    View vwSelected2;

    @BindView(R.id.rlFilter)
    RelativeLayout rlFilter;

    @BindView(R.id.txtFilter)
    TextView txtFilter;

    @BindView(R.id.rlSort)
    RelativeLayout rlSort;

    @BindView(R.id.txtSort)
    TextView txtSort;

    private HandlerMainActivityViewModel mHandlerMainActivityViewModel;
    private ActivityMainBinding mBinding;
    private int mTabSelected;
    private ViewPagerAdapter viewPagerAdapter;
    private MainActivityViewModel mainActivityViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        ButterKnife.bind(this);

        //initialization();
        //getFlights();
        configHandlerViewModel();
        configPager();
        configViews();
    }

    private void initialization() {
        // View Model
        mainActivityViewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);
    }

    private void configHandlerViewModel(){
        mHandlerMainActivityViewModel = ViewModelProviders.of(this).get(HandlerMainActivityViewModel.class);
        mBinding.setHandlerMainActivityViewModel(mHandlerMainActivityViewModel);

        mHandlerMainActivityViewModel.getmTab1Clicked().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                tabClicked(TAB_OUTBOUND);
            }
        });

        mHandlerMainActivityViewModel.getmTab2Clicked().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                tabClicked(TAB_INBOUND);
            }
        });
    }

    private void tabClicked(int tab){
        mTabSelected = tab;
        viewPagerAdapter.swipeToPrevious(mTabSelected);
        configTabs();
    }

    private void configPager(){
        viewPagerAdapter = new ViewPagerAdapter(this, getSupportFragmentManager(), this);
        viewPager.setAdapter(viewPagerAdapter);
        viewPagerAdapter.notifyDataSetChanged();

        viewPager.addOnPageChangeListener(viewPagerAdapter);
        viewPager.setCurrentItem(0);
        viewPager.setOffscreenPageLimit(3);

        viewPager.setPageTransformer(false, new ViewPager.PageTransformer() {
            @Override
            public void transformPage(View page, float position) {
                    mTabSelected = viewPager.getCurrentItem();
                    configTabs();
            }
        });
    }

    private void configViews(){
        FontDrawable iconFilter = new FontDrawable(this, R.string.fa_icon_filter, true, false);
        iconFilter.setTextColor(ContextCompat.getColor(this, android.R.color.white));
        txtFilter.setCompoundDrawablesWithIntrinsicBounds(iconFilter, null, null, null);

        FontDrawable iconSort = new FontDrawable(this, R.string.fa_icon_sort, true, false);
        iconSort.setTextColor(ContextCompat.getColor(this, android.R.color.white));
        txtSort.setCompoundDrawablesWithIntrinsicBounds(iconSort, null, null, null);
    }

    private void configTabs(){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                rlTab1.setBackgroundColor(getResources()
                        .getColor(mTabSelected == TAB_OUTBOUND ? R.color.white :  R.color.light_gray));
                rlTab2.setBackgroundColor(getResources()
                        .getColor(mTabSelected == TAB_INBOUND ? R.color.white :  R.color.light_gray));
                txtTitle1.setTextColor(getResources()
                        .getColor(mTabSelected == TAB_OUTBOUND ? R.color.text_color :  R.color.text_color_desc));
                txtTitle2.setTextColor(getResources()
                        .getColor(mTabSelected == TAB_INBOUND ? R.color.text_color :  R.color.text_color_desc));
                vwSelected1.setBackgroundColor(getResources()
                        .getColor(mTabSelected == TAB_OUTBOUND ? R.color.colorAccent :  R.color.transparent));
                vwSelected2.setBackgroundColor(getResources()
                        .getColor(mTabSelected == TAB_INBOUND ? R.color.colorAccent :  R.color.transparent));
            }
        });
    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrolled(float positionOffset) {

    }

    /**
     * get flights articles from api
     *
     * @param @null
     */
    private void getFlights() {
        mainActivityViewModel.getFlightResponseLiveData().observe(this, flightResponse -> {
            if (flightResponse != null) {

                /*
                progress_circular_movie_article.setVisibility(View.GONE);
                List<Article> articles = articleResponse.getArticles();
                articleArrayList.addAll(articles);
                adapter.notifyDataSetChanged(); */
            }
        });
    }
}
