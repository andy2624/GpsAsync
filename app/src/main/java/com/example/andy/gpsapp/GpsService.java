package com.example.andy.gpsapp;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;


import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;
import java.net.URL;

import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

public class GpsService extends Service implements LocationListener{

Context mContext;

    public GpsService(Context mContext) {
        this.mContext = mContext;
    }

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

        PostReqServer reqServer = new PostReqServer();
        reqServer.execute();

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



class PostReqServer extends AsyncTask<String, String, String> {


    @Override
    protected String doInBackground(String... strings) {

        String urlString = "https://nitsbirco7.execute-api.ap-south-1.amazonaws.com/latest/"; // URL to call

        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("name","Anand"));
        params.add(new BasicNameValuePair("loc","Anand"));
        params.add(new BasicNameValuePair("time","sometime"));

        JSONObject response = JsonParser.makeHttpsRequest(urlString,"POST",params);

        return null;
    }



}
























































}
