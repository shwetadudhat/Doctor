package com.sparrow.doctor.Utility;

public class Constants {

    public static final String IS_LOGIN = "isLogin";

    public static final String MobileNumber = "mobilenumber";
    public static final String UserId = "user_id";
    public static final String UserName = "user_name";
    public static final String Password = "password";
    public static final String TOTALGODOWN = "total_godown";
    public static final String UserPersonName = "contact_person_name";
    public static final String UserDetails = "user_detail";
    public static final String Currency = "currency";
    public static final String Fcm = "FCM_KEY";
    public static final String PROFILE_PIC = "pic";
    public static final String SHARED_PREF_USER_TYPE = "user_type";
    public static final String USER_TYPE_CUSTOMER = "customer";
    public static final String USER_TYPE_VENDOR = "vendor";

    public static final String NETWORK_AVAILABLE_ACTION="com.sparrow.mjpay.NetworkAvailable";
    public static final String IS_NETWORK_AVAILABLE ="isNetworkAvailable";

    public static final int RESEND_OTP_TIME_DURATION = 45000;
    public static final int RESEND_OTP_TIME_INTERVAL = 1000;
    public static final int PICKFILE_RESULT_CODE = 1;


    public static final int PANCARD_DOC = 301;
    public static final int AADHAAR_DOC = 302;
    public static final int OTHER_DOC = 303;
    public static final int LICENSE_DOC = 304;

    public enum UpdateMode {
        FLEXIBLE,
        IMMEDIATE
    }

    public static final int UPDATE_ERROR_START_APP_UPDATE_FLEXIBLE = 100;
    public static final int UPDATE_ERROR_START_APP_UPDATE_IMMEDIATE = 101;

}
