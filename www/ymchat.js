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
  unlinkDeviceToken: (botId, apiKey, deviceToken, success, failure) => {
    exec(success, failure, "ymchat", "unlinkDeviceToken", [botId, apiKey, deviceToken])
  },
  setVersion: (version) => {
    exec(null, null, "ymchat", "setVersion", [version])
  }
};

module.exports = YmChatAPI;