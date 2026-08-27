# Changelog

All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [3.0.0]

### Added

- **V3 widget support** — upgraded the pinned native SDKs to the versions that render the V3 chatbot widget: Android `YMChatbot-Android` v2.22.0 → v3.4.0, iOS `YMChat` ~> 1.24.0 → ~> 2.3.0. Call `setVersion(3)` before `startChatbot()` to use it, same as `setVersion(2)` for the V2 bot.
- As a side effect of the native SDK bump, voice conversations now automatically keep the screen awake for their duration (native SDK behavior, no plugin API change).

### Changed

- No breaking changes — every existing bridged method/config property (`setBotId`, `setPayload`, `speechConfig`, `theme.*`, `revalidateToken`, `sendEventToBot`, etc.) was verified against the new native SDK versions before this bump; none were renamed, removed, or changed signature.
