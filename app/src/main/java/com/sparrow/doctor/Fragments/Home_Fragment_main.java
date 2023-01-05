package com.sparrow.doctor.Fragments;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.viewpager.widget.ViewPager;

import com.sparrow.doctor.Activity.WebService;
import com.sparrow.doctor.Adapter.DeliveryMainAdapter;
import com.sparrow.doctor.Adapter.SliderAdapter;
import com.sparrow.doctor.Adapter.SliderImage;
import com.sparrow.doctor.Model.Appointment1;
import com.sparrow.doctor.Model.DocDetail;
import com.sparrow.doctor.Utility.CommonFunctions;
import com.sparrow.doctor.Utility.Constants;
import com.sparrow.doctor.Utility.Session_management;
import com.sparrow.doctor.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Home_Fragment_main extends BaseFragment{

    ViewPager viewPager;
    LinearLayout sliderDotspanel,ll_appointment;
    private int dotscount;
    private ImageView[] dots;
    int currentPage = 0;
    Timer timer;
    final long DELAY_MS = 500;//delay in milliseconds before task is to be executed
    final long PERIOD_MS = 3000; // time in milliseconds between successive task executions.

    Session_management session_management;
    String user_id,user_name,pass;

    private List<SliderImage> listItems;
    FrameLayout container_main;

    TextView tv_coin,tv_name1,tv_location,tv_type;

    public Home_Fragment_main() {
        // Required empty public -constructor

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home1, container, false);


        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getPreferenceData();

        listItems = new ArrayList<>() ;
        listItems.add(new SliderImage(R.drawable.slide1,"Slider 1 Title"));
        listItems.add(new SliderImage(R.drawable.slide2,"Slider 2 Title"));
        listItems.add(new SliderImage(R.drawable.slide3,"Slider 3 Title"));

        viewPager =view.findViewById(R.id.viewPager);

        sliderDotspanel = view.findViewById(R.id.SliderDots);
        ll_appointment = view.findViewById(R.id.ll_appointment);
        container_main = view.findViewById(R.id.container_main);

        tv_coin = view.findViewById(R.id.tv_coin);
        tv_name1 = view.findViewById(R.id.tv_name1);
        tv_location = view.findViewById(R.id.tv_location);
        tv_type = view.findViewById(R.id.tv_type);


        SliderAdapter itemsPager_adapter = new SliderAdapter(getContext(), listItems);


        viewPager.setAdapter(itemsPager_adapter);

        dotscount = itemsPager_adapter.getCount();
        dots = new ImageView[dotscount];

        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                viewPager.setCurrentItem((viewPager.getCurrentItem()+1)%dotscount);
            }
        };



        timer = new Timer(); // This will create a new Thread
        timer.schedule(new TimerTask() { // task to be scheduled
            @Override
            public void run() {
                handler.post(Update);
            }
        }, DELAY_MS, PERIOD_MS);

        for(int i = 0; i < dotscount; i++){

            dots[i] = new ImageView(getContext());
            dots[i].setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.non_active_dot));

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

            params.setMargins(8, 0, 8, 0);

            sliderDotspanel.addView(dots[i], params);

        }

        dots[0].setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.active_dot));

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                for(int i = 0; i< dotscount; i++){
                    dots[i].setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.non_active_dot));
                }

                dots[position].setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.active_dot));

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        ll_appointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentManager fragmentManager = getParentFragmentManager();
                Appointments_Fragment fragment = new Appointments_Fragment();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.add(R.id.container_main,fragment);
                fragmentTransaction.commit();

            }
        });

        if ( CommonFunctions.isInternetConnected(getContext())) {
            showDialog("");
            DetailDoc(user_name,pass);
        } else {
            CommonFunctions.showInternetConnectionDialog(getContext());

        }


    }

    private void DetailDoc(String user_name, String pass) {

        WebService.getClient().getDetail(user_name,pass).enqueue(new Callback<DocDetail>() {
            @Override
            public void onResponse(Call<DocDetail> call, Response<DocDetail> response) {
//                loadingDialog.dismissLoadingDialog();
                dismissDialog();
                if (response.isSuccessful()){
                    tv_coin.setText(response.body().getCoins());
                    tv_name1.setText(response.body().getFull_name_en());
                    tv_location.setText(response.body().getSpecialty());
                    tv_type.setText(response.body().getFacility_name());
                }


            }

            @Override
            public void onFailure(Call<DocDetail> call, Throwable t) {
                dismissDialog();
//                loadingDialog.dismissLoadingDialog();
            }
        });

    }

    private void getPreferenceData() {
        session_management=new Session_management(getContext());
        user_id = session_management.getUserDetails().get(Constants.UserId);
        user_name = session_management.getUserDetails().get(Constants.UserName);
        pass = session_management.getUserDetails().get(Constants.Password);
    }
}
