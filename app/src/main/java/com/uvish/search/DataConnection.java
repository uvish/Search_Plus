package com.uvish.search;

import android.content.Context;
import android.net.ConnectivityManager;

/**
 * Created by uvish on 9/1/2017.
 */

public class DataConnection {
    public static boolean checkInternetConnection(Context context){
        ConnectivityManager cm=(ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if(cm.getActiveNetworkInfo()!=null && cm.getActiveNetworkInfo().isAvailable() && cm.getActiveNetworkInfo().isConnected())
            return true;
        else return false;
    }
}
