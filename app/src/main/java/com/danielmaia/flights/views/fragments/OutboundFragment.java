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

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.danielmaia.flights.AppFlights;
import com.danielmaia.flights.R;
import com.danielmaia.flights.database.VooDatabase;
import com.danielmaia.flights.model.Flight;
import com.danielmaia.flights.util.AppPreferences;
import com.danielmaia.flights.util.Constantes;
import com.danielmaia.flights.viewModels.PageOutboundViewModel;
import com.danielmaia.flights.views.adapters.OutboundAdapter;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OutboundFragment extends Fragment {

    @BindView(R.id.progress)
    ProgressBar progress;

    @BindView(R.id.rvFlights)
    RecyclerView rvFlights;

    @BindView(R.id.txtCountFilter)
    TextView txtCountFilter;

    OutboundAdapter adapter;
    private PageOutboundViewModel pageOutboundViewModel;
    private List<Flight> flightList;


    public OutboundFragment() {
    }

    public static OutboundFragment newInstance() {
        return new OutboundFragment();
    }

    @Override public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_outbound, container, false);
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
        rvFlights.setVisibility(View.INVISIBLE);
        txtCountFilter.setVisibility(View.INVISIBLE);

        // init ViewModel
        pageOutboundViewModel = ViewModelProviders.of(requireActivity()).get(PageOutboundViewModel.class);
        getFlights();
        configFilterCount();
    }

    private void initialization() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        rvFlights.setLayoutManager(layoutManager);
        rvFlights.setHasFixedSize(true);
        adapter = new OutboundAdapter(flightList);
        rvFlights.setAdapter(adapter);

        pageOutboundViewModel.getFilterCount().setValue(flightList.size());
    }

    private void configFilterCount(){
        pageOutboundViewModel.getFilterCount().observe(this, new Observer<Integer>() {
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

    private void getFlights() {
        pageOutboundViewModel.getFlightResponseLiveData().observe(this, flightResponse -> {
            if (flightResponse != null) {
                new fillListTask(this).execute();
            }
        });
    }

    private static class fillListTask extends AsyncTask<Void, Void, Void> {
        private OutboundFragment fragment;

        public fillListTask(OutboundFragment fragment) {
            this.fragment = fragment;
        }

        @Override
        protected Void doInBackground(Void... params) {
            int[] filters = AppPreferences.getInstance().getCurrentFilter();
            int lenght = filters == null ? 0 : filters.length;

            switch (lenght){
                case 0:
                    fragment.flightList = VooDatabase.getInstance(AppFlights.getInstance())
                            .getFlightDao().getAllFlights(Constantes.OUTBOUND);
                    break;
                case 1:
                    VooDatabase.getInstance(AppFlights.getInstance())
                            .getFlightDao().getAllFlights1Period(Constantes.OUTBOUND, filters[0]);
                    break;
                case 2:
                    fragment.flightList = VooDatabase.getInstance(AppFlights.getInstance())
                            .getFlightDao().getAllFlights2Period(Constantes.OUTBOUND, filters[0], filters[1]);
                    break;
                case 3:
                    fragment.flightList = VooDatabase.getInstance(AppFlights.getInstance())
                            .getFlightDao().getAllFlights3Period(Constantes.OUTBOUND, filters[0], filters[1], filters[2]);
                    break;
                case 4:
                    fragment.flightList = VooDatabase.getInstance(AppFlights.getInstance())
                            .getFlightDao().getAllFlights4Period(Constantes.OUTBOUND, filters[0], filters[1],
                                    filters[2], filters[3]);
                    break;
            }

            Collections.sort(fragment.flightList, new SortFlights());
            return null;
        }

        @Override
        protected void onPostExecute(Void agentsCount) {
            fragment.initialization();
            fragment.progress.setVisibility(View.GONE);
            fragment.rvFlights.setVisibility(View.VISIBLE);
        }

        class SortFlights implements Comparator<Flight> {
            public int compare(Flight a, Flight b) {
                int currentSort = AppPreferences.getInstance().getCurrentSort();

                if (currentSort == Constantes.FILTER_PRICE_ASC)
                    return (int) (a.getSaleTotal() - b.getSaleTotal());
                if (currentSort == Constantes.FILTER_PRICE_DESC)
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
