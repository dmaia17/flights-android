package com.danielmaia.flights.views.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.danielmaia.flights.AppVoo;
import com.danielmaia.flights.R;
import com.danielmaia.flights.database.VooDatabase;
import com.danielmaia.flights.model.Flight;
import com.danielmaia.flights.util.Constantes;
import com.danielmaia.flights.viewModels.PageOutboundViewModel;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class OutboundFragment extends Fragment {
    private PageOutboundViewModel pageOutboundViewModel;
    private List<Flight> flightList;

    public OutboundFragment() {
        // Required empty public constructor
    }

    public static OutboundFragment newInstance() {
        return new OutboundFragment();
    }

    @Override public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // init ViewModel
        pageOutboundViewModel = ViewModelProviders.of(requireActivity()).get(PageOutboundViewModel.class);
        getFlights();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_go, container, false);
    }

    @Override public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    private void getFlights() {
        pageOutboundViewModel.getFlightResponseLiveData().observe(this, flightResponse -> {
            if (flightResponse != null) {

                /*
                progress_circular_movie_article.setVisibility(View.GONE);
                List<Article> articles = articleResponse.getArticles();
                articleArrayList.addAll(articles);
                adapter.notifyDataSetChanged(); */
            }
        });
    }

    private void setList(){
        int[] filters = AppVoo.getInstance().getCurrentFilter();

        switch (filters.length){
            case 0:
                flightList = VooDatabase.getInstance(AppVoo.getInstance())
                        .getFlightDao().getAllFlights(Constantes.OUTBOUND);
                break;
            case 1:
                VooDatabase.getInstance(AppVoo.getInstance())
                        .getFlightDao().getAllFlights1Period(Constantes.OUTBOUND, filters[0]);
                break;
            case 2:
                flightList = VooDatabase.getInstance(AppVoo.getInstance())
                        .getFlightDao().getAllFlights2Period(Constantes.OUTBOUND, filters[0], filters[1]);
                break;
            case 3:
                flightList = VooDatabase.getInstance(AppVoo.getInstance())
                        .getFlightDao().getAllFlights3Period(Constantes.OUTBOUND, filters[0], filters[1], filters[2]);
                break;
            case 4:
                flightList = VooDatabase.getInstance(AppVoo.getInstance())
                        .getFlightDao().getAllFlights4Period(Constantes.OUTBOUND, filters[0], filters[1],
                                filters[2], filters[3]);
                break;
        }

        Collections.sort(flightList, new SortFlights());
    }


    class SortFlights implements Comparator<Flight> {
        public int compare(Flight a, Flight b) {
            if (AppVoo.getInstance().getCurrentSort() == Constantes.FILTER_PRICE_DESC)
                return (int) (a.getSaleTotal() - b.getSaleTotal());
            if (AppVoo.getInstance().getCurrentSort() == Constantes.FILTER_PRICE_ASC)
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
