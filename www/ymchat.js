var exec = require("cordova/exec");

const YmChatAPI = {
  setBotId: (botId) => {
    exec(null, null, "ymchat", "setBotId", [botId]);
  },
  setDeviceToken: () => {
    exec(null, null, "ymchat", "setDeviceToken", []);
  },
  setEnableSpeech: (speech) => {
    exec(null, null, "ymchat", "setEnableSpeech", [speech]);
  },
  setEnableHistory: (history) => {
    exec(null, null, "ymchat", "setEnableHistory", [history]);
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
  showCloseBot: (showCloseBot) => {
    exec(null, null, "ymchat", "showCloseBot", [showCloseBot]);
  },
  onEventFromBot: (eventListener) => {
    exec(eventListener, null, "ymchat", "onEventFromBot", []);
  },
  onBotClose: (onBotCloseEvent) => {
    exec(onBotCloseEvent, null, "ymchat", "onEventFromBot", []);
  },
  startBot: (success, error) => {
    exec(success, error, "ymchat", "startBot", []);
  },
  closeBot: () => {
    exec(null, null, "ymchat", "closeBot", []);
  },
};

module.exports = YmChatAPI;
