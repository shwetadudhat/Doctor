package com.sparrow.doctor.Fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sparrow.doctor.Adapter.DeliveryMainAdapter;
import com.sparrow.doctor.HorizontalCalanderView.HorizontalCalendar;
import com.sparrow.doctor.HorizontalCalanderView.utils.HorizontalCalendarListener;
import com.sparrow.doctor.Model.Appointment;
import com.sparrow.doctor.Model.Appointment1;
import com.sparrow.doctor.Utility.CommonFunctions;
import com.sparrow.doctor.Utility.LoadingDialog;
import com.sparrow.doctor.Activity.WebService;
import com.sparrow.doctor.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Appointments_Fragment extends BaseFragment{
    private HorizontalCalendar horizontalCalendar;
    String todaydate;

    RecyclerView rv_list;

    LoadingDialog loadingDialog;

    List<Appointment>  appointmentList;

    DeliveryMainAdapter deliveryMainAdapter;

    LinearLayout ll_addapoin;
    ImageView iv_back,iv_prev,iv_next;
    TextView tv_month,tv_curdate;

    String a;
    Calendar startDate,endDate, defaultDate,curmonth,endMonthText;

    public Appointments_Fragment() {
        // Required empty public -constructor

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_appointments, container, false);




        return view;
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ll_addapoin=view.findViewById(R.id.ll_addapoin);
        rv_list=view.findViewById(R.id.rv_list);
        iv_back=view.findViewById(R.id.iv_back);
        iv_prev=view.findViewById(R.id.iv_prev);
        iv_next=view.findViewById(R.id.iv_next);
        tv_month=view.findViewById(R.id.tv_month);
        tv_curdate=view.findViewById(R.id.tv_curdate);


        appointmentList=new ArrayList<>();
        appointmentList.add(new Appointment("1","Test1","Procedure1","10:00PM","11:00PM"));
        appointmentList.add(new Appointment("2","Test1","Procedure6","01:00PM","02:00PM"));





     /*   Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);
        try {
            cal.setTime(sdf.parse(CommonFunctions.getDateConvert(start_date,"yyyy-MM-dd","EEE MMM dd HH:mm:ss z yyyy")));// all done
        } catch (ParseException e) {
            e.printStackTrace();
        }*/



        endDate = Calendar.getInstance(); // End date
//        endDate.add(Calendar.DAY_OF_MONTH, 7);
        endDate.add(Calendar.MONTH, 1);

         startDate = Calendar.getInstance(); // Start date
        todaydate= String.valueOf(DateFormat.format("yyyy-MM-dd",startDate));
//        startDate.add(Calendar.DAY_OF_MONTH, -7);
        startDate.add(Calendar.MONTH, -1);

        Log.d("todaydate",todaydate);
        Log.d("endDate",String.valueOf(DateFormat.format("yyyy-MM-dd",endDate)));


        defaultDate = Calendar.getInstance();
        curmonth = Calendar.getInstance();
        endMonthText = Calendar.getInstance();
        Log.i("Default Date", DateFormat.format("EEE, MMMM d, yyyy", defaultDate).toString());
        Log.i("Default Date1", DateFormat.format(" MMMM,dd", defaultDate).toString());

        tv_curdate.setText(DateFormat.format(" MMMM,dd", defaultDate).toString());
        tv_month.setText(DateFormat.format(" MMMM", defaultDate).toString());

        iv_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                curmonth.add(Calendar.MONTH, 1);
                endMonthText.add(Calendar.MONTH+1, -1);

                Log.d("curdate12",String.valueOf(curmonth)+"\n"+endMonthText);
                tv_month.setText(DateFormat.format("MMMM", curmonth).toString());
             /*   HorizontalSnapHelper snapHelper = new HorizontalSnapHelper();
                snapHelper.attachToHorizontalCalendar(null);*/

//                setHorizontalCalender1( startDate, endDate);

            }
        });

        iv_prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                curmonth.add(Calendar.MONTH, -1);
                endMonthText.add(Calendar.MONTH+1, -1);
                Log.d("curdate111",String.valueOf(curmonth)+"\n"+endMonthText);
                tv_month.setText(DateFormat.format("MMMM", curmonth).toString());
               /* HorizontalSnapHelper snapHelper = new HorizontalSnapHelper();
                snapHelper.attachToHorizontalCalendar(null);*/
//                recyclerView.setOnFlingListener(null);
//                setHorizontalCalender1(startDate, endDate);

//                setHorizontalCalender( startDate, endDate,defaultDate);


            }
        });

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = null,date2 = null,curdate=null;
        try {
            curdate = simpleDateFormat.parse(todaydate);

            Log.d("curdate",String.valueOf(curdate));


        } catch (ParseException e) {
            e.printStackTrace();
        }


        if ( CommonFunctions.isInternetConnected(getContext())) {
            showDialog("");
            Schedule(todaydate);
        } else {
            CommonFunctions.showInternetConnectionDialog(getContext());

        }

        setHorizontalCalender( startDate, endDate,defaultDate);





        ll_addapoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getParentFragmentManager();
                SceduleApponitment_Fragment fragment = new SceduleApponitment_Fragment();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.add(R.id.container_main,fragment);
                fragmentTransaction.commit();
            }
        });

        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getParentFragmentManager();
                Home_Fragment_main fragment = new Home_Fragment_main();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.add(R.id.container_main,fragment);
                fragmentTransaction.commit();
                if (horizontalCalendar != null) {
                    horizontalCalendar.setCalendarListener(null);
                }
            }
        });

    }

    private void setHorizontalCalender1(Calendar startDate, Calendar endDate) {

        if (horizontalCalendar != null) {
            horizontalCalendar.setCalendarListener(null);
        }
        horizontalCalendar = new HorizontalCalendar.Builder(getActivity(), R.id.calendarView)
                .range(startDate, endDate)
                .datesNumberOnScreen(5)
                .configure()
                .formatTopText("MMM")
                .formatMiddleText("dd")
                .formatBottomText("EEE")
                .textSize(11f, 20f, 12f)
                .showTopText(true)
                .showBottomText(true)
                .selectedDateBackground(getResources().getDrawable(R.drawable.active_date))
                .end()
                /*.addEvents(new CalendarEventsPredicate() {

                    Random rnd = new Random();
                    @Override
                    public List<CalendarEvent> events(Calendar date) {
                        List<CalendarEvent> events = new ArrayList<>();
                        int count = rnd.nextInt(6);

                        for (int i = 0; i <= count; i++){
                            events.add(new CalendarEvent(Color.rgb(rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256)), "event"));
                        }

                        return events;
                    }
                })*/
                .build();

        horizontalCalendar.setCalendarListener(new HorizontalCalendarListener() {
            @Override
            public void onDateSelected(Calendar date, int position) {

                a = String.valueOf(DateFormat.format("yyyy-MM-dd", date));
                Log.d("active_date",a);

                Schedule(a);
            }

        });


        horizontalCalendar.getDefaultStyle()
                .setBackground(getResources().getDrawable(R.drawable.deactive_date))
                .setColorTopText(getResources().getColor(R.color.small_title_clr))
                .setColorMiddleText(getResources().getColor(R.color.colorPrimary))
                .setColorBottomText(getResources().getColor(R.color.date));

        horizontalCalendar.getSelectedItemStyle()
                .setBackground(getResources().getDrawable(R.drawable.active_date))
                .setColorTopText(getResources().getColor(R.color.white))
                .setColorMiddleText(getResources().getColor(R.color.white))
                .setColorBottomText(getResources().getColor(R.color.white));
    }

    private void setHorizontalCalender(Calendar cal_start, Calendar cal_end, Calendar defaultDate) {

        if (horizontalCalendar != null) {
            horizontalCalendar.setCalendarListener(null);
        }
        horizontalCalendar = new HorizontalCalendar.Builder(getActivity(), R.id.calendarView)
                .range(cal_start, cal_end)
                .datesNumberOnScreen(5)
                .configure()
                .formatTopText("MMM")
                .formatMiddleText("dd")
                .formatBottomText("EEE")
                .textSize(11f, 20f, 12f)
                .showTopText(true)
                .showBottomText(true)
                .selectedDateBackground(getResources().getDrawable(R.drawable.active_date))
                .end()
                .defaultSelectedDate(defaultDate)
                /*.addEvents(new CalendarEventsPredicate() {

                    Random rnd = new Random();
                    @Override
                    public List<CalendarEvent> events(Calendar date) {
                        List<CalendarEvent> events = new ArrayList<>();
                        int count = rnd.nextInt(6);

                        for (int i = 0; i <= count; i++){
                            events.add(new CalendarEvent(Color.rgb(rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256)), "event"));
                        }

                        return events;
                    }
                })*/
                .build();

        horizontalCalendar.setCalendarListener(new HorizontalCalendarListener() {
            @Override
            public void onDateSelected(Calendar date, int position) {

                a = String.valueOf(DateFormat.format("yyyy-MM-dd", date));
                Log.d("active_date",a);

                Schedule(a);
            }

        });


        horizontalCalendar.getDefaultStyle()
                .setBackground(getResources().getDrawable(R.drawable.deactive_date))
                .setColorTopText(getResources().getColor(R.color.small_title_clr))
                .setColorMiddleText(getResources().getColor(R.color.colorPrimary))
                .setColorBottomText(getResources().getColor(R.color.date));

        horizontalCalendar.getSelectedItemStyle()
                .setBackground(getResources().getDrawable(R.drawable.active_date))
                .setColorTopText(getResources().getColor(R.color.white))
                .setColorMiddleText(getResources().getColor(R.color.white))
                .setColorBottomText(getResources().getColor(R.color.white));

    }

    private void Schedule(String a) {

        WebService.getClient().getScheduleList(a).enqueue(new Callback<Appointment1>() {
            @Override
            public void onResponse(Call<Appointment1> call, Response<Appointment1> response) {
//                loadingDialog.dismissLoadingDialog();
                dismissDialog();
                Log.d("response",response.body().toString());
                deliveryMainAdapter=new DeliveryMainAdapter(getContext(),response.body().getCity());
                rv_list.setLayoutManager(new LinearLayoutManager(getContext()));
                rv_list.setAdapter(deliveryMainAdapter);

            }

            @Override
            public void onFailure(Call<Appointment1> call, Throwable t) {
                dismissDialog();
//                loadingDialog.dismissLoadingDialog();
            }
        });

    }
}
