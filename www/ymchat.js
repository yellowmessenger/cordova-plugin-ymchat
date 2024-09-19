var exec = require("cordova/exec");

const YmChatAPI = {
  setBotId: (botId) => {
    console.log("setting bot id");
    exec(null, null, "ymchat", "setBotId", [botId]);
  },
  setDeviceToken: (token) => {
    exec(null, null, "ymchat", "setDeviceToken", [token]);
  },
  setEnableSpeech: (speech) => {
    exec(null, null, "ymchat", "setEnableSpeech", [speech]);
  },
  setAuthenticationToken: (token) => {
    exec(null, null, "ymchat", "setAuthenticationToken", [token]);
  },
  showCloseButton: (closeBot) => {
    exec(null, null, "ymchat", "showCloseButton", [closeBot]);
  },
  setCustomURL: (url) => {
    exec(null, null, "ymchat", "setCustomURL", [url]);
  },
  setPayload: (payload) => {
    exec(null, null, "ymchat", "setPayload", [payload]);
  },
  onEventFromBot: (eventListener) => {
    exec(eventListener, null, "ymchat", "onEventFromBot", []);
  },
  onBotClose: (onBotCloseEvent) => {
    exec(onBotCloseEvent, null, "ymchat", "onBotClose", []);
  },
  startChatbot: (success, failure) => {
    exec(success, failure, "ymchat", "startChatbot", []);
  },
  closeBot: () => {
    exec(null, null, "ymchat", "closeBot", []);
  },
  unlinkDeviceToken: (apiKey, success, failure) => {
    exec(success, failure, "ymchat", "unlinkDeviceToken", [apiKey])
  },
  registerDevice: (apiKey, success, failure) => {
    exec(success, failure, "ymchat", "registerDevice", [apiKey])
  },
  getUnreadMessagesCount: (success, failure) => {
    exec(success, failure, "ymchat", "getUnreadMessagesCount", [])
  },
  setVersion: (version) => {
    exec(null, null, "ymchat", "setVersion", [version])
  },
  setCustomLoaderURL: (url) => {
    exec(null, null, "ymchat", "setCustomLoaderURL", [url]);
  },
  setStatusBarColor: (color) => {
    exec(null, null, "ymchat", "setStatusBarColor", [color]);
  },
  setCloseButtonColor: (color) => {
    exec(null, null, "ymchat", "setCloseButtonColor", [color]);
  },
  setDisableActionsOnLoad: (shouldDisableActionsOnLoad) => {
    exec(null, null, "ymchat", "setDisableActionsOnLoad", [shouldDisableActionsOnLoad]);
  },
  useLiteVersion: (shouldUseLiteVersion) => {
    exec(null, null, "ymchat", "useLiteVersion", [shouldUseLiteVersion]);
  },
  reloadBot: () => {
    exec(null, null, "ymchat", "reloadBot", []);
  },
  setMicIconColor: (color) => {
    exec(null, null, "ymchat", "setMicIconColor", [color]);
  },
  setMicBackgroundColor: (color) => {
    exec(null, null, "ymchat", "setMicBackgroundColor", [color]);
  },
  useSecureYmAuth: (shouldUseSecureYmAuth) => {
    exec(null, null, "ymchat", "useSecureYmAuth", [shouldUseSecureYmAuth]);
  },
  setThemeBotName: (name) => {
    exec(null, null, "ymchat", "setThemeBotName", [name]);
  },
  setThemeBotDescription: (description) => {
    exec(null, null, "ymchat", "setThemeBotDescription", [description]);
  },
  setThemePrimaryColor: (color) => {
    exec(null, null, "ymchat", "setThemePrimaryColor", [color]);
  },
  setThemeSecondaryColor: (color) => {
    exec(null, null, "ymchat", "setThemeSecondaryColor", [color]);
  },
  setThemeBotBubbleBackgroundColor: (color) => {
    exec(null, null, "ymchat", "setThemeBotBubbleBackgroundColor", [color]);
  },
  setThemeBotIcon: (iconUrl) => {
    exec(null, null, "ymchat", "setThemeBotIcon", [iconUrl]);
  },
  setThemeBotClickIcon: (iconUrl) => {
    exec(null, null, "ymchat", "setThemeBotClickIcon", [iconUrl]);
  },
  setChatContainerTheme: (theme) => {
    exec(null, null, "ymchat", "setChatContainerTheme", [theme]);
  },
  revalidateToken: (token, refreshSession) => {
    exec(null, null, "ymchat", "revalidateToken", [token, refreshSession]);
  },
  sendEventToBot: (code, data) => {
    exec(null, null, "ymchat", "sendEventToBot", [code, data]);
  }
};

module.exports = YmChatAPI;