package com.sergon146.mobilization18.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * @author Sergon146 (sergon146@gmail.com).
 * @since 15.04.2018
 */
public class NetworkUtil {

    /**
     * Check is there are network connection
     *
     * @param context Context
     * @return is there a connection
     */
    public static boolean isLostConnection(Context context) {
        try {
            ConnectivityManager cm = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);

            NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
            if (null != activeNetwork) {
                if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI || activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE)
                    return false;
            }
            return true;
        } catch (Exception e) {
            return true;
        }
    }
}
