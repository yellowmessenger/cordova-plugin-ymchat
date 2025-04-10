package com.yellow.ai.ymchat;

import android.content.Context;
import android.util.Log;

import com.yellow.ai.ymchat.utils.Utils;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONObject;

public class YmChatCordova extends CordovaPlugin {

  final String Tag = "YmChat";
  final String ExceptionString = "Exception";

  private final YmChatService ymChatService = new YmChatService();

  @Override
  public boolean execute(String action, JSONArray args, CallbackContext callbackContext) {
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
      case "unlinkDeviceToken":
        unlinkDeviceToken(args, callbackContext);
        return true;
      case "registerDevice":
        registerDevice(args, callbackContext);
        return true;
      case "getUnreadMessagesCount":
        getUnreadMessagesCount(callbackContext);
        return true;
      case "setVersion":
        setVersion(args,callbackContext);
        return  true;
      case "setStatusBarColor":
        setStatusBarColor(args,callbackContext);
        return  true;
      case "setCloseButtonColor":
      setCloseButtonColor(args,callbackContext);
        return  true;
      case "setCustomLoaderURL":
        setCustomLoaderURL(args, callbackContext);
        return true;
      case "setDisableActionsOnLoad":
      setDisableActionsOnLoad(args, callbackContext);
        return true;
      case "useLiteVersion":
        useLiteVersion(args, callbackContext);
        return true;
      case "reloadBot":
        reloadBot();
        return true;
      case "setMicIconColor":
        setMicIconColor(args, callbackContext);
        return true; 
      case "setMicBackgroundColor":
        setMicBackgroundColor(args, callbackContext);
        return true;  
      case "setMicButtonMovable":
        setMicButtonMovable(args, callbackContext);
        return true;
      case "useSecureYmAuth":
        useSecureYmAuth(args,callbackContext);
        return true;
      case "revalidateToken":
        revalidateToken(args,callbackContext);
        return true;
      case "sendEventToBot":
        sendEventToBot(args,callbackContext);
        return true;
      case "setOpenLinkExternally":
        setOpenLinkExternally(args,callbackContext);
        return true;
      case "setThemeBotName":
        setThemeBotName(args,callbackContext);
        return true;
      case "setThemeBotDescription":
        setThemeBotDescription(args,callbackContext);
        return true;
      case "setThemePrimaryColor":
        setThemePrimaryColor(args,callbackContext);
        return true;
      case "setThemeSecondaryColor":
        setThemeSecondaryColor(args,callbackContext);
        return true;
      case "setThemeBotBubbleBackgroundColor":
        setThemeBotBubbleBackgroundColor(args,callbackContext);
        return true;
      case "setThemeBotIcon":
        setThemeBotIcon(args,callbackContext);
        return true;
      case "setThemeBotClickIcon":
        setThemeBotClickIcon(args,callbackContext);
        return true;
      case "setChatContainerTheme":
        setChatContainerTheme(args,callbackContext);
        return true;
      case "setThemeLinkColor":
        setThemeLinkColor(args,callbackContext);
        return true;
    }
    return false;
  }

  private void useLiteVersion(JSONArray args, CallbackContext callbackContext) {
    try {
      boolean shouldUseLiteVersion = args.getBoolean(0);
      ymChatService.useLiteVersion(shouldUseLiteVersion, callbackContext);
    } catch (Exception e) {
      Utils.genericErrorHelper(e, callbackContext);
    }
  }

  private void setDisableActionsOnLoad(JSONArray args, CallbackContext callbackContext) {
    try {
      boolean shouldDisableActionsOnLoad = args.getBoolean(0);
      ymChatService.setDisableActionsOnLoad(shouldDisableActionsOnLoad, callbackContext);
    } catch (Exception e) {
      Utils.genericErrorHelper(e, callbackContext);
    }
  }

  private void setCloseButtonColor(JSONArray args, CallbackContext callbackContext) {
    try {
      String color = args.getString(0);
      ymChatService.setCloseButtonColor(color, callbackContext);
    } catch (Exception e) {
      Utils.genericErrorHelper(e, callbackContext);
    }
  }

  private void setStatusBarColor(JSONArray args, CallbackContext callbackContext) {
    try {
      String color = args.getString(0);
      ymChatService.setStatusBarColor(color, callbackContext);
    } catch (Exception e) {
      Utils.genericErrorHelper(e, callbackContext);
    }
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

  public void reloadBot() {
    ymChatService.reloadBot();
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

  private void unlinkDeviceToken(JSONArray args, CallbackContext callbackContext) {
    try {
      String apiKey = args.getString(0);
      ymChatService.unlinkDeviceToken(apiKey, callbackContext);
    } catch (Exception e) {
      Utils.genericErrorHelper(e, callbackContext);
    }
  }

  private void registerDevice(JSONArray args, CallbackContext callbackContext) {
    try {
      String apiKey = args.getString(0);
      ymChatService.registerDevice(apiKey, callbackContext);
    } catch (Exception e) {
      Utils.genericErrorHelper(e, callbackContext);
    }
  }

  private void getUnreadMessagesCount(CallbackContext callbackContext) {
    try {
      ymChatService.getUnreadMessagesCount(callbackContext);
    } catch (Exception e) {
      Utils.genericErrorHelper(e, callbackContext);
    }
  }

  private void setVersion(JSONArray args, CallbackContext callbackContext) {
    try{
      int version = args.getInt(0);
      ymChatService.setVersion(version,callbackContext);
    }catch(Exception e)
    {
      Utils.genericErrorHelper(e, callbackContext);
    }
  }

  public void setCustomLoaderURL(JSONArray args, CallbackContext callbackContext) {
    try {
      String customUrl = args.getString(0);
      ymChatService.setCustomLoaderUrl(customUrl, callbackContext);
    } catch (Exception e) {
      Utils.genericErrorHelper(e, callbackContext);
    }
  }

  private void setMicIconColor(JSONArray args, CallbackContext callbackContext) {
    try {
      String color = args.getString(0);
      ymChatService.setMicIconColor(color, callbackContext);
    } catch (Exception e) {
      Utils.genericErrorHelper(e, callbackContext);
    }
  }

  private void setMicBackgroundColor(JSONArray args, CallbackContext callbackContext) {
    try {
      String color = args.getString(0);
      ymChatService.setMicBackgroundColor(color, callbackContext);
    } catch (Exception e) {
      Utils.genericErrorHelper(e, callbackContext);
    }
  }

  private void setMicButtonMovable(JSONArray args, CallbackContext callbackContext) {
    try {
      boolean shouldMicButtonMovable = args.getBoolean(0);
      ymChatService.setMicButtonMovable(shouldMicButtonMovable, callbackContext);
    } catch (Exception e) {
      Utils.genericErrorHelper(e, callbackContext);
    }
  }

  private void useSecureYmAuth(JSONArray args, CallbackContext callbackContext) {
    try {
      boolean shouldUseSecureYmAuth = args.getBoolean(0);
      ymChatService.useSecureYmAuth(shouldUseSecureYmAuth, callbackContext);
    } catch (Exception e) {
      Utils.genericErrorHelper(e, callbackContext);
    }
  }

  private void setOpenLinkExternally(JSONArray args, CallbackContext callbackContext) {
    try {
      boolean shouldOpenLinkExternally = args.getBoolean(0);
      ymChatService.setOpenLinkExternally(shouldOpenLinkExternally, callbackContext);
    } catch (Exception e) {
      Utils.genericErrorHelper(e, callbackContext);
    }
  }

  public void setThemeBotName(JSONArray args, CallbackContext callbackContext) {
    try {
      String botName = args.getString(0);
      ymChatService.setThemeBotName(botName, callbackContext);
    } catch (Exception e) {
      Utils.genericErrorHelper(e, callbackContext);
    }
  }

  public void setThemeBotDescription(JSONArray args, CallbackContext callbackContext) {
    try {
      String botDesc = args.getString(0);
      ymChatService.setThemeBotDescription(botDesc, callbackContext);
    } catch (Exception e) {
      Utils.genericErrorHelper(e, callbackContext);
    }
  }

  public void setThemePrimaryColor(JSONArray args, CallbackContext callbackContext) {
    try {
      String color = args.getString(0);
      ymChatService.setThemePrimaryColor(color, callbackContext);
    } catch (Exception e) {
      Utils.genericErrorHelper(e, callbackContext);
    }
  }

  public void setThemeSecondaryColor(JSONArray args, CallbackContext callbackContext) {
    try {
      String color = args.getString(0);
      ymChatService.setThemeSecondaryColor(color, callbackContext);
    } catch (Exception e) {
      Utils.genericErrorHelper(e, callbackContext);
    }
  }

  public void setThemeBotBubbleBackgroundColor(JSONArray args, CallbackContext callbackContext) {
    try {
      String color = args.getString(0);
      ymChatService.setThemeBotBubbleBackgroundColor(color, callbackContext);
    } catch (Exception e) {
      Utils.genericErrorHelper(e, callbackContext);
    }
  }

  public void setThemeBotIcon(JSONArray args, CallbackContext callbackContext) {
    try {
      String iconUrl = args.getString(0);
      ymChatService.setThemeBotIcon(iconUrl, callbackContext);
    } catch (Exception e) {
      Utils.genericErrorHelper(e, callbackContext);
    }
  }

  public void setThemeBotClickIcon(JSONArray args, CallbackContext callbackContext) {
    try {
      String iconUrl = args.getString(0);
      ymChatService.setThemeBotClickIcon(iconUrl, callbackContext);
    } catch (Exception e) {
      Utils.genericErrorHelper(e, callbackContext);
    }
  }

  public void setChatContainerTheme(JSONArray args, CallbackContext callbackContext) {
    try {
      String theme = args.getString(0);
      ymChatService.setChatContainerTheme(theme, callbackContext);
    } catch (Exception e) {
      Utils.genericErrorHelper(e, callbackContext);
    }
  }

  public void setThemeLinkColor(JSONArray args, CallbackContext callbackContext) {
    try {
      String color = args.getString(0);
      ymChatService.setThemeLinkColor(color, callbackContext);
    } catch (Exception e) {
      Utils.genericErrorHelper(e, callbackContext);
    }
  }

  private void revalidateToken(JSONArray args, CallbackContext callbackContext) {
    try {
      String token = args.getString(0);
      boolean refreshSession = args.getBoolean(1);
      ymChatService.revalidateToken(token, refreshSession, callbackContext);
    } catch (Exception e) {
      Utils.genericErrorHelper(e, callbackContext);
    }
  }
  
  private void sendEventToBot(JSONArray args, CallbackContext callbackContext) {
    try {
      String code = args.getString(0);
      JSONObject data = args.getJSONObject(1);
      ymChatService.sendEventToBot(code, data, callbackContext);
    } catch (Exception e) {
      Utils.genericErrorHelper(e, callbackContext);
    }
  }
}
