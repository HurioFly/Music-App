package com.example.musicapp.Fragment;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.example.musicapp.Adapter.BannerAdapter;
import com.example.musicapp.Model.Advertisement;
import com.example.musicapp.R;
import com.example.musicapp.Service.APIService;
import com.example.musicapp.Service.DataService;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import me.relex.circleindicator.CircleIndicator3;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentBanner extends Fragment {
    View view;
    ViewPager2 viewPager;
    CircleIndicator3 circleIndicator;
    BannerAdapter bannerAdapter;
    Handler handler;
    Runnable runnable;
    int currentItem;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_banner, container, false);
        mapping();
        GetDaTa();
        return view;
    }

    private void mapping() {
        viewPager = view.findViewById(R.id.viewPagerBanner);
        circleIndicator = view.findViewById(R.id.circleIndicator);
    }

    private void GetDaTa() {
        DataService dataService = APIService.getService();
        Call<List<Advertisement>> callback = dataService.GetAdvertisement();
        callback.enqueue(new Callback<List<Advertisement>>() {
            @Override
            public void onResponse(@NonNull Call<List<Advertisement>> call, @NonNull Response<List<Advertisement>> response) {
                ArrayList<Advertisement> banners = (ArrayList<Advertisement>) response.body();
                bannerAdapter = new BannerAdapter(getActivity(),banners);
                viewPager.setAdapter(bannerAdapter);
                circleIndicator.setViewPager(viewPager);
                handler = new Handler();
                runnable = () -> {
                    currentItem = viewPager.getCurrentItem();
                    currentItem++;
                    if(currentItem >= Objects.requireNonNull(viewPager.getAdapter()).getItemCount()){
                        currentItem = 0;
                    }
                    viewPager.setCurrentItem(currentItem, true);
                    handler.postDelayed(runnable, 3000);
                };
                handler.postDelayed(runnable, 3000);
            }

            @Override
            public void onFailure(@NonNull Call<List<Advertisement>> call, @NonNull Throwable t) {

            }
        });
    }
}
