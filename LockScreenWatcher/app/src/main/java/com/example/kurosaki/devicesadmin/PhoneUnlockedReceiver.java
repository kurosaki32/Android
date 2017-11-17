package com.example.kurosaki.devicesadmin;

import android.app.KeyguardManager;
import android.app.admin.DeviceAdminReceiver;
import android.app.admin.DevicePolicyManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import static android.content.ContentValues.TAG;

/**
 * Created by Kurosaki on 16/11/2017.
 */

public class PhoneUnlockedReceiver extends DeviceAdminReceiver {
    /*
    @Override
    public void onReceive(Context arg0, Intent intent) {
        if (intent.getAction().equals(Intent.ACTION_USER_PRESENT)) {
            Log.d(TAG, "Phone unlocked");
        }
        else if (intent.getAction().equals(Intent.ACTION_SCREEN_OFF)) {
            Log.d(TAG, "Phone locked");
        }

    }
    */
    @Override
    public void onEnabled(Context ctxt, Intent intent) {
        ComponentName cn=new ComponentName(ctxt, PhoneUnlockedReceiver.class);
        DevicePolicyManager mgr=(DevicePolicyManager)ctxt.getSystemService(Context.DEVICE_POLICY_SERVICE);

        mgr.setPasswordQuality(cn,
                DevicePolicyManager.PASSWORD_QUALITY_ALPHANUMERIC);

        onPasswordChanged(ctxt, intent);
    }
    @Override
    public void onPasswordFailed(Context ctxt, Intent intent) {

        DevicePolicyManager mgr = (DevicePolicyManager)ctxt.getSystemService(Context.DEVICE_POLICY_SERVICE);
        int limit = mgr.getCurrentFailedPasswordAttempts();
        if (limit==2) {
            Log.d(TAG, "MAX");
            Intent i = new Intent(ctxt, Camera.class);
            ctxt.startActivity(i);
        }
        else{
            Toast.makeText(ctxt, R.string.password_failed, Toast.LENGTH_LONG).show();
            Log.d(TAG,"GAGAL");
        }

    }

    @Override
    public void onPasswordSucceeded(Context ctxt, Intent intent) {
        Toast.makeText(ctxt, R.string.password_success, Toast.LENGTH_LONG)
                .show();
    }
}
