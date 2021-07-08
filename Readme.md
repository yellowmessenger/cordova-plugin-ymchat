# ymchat

## Installation

### cordova

Specify the path to the plugin folder here my path is ../ymchat

```
ionic cordova plugin add ../ymchat
```

## Usage

import plugin

```javascript
const YmChat = cordova.plugins.ymchat;
```

### Set botId

```javascript
YmChat.setBotId("x1597301712805");
```

### Present chatbot

```javascript
YmChat.startBot(successCallBackFunc, failureCallBackFunc);
```

### Other configurations

Speech to text can be enabled and disabled by calling setEnableSpeech(). Default value is `false`

```javascript
YmChat.setEnableSpeech(true);
```

### Payload

Information can be passed from app to bot using payload.

The payload dictionary should be JSON compatible else an error will be thrown

```javascript
YmChat.setPayload({ "Developer name": "Purushottam" });
```

### History

Chat history can be enabled and disabled by calling setEnableHistory(). Default value is `false`

```javascript
YmChat.setEnableHistory(true);
```

### Event from bot

Bot can send events to the host app.

```javascript
YmChat.onEventFromBot((result) => {
  console.log(JSON.stringify(result));
});
```

## Close bot

Bot canbe closed by tapping on cross button at top, and they can be progrmatically closed using `closeBot()` function

```javascript
YmChat.showCloseBot(true);
```

## Close bot event

Bot close event is separetly sent and it can be handled in following way.

```javascript
YmChat.onBotClose((onBotCloseEvent) => {
  console.log(JSON.stringify(onBotCloseEvent));
});
```

## Push Notifications

ymchat-react-native supports firebase notifications. Push notifications needs `authentication token` and `device token`

### Authentication Token

Authentication token can be set using `setAuthenticationToken` method. Auth token can be a unique identifier like email or UUID

```javascript
YmChat.setAuthenticationToken("token");
```

### Device Token

Device token can be set using `setDeviceToken` method. Pass `fcmToken` as a parameter to this method.

```javascript
YmChat.setDeviceToken("token");
```

It is recommended to set authentication token and device token before `startChatbot()`

Note: Firebase service account key is required to send notifications. You can share the service account key with us. More info [here](https://developers.google.com/assistant/engagement/notifications#get_a_service_account_key)

## On-Prem Deployments

ymchat-react-native supports bots with on-prem deployments. For the bot to work, pass the on-prem URL to `setCustomURL()` method.

```javascript
YmChat.setCustomURL("https://your-on-prem-url.com");
```
