package com.example.currency.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.currency.PreferenceUtils;
import com.example.currency.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;

public class CurrencyFragment extends Fragment {

    List<String> keysList;
    Spinner toCurrency;
    TextView textView,textView4,tvResult;
    Context context;
    int id;

    public CurrencyFragment(Context mContext) {
        context = mContext;
        // Required empty public constructor
    }

    public void hideKeyBoard(View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public void showSoftKeyboard(View view) {
        if (view.requestFocus()) {
            InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT);
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if (this.getArguments() != null) {
            id = this.getArguments().getInt("id");
        } else {
            id = 3534;
        }
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.activity_currency, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        toCurrency = view.findViewById(R.id.planets_spinner);
        final EditText edtEuroValue = view.findViewById(R.id.editText4);
        final Button btnConvert = view.findViewById(R.id.button);
        textView = view.findViewById(R.id.textView7);
        tvResult = view.findViewById(R.id.textView6);
        textView4 = view.findViewById(R.id.textView4);

        try {
            loadConvTypes();
        } catch (IOException e) {
            e.printStackTrace();
        }

        btnConvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideKeyBoard(v);
                if (!edtEuroValue.getText().toString().isEmpty()) {
                    String toCurr = toCurrency.getSelectedItem().toString();
                    double euroVlaue = Double.valueOf(edtEuroValue.getText().toString());

                    Toast.makeText(context, "Please Wait..", Toast.LENGTH_SHORT).show();
                    try {
                        convertCurrency(toCurr, euroVlaue);
                        tvResult.setVisibility(View.VISIBLE);
                    } catch (IOException e) {
                        e.printStackTrace();
                        Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(context, "Please Enter a Value to Convert..", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


    public void loadConvTypes() throws IOException {

        String url ="";
        PreferenceUtils preferenceUtils = new PreferenceUtils(context);
        String currency = preferenceUtils.getStringFromPreference(PreferenceUtils.BASE_CURRENCY,"");
        if(currency.isEmpty()){
            url = "https://api.exchangeratesapi.io/latest";

        }else {
            url = "https://api.exchangeratesapi.io/latest?base="+currency;
            textView4.setText(currency);


        }
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(url)
                .header("Content-Type", "application/json")
                .build();


        client.newCall(request).enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(okhttp3.Call call, IOException e) {
                final String mMessage = e.getMessage().toString();
                Log.w("failure Response", mMessage);
                Handler mHandler = new Handler(Looper.getMainLooper());

// anywhere else in your code
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(context, mMessage, Toast.LENGTH_SHORT).show();

                    }
                });
            }


            @Override
            public void onResponse(okhttp3.Call call, okhttp3.Response response) throws IOException {
                final String mMessage = response.body().string();
                Handler mHandler = new Handler(Looper.getMainLooper());
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        //Toast.makeText(context, mMessage, Toast.LENGTH_SHORT).show();
                        try {
                            JSONObject obj = new JSONObject(mMessage);
                            JSONObject b = obj.getJSONObject("rates");

                            Iterator keysToCopyIterator = b.keys();
                            keysList = new ArrayList<String>();

                            while (keysToCopyIterator.hasNext()) {

                                String key = (String) keysToCopyIterator.next();

                                keysList.add(key);

                            }


                            ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, keysList);
                            toCurrency.setAdapter(spinnerArrayAdapter);


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });


            }


        });
    }

    public void convertCurrency(final String toCurr, final double euroVlaue) throws IOException {
        String url ="";
        PreferenceUtils preferenceUtils = new PreferenceUtils(context);
        String currency = preferenceUtils.getStringFromPreference(PreferenceUtils.BASE_CURRENCY,"");
        if(currency.isEmpty()){
             url = "https://api.exchangeratesapi.io/latest";

        }else {
            url = "https://api.exchangeratesapi.io/latest?base="+currency;
            textView4.setText(currency);


        }



        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(url)
                .header("Content-Type", "application/json")
                .build();


        client.newCall(request).enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(okhttp3.Call call, IOException e) {
                String mMessage = e.getMessage().toString();
                Log.w("failure Response", mMessage);
                Toast.makeText(context, mMessage, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onResponse(okhttp3.Call call, okhttp3.Response response) throws IOException {
                final String mMessage = response.body().string();
                Handler mHandler = new Handler(Looper.getMainLooper());

// anywhere else in your code
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            JSONObject obj = new JSONObject(mMessage);
                            JSONObject b = obj.getJSONObject("rates");

                            String val = b.getString(toCurr);

                            double output = euroVlaue * Double.valueOf(val);


                            textView.setText(String.valueOf(output));

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });


            }


        });
    }

}
