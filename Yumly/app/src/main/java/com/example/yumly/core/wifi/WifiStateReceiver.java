package com.example.yumly.core.wifi;

import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.airbnb.lottie.LottieAnimationView;
import com.example.yumly.R;

public class WifiStateReceiver extends BroadcastReceiver {
    private Context context;
    private Dialog wifiDialog;

    public WifiStateReceiver(Context context) {
        this.context = context;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (WifiManager.WIFI_STATE_CHANGED_ACTION.equals(intent.getAction())) {
            int wifiState = intent.getIntExtra(WifiManager.EXTRA_WIFI_STATE, WifiManager.WIFI_STATE_UNKNOWN);

            switch (wifiState) {
                case WifiManager.WIFI_STATE_ENABLED:
                    Log.d("WifiStateReceiver", "WiFi is ON");
                    dismissWifiDialog();
                    break;
                case WifiManager.WIFI_STATE_DISABLED:
                    Log.d("WifiStateReceiver", "WiFi is OFF");
                    showWifiDisabledDialog();
                    break;
            }
        }
    }

    private void showWifiDisabledDialog() {
        if (context != null) {
            if (wifiDialog != null && wifiDialog.isShowing()) {
                return;
            }

            wifiDialog = new Dialog(context);
            wifiDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            wifiDialog.setContentView(R.layout.custom_wifi_dialog);
            wifiDialog.setCancelable(false);

            if (wifiDialog.getWindow() != null) {
                wifiDialog.getWindow().setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);
                wifiDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
            }

            LottieAnimationView lottieAnimationView = wifiDialog.findViewById(R.id.lottie);
            Button cancelBtn = wifiDialog.findViewById(R.id.dialog_cancel);
            Button confirmBtn = wifiDialog.findViewById(R.id.dialog_confirm);

            cancelBtn.setOnClickListener(v -> wifiDialog.dismiss());
            confirmBtn.setOnClickListener(v -> {
                context.startActivity(new Intent(WifiManager.ACTION_PICK_WIFI_NETWORK));
                wifiDialog.dismiss();
            });

            wifiDialog.show();
        }
    }

    private void dismissWifiDialog() {
        if (wifiDialog != null && wifiDialog.isShowing()) {
            wifiDialog.dismiss();
        }
    }
}


