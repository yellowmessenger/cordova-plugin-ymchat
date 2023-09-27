/********* ymchat.m Cordova Plugin Implementation *******/

#import <Cordova/CDV.h>

@import YMChat;

@interface ymchat : CDVPlugin<YMChatDelegate> 
{
    CDVInvokedUrlCommand* onEvent;
    CDVInvokedUrlCommand* onBotClosed;
}

@end

@implementation ymchat
- (void)setBotId:(CDVInvokedUrlCommand*)command
{
    NSString* botId = [command.arguments objectAtIndex:0];
    YMConfig *config = [[YMConfig alloc] initWithBotId:botId];
    YMChat.shared.config = config;
    YMChat.shared.enableLogging = true;
}

- (void)setDeviceToken:(CDVInvokedUrlCommand*)command
{
    NSString* deviceToken = [command.arguments objectAtIndex:0];
    assert(YMChat.shared.config != nil);
    YMChat.shared.config.deviceToken = deviceToken;
}

- (void)setEnableSpeech:(CDVInvokedUrlCommand*)command
{
    BOOL enableSpeech = [command.arguments objectAtIndex:0];
    assert(YMChat.shared.config != nil);
    YMChat.shared.config.enableSpeech = enableSpeech;
}

- (void)setAuthenticationToken:(CDVInvokedUrlCommand*)command
{
    NSString* authToken = [command.arguments objectAtIndex:0];
    assert(YMChat.shared.config != nil);
    YMChat.shared.config.ymAuthenticationToken = authToken;
}

- (void)showCloseButton:(CDVInvokedUrlCommand*)command
{
    BOOL closeBot = [command.arguments objectAtIndex:0];
    assert(YMChat.shared.config != nil);
    YMChat.shared.config.showCloseButton = closeBot;
}

- (void)setCustomURL:(CDVInvokedUrlCommand*)command
{
    NSString* url = [command.arguments objectAtIndex:0];
    assert(YMChat.shared.config != nil);
    YMChat.shared.config.customBaseUrl = url;
}

- (void)setPayload:(CDVInvokedUrlCommand*)command
{
    NSDictionary* payload = [command.arguments objectAtIndex:0];
    assert(YMChat.shared.config != nil);
    YMChat.shared.config.payload = payload;
}

- (void)showCloseBot:(CDVInvokedUrlCommand*)command
{
    BOOL enableSpeech = [command.arguments objectAtIndex:0];
    assert(YMChat.shared.config != nil);
    YMChat.shared.config.enableSpeech = enableSpeech;
}

- (void)onEventFromBot:(CDVInvokedUrlCommand*)command
{
    onEvent = command;
}

- (void)onBotClose:(CDVInvokedUrlCommand*)command
{
    onBotClosed = command;
}

- (void)startChatbot:(CDVInvokedUrlCommand*)command
{
    assert(YMChat.shared.config != nil);
    YMChat.shared.delegate = self;
    [[YMChat shared] startChatbotWithAnimated:YES error: nil completion:nil];
}

- (void)closeBot:(CDVInvokedUrlCommand*)command
{
    [[YMChat shared] closeBot];
}

- (void)reloadBot:(CDVInvokedUrlCommand*)command
{
    [[YMChat shared] reloadBotAndReturnError:nil];
}

- (void)onEventFromBotWithResponse:(YMBotEventResponse *)response {
    CDVPluginResult* pluginResult = nil;
    NSMutableDictionary *event = [[NSMutableDictionary alloc]initWithCapacity:10];
    [event setObject:response.code forKey:@"code"];
    if(response.data){
        [event setObject:response.data forKey:@"data"];
    }
    pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsDictionary:event];
    [pluginResult setKeepCallbackAsBool:YES];
    [self.commandDelegate sendPluginResult:pluginResult callbackId:onEvent.callbackId];
}

- (void) onBotClose {
    CDVPluginResult* pluginResult = nil;
    pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsString:@"Hello"];
    [pluginResult setKeepCallbackAsBool:YES];
    [self.commandDelegate sendPluginResult:pluginResult callbackId:onBotClosed.callbackId];
}

- (void)setVersion:(CDVInvokedUrlCommand*)command
{
    assert(YMChat.shared.config != nil);
    NSNumber* version = [command.arguments objectAtIndex:0];
    YMChat.shared.config.version = version.integerValue;
}

- (void) unlinkDeviceToken:(CDVInvokedUrlCommand*)command {
    if(YMChat.shared.config) {
        NSString* apiKey = [command.arguments objectAtIndex:0];
        [[YMChat shared] unlinkDeviceTokenWithApiKey:apiKey ymConfig:YMChat.shared.config success:^{
            CDVPluginResult* pluginResult = nil;
            pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK];
            [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
        } failure:^(NSString * _Nonnull failureMessage) {
            CDVPluginResult* pluginResult = nil;
            pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_ERROR messageAsString:failureMessage];
            [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
        }];
    } else {
        CDVPluginResult* pluginResult = nil;
        pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_ERROR messageAsString:@"Bot id not found"];
        [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
    }
}

- (void) registerDevice:(CDVInvokedUrlCommand*)command {
    NSString* apiKey = [command.arguments objectAtIndex:0];
    if(YMChat.shared.config) {
        [[YMChat shared] registerDeviceWithApiKey:apiKey ymConfig:YMChat.shared.config success:^{
            CDVPluginResult* pluginResult = nil;
            pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK];
            [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
        } failure:^(NSString * _Nonnull failureMessage) {
            CDVPluginResult* pluginResult = nil;
            pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_ERROR messageAsString:failureMessage];
            [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
        }];
    } else {
        CDVPluginResult* pluginResult = nil;
        pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_ERROR messageAsString:@"Bot id not found"];
        [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
    }
}

- (void) getUnreadMessagesCount:(CDVInvokedUrlCommand*)command {
    if(YMChat.shared.config) {
        [[YMChat shared] getUnreadMessagesCountWithYmConfig:YMChat.shared.config success:^(NSString * _Nonnull unreadCount) {
            CDVPluginResult* pluginResult = nil;
            pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsString:unreadCount];
            [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
        } failure:^(NSString * _Nonnull failureMessage) {
            CDVPluginResult* pluginResult = nil;
            pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_ERROR messageAsString:failureMessage];
            [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
        }];
    } else {
        CDVPluginResult* pluginResult = nil;
        pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_ERROR messageAsString:@"Bot id not found"];
        [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
    }
}

- (void)setCustomLoaderURL:(CDVInvokedUrlCommand*)command
{
    NSString* url = [command.arguments objectAtIndex:0];
    assert(YMChat.shared.config != nil);
    YMChat.shared.config.customLoaderUrl = url;
}

- (void)setStatusBarColor:(CDVInvokedUrlCommand*)command
{
    NSString* color = [command.arguments objectAtIndex:0];
    assert(YMChat.shared.config != nil);
    YMChat.shared.config.statusBarColor = [self getColorFromHexString:color];
}

- (void)setCloseButtonColor:(CDVInvokedUrlCommand*)command
{
    NSString* color = [command.arguments objectAtIndex:0];
    assert(YMChat.shared.config != nil);
    YMChat.shared.config.closeButtonColor = [self getColorFromHexString:color];
}

- (void)setDisableActionsOnLoad:(CDVInvokedUrlCommand*)command
{
    BOOL shouldDisableActionsOnLoad = [command.arguments objectAtIndex:0];
    assert(YMChat.shared.config != nil);
    YMChat.shared.config.disableActionsOnLoad = shouldDisableActionsOnLoad;
}

- (void)useLiteVersion:(CDVInvokedUrlCommand*)command
{
    BOOL shouldUseLiteVersion = [command.arguments objectAtIndex:0];
    assert(YMChat.shared.config != nil);
    YMChat.shared.config.useLiteVersion = shouldUseLiteVersion;
}

- (void)setMicIconColor:(CDVInvokedUrlCommand*)command
{
    NSString* color = [command.arguments objectAtIndex:0];
    assert(YMChat.shared.config != nil);
    YMChat.shared.config.enableSpeechConfig.fabIconColor = [self getColorFromHexString:color];
}

- (void)setMicBackgroundColor:(CDVInvokedUrlCommand*)command
{
    NSString* color = [command.arguments objectAtIndex:0];
    assert(YMChat.shared.config != nil);
    YMChat.shared.config.enableSpeechConfig.fabBackgroundColor = [self getColorFromHexString:color];
}

- (void)useSecureYmAuth:(CDVInvokedUrlCommand*)command
{
    BOOL shouldUseSecureYmAuth = [command.arguments objectAtIndex:0];
    assert(YMChat.shared.config != nil);
    YMChat.shared.config.useSecureYmAuth = shouldUseSecureYmAuth;
}

- (void)revalidateToken:(CDVInvokedUrlCommand*)command
{
    NSString* token = [command.arguments objectAtIndex:0];
    BOOL refreshSession = [command.arguments objectAtIndex:1];
    [[YMChat shared] revalidateTokenWithToken:token refreshSession:refreshSession error:nil];
}

- (void)sendEventToBot:(CDVInvokedUrlCommand*)command
{
    NSString* code = [command.arguments objectAtIndex:0];
    NSDictionary* data = [command.arguments objectAtIndex:1];
    YMEventModel* event = [[YMEventModel alloc] initWithCode:code data:data];
    [[YMChat shared] sendEventToBotWithEvent:event error:nil];
}

- (UIColor *)getColorFromHexString:(NSString *)hexString {
    unsigned rgbValue = 0;
    NSScanner *scanner = [NSScanner scannerWithString:hexString];
    if ([(NSString*) hexString characterAtIndex:0] == '#') {
        [scanner setScanLocation:1]; // bypass '#' character
    } else {
        [scanner setScanLocation:0];
    }
    [scanner scanHexInt:&rgbValue];
    return [UIColor colorWithRed:((rgbValue & 0xFF0000) >> 16)/255.0 green:((rgbValue & 0xFF00) >> 8)/255.0 blue:(rgbValue & 0xFF)/255.0 alpha:1.0];
}

@end
