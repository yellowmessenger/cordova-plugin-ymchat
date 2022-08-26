package com.yellow.ai.ymchat;

import android.content.Context;
import android.net.Uri;
import android.util.Log;

import com.yellow.ai.ymchat.utils.Utils;
import com.yellowmessenger.ymchat.YMChat;
import com.yellowmessenger.ymchat.YMConfig;
import com.yellowmessenger.ymchat.models.YellowCallback;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;

public class YmChatService {
  YMChat ymChat;
  final String Tag = "YmChat";
  final String ExceptionString = "Exception";
  final String code = "code";
  final String data = "date";

  HashMap<String, Object> payloadData = new HashMap<>();

  YmChatService() {
    this.ymChat = YMChat.getInstance();
  }

  public void setBotId(String botId, CallbackContext callbackContext) {
    ymChat.config = new YMConfig(botId);
    ymChat.config.ymAuthenticationToken = "";
    ymChat.config.payload = payloadData;
  }

  public void startChatbot(Context context, CallbackContext callbackContext) {
    try {
      ymChat.startChatbot(context);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void closeBot() {
    ymChat.closeBot();
  }

  public void setDeviceToken(String token, CallbackContext callbackContext) {
    ymChat.config.deviceToken = token;
  }

  public void setEnableSpeech(boolean speech, CallbackContext callbackContext) {
    ymChat.config.enableSpeech = speech;
  }

  public void setAuthenticationToken(String token, CallbackContext callbackContext) {
    ymChat.config.ymAuthenticationToken = token;
  }

  public void showCloseButton(boolean show, CallbackContext callbackContext) {
    ymChat.config.showCloseButton = show;
  }

  public void customBaseUrl(String url, CallbackContext callbackContext) {
    ymChat.config.customBaseUrl = url;
  }

  public void setPayload(JSONObject payload, CallbackContext callbackContext)  {
    try{
      ymChat.config.payload.putAll(Utils.jsonToMap(payload));
    }
    catch (Exception e) {
      Utils.genericErrorHelper(e, callbackContext);
    }
  }

  public void onEventFromBot(CallbackContext callback) {
    ymChat.onEventFromBot(botEvent ->
    {
      JSONObject jsonObject = new JSONObject();
      try {
        jsonObject.put(code, botEvent.getCode());
        jsonObject.put(data, new JSONObject(botEvent.getData()));
        PluginResult result = new PluginResult(PluginResult.Status.OK, jsonObject);
        result.setKeepCallback(true);
        callback.sendPluginResult(result);
      } catch (Exception e) {
        Log.e(Tag, ExceptionString, e);
      }
    });
  }

  public void onBotClose(CallbackContext callback) {
    ymChat.onBotClose(() ->
    {
      try {
        PluginResult result = new PluginResult(PluginResult.Status.OK);
        result.setKeepCallback(true);
        callback.sendPluginResult(result);
      } catch (Exception e) {
        Log.e(Tag, ExceptionString, e);
      }
    });
  }

  public void unlinkDeviceToken(String botId, String apiKey, String deviceToken,CallbackContext callbackContext) {
    try{
      ymChat.unlinkDeviceToken(botId, apiKey, deviceToken, new YellowCallback() {
        @Override
        public void success() {
          callbackContext.success();
        }

        @Override
        public void failure(String message) {
          Utils.genericErrorHelper(new Exception(),callbackContext);
        }
      });
    }
    catch (Exception e)
    {
      Utils.genericErrorHelper(e, callbackContext);
    }
  }

  public void setVersion(int version, CallbackContext callbackContext) {
    ymChat.config.version = version;
  }

  public void setCustomLoaderUrl(String url, CallbackContext callbackContext) {
    ymChat.config.customLoaderUrl = url;
  }

  public void setCloseButtonColor(String color, CallbackContext callbackContext) {
    ymChat.config.closeButtonColorFromHex = color;
  }

  public void setStatusBarColor(String color, CallbackContext callbackContext) {
    ymChat.config.statusBarColorFromHex = color;
  }

  public void setDisableActionsOnLoad(boolean shouldDisableActionsOnLoad, CallbackContext callbackContext) {
    ymChat.config.disableActionsOnLoad = shouldDisableActionsOnLoad;
  }
}
