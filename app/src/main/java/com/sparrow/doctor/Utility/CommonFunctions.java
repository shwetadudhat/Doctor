package com.sparrow.doctor.Utility;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.sparrow.doctor.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CommonFunctions {

    public static RequestOptions getGlideRequestOptions(int placeholder) {
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(placeholder);
        requestOptions.error(placeholder);
        return requestOptions;
    }

    public static void loadGlideImage(Context context, String imageName, String imageURL, ImageView imageView){
        if (imageName != null && !TextUtils.isEmpty(imageName)) {
            Glide.with(context).load(imageURL)
//                    .apply(getGlideRequestOptions(R.drawable.not_found))
                    .listener(new RequestListener<Drawable>() {
                        @Override
                        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
//                            holder.featuredImage.setVisibility(View.GONE);
//                            imageView.setImageDrawable(context.getResources().getDrawable(R.drawable.not_found));
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                            return false;
                        }

                    })
                    .into(imageView);
        }else {
            imageView.setImageResource(R.mipmap.ic_launcher);
        }
    }

    public static String getDateConvert(String start_date, String pattern1/*"yyyy-MM-dd"*/, String pattern2/*"EEE dd, MMM yyyy"*/) throws ParseException {
        /*"yyyy-MM-dd'T'HH:mm:ss.SSSXXX"*/
        SimpleDateFormat format1 = new SimpleDateFormat(pattern1);
        SimpleDateFormat format2 = new SimpleDateFormat(pattern2);
        Date date = format1.parse(start_date);
        System.out.println(format2.format(date));
        Log.e("dateeee",format2.format(date));
        String formattedDate = format2.format(date);

        return  formattedDate;

    }


    public static void setTypeFaceBold(Context context,TextView txt){
        Typeface face = Typeface.createFromAsset(context.getAssets(), "fonts/ubuntu_bold.ttf");
        txt.setTypeface(face);
    }

    public static void setTypeFaceRegular(Context context,TextView txt){
        Typeface face = Typeface.createFromAsset(context.getAssets(), "fonts/ubuntu_regular.ttf");
        txt.setTypeface(face);
    }

    public void setTextViewDrawableColor(TextView textView, int color) {

        for (Drawable drawable : textView.getCompoundDrawables()) {
            if (drawable != null) {
                textView.setTextColor(color);
                drawable.setColorFilter(new PorterDuffColorFilter(ContextCompat.getColor(textView.getContext(), color), PorterDuff.Mode.SRC_IN));
            }else{
                Log.e("drawable","drawable not found");
            }
        }
    }

    public static int getDeviceWidth(Context context) {
        try {
            DisplayMetrics metrics = context.getResources().getDisplayMetrics();
            return metrics.widthPixels;
        } catch (Exception e) {
            sendExceptionReport(e);
        }

        return 480;
    }

    public static int getDeviceHeight(Context context) {
        try {
            DisplayMetrics metrics = context.getResources().getDisplayMetrics();
            return metrics.heightPixels;
        } catch (Exception e) {
            sendExceptionReport(e);
        }

        return 800;
    }

    public static void sendExceptionReport(Exception e) {
        e.printStackTrace();

        try {
            // Writer result = new StringWriter();
            // PrintWriter printWriter = new PrintWriter(result);
            // e.printStackTrace(printWriter);
            // String stacktrace = result.toString();
            // new CustomExceptionHandler(c, URLs.URL_STACKTRACE)
            // .sendToServer(stacktrace);
        } catch (Exception e1) {
            e1.printStackTrace();
        }

    }


    //region FOR SHOW INTERNET CONNECTION DIALOG
    public static void showInternetConnectionDialog(Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context)
                .setTitle(context.getResources().getString(R.string.no_internet_connection_with_emoji))
                .setMessage(context.getResources().getString(R.string.connection_not_available))
                .setPositiveButton(context.getResources().getString(R.string.btn_enable), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                        try {
                            Intent intent = new Intent(Settings.ACTION_WIFI_SETTINGS);
                            context.startActivity(intent);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                })
                .setNegativeButton(context.getResources().getString(R.string.btn_cancel), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();

    }


    public static boolean isInternetConnected(Context mContext) {
        boolean outcome = false;

        try {
            if (mContext != null) {
                ConnectivityManager cm = (ConnectivityManager) mContext
                        .getSystemService(Context.CONNECTIVITY_SERVICE);

                NetworkInfo[] networkInfos = cm.getAllNetworkInfo();

                for (NetworkInfo tempNetworkInfo : networkInfos) {
                    if (tempNetworkInfo.isConnected()) {
                        outcome = true;
                        break;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return outcome;
    }

    public static void hideKeyBoard(Context c, View v) {
        InputMethodManager imm = (InputMethodManager) c
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
    }


    public static void showKeyBoard(Context c, View v) {
        v.requestFocus();
        v.setFocusableInTouchMode(true);

        InputMethodManager imm = (InputMethodManager) c.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(v, InputMethodManager.SHOW_FORCED);
    }


    public void setTextViewDrawableColor1(Context context,TextView textView, int drawableRes, int color) {

        textView.setCompoundDrawablesWithIntrinsicBounds(
                null,  // Left
                tintDrawable(ContextCompat.getDrawable(context, drawableRes),
                        ContextCompat.getColor(context, color)), // Top
                null, // Right
                null); //Bottom
    }

    public static Drawable tintDrawable(Drawable drawable, int tint) {
        drawable = DrawableCompat.wrap(drawable);
        DrawableCompat.setTint(drawable, tint);
        DrawableCompat.setTintMode(drawable, PorterDuff.Mode.SRC_ATOP);

        return drawable;
    }
}
