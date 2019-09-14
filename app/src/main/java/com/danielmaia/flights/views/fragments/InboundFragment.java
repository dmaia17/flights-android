package com.danielmaia.flights.views.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.danielmaia.flights.R;
import com.danielmaia.flights.viewModels.PageOutboundViewModel;


public class InboundFragment extends Fragment {
    private PageOutboundViewModel pageOutboundViewModel;
    private TextView txtName;

    public InboundFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of this fragment.
     *
     * @return A new instance of fragment SecondFragment.
     */
    public static InboundFragment newInstance() {
        return new InboundFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // initialise ViewModel here
        pageOutboundViewModel = ViewModelProviders.of(requireActivity()).get(PageOutboundViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_back, container, false);
    }

    @Override public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        txtName = view.findViewById(R.id.textViewName);

        /*
        pageOutboundViewModel.getName().observe(requireActivity(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                txtName.setText(s);
            }
        }); */
    }
}
