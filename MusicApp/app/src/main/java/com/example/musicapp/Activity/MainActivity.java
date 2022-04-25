package com.example.musicapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.SearchView;

import com.example.musicapp.Adapter.MainViewPagerAdapter;
import com.example.musicapp.Fragment.FragmentDiscover;
import com.example.musicapp.Fragment.FragmentMusicPlayer;
import com.example.musicapp.Fragment.FragmentPersonal;
import com.example.musicapp.Fragment.FragmentSearch;
import com.example.musicapp.R;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ViewPager2 viewPager;
    TabLayout tabLayout;
    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mapping();
        init();
//        FragmentSearch fragmentSearch = new FragmentSearch();
//        searchView.setOnQueryTextFocusChangeListener(new View.OnFocusChangeListener() {
//            @Override
//            public void onFocusChange(View view, boolean b) {
//                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
//                fragmentTransaction.replace(R.id.mainFragmentContainerView, fragmentSearch);
//                fragmentTransaction.commit();
//            }
//        });
    }

    private void mapping() {
        viewPager = findViewById(R.id.mainViewPager);
        tabLayout = findViewById(R.id.tabLayout);
        searchView = findViewById(R.id.search_view);
    }

    private void init(){
        MainViewPagerAdapter mainViewPagerAdapter = new MainViewPagerAdapter(this);
        mainViewPagerAdapter.addFragment(new FragmentDiscover(), "Khám phá", R.drawable.ic_discover);
        mainViewPagerAdapter.addFragment(new FragmentPersonal(), "Cá nhân", R.drawable.ic_personal);

        viewPager.setUserInputEnabled(false);
        viewPager.setAdapter(mainViewPagerAdapter);

        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> {
            tab.setText(mainViewPagerAdapter.getTitle(position));
            tab.setIcon(mainViewPagerAdapter.getIcon(position));
        }).attach();
    }
}