package com.example.currency.fragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.currency.CountryDO;
import com.example.currency.CurrencyAdapter;
import com.example.currency.R;

public class CountryList extends Fragment {
    RecyclerView recyclerView;
    int id;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);
        recyclerView = (RecyclerView) view.findViewById(R.id.my_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);

        CurrencyAdapter myDataAdapter = new CurrencyAdapter(getActivity(), COUNTRIES);

        recyclerView.setAdapter(myDataAdapter);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (this.getArguments() != null) {
            id = this.getArguments().getInt("id");
        } else {
            id = 3534;
        }
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.activity_country_list, container, false);

    }


    //31
    static final String[] COUNTRIES = new String[]{
            "United States Dollar - USD", "Hong Kong - HKD", "Canada - CAD", "Icelandic Króna - ISK", "Philippine peso - PHP",
            "Danish Krone - DKK", "Czech Koruna - CZK", "Australia - AUD", "Romanian Leu - RON",
            "Swedish Krona - SEK", "Indonesian Rupiah - IDR", "Indian Rupee - INR", "Brazilian Real - BRL",
            "Russian Ruble - RUB", "Croatian Kuna - HRK", "Japanese Yen - JPY", "Thai Baht - THB",
            "Swiss Franc - CHF", "Singapore Dollar - SGD", "Poland złoty - PLN", "Bulgarian Lev - BGN",
            "Turkish lira - TRY", "Chinese Yuan - CNY", "Norwegian Krone - NOK", "New Zealand - NZD",
            "South African Rand - ZAR", "Mexican Peso - MXN", "Israeli New Shekel - ILS",
            "Pound sterling - GBP", "South Korean won - KRW", "Malaysian Ringgit - MYR"
    };
}
