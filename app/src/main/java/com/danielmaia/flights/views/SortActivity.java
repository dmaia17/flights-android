package com.danielmaia.flights.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.widget.RadioButton;

import com.danielmaia.flights.R;
import com.danielmaia.flights.databinding.ActivitySortBinding;
import com.danielmaia.flights.util.AppPreferences;
import com.danielmaia.flights.util.Constants;
import com.danielmaia.flights.viewModels.HandlerSortActivityViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SortActivity extends AppCompatActivity {

    @BindView(R.id.rbLowestPrice)
    RadioButton rbLowestPrice;

    @BindView(R.id.rbBiggestPrice)
    RadioButton rbBiggestPrice;

    @BindView(R.id.rbPriceTime)
    RadioButton rbPriceTime;

    private HandlerSortActivityViewModel handlerSortActivityViewModel;
    private ActivitySortBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sort);
        ButterKnife.bind(this);

        getSupportActionBar().setTitle(getResources().getString(R.string.sort_title));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        configHandlerViewModel();
        configInitialData();
    }


    private void configInitialData(){
        int currentSort = AppPreferences.getInstance().getCurrentSort();

        switch (currentSort){
            case Constants.SORT_PRICE_ASC:
                handlerSortActivityViewModel.getOptionLowestClicked().setValue(true);
                break;
            case Constants.SORT_PRICE_DESC:
                handlerSortActivityViewModel.getOptionBiggestClicked().setValue(true);
                break;
            case Constants.SORT_PRICE_TIME:
                handlerSortActivityViewModel.getOptionPriceTimeClicked().setValue(true);
                break;
        }
    }

    private void configHandlerViewModel() {
        handlerSortActivityViewModel = ViewModelProviders.of(this).get(HandlerSortActivityViewModel.class);
        binding.setHandlerSortActivityViewModel(handlerSortActivityViewModel);

        handlerSortActivityViewModel.getOptionLowestClicked().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                AppPreferences.getInstance().setCurrentSort(Constants.SORT_PRICE_ASC);

                if (!rbLowestPrice.isChecked())
                    rbLowestPrice.setChecked(true);
            }
        });

        handlerSortActivityViewModel.getOptionBiggestClicked().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                AppPreferences.getInstance().setCurrentSort(Constants.SORT_PRICE_DESC);

                if (!rbBiggestPrice.isChecked())
                    rbBiggestPrice.setChecked(true);
            }
        });


        handlerSortActivityViewModel.getOptionPriceTimeClicked().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                AppPreferences.getInstance().setCurrentSort(Constants.SORT_PRICE_TIME);

                if (!rbPriceTime.isChecked())
                    rbPriceTime.setChecked(true);
            }
        });

        handlerSortActivityViewModel.getSortButtonClicked().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                int selected = AppPreferences.getInstance().getCurrentSort();
                finish();
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
