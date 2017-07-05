package hahoang.marketingmessage.view.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import hahoang.marketingmessage.R;
import hahoang.marketingmessage.support.DetectLatLngToAddress;

/**
 * Created by Hoang Ha on 7/5/2017.
 */

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback,
        GoogleMap.OnMapClickListener, View.OnClickListener {
    GoogleMap map;
    SupportMapFragment supportMapFragment;
    MarkerOptions markerOptions;
    LatLng mLatLng = null;
    List<Address> adds;
    Geocoder geocoder;
    Marker marker;
    EditText etAdd;
    DetectLatLngToAddress detectLatLngToAddress;
    ImageView ivFindLocation;
    int result = -1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.get_lat_lng_activity);
        supportMapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        supportMapFragment.getMapAsync(this);
        etAdd = (EditText) findViewById(R.id.et_search_add);
        ivFindLocation = (ImageView) findViewById(R.id.iv_search_button);
        ivFindLocation.setOnClickListener(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        geocoder = new Geocoder(MapActivity.this);
        this.map = googleMap;
        map.setOnMapClickListener(this);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        map.setMyLocationEnabled(true);
        LocationManager currentLocation = (LocationManager) getSystemService(LOCATION_SERVICE);
        Location location = currentLocation.getLastKnownLocation(LocationManager.PASSIVE_PROVIDER);
        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15f));
        map.setPadding(0, 350, 0, 0);

    }

    @Override
    public void onMapClick(final LatLng latLng) {
        mLatLng = latLng;
        if (marker != null)
            marker.setPosition(latLng);
        else {
            markerOptions = new MarkerOptions().position(latLng).title("");
            marker = map.addMarker(markerOptions);
        }
        detectLatLngToAddress = new DetectLatLngToAddress(mLatLng, MapActivity.this);
        detectLatLngToAddress.setOnComplete(new DetectLatLngToAddress.OnComplete() {
            @Override
            public void onResponse(String add, ArrayList<Address> addresses) {
                if (!add.isEmpty()) {
                    etAdd.setText(add);
                    adds = addresses;
                } else {
                    Toast.makeText(MapActivity.this, getString(R.string.no_add), Toast.LENGTH_LONG).show();
                }
            }
        });
        detectLatLngToAddress.execute();
    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.iv_search_button:
                if (!etAdd.getText().toString().isEmpty())
                    try {
                        adds = geocoder.getFromLocationName(etAdd.getText().toString(), 1);
                        if (adds.size() > 0) {
                            LatLng latLng = new LatLng(adds.get(0).getLatitude(), adds.get(0).getLongitude());
                            if (marker != null)
                                marker.setPosition(latLng);
                            else
                                marker = map.addMarker(new MarkerOptions().position(latLng));

                            map.animateCamera(CameraUpdateFactory.newLatLng(latLng));
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                break;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case android.R.id.home:
                result = 0;
                sendResult();
            case R.id.it_ok:
                result = 1;
                sendResult();
                break;
        }
        return super.onOptionsItemSelected(item);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_map_activity, menu);
        return super.onCreateOptionsMenu(menu);
    }

    private void sendResult() {
        Intent intent = new Intent();
        ArrayList<String> strings = new ArrayList<>();
        if (adds != null && adds.size() > 0) {
            Address address = this.adds.get(0);
            if (address != null) {
                String add = "";
                for (int i = 0; i < address.getMaxAddressLineIndex(); ++i)
                    if (address.getAddressLine(i) != null)
                        strings.add(address.getAddressLine(i));
                for (String s : strings
                        ) {
                    add += s + ", ";
                }
                add = add.substring(0, add.length() - 2);
                intent.putExtra("address", add);
            }
            setResult(result, intent);
        }
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        result = 0;
        sendResult();
    }
}
