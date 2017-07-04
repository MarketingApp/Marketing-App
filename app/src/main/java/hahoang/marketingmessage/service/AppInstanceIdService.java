package hahoang.marketingmessage.service;

import android.content.SharedPreferences;
import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

import hahoang.marketingmessage.config.Config;

/**
 * Created by Ha Hoang on 7/3/2017.
 */

public class AppInstanceIdService extends FirebaseInstanceIdService {
    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();
        storeToken(FirebaseInstanceId.getInstance().getToken());
    }

    private void storeToken(String token) {
        getSharedPreferences(Config.PREF, 0).edit().putString("token", token).commit();
        Log.v("HH",token);
    }
}
