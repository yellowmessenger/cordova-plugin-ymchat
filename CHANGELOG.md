# Changelog

All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [3.4.0]

### Added

- **Auto-Sent Initial User Message** — lets the host app automatically send a configured message as soon as the chat widget opens, rendered as a real outgoing user message, via `cordova.plugins.ymchat.setInitialUserMessage('...')`. Bridges the native SDKs' `initialUserMessage` config for the first time (`YMChatbot-Android` since v3.5.0, iOS `YMChat` since 2.4.0 — this plugin now requires those versions or newer).

## [3.3.0]

### Added

- **Configurable Upload Sources** (Android only) — restricts the attachment picker to specific sources via `cordova.plugins.ymchat.setAllowedUploadSources(['camera'])` (or `['file']`, or both). Bridges the native Android SDK's `allowedUploadSources` config for the first time (`YMChatbot-Android` since v3.3.0, already pinned since v3.0.0 of this plugin). No iOS equivalent exists natively, so calling this on iOS is a no-op — the plugin doesn't implement the selector there.

## [3.2.0]

### Added

- **`stopVoiceMode()`** — lets the host app programmatically end an active voice call, e.g. when a modal presentation covers the chatbot and the native view-lifecycle callback that would normally stop voice mode doesn't fire. Bridges the native SDKs' `stopVoiceMode()` for the first time (iOS `YMChat` since 2.2.0, Android `YMChatbot-Android` since v3.2.0 — both already pinned since v3.0.0 of this plugin).

## [3.1.0]

### Added

- **Activation Mode** — lets the host app open the widget directly into voice mode instead of chat, via `cordova.plugins.ymchat.setActivationMode('voice')` (or `'chat'`, the default). Bridges the native SDKs' `activationMode` config for the first time (iOS `YMChat` since 2.1.0, Android `YMChatbot-Android` since v3.1.0 — both already pinned since v3.0.0 of this plugin).

## [3.0.0]

### Added

- **V3 widget support** — upgraded the pinned native SDKs to the versions that render the V3 chatbot widget: Android `YMChatbot-Android` v2.22.0 → v3.4.0, iOS `YMChat` ~> 1.24.0 → ~> 2.3.0. Call `setVersion(3)` before `startChatbot()` to use it, same as `setVersion(2)` for the V2 bot.
- As a side effect of the native SDK bump, voice conversations now automatically keep the screen awake for their duration (native SDK behavior, no plugin API change).

### Changed

- No breaking changes — every existing bridged method/config property (`setBotId`, `setPayload`, `speechConfig`, `theme.*`, `revalidateToken`, `sendEventToBot`, etc.) was verified against the new native SDK versions before this bump; none were renamed, removed, or changed signature.
