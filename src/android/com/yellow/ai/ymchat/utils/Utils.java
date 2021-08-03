package com.yellow.ai.ymchat.utils;
import android.util.Log;

import org.apache.cordova.CallbackContext;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Utils {

    public static Map<String, Object> jsonToMap(Object json) throws JSONException {

        if(json instanceof JSONObject)
            return jsonObjToMap((JSONObject)json) ;

        else if (json instanceof String)
        {
            JSONObject jsonObject = new JSONObject((String)json) ;
            return jsonObjToMap(jsonObject) ;
        }
        return null ;
    }


   private static Map<String, Object> jsonObjToMap(JSONObject json) throws JSONException {
        Map<String, Object> retMap = new HashMap<String, Object>();

        if(json != JSONObject.NULL) {
            retMap = toMap(json);
        }
        return retMap;
    }


    private static Map<String, Object> toMap(JSONObject object) throws JSONException {
        Map<String, Object> map = new HashMap<String, Object>();

        Iterator<String> keysItr = object.keys();
        while(keysItr.hasNext()) {
            String key = keysItr.next();
            Object value = object.get(key);

            if(value instanceof JSONArray) {
                value = toList((JSONArray) value);
            }

            else if(value instanceof JSONObject) {
                value = toMap((JSONObject) value);
            }
            map.put(key, value);
        }
        return map;
    }


    public static List<Object> toList(JSONArray array) throws JSONException {
        List<Object> list = new ArrayList<Object>();
        for(int i = 0; i < array.length(); i++) {
            Object value = array.get(i);
            if(value instanceof JSONArray) {
                value = toList((JSONArray) value);
            }

            else if(value instanceof JSONObject) {
                value = toMap((JSONObject) value);
            }
            list.add(value);
        }
        return list;
    }
    public static void genericErrorHelper(Exception exception, CallbackContext callbackContext) {
        Log.e("YmLog", "Failure", exception);
        try {
          String error = exception.getMessage();
          StackTraceElement[] stackTrace = exception.getStackTrace();
    
          JSONObject errorJson = new JSONObject();
          errorJson.put("success", false);
          errorJson.put("error", error);
          errorJson.put("stackTrace", stackTrace);
          callbackContext.error(errorJson);
    
        } catch (Exception e) {
    
          callbackContext.error("Error");
    
        }
    
      }
    
      public static void sdkErrorHelper(String error, CallbackContext callbackContext) {
        Log.d("YmLog", error);
        try {
    
          JSONObject errorJson = new JSONObject();
          errorJson.put("success", false);
          errorJson.put("error", error);
          errorJson.put("stackTrace", new JSONObject());
          callbackContext.error(errorJson);
    
        } catch (Exception e) {
    
          callbackContext.error("Error");
    
        }
    
      }
    
      public static void genericSuccessHelper(CallbackContext callbackContext) {
        Log.d("YmLog", "Success");
        callbackContext.success();
    
      }
}
