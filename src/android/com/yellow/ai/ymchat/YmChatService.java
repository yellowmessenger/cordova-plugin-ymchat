package com.yellow.ai.ymchat;

import android.content.Context;
import android.util.Log;

import com.yellow.ai.ymchat.utils.Utils;
import com.yellowmessenger.ymchat.YMChat;
import com.yellowmessenger.ymchat.YMConfig;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.PluginResult;
import org.json.JSONObject;

import java.util.HashMap;

public class YmChatService {
  YMChat ymChat;

  HashMap<String, Object> payloadData = new HashMap<>();

  YmChatService() {
    this.ymChat = YMChat.getInstance();
    ymChat.onBotClose(() -> {
    });
  }

  public void setBotId(String botId) {
    ymChat.config = new YMConfig(botId);
    ymChat.config.ymAuthenticationToken = "";
    ymChat.config.payload = payloadData;
  }

  public void startChatbot(Context context) {
    try {
      ymChat.startChatbot(context);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void closeBot() {
    ymChat.closeBot();
  }

  public void setDeviceToken(String token) {
    ymChat.config.deviceToken = token;
  }

  public void setEnableSpeech(boolean speech) {
    ymChat.config.enableSpeech = speech;
  }

  public void setEnableHistory(boolean history) {
    ymChat.config.enableHistory = history;
  }

  public void setAuthenticationToken(String token) {
    ymChat.config.ymAuthenticationToken = token;
  }

  public void showCloseButton(boolean show) {
    ymChat.config.showCloseButton = show;
  }

  public void customBaseUrl(String url) {
    ymChat.config.customBaseUrl = url;
  }

  public void setPayload(JSONObject payload) throws Exception {
    ymChat.config.payload.putAll(Utils.jsonToMap(payload));
  }

  public void onEventFromBot(CallbackContext onEventFromBot) {
    ymChat.onEventFromBot(botEvent ->
    {
      JSONObject jsonObject = new JSONObject();
      try {
        jsonObject.put(botEvent.getCode(), new JSONObject(botEvent.getData()));
        PluginResult result = new PluginResult(PluginResult.Status.OK, jsonObject);
        result.setKeepCallback(true);
        onEventFromBot.sendPluginResult(result);
      } catch (Exception e) {
        Log.e("YmChat","Exception",e);
      }
    });
  }
}
