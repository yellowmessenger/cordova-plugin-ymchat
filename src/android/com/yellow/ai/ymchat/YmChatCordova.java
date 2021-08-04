package com.yellow.ai.ymchat;

import android.content.Context;
import android.util.Log;

import com.yellow.ai.ymchat.utils.Utils;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class YmChatCordova extends CordovaPlugin {

  final String Tag = "YmChat";
  final String ExceptionString = "Exception";

  private final YmChatService ymChatService = new YmChatService();

  @Override
  public boolean execute(String action, JSONArray args, CallbackContext callbackContext) {
    Log.d("YmSdkLog", action);
    switch (action) {
      case "setBotId":
        setBotId(args, callbackContext);
        return true;
      case "setDeviceToken":
        setDeviceToken(args, callbackContext);
        return true;
      case "setEnableSpeech":
        setEnableSpeech(args, callbackContext);
        return true;
      case "setEnableHistory":
        setEnableHistory(args, callbackContext);
        return true;
      case "setAuthenticationToken":
        setAuthenticationToken(args, callbackContext);
        return true;
      case "showCloseButton":
        showCloseButton(args, callbackContext);
        return true;
      case "setCustomURL":
        setCustomURL(args, callbackContext);
        return true;
      case "setPayload":
        setPayload(args, callbackContext);
        return true;
      case "onEventFromBot":
        onEventFromBot(callbackContext);
        return true;
      case "onBotClose":
        onBotClose(callbackContext);
        return true;
      case "startChatbot":
      startChatbot(callbackContext);
        return true;
      case "closeBot":
        closeBot();
        return true;
    }
    return false;
  }

  public void setBotId(JSONArray args, CallbackContext callbackContext) {
    try {
      String botId = args.getString(0);
      ymChatService.setBotId(botId, callbackContext);
    } catch (Exception e) {
      Utils.genericErrorHelper(e, callbackContext);
    }
  }

  public void startChatbot(CallbackContext callbackContext) {
    cordova.getActivity().runOnUiThread(new Runnable() {
      @Override
      public void run() {
        Context ionicContext = cordova.getActivity().getApplicationContext();
        try {
          ymChatService.startChatbot(ionicContext, callbackContext);
        } catch (Exception e) {
          callbackContext.error(e.toString());
        }
      }
    });
  }

  public void closeBot() {
    ymChatService.closeBot();
  }

  public void setDeviceToken(JSONArray args, CallbackContext callbackContext) {
    try {
      String deviceToken = args.getString(0);
      ymChatService.setDeviceToken(deviceToken, callbackContext);
    } catch (Exception e) {
      Utils.genericErrorHelper(e, callbackContext);
    }
  }

  public void setEnableSpeech(JSONArray args, CallbackContext callbackContext) {
    try {
      boolean enableSpeech = args.getBoolean(0);
      ymChatService.setEnableSpeech(enableSpeech, callbackContext);
    } catch (Exception e) {
      Utils.genericErrorHelper(e, callbackContext);
    }
  }

  public void setEnableHistory(JSONArray args, CallbackContext callbackContext) {
    try {
      boolean enableHistory = args.getBoolean(0);
      ymChatService.setEnableHistory(enableHistory, callbackContext);
    } catch (Exception e) {
      Utils.genericErrorHelper(e, callbackContext);
    }
  }

  public void setAuthenticationToken(JSONArray args, CallbackContext callbackContext) {
    try {
      String authToken = args.getString(0);
      ymChatService.setAuthenticationToken(authToken, callbackContext);
    } catch (Exception e) {
      Utils.genericErrorHelper(e, callbackContext);
    }
  }

  public void showCloseButton(JSONArray args, CallbackContext callbackContext) {
    try {
      boolean closeButton = args.getBoolean(0);
      ymChatService.showCloseButton(closeButton, callbackContext);
    } catch (Exception e) {
      Utils.genericErrorHelper(e, callbackContext);
    }
  }

  public void setCustomURL(JSONArray args, CallbackContext callbackContext) {
    try {
      String customUrl = args.getString(0);
      ymChatService.customBaseUrl(customUrl, callbackContext);
    } catch (Exception e) {
      Utils.genericErrorHelper(e, callbackContext);
    }
  }

  public void setPayload(JSONArray args, CallbackContext callbackContext) {
    try {
      JSONObject payload = args.getJSONObject(0);
      ymChatService.setPayload(payload, callbackContext);
    } catch (Exception e) {
      Log.e(Tag, ExceptionString, e);
    }
  }

  public void onEventFromBot(CallbackContext onEventFromBot) {
    ymChatService.onEventFromBot(onEventFromBot);
  }

  public void onBotClose(CallbackContext onBotCloseEvent) {
    ymChatService.onBotClose(onBotCloseEvent);
  }
}
