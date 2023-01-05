package com.sparrow.doctor.Activity;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sparrow.doctor.Model.Appointment1;
import com.sparrow.doctor.Model.DocDetail;
import com.sparrow.doctor.Model.userModel;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public class WebService {
    public  static String baseurl="https://dreamcatcherbykashish.com/doctor/Apis/";
    private static WebServiceInterface webApiInterface;

    public static WebServiceInterface getClient() {
        if (webApiInterface == null) {

            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);

            OkHttpClient okclient = new OkHttpClient.Builder()
                    .addInterceptor(logging)
                   /* .connectTimeout(1, TimeUnit.MINUTES)
                    .readTimeout(30, TimeUnit.SECONDS)
                    .writeTimeout(15, TimeUnit.SECONDS)*/
                    .connectTimeout(60, TimeUnit.SECONDS)
                    .readTimeout(60, TimeUnit.SECONDS)
                    .writeTimeout(60, TimeUnit.SECONDS)
                    .build();

            Gson gson = new GsonBuilder()
                    .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                    .setLenient()
                    .create();

            // Post post = gson.fromJson(reader, Post.class);

            Retrofit client = new Retrofit.Builder()
                    .baseUrl(baseurl)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .client(okclient)
                    .build();

            webApiInterface = client.create(WebServiceInterface.class);
        }
        return webApiInterface;
    }

    public interface WebServiceInterface {
        @POST("login.php")
        @FormUrlEncoded
        Call<userModel> getLoginData(@Field("user_name") String user_name, @Field("password") String password);


        @POST("schedule_post.php")
        @FormUrlEncoded
        Call<userModel> scheduleAppointment(@Field("patient") String patient,@Field("schedule") String schedule, @Field("time") String time,@Field("date") String date);


        @POST("schedule_get.php")
        @FormUrlEncoded
        Call<Appointment1> getScheduleList(@Field("date") String date);


        @POST("patient_name_get.php")
        @FormUrlEncoded
        Call<DocDetail> getDetail(@Field("user_name") String user_name, @Field("password") String password);

        @GET("patient_name.php")
        Call<Appointment1> getPatients();



    }
}



