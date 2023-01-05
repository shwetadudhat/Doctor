package com.sparrow.doctor.Utility;

import android.app.Dialog;
import android.content.Context;

import com.sparrow.doctor.R;


public class LoadingDialog {

    private Dialog dialog;

    public LoadingDialog(Context context) {
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_loading);
        dialog.getWindow().setBackgroundDrawableResource(R.color.fully_transparent);
    }

    public void showLoadingDialog() {
        dialog.show();
    }

    public void dismissLoadingDialog() {
        dialog.dismiss();
    }
}

