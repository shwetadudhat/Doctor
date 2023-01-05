package com.sparrow.doctor.Fragments;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.sparrow.doctor.Adapter.AutoCmpleteAdapter;
import com.sparrow.doctor.Adapter.ProcedureAdapter;
import com.sparrow.doctor.Model.Appointment1;
import com.sparrow.doctor.Model.City;
import com.sparrow.doctor.Model.Detail;
import com.sparrow.doctor.Model.userModel;
import com.sparrow.doctor.Utility.LoadingDialog;
import com.sparrow.doctor.Activity.WebService;
import com.sparrow.doctor.R;
import com.weiwangcn.betterspinner.library.BetterSpinner;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SceduleApponitment_Fragment extends BaseFragment{

    SearchView searchView;
    Spinner spinner_procedure;
    TextView tv_year,tv_month,btn_schedule;
    ImageView iv_drop,iv_back;
    BetterSpinner spinner_procedure1;
    RelativeLayout ll_searchView;

    String patientname,patientid;

    Button tv_10,tv_11,tv_12,tv_1,tv_2,tv_3;
    ArrayList<Detail> details;

    Calendar cal;
    CalendarView calendar1;

    String Date,time1, _pickedDate1;
    AutoCompleteTextView AutosearchView;

    LoadingDialog loadingDialog;
    int index;
    String selestedItem;

    String ProcedureOpt;

    String[] Procedure_Opt =new String[] {"Select a procedure", "Procedure 1", "Procedure 2","Procedure 3",
    "Procedure 4","Procedure 5","Procedure 6","Procedure 7","Procedure 8"};

    public SceduleApponitment_Fragment() {
        // Required empty public -constructor

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_shedule_appointments, container, false);




        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        searchView=view.findViewById(R.id.searchView);
        spinner_procedure=view.findViewById(R.id.spinner_procedure);
        spinner_procedure1=view.findViewById(R.id.spinner_procedure1);
        tv_year=view.findViewById(R.id.tv_year);
        tv_month=view.findViewById(R.id.tv_month);
        iv_drop=view.findViewById(R.id.iv_drop);
        tv_10=view.findViewById(R.id.tv_10);
        tv_11=view.findViewById(R.id.tv_11);
        tv_12=view.findViewById(R.id.tv_12);
        tv_1=view.findViewById(R.id.tv_1);
        tv_2=view.findViewById(R.id.tv_2);
        tv_3=view.findViewById(R.id.tv_3);
        iv_back=view.findViewById(R.id.iv_back);
        btn_schedule=view.findViewById(R.id.btn_schedule);
        calendar1 =view.findViewById(R.id.calendar );
        ll_searchView =view.findViewById(R.id.ll_searchView );
        AutosearchView =view.findViewById(R.id.AutosearchView );

        details=new ArrayList<>();
        details.add(new Detail("1","Procedure 1"));
        details.add(new Detail("2","Procedure 2"));
        details.add(new Detail("3","Procedure 3"));
        details.add(new Detail("4","Procedure 4"));
        details.add(new Detail("5","Procedure 5"));
        details.add(new Detail("6","Procedure 6"));
        details.add(new Detail("7","Procedure 7"));
        details.add(new Detail("8","Procedure 8"));

        getVillageList( );


      /*  ArrayAdapter adapterBankPassBook = new ArrayAdapter(getContext(),
                android.R.layout.simple_list_item_1, Procedure_Opt);
        spinner_procedure1.setAdapter(adapterBankPassBook);*/

        ProcedureAdapter arrayAdapter = new ProcedureAdapter(getContext(),R.layout.design_product,Procedure_Opt);
        spinner_procedure1.setAdapter(arrayAdapter);

        spinner_procedure1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                index=i;
                Object item = adapterView.getItemAtPosition(+i);
                selestedItem= String.valueOf(item);
                Log.e("selestedItem",selestedItem);
                if (String.valueOf(i).equals("Select a procedure")){
                    selestedItem="";
                }else{
                    selestedItem=String.valueOf(i);
                }

                // Toast.makeText(getBaseContext(), sort_type[index], Toast.LENGTH_SHORT).show();
            }
        });


        tv_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                time1="01:00 PM";
                tv_1.setBackground(getResources().getDrawable(R.drawable.button_pressed));
                tv_2.setBackground(getResources().getDrawable(R.drawable.button_time));
                tv_3.setBackground(getResources().getDrawable(R.drawable.button_time));
                tv_10.setBackground(getResources().getDrawable(R.drawable.button_time));
                tv_11.setBackground(getResources().getDrawable(R.drawable.button_time));
                tv_12.setBackground(getResources().getDrawable(R.drawable.button_time));
            }
        });

        tv_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                time1="02:00 PM";
                tv_1.setBackground(getResources().getDrawable(R.drawable.button_time));
                tv_2.setBackground(getResources().getDrawable(R.drawable.button_pressed));
                tv_3.setBackground(getResources().getDrawable(R.drawable.button_time));
                tv_10.setBackground(getResources().getDrawable(R.drawable.button_time));
                tv_11.setBackground(getResources().getDrawable(R.drawable.button_time));
                tv_12.setBackground(getResources().getDrawable(R.drawable.button_time));
            }
        });


        tv_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                time1="03:00 PM";
                tv_1.setBackground(getResources().getDrawable(R.drawable.button_time));
                tv_2.setBackground(getResources().getDrawable(R.drawable.button_time));
                tv_3.setBackground(getResources().getDrawable(R.drawable.button_pressed));
                tv_10.setBackground(getResources().getDrawable(R.drawable.button_time));
                tv_11.setBackground(getResources().getDrawable(R.drawable.button_time));
                tv_12.setBackground(getResources().getDrawable(R.drawable.button_time));
            }
        });

        tv_10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                time1="10:00 AM";
                tv_1.setBackground(getResources().getDrawable(R.drawable.button_time));
                tv_2.setBackground(getResources().getDrawable(R.drawable.button_time));
                tv_3.setBackground(getResources().getDrawable(R.drawable.button_time));
                tv_10.setBackground(getResources().getDrawable(R.drawable.button_pressed));
                tv_11.setBackground(getResources().getDrawable(R.drawable.button_time));
                tv_12.setBackground(getResources().getDrawable(R.drawable.button_time));
            }
        });

        tv_11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                time1="11:00 AM";
                tv_1.setBackground(getResources().getDrawable(R.drawable.button_time));
                tv_2.setBackground(getResources().getDrawable(R.drawable.button_time));
                tv_3.setBackground(getResources().getDrawable(R.drawable.button_time));
                tv_10.setBackground(getResources().getDrawable(R.drawable.button_time));
                tv_11.setBackground(getResources().getDrawable(R.drawable.button_pressed));
                tv_12.setBackground(getResources().getDrawable(R.drawable.button_time));
            }
        });

        tv_12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                time1="12:00 AM";
                tv_1.setBackground(getResources().getDrawable(R.drawable.button_time));
                tv_2.setBackground(getResources().getDrawable(R.drawable.button_time));
                tv_3.setBackground(getResources().getDrawable(R.drawable.button_time));
                tv_10.setBackground(getResources().getDrawable(R.drawable.button_time));
                tv_11.setBackground(getResources().getDrawable(R.drawable.button_time));
                tv_12.setBackground(getResources().getDrawable(R.drawable.button_pressed));
            }
        });


        cal = Calendar.getInstance();

        DateFormat monthFormat = new SimpleDateFormat("MMMM");
        Date date = new Date();
        Log.d("Month",monthFormat.format(date));
        tv_month.setText(monthFormat.format(date));

        DateFormat yearFormat = new SimpleDateFormat("yyyy");
        Log.d("yearFormat",yearFormat.format(date));
        tv_year.setText(yearFormat.format(date));



        // Add Listener in calendar
        calendar1
                .setOnDateChangeListener(
                        new CalendarView
                                .OnDateChangeListener() {
                            @Override

                            // In this Listener have one method
                            // and in this method we will
                            // get the value of DAYS, MONTH, YEARS
                            public void onSelectedDayChange(
                                    @NonNull CalendarView view,
                                    int year,
                                    int month,
                                    int dayOfMonth)
                            {

                                String _year = String.valueOf(year);
                                String _month = (month + 1) < 10 ? "0" + (month + 1) : String.valueOf(month + 1);
                                String _date = dayOfMonth < 10 ? "0" + dayOfMonth : String.valueOf(dayOfMonth);
                                String _pickedDate =  _date+ "-" + _month +"-"+year ;
                                _pickedDate1 =  year+ "-" + _month +"-"+ _date;
                                Log.e("PickedDate: ", "Date: " + _pickedDate); //2019-02-12


                                // set this date in TextView for Display
//                                tv_date.setText(date);
//                                tv_month.setText(month);
//                                tv_year.setText(year);

                                Toast.makeText(getContext(), "MMDDYYYY Min date - " + Date , Toast.LENGTH_LONG).show();


                            }
                        });


       /* calendar1.setCustomizationRule(new Procedure<CalendarCell>() {
            @Override
            public void apply(CalendarCell argument) {
                if (argument instanceof CalendarDayCell) {
                    calendar1.setTimeInMillis(argument.getDate());
                    ((CalendarDayCell) argument).setSelectable(
                            !(calendar1.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY ||
                                    calendar1.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY));
                }
            }
        });*/

        //Disable all SUNDAYS and SATURDAYS between Min and Max Dates

          /*  int dayOfWeek = loopdate.get(Calendar.DAY_OF_WEEK);
            if (dayOfWeek == Calendar.SUNDAY || dayOfWeek == Calendar.SATURDAY) {
                Calendar[] disabledDays =  new Calendar[1];
                disabledDays[0] = loopdate;
                datePickerDialog.setDisabledDays(disabledDays);
            }*/




        btn_schedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                   WebService.getClient().scheduleAppointment(AutosearchView.getText().toString(),selestedItem,time1,_pickedDate1
                           ).enqueue(new Callback<userModel>() {
                        @Override
                        public void onResponse(Call<userModel> call, Response<userModel> response) {

                            Toast.makeText(getContext(), response.body().getMsg(), Toast.LENGTH_SHORT).show();
                            if (response.body().getSuccess().equals(true)){
                                Dialog dialog = new Dialog(getContext());
                                dialog.setContentView(R.layout.dialog_schedule_appointment);
                                dialog.getWindow().setBackgroundDrawableResource(R.color.fully_transparent);
                                ImageView cutBtn = (ImageView) dialog.findViewById(R.id.bidCutBtn);
                                TextView title = dialog.findViewById(R.id.title);

                                title.setText(response.body().getMsg());

                                cutBtn.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        dialog.dismiss();
                                    }
                                });

                                dialog.show();
                            }
                        }

                        @Override
                        public void onFailure(Call<userModel> call, Throwable t) {
                            Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();


                        }
                    });


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
            }
        });

    }

    private void getVillageList() {
        WebService.getClient().getPatients().enqueue(new Callback<Appointment1>() {
            @Override
            public void onResponse(Call<Appointment1> call, Response<Appointment1> response) {

                if (response.isSuccessful()) {
                    if (response.body() != null) {

                        Log.d("DealstageActivity", "onResponse--villagelist: ---checkingggg");

                        AutoCmpleteAdapter adapter = new AutoCmpleteAdapter((Activity) getContext(), (ArrayList<City>) response.body().getCity());
                        AutosearchView.setAdapter(adapter);


                        Log.e("autotext12", AutosearchView.getText().toString());


                        AutosearchView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                                Object item = adapterView.getItemAtPosition(i);
                                if (item instanceof City) {
                                    City city = (City) item;
                                    AutosearchView.setText(city.getPatientName());

                                    patientname = city.getPatientName();
                                    Log.e("autotext", AutosearchView.getText().toString());
                                    Log.d("patientname", patientname);

                                    patientid = city.getId();

//                                    setCountMethod(VillageId);
                                    adapter.notifyDataSetChanged();

                                }

                            }
                        });

                    }
                }
            }

            @Override
            public void onFailure(Call<Appointment1> call, Throwable t) {
                Toast.makeText(getContext(), "Something went wrong... Please try again after sometime! \n" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }


}
