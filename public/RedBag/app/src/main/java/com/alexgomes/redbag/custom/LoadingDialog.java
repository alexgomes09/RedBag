package com.alexgomes.redbag.custom;

import android.app.ProgressDialog;
import android.content.Context;

/**
 * Created by agomes on 10/21/18.
 */

public class LoadingDialog {

    private static LoadingDialog instance;
    private ProgressDialog progressDialog;
    private boolean isShowing = false;

    public static LoadingDialog getInstance() {
        if (instance == null) {
            instance = new LoadingDialog();
        }
        return instance;
    }

    public void showDialog(Context context, String message) {
        if (!isShowing) {
            progressDialog = new ProgressDialog(context);
            progressDialog.setMessage(message);
            progressDialog.setCancelable(false);
            progressDialog.show();
            isShowing = true;
        }

    }


    public void dismiss() {
        if (isShowing) {
            progressDialog.dismiss();
            isShowing = false;
        }
    }
}
