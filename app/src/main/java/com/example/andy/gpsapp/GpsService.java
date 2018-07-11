package com.example.andy.gpsapp;

import android.app.Service;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;

public class GpsService extends Service implements LocationListener{




    @Nullable
    @Override
    public IBinder onBind(Intent intent) {

        // Service method
        return null;
    }


    @Override
    public void onLocationChanged(Location location) {

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
/////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

//        PostReqServer reqServer = new PostReqServer();
//        reqServer.execute();

        return START_STICKY;
    }

    @Override
    public void onCreate() {
        super.onCreate();

//        PostReqServer reqServer = new PostReqServer();
//        reqServer.execute();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

///////////////////////////////////////////////////////////////////////////////////////////



class PostReqServer extends AsyncTask<String, String, InputStream> {


    @Override
    protected InputStream doInBackground(String... strings) {

        URL url = null;
        try {
            url = new URL("https://nitsbirco7.execute-api.ap-south-1.amazonaws.com/latest/");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        HttpsURLConnection conn = null;
        try {
            conn = (HttpsURLConnection) url.openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Create the SSL connection
        SSLContext sc = null;
        try {
            sc = SSLContext.getInstance("TLS");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        try {
            sc.init(null, null, new java.security.SecureRandom());
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }
        conn.setSSLSocketFactory(sc.getSocketFactory());


        // set Timeout and method
        conn.setReadTimeout(7000);
        conn.setConnectTimeout(7000);
        try {
            conn.setRequestMethod("POST");
        } catch (ProtocolException e) {
            e.printStackTrace();
        }
        conn.setDoInput(true);

        // Add any data you wish to post here

        try {
            conn.connect();
            int responseCode = conn.getResponseCode();
            Log.i("ResponseCode", "doInBackground: " + responseCode);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            return conn.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return null;
    }



}
























































}
