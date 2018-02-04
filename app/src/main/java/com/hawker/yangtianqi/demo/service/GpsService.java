package com.hawker.yangtianqi.demo.service;

import android.Manifest;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

public class GpsService extends Service {
    private static double distanceMeters;
    private static Location lastLocation = null;
    private final IBinder binder = new GpsBinder();

    public class GpsBinder extends Binder {
        public GpsService getGps() {
            return GpsService.this;
        }
    }

    public GpsService() {
    }

    @Override
    public void onCreate() {
        LocationListener listener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                Log.i("","&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
                if (lastLocation == null) {
                    lastLocation = location;
                }
                distanceMeters += location.distanceTo(lastLocation);
                lastLocation = location;
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        };
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        //权限检查
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        //设备移动距离每秒超过1米调用更新方法
        locationManager.requestLocationUpdates(locationManager.GPS_PROVIDER, 1000, 1, listener);
    }

    public double getMiles(){
        return this.distanceMeters;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }
}
