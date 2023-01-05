package com.sparrow.doctor.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sparrow.doctor.Fragments.Appointments_Fragment;
import com.sparrow.doctor.Fragments.Home_Fragment1;
import com.sparrow.doctor.Fragments.Profile_Fragment;
import com.sparrow.doctor.Fragments.SceduleApponitment_Fragment;
import com.sparrow.doctor.Utility.Constants;
import com.sparrow.doctor.Utility.Session_management;
import com.sparrow.doctor.R;

public class MainActivity extends AppCompatActivity {


    ImageView iv_home,iv_notification,iv_msg,iv_profile;
    TextView tv_home,tv_notification,tv_msg,tv_profile;
    View v1,v2,v3,v4;

    Session_management session_management;
    String user_id,user_name,pass;

    LinearLayout ll_nootification,ll_home,ll_message,ll_profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getPreferenceData();


        InitiateView();

        Home_Fragment1 fragmenthome = new Home_Fragment1();
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.flFragment, fragmenthome);
        transaction.commit();
        iv_home.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorPrimary)));

        tv_home.setTextColor(getResources().getColor(R.color.colorPrimary));

        ll_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ll_home.setEnabled(false);
                ll_message.setEnabled(true);
                ll_nootification.setEnabled(true);
                ll_profile.setEnabled(true);

                iv_home.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorPrimary)));
                iv_msg.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.login_txt)));
                iv_notification.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.login_txt)));
                iv_profile.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.login_txt)));


                v1.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                v2.setBackgroundColor(getResources().getColor(R.color.bottomViewColor));
                v3.setBackgroundColor(getResources().getColor(R.color.bottomViewColor));
                v4.setBackgroundColor(getResources().getColor(R.color.bottomViewColor));

                tv_home.setTextColor(getResources().getColor(R.color.colorPrimary));
                tv_msg.setTextColor(getResources().getColor(R.color.login_txt));
                tv_notification.setTextColor(getResources().getColor(R.color.login_txt));
                tv_profile.setTextColor(getResources().getColor(R.color.login_txt));

                Home_Fragment1 home_fragment=new Home_Fragment1();
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.flFragment, home_fragment);
                transaction.commit();

            }
        });

        ll_message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ll_home.setEnabled(true);
                ll_message.setEnabled(false);
                ll_nootification.setEnabled(true);
                ll_profile.setEnabled(true);

                iv_home.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.login_txt)));
                iv_msg.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorPrimary)));
                iv_notification.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.login_txt)));
                iv_profile.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.login_txt)));


                v1.setBackgroundColor(getResources().getColor(R.color.bottomViewColor));
                v2.setBackgroundColor(getResources().getColor(R.color.bottomViewColor));
                v3.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                v4.setBackgroundColor(getResources().getColor(R.color.bottomViewColor));

                tv_home.setTextColor(getResources().getColor(R.color.login_txt));
                tv_msg.setTextColor(getResources().getColor(R.color.colorPrimary));
                tv_notification.setTextColor(getResources().getColor(R.color.login_txt));
                tv_profile.setTextColor(getResources().getColor(R.color.login_txt));

             /*   Message_Fragment message_fragment=new Message_Fragment();
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.flFragment, message_fragment);
                transaction.commit();*/

                SceduleApponitment_Fragment message_fragment=new SceduleApponitment_Fragment();
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.flFragment, message_fragment);
                transaction.commit();

            }
        });

        ll_nootification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ll_home.setEnabled(true);
                ll_message.setEnabled(true);
                ll_nootification.setEnabled(false);
                ll_profile.setEnabled(true);

                iv_home.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.login_txt)));
                iv_msg.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.login_txt)));
                iv_notification.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorPrimary)));
                iv_profile.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.login_txt)));


                v1.setBackgroundColor(getResources().getColor(R.color.bottomViewColor));
                v2.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                v3.setBackgroundColor(getResources().getColor(R.color.bottomViewColor));
                v4.setBackgroundColor(getResources().getColor(R.color.bottomViewColor));

                tv_home.setTextColor(getResources().getColor(R.color.login_txt));
                tv_msg.setTextColor(getResources().getColor(R.color.login_txt));
                tv_notification.setTextColor(getResources().getColor(R.color.colorPrimary));
                tv_profile.setTextColor(getResources().getColor(R.color.login_txt));

              /*  Notification_Fragment notification_fragment=new Notification_Fragment();
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.flFragment, notification_fragment);
                transaction.commit();*/

                Appointments_Fragment notification_fragment=new Appointments_Fragment();
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.flFragment, notification_fragment);
                transaction.commit();

            }
        });

        ll_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ll_home.setEnabled(true);
                ll_message.setEnabled(true);
                ll_nootification.setEnabled(true);
                ll_profile.setEnabled(false);

                iv_home.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.login_txt)));
                iv_msg.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.login_txt)));
                iv_notification.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.login_txt)));
                iv_profile.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorPrimary)));

                v1.setBackgroundColor(getResources().getColor(R.color.bottomViewColor));
                v2.setBackgroundColor(getResources().getColor(R.color.bottomViewColor));
                v3.setBackgroundColor(getResources().getColor(R.color.bottomViewColor));
                v4.setBackgroundColor(getResources().getColor(R.color.colorPrimary));

                tv_home.setTextColor(getResources().getColor(R.color.login_txt));
                tv_msg.setTextColor(getResources().getColor(R.color.login_txt));
                tv_notification.setTextColor(getResources().getColor(R.color.login_txt));
                tv_profile.setTextColor(getResources().getColor(R.color.colorPrimary));

                Profile_Fragment profile_fragment=new Profile_Fragment();
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.flFragment, profile_fragment);
                transaction.commit();

            }
        });
    }

    private void InitiateView() {

        iv_home=findViewById(R.id.iv_home);
        iv_notification=findViewById(R.id.iv_notification);
        iv_msg=findViewById(R.id.iv_msg);
        iv_profile=findViewById(R.id.iv_profile);


        tv_home=findViewById(R.id.tv_home);
        tv_notification=findViewById(R.id.tv_notification);
        tv_msg=findViewById(R.id.tv_msg);
        tv_profile=findViewById(R.id.tv_profile);

        v1=findViewById(R.id.v1);
        v2=findViewById(R.id.v2);
        v3=findViewById(R.id.v3);
        v4=findViewById(R.id.v4);

        ll_home=findViewById(R.id.ll_home);
        ll_nootification=findViewById(R.id.ll_nootification);
        ll_message=findViewById(R.id.ll_message);
        ll_profile=findViewById(R.id.ll_profile);


    }

    private void getPreferenceData() {
        session_management=new Session_management(getApplicationContext());
        user_id = session_management.getUserDetails().get(Constants.UserId);
        user_name = session_management.getUserDetails().get(Constants.UserName);
        pass = session_management.getUserDetails().get(Constants.Password);

    }
}