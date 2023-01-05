package com.sparrow.doctor.Utility;

import android.app.Application;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by Rajesh Dabhi on 22/6/2017.
 */

public class AppController extends Application {

    public static final String TAG = AppController.class.getSimpleName();
    public static final int MY_SOCKET_TIMEOUT_MS = 5000;



    private static AppController mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;

        List<Locale> locales = new ArrayList<>();
        locales.add(Locale.ENGLISH);
        locales.add(new Locale("ar", "ARABIC"));
//        LocaleHelper.setLocale(getApplicationContext(),"ar");

    }

    public static synchronized AppController getInstance() {
        return mInstance;
    }


    public void setConnectivityListener(ConnectivityReceiver.ConnectivityReceiverListener listener) {
        ConnectivityReceiver.connectivityReceiverListener = listener;
    }

}
