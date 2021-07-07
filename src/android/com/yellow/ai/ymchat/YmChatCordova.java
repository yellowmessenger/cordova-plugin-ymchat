package com.yellow.ai.ymchat;

import android.content.Context;
import android.util.Log;

import com.yellowmessenger.ymchat.YMChat;
import com.yellowmessenger.ymchat.YMConfig;
import com.yellowmessenger.ymchat.models.YMBotEventResponse;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class YmChatCordova extends CordovaPlugin {

  final String Tag = "YmChat";
  final String ExceptionString = "Exception";

  private final YmChatService ymChatService = new YmChatService();

  @Override
  public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
    Log.d("YmSdkLog", action);
    switch (action) {
      case "setBotId":
        String botId = args.getString(0);
        setBotId(botId);
        return true;
      case "setDeviceToken":
        String deviceToken = args.getString(0);
        setDeviceToken(deviceToken);
        return true;
      case "setEnableSpeech":
        boolean enableSpeech = args.getBoolean(0);
        setEnableSpeech(enableSpeech);
        return true;
      case "setEnableHistory":
        boolean enableHistory = args.getBoolean(0);
        setEnableHistory(enableHistory);
        return true;
      case "setAuthenticationToken":
        String authToken = args.getString(0);
        setAuthenticationToken(authToken);
        return true;
      case "showCloseButton":
        boolean closeButton = args.getBoolean(0);
        showCloseButton(closeButton);
        return true;
      case "setCustomURL":
        String customUrl = args.getString(0);
        setCustomURL(customUrl);
        return true;
      case "setPayload":
        JSONObject payload = args.getJSONObject(0);
        setPayload(payload);
        return true;
      case "onEventFromBot":
        onEventFromBot(callbackContext);
      case "onBotClose":
        onBotClose(callbackContext);
      case "startBot":
        cordova.getThreadPool().execute(new Runnable() {
          @Override
          public void run() {
            startBot(callbackContext);
          }
        });
        return true;
      case "closeBot":
        closeBot();
        return true;
    }
    return false;
  }

  public void setBotId(String botId) {
    ymChatService.setBotId(botId);
  }

  public void startBot(CallbackContext callbackContext) {
    Context ionicContext = this.cordova.getActivity().getApplicationContext();
    try {
      ymChatService.startChatbot(ionicContext);
    } catch (Exception e) {
      callbackContext.error(e.toString());
    }
  }

  public void closeBot() {
    ymChatService.closeBot();
  }

  public void setDeviceToken(String token) {
    ymChatService.setDeviceToken(token);
  }

  public void setEnableSpeech(boolean speech) {
    ymChatService.setEnableSpeech(speech);
  }

  public void setEnableHistory(boolean history) {
    ymChatService.setEnableHistory(history);
  }

  public void setAuthenticationToken(String token) {
    ymChatService.setAuthenticationToken(token);
  }

  public void showCloseButton(boolean show) {
    ymChatService.showCloseButton(show);
  }

  public void setCustomURL(String url) {
    ymChatService.customBaseUrl(url);
  }

  public void setPayload(JSONObject payload) {
    try{
      ymChatService.setPayload(payload);
    }
    catch (Exception e)
    {
      Log.e(Tag,ExceptionString,e);
    }
  }

  public void onEventFromBot(CallbackContext onEventFromBot)
  {
    ymChatService.onEventFromBot(onEventFromBot);
  }

  public void onBotClose(CallbackContext onBotCloseEvent)
  {
    ymChatService.onBotClose(onBotCloseEvent);
  }
}
