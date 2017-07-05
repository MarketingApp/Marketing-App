package hahoang.marketingmessage.support;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.os.AsyncTask;
import android.util.Log;

import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Hoang Ha on 7/5/2017.
 */

public class DetectLatLngToAddress extends AsyncTask<Void, Void, String> {
    OnComplete onComplete;
    LatLng latLng;
    Geocoder geocoder;
    private Context mContext;
    String add;
    ArrayList<Address> adds;

    public DetectLatLngToAddress(LatLng ll, Context context) {
        mContext = context;
        latLng = ll;
        geocoder = new Geocoder(mContext);
        add = "";
    }

    @Override
    protected String doInBackground(Void... params) {
        try {
            adds = new ArrayList<>();
            adds = (ArrayList<Address>) geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1);
            if (adds.size() > 0) {
                Address address = adds.get(0);
                ArrayList<String> strings = new ArrayList<>();
                for (int i = 0; i < address.getMaxAddressLineIndex(); ++i)
                    if (address.getAddressLine(i) != null)
                        strings.add(address.getAddressLine(i));
                for (String s : strings
                        ) {
                    add += s + ", ";
                }
                Log.v("ADDRESS",add);
                if (add.length() > 2)
                    add = add.substring(0, add.length() - 2);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return add;
    }

    @Override
    protected void onPostExecute(String res) {
        super.onPostExecute(res);
        onComplete.onResponse(res, adds);
    }


    public interface OnComplete {
        public void onResponse(String add, ArrayList<Address> addresses);
    }

    public OnComplete getOnComplete() {
        return onComplete;
    }

    public void setOnComplete(OnComplete onComplete) {
        this.onComplete = onComplete;
    }

    public LatLng getLatLng() {
        return latLng;
    }

    public void setLatLng(LatLng latLng) {
        this.latLng = latLng;
    }
}