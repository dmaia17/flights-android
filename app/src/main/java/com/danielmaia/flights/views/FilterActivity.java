package com.danielmaia.flights.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.danielmaia.flights.AppFlights;
import com.danielmaia.flights.R;
import com.danielmaia.flights.database.FlightDatabase;
import com.danielmaia.flights.databinding.ActivityFilterBinding;
import com.danielmaia.flights.model.Filter;
import com.danielmaia.flights.repository.FlightRepository;
import com.danielmaia.flights.util.Constants;
import com.danielmaia.flights.viewModels.FilterActivityViewModel;
import com.danielmaia.flights.viewModels.HandlerFilterActivityViewModel;
import com.danielmaia.flights.viewModels.PageOutboundViewModel;
import com.danielmaia.flights.views.fragments.OutboundFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.danielmaia.flights.util.Constants.FILTER_STOP_0;
import static com.danielmaia.flights.util.Constants.FILTER_STOP_1;
import static com.danielmaia.flights.util.Constants.FILTER_TIME_AFTERNOON;
import static com.danielmaia.flights.util.Constants.FILTER_TIME_DAWN;
import static com.danielmaia.flights.util.Constants.FILTER_TIME_MORNING;
import static com.danielmaia.flights.util.Constants.FILTER_TIME_NIGHT;
import static com.danielmaia.flights.util.Constants.FILTER_TYPE_TIME;

public class FilterActivity extends AppCompatActivity {

    private static final String TAG = FlightRepository.class.getSimpleName();

    @BindView(R.id.imgMorning)
    ImageView imgMorning;

    @BindView(R.id.imgAfternoon)
    ImageView imgAfternoon;

    @BindView(R.id.imgNight)
    ImageView imgNight;

    @BindView(R.id.imgDawn)
    ImageView imgDawn;

    @BindView(R.id.imgNoStop)
    ImageView imgNoStop;

    @BindView(R.id.imgOneStop)
    ImageView imgOneStop;

    private ActivityFilterBinding binding;
    private HandlerFilterActivityViewModel handlerFilterActivityViewModel;
    public List<Integer> list = new ArrayList<>();
    private FilterActivityViewModel filterActivityViewModel;
    private boolean edditing = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_filter);
        ButterKnife.bind(this);

        getSupportActionBar().setTitle(getResources().getString(R.string.filter_title));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        configViewModel();
        configHandlerViewModel();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_filter, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if (item.getItemId() == R.id.clean)
            cleanAllFilters();

        return true;
    }

    private void configViewModel(){
        filterActivityViewModel = ViewModelProviders.of(this).get(FilterActivityViewModel.class);

        filterActivityViewModel.getFiltersLiveData().observe(this, filterList -> {
                if (filterList != null) {
                    list = filterList;
                    configViews();
                }
            });
    }

    private void cleanAllFilters(){
        filterActivityViewModel.deleteAllFilters().observe(FilterActivity.this, success -> {
            if (success) {
                list.clear();
                configViews();
            }
        });
    }

    private void configViews(){
        handlerFilterActivityViewModel.getTimeMorningClicked().setValue(false);
        handlerFilterActivityViewModel.getTimeAfternoonClicked().setValue(false);
        handlerFilterActivityViewModel.getTimeNightClicked().setValue(false);
        handlerFilterActivityViewModel.getTimeDawnClicked().setValue(false);
        handlerFilterActivityViewModel.getStopNoClicked().setValue(false);
        handlerFilterActivityViewModel.getStopOneClicked().setValue(false);
    }

    private void configHandlerViewModel() {
        handlerFilterActivityViewModel = ViewModelProviders.of(this).get(HandlerFilterActivityViewModel.class);
        binding.setHandlerFilterActivityViewModel(handlerFilterActivityViewModel);

        handlerFilterActivityViewModel.getTimeMorningClicked().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean result) {
                configOptions(imgMorning, FILTER_TIME_MORNING, result);
                //new configViewTask(imgMorning, Constants.FILTER_TYPE_TIME, Constants.FILTER_TIME_MORNING, result).execute();
            }
        });

        handlerFilterActivityViewModel.getFilterButtonClicked().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean result) {
                filterActivityViewModel.insertFilters(list).observe(FilterActivity.this, success -> {
                    if (success) {
                        finish();
                    }
                });
            }
        });

        handlerFilterActivityViewModel.getTimeAfternoonClicked().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean result) {
                configOptions(imgAfternoon, FILTER_TIME_AFTERNOON, result);
            }
        });

        handlerFilterActivityViewModel.getTimeNightClicked().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean result) {
                configOptions(imgNight, FILTER_TIME_NIGHT, result);
            }
        });

        handlerFilterActivityViewModel.getTimeDawnClicked().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean result) {
                configOptions(imgDawn, FILTER_TIME_DAWN, result);
            }
        });

        handlerFilterActivityViewModel.getStopNoClicked().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean result) {
                configOptions(imgNoStop, FILTER_STOP_0, result);
            }
        });

        handlerFilterActivityViewModel.getStopOneClicked().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean result) {
                configOptions(imgOneStop, FILTER_STOP_1, result);
            }
        });


    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private void configOptions(ImageView imageView, int filterId, boolean clicked){
        new Thread() {
            public void run() {
                try {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            boolean doSelect;

                            if (list.contains(filterId)) {
                                doSelect = true;

                                if (clicked) {
                                    list.remove(new Integer(filterId));
                                    doSelect = false;
                                }

                            } else {
                                doSelect = false;

                                if (clicked) {
                                    list.add(filterId);
                                    doSelect = true;
                                }
                            }

                            imageView.setBackground(doSelect ? AppFlights.getInstance().getDrawable(R.drawable.ic_selected)
                                    : AppFlights.getInstance().getDrawable(R.drawable.ic_unselected));
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();


    }
}














