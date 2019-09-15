package com.danielmaia.flights.views.fragments;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.danielmaia.flights.AppFlights;
import com.danielmaia.flights.R;
import com.danielmaia.flights.model.Flight;
import com.danielmaia.flights.util.AppPreferences;
import com.danielmaia.flights.util.Constants;
import com.danielmaia.flights.viewModels.InboundFragmentViewModel;
import com.danielmaia.flights.views.adapters.InboundAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class InboundFragment extends Fragment {

    @BindView(R.id.progress)
    ProgressBar progress;

    @BindView(R.id.llContainer)
    LinearLayout llContainer;

    @BindView(R.id.rvFlights)
    RecyclerView rvFlights;

    @BindView(R.id.txtCountFilter)
    TextView txtCountFilter;

    @BindView(R.id.txtEmptyList)
    TextView txtEmptyList;

    InboundAdapter adapter;
    private InboundFragmentViewModel inboundFragmentViewModel;
    private List<Flight> flightList = new ArrayList<>();;

    public InboundFragment() {
    }

    public static InboundFragment newInstance() {
        return new InboundFragment();
    }

    @Override public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_inbound, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @Override public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();

        progress.setVisibility(View.VISIBLE);
        llContainer.setVisibility(View.INVISIBLE);
        txtCountFilter.setVisibility(View.INVISIBLE);

        inboundFragmentViewModel = ViewModelProviders.of(requireActivity()).get(InboundFragmentViewModel.class);
        getFlightsOnService();
        configFilterCount();
    }

    private void initialization() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        rvFlights.setLayoutManager(layoutManager);
        rvFlights.setHasFixedSize(true);
        adapter = new InboundAdapter(flightList);
        rvFlights.setAdapter(adapter);

        inboundFragmentViewModel.getFilterCount().setValue(flightList.size());
    }

    private void configFilterCount(){
        inboundFragmentViewModel.getFilterCount().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer sizeList) {
                txtCountFilter.setVisibility(View.VISIBLE);

                if (sizeList == 1)
                    txtCountFilter.setText(AppFlights.getInstance().getResources().getString(R.string.main_count_filter_one));
                else
                    txtCountFilter.setText(String.format(AppFlights.getInstance().getResources().getString(R.string.main_count_filter), sizeList));
            }
        });
    }

    private void getFlightsOnService() {
        inboundFragmentViewModel.getFlightResponseLiveData().observe(this, flightResponse -> {
            if (flightResponse != null) {
                getFlightsOnDatabase();
            } else {
                Toast.makeText(AppFlights.getInstance(), AppFlights.getInstance()
                        .getString(R.string.main_error_service), Toast.LENGTH_SHORT).show();
                checkEmptyList();
            }
        });
    }

    private void getFlightsOnDatabase(){
        inboundFragmentViewModel.getListFlightInboundOnDatabase().observe(this, flightList -> {
            if (flightList != null) {
                this.flightList = flightList;
                new InboundFragment.fillListTask(this).execute();
            }
        });
    }

    private void checkEmptyList(){
        progress.setVisibility(View.GONE);

        if (flightList.size() > 0){
            llContainer.setVisibility(View.VISIBLE);
            txtEmptyList.setVisibility(View.GONE);
        } else {
            llContainer.setVisibility(View.GONE);
            txtEmptyList.setVisibility(View.VISIBLE);
        }
    }

    private static class fillListTask extends AsyncTask<Void, Void, Void> {
        private InboundFragment fragment;

        public fillListTask(InboundFragment fragment) {
            this.fragment = fragment;
        }

        @Override
        protected Void doInBackground(Void... params) {
            Collections.sort(fragment.flightList, new InboundFragment.fillListTask.SortFlights());
            return null;
        }

        @Override
        protected void onPostExecute(Void agentsCount) {
            fragment.initialization();
            fragment.checkEmptyList();
        }

        class SortFlights implements Comparator<Flight> {
            public int compare(Flight a, Flight b) {
                int currentSort = AppPreferences.getInstance().getCurrentSort();

                if (currentSort == Constants.SORT_PRICE_ASC)
                    return (int) (a.getSaleTotal() - b.getSaleTotal());
                if (currentSort == Constants.SORT_PRICE_DESC)
                    return (int) (b.getSaleTotal() - a.getSaleTotal());
                else {
                    int result = Integer.valueOf((int) a.getSaleTotal()).compareTo((int) b.getSaleTotal());
                    if(result==0) {
                        return Integer.valueOf((int) a.getDuration()).compareTo((int) b.getDuration());
                    } else {
                        return result;
                    }
                }
            }
        }
    }
}
