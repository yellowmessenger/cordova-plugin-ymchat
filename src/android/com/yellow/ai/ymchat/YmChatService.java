package com.yellow.ai.ymchat;

import android.content.Context;
import android.net.Uri;
import android.util.Log;

import com.yellow.ai.ymchat.utils.Utils;
import com.yellowmessenger.ymchat.YMChat;
import com.yellowmessenger.ymchat.YMConfig;
import com.yellowmessenger.ymchat.models.YellowCallback;
import com.yellowmessenger.ymchat.models.YellowDataCallback;
import com.yellowmessenger.ymchat.models.YellowUnreadMessageResponse;
import com.yellowmessenger.ymchat.models.YMEventModel;

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
  final String data = "data";

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

  public void reloadBot() {
    ymChat.reloadBot();
  }

  public void setDeviceToken(String token, CallbackContext callbackContext) {
    ymChat.config.deviceToken = token;
  }

  public void setEnableSpeech(boolean speech, CallbackContext callbackContext) {
    ymChat.config.speechConfig.enableSpeech = speech;
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
        JSONObject dataJsonObject = new JSONObject();
        if (botEvent.getData() != null) {
          try {
            dataJsonObject = new JSONObject(botEvent.getData());
          }catch(Exception e) {
            Log.e(Tag, ExceptionString, e);
          }
        }
        jsonObject.put(data, dataJsonObject);
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

  public void unlinkDeviceToken(String apiKey, CallbackContext callbackContext) {
    try{
      ymChat.unlinkDeviceToken(apiKey, ymChat.config, new YellowCallback() {
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

  public void registerDevice(String apiKey, CallbackContext callbackContext) {
    try{
      ymChat.registerDevice(apiKey, ymChat.config, new YellowCallback() {
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

  public void getUnreadMessagesCount(CallbackContext callbackContext) {
    try{
      ymChat.getUnreadMessagesCount(ymChat.config, new YellowDataCallback() {
        @Override
        public <T> void success(T data) {
        YellowUnreadMessageResponse response = (YellowUnreadMessageResponse) data;
          callbackContext.success(response.getUnreadCount());
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

  public void useLiteVersion(boolean shouldUseLiteVersion, CallbackContext callbackContext) {
    ymChat.config.useLiteVersion = shouldUseLiteVersion;
  }

  public void setMicIconColor(String color, CallbackContext callbackContext) {
    ymChat.config.speechConfig.fabIconColor = color;
  }

  public void setMicBackgroundColor(String color, CallbackContext callbackContext) {
    ymChat.config.speechConfig.fabBackgroundColor = color;
  }

  public void setMicButtonMovable(boolean shouldMicButtonMovable, CallbackContext callbackContext) {
    ymChat.config.speechConfig.isButtonMovable = shouldMicButtonMovable;
  }

  public void useSecureYmAuth(boolean shouldUseSecureYmAuth, CallbackContext callbackContext) {
    ymChat.config.useSecureYmAuth = shouldUseSecureYmAuth;
  }

  public void setOpenLinkExternally(boolean shouldOpenLinkExternally, CallbackContext callbackContext) {
    ymChat.config.shouldOpenLinkExternally = shouldOpenLinkExternally;
  }

  public void setThemeBotName(String name, CallbackContext callbackContext) {
    ymChat.config.theme.botName = name;
  }

  public void setThemeBotDescription(String description, CallbackContext callbackContext) {
    ymChat.config.theme.botDesc = description;
  }

  public void setThemePrimaryColor(String color, CallbackContext callbackContext) {
      ymChat.config.theme.primaryColor = color;
  }

  public void setThemeSecondaryColor(String color, CallbackContext callbackContext) {
      ymChat.config.theme.secondaryColor = color;
  }

  public void setThemeBotBubbleBackgroundColor(String color, CallbackContext callbackContext) {
      ymChat.config.theme.botBubbleBackgroundColor = color;
  }

  public void setThemeBotIcon(String iconUrl, CallbackContext callbackContext) {
      ymChat.config.theme.botIcon = iconUrl;
  }

  public void setThemeBotClickIcon(String iconUrl, CallbackContext callbackContext) {
      ymChat.config.theme.botClickIcon = iconUrl;
  }

  public void setChatContainerTheme(String theme, CallbackContext callbackContext) {
      ymChat.config.theme.chatBotTheme = theme;
  }

  public void setThemeLinkColor(String color, CallbackContext callbackContext) {
      ymChat.config.theme.linkColor = color;
  }

  public void revalidateToken(String token, boolean refreshSession, CallbackContext callbackContext) {
    try {
      ymChat.revalidateToken(token, refreshSession);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void sendEventToBot(String code, JSONObject data, CallbackContext callbackContext) {
    try {
      YMEventModel model = new YMEventModel(code, Utils.jsonToMap(data));
      ymChat.sendEventToBot(model);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
