package com.example.currency.activity;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.currency.PreferenceUtils;
import com.example.currency.R;
import com.example.currency.fragment.AboutFragment;
import com.example.currency.fragment.CountryList;
import com.example.currency.fragment.CurrencyFragment;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    ListView drawerListView;
    String[] OPTIONS = {"Home", "About", "Canadian Dollar", "Indian Currency","US Dollar"};
    ActionBarDrawerToggle actionBarDrawerToggle;

    public Toolbar toolbar;
    public DrawerLayout drawerLayout;
    public NavController navController;
    public NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUpNavigation();

        cityFragment(3534);
    }

    public void setUpNavigation() {
        toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_drawer);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar() .setDisplayShowHomeEnabled(true);
            getSupportActionBar() .setDisplayShowHomeEnabled(true);
            getSupportActionBar() .setDisplayShowHomeEnabled(true);
            getSupportActionBar() .setDisplayShowHomeEnabled(true);

        }

        getSupportActionBar().setTitle("Select Currency");
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerListView = (ListView) findViewById(R.id.left_drawer);

        // creating ActionBarDrawerToggle:This class provides a handy way to tie
        // together the functionality of DrawerLayout and the framework
        // ActionBar to implement the recommended design for navigation drawers.
        actionBarDrawerToggle = new ActionBarDrawerToggle(MainActivity.this, drawerLayout,
                toolbar, R.string.drawer_open,
                R.string.drawer_close);

        // Set the drawer toggle as the DrawerListener
        drawerLayout.setDrawerListener(actionBarDrawerToggle);
//        getActionBar().setDisplayHomeAsUpEnabled(true);

//        getActionBar().setDisplayHomeAsUpEnabled(true);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                getApplicationContext(), R.layout.text, OPTIONS);

        // Set the adapter for the list view
        drawerListView.setAdapter(adapter);

        // Set the list's click listener
        drawerListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapter, View arg1,
                                    int position, long arg3) {
                // TODO Auto-generated method stub
                if(position==0){
                    getSupportActionBar().setTitle("Select Currency");

                    Bundle bundle = new Bundle();
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    CountryList homeFragment = new CountryList();

                    for (int i = 0; i < fragmentManager.getBackStackEntryCount(); i++) {
                        fragmentManager.popBackStack();
                    }

                    fragmentTransaction.add(R.id.host_fragment, homeFragment);
                    homeFragment.setArguments(bundle);
                    fragmentTransaction.commit();

                }else if (position==1){
                    getSupportActionBar().setTitle("About App");

                    Bundle bundle = new Bundle();
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    AboutFragment homeFragment = new AboutFragment();

                    for (int i = 0; i < fragmentManager.getBackStackEntryCount(); i++) {
                        fragmentManager.popBackStack();
                    }

                    fragmentTransaction.add(R.id.host_fragment, homeFragment);
                    homeFragment.setArguments(bundle);
                    fragmentTransaction.commit();
                }
                else if (position==2){
                    getSupportActionBar().setTitle("Select Currency");

                    Bundle bundle = new Bundle();
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    PreferenceUtils preferenceUtils = new PreferenceUtils(MainActivity.this);
                    preferenceUtils.removeFromPreference(PreferenceUtils.BASE_CURRENCY);
                    CurrencyFragment homeFragment = new CurrencyFragment(MainActivity.this);

                    for (int i = 0; i < fragmentManager.getBackStackEntryCount(); i++) {
                        fragmentManager.popBackStack();
                    }

                    fragmentTransaction.add(R.id.host_fragment, homeFragment);
                    homeFragment.setArguments(bundle);
                    fragmentTransaction.commit();
                }
                else if (position==3){
                    getSupportActionBar().setTitle("Select Currency");

                    Bundle bundle = new Bundle();
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    PreferenceUtils preferenceUtils = new PreferenceUtils(MainActivity.this);
                    preferenceUtils.removeFromPreference(PreferenceUtils.BASE_CURRENCY);
                    preferenceUtils.saveString(PreferenceUtils.BASE_CURRENCY,"INR");

                    CurrencyFragment homeFragment = new CurrencyFragment(MainActivity.this);

                    for (int i = 0; i < fragmentManager.getBackStackEntryCount(); i++) {
                        fragmentManager.popBackStack();
                    }

                    fragmentTransaction.add(R.id.host_fragment, homeFragment);
                    homeFragment.setArguments(bundle);
                    fragmentTransaction.commit();
                }
                else if (position==4){
                    getSupportActionBar().setTitle("Select Currency");

                    Bundle bundle = new Bundle();
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    PreferenceUtils preferenceUtils = new PreferenceUtils(MainActivity.this);
                    preferenceUtils.removeFromPreference(PreferenceUtils.BASE_CURRENCY);
                    preferenceUtils.saveString(PreferenceUtils.BASE_CURRENCY,"USD");

                    CurrencyFragment homeFragment = new CurrencyFragment(MainActivity.this);

                    for (int i = 0; i < fragmentManager.getBackStackEntryCount(); i++) {
                        fragmentManager.popBackStack();
                    }

                    fragmentTransaction.add(R.id.host_fragment, homeFragment);
                    homeFragment.setArguments(bundle);
                    fragmentTransaction.commit();
                }

                drawerLayout.closeDrawers();
            }
        });

    }

    public void cityFragment(int id) {

        Bundle bundle = new Bundle();
        bundle.putInt("id", id);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        CountryList homeFragment = new CountryList();

        for (int i = 0; i < fragmentManager.getBackStackEntryCount(); i++) {
            fragmentManager.popBackStack();
        }

        fragmentTransaction.add(R.id.host_fragment, homeFragment);
        homeFragment.setArguments(bundle);
        fragmentTransaction.commit();

    }

    @Override
    public boolean onSupportNavigateUp() {
        return NavigationUI.navigateUp(Navigation.findNavController(this, R.id.host_fragment), drawerLayout);
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else

            super.onBackPressed();
    }
}
