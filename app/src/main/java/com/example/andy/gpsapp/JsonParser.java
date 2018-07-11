package com.example.andy.gpsapp;

import android.content.Context;
import android.util.Log;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by Shubham on 16-01-2017.
 */

public class JsonParser {

    public static JSONObject makeHttpsRequest(String url_str, String method, List<NameValuePair> params) {
        String result = "";
        int respCode = -1;
        JSONObject object = new JSONObject();
        try {
            //trustIFNetServer(context);
            UrlEncodedFormEntity ur = new UrlEncodedFormEntity(params);
            int timeoutConnection = 60000;

            URL url = new URL(url_str);
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
            connection.setRequestMethod(method);

            connection.setRequestProperty("Content-Type", "application/json");

            connection.setConnectTimeout(timeoutConnection);
            connection.setDoOutput(true);
            connection.connect();
            ur.writeTo(connection.getOutputStream());
            respCode = connection.getResponseCode();
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                result = result.concat(inputLine);
            }
            in.close();
        } catch (Exception e){
        }
        try {
            object = new JSONObject(result);
            object.put("RespCode",respCode);
        } catch (Exception e){
            try {
                object = new JSONObject();
                object.put("Error", e.toString());
                object.put("RespCode",respCode);
            } catch (Exception e1){
                e1.printStackTrace();
            }
        }
        //Log.d("ALGO_LOG_RESPONSE",object.toString());
        return object;
    }

    public static JSONObject makeHttpRequest(String url_str, String method, List<NameValuePair> params) {
        String result = "";
        int respCode = -1;
        JSONObject object = new JSONObject();
        try {
            URL url = new URL(url_str);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            if (params != null) {
                connection.setRequestMethod(method);
                connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                connection.setDoOutput(true);
                connection.setDoInput(true);
                UrlEncodedFormEntity ur = new UrlEncodedFormEntity(params);
                ur.writeTo(connection.getOutputStream());
            }
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            respCode = connection.getResponseCode();
            while ((inputLine = in.readLine()) != null) {
                result = result.concat(inputLine);
            }
            in.close();
        } catch (Exception e){
            e.printStackTrace();
        }
        try {
            object = new JSONObject(result);
            object.put("RespCode",respCode);
        } catch (Exception e){
            try {
                object = new JSONObject();
                object.put("Error", e.toString());
            } catch (Exception e1){
                e1.printStackTrace();
            }
        }
        //Log.d("APP_REQUSET",params.toString());
        Log.d("APP_RESPONSE",object.toString());
        return object;
    }

}
