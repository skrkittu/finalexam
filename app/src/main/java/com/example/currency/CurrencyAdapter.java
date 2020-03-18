package com.example.currency;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;


import com.example.currency.fragment.CurrencyFragment;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class CurrencyAdapter extends RecyclerView.Adapter<CurrencyAdapter.MyViewHolder> {

    private String[]  countryDOS;
    private Context context;


    public void refreshAdapter(@NotNull String[]  countryDOS) {
        this.countryDOS = countryDOS;
        notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tvCountryCode, tvCountryName;

        public MyViewHolder(View view) {
            super(view);
            tvCountryName = itemView.findViewById(R.id.tvCountryName);



        }
    }


    public CurrencyAdapter(Context context, String[] countryDOS) {
        this.context = context;
        this.countryDOS = countryDOS;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.countrys_data, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        holder.tvCountryName.setText(countryDOS[position]);
        final String currency    = countryDOS[position].substring(countryDOS[position].length() - 3);

        if ((position % 2 == 0)) {
            holder.itemView.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.edit_text_background_lg));
        } else {
            holder.itemView.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.edit_text_background_lsg));
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PreferenceUtils preferenceUtils = new PreferenceUtils(context);
                preferenceUtils.saveString(PreferenceUtils.BASE_CURRENCY,currency);

                Bundle bundle = new Bundle();
                bundle.putInt("id", 3534);
                FragmentManager fragmentManager =    ((AppCompatActivity) context).getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                CurrencyFragment homeFragment = new CurrencyFragment(context);

                for (int i = 0; i < fragmentManager.getBackStackEntryCount(); i++) {
                    fragmentManager.popBackStack();
                }
                fragmentTransaction.add(R.id.host_fragment, homeFragment);
                fragmentTransaction.addToBackStack(null);
                homeFragment.setArguments(bundle);
                fragmentTransaction.commit();
            }
        });


    }

    @Override
    public int getItemCount() {
        return countryDOS != null ? countryDOS.length : 10;
//        return 10;

    }

}
