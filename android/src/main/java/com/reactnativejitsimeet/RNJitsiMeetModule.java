package com.reactnativejitsimeet;

import android.util.Log;
import java.net.URL;
import java.net.MalformedURLException;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.bridge.ReadableMap;

@ReactModule(name = RNJitsiMeetModule.MODULE_NAME)
public class RNJitsiMeetModule extends ReactContextBaseJavaModule {
    public static final String MODULE_NAME = "RNJitsiMeetModule";
    private IRNJitsiMeetViewReference mJitsiMeetViewReference;

    public RNJitsiMeetModule(ReactApplicationContext reactContext, IRNJitsiMeetViewReference jitsiMeetViewReference) {
        super(reactContext);
        mJitsiMeetViewReference = jitsiMeetViewReference;
    }

    @Override
    public String getName() {
        return MODULE_NAME;
    }

    @ReactMethod
    public void initialize() {
        Log.d("JitsiMeet", "Initialize is deprecated in v2");
    }

    @ReactMethod
    public void call(String url, ReadableMap userInfo, ReadableMap meetOptions, ReadableMap meetFeatureFlags) {
        UiThreadUtil.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (mJitsiMeetViewReference.getJitsiMeetView() != null) {
                    RNJitsiMeetUserInfo _userInfo = new RNJitsiMeetUserInfo();
                    if (userInfo != null) {
                        if (userInfo.hasKey("displayName")) {
                            _userInfo.setDisplayName(userInfo.getString("displayName"));
                          }
                          if (userInfo.hasKey("email")) {
                            _userInfo.setEmail(userInfo.getString("email"));
                          }
                          if (userInfo.hasKey("avatar")) {
                            String avatarURL = userInfo.getString("avatar");
                            try {
                                _userInfo.setAvatar(new URL(avatarURL));
                            } catch (MalformedURLException e) {
                            }
                          }
                    }
                    RNJitsiMeetConferenceOptions options = new RNJitsiMeetConferenceOptions.Builder()
                        .setRoom(url)
                        .setToken(meetOptions.getString("token"))
                        .setSubject(meetOptions.getString("subject"))
                        .setAudioMuted(meetOptions.getBoolean("audioMuted"))
                        .setAudioOnly(meetOptions.getBoolean("audioOnly"))
                        .setVideoMuted(meetOptions.getBoolean("videoMuted"))
                        .setUserInfo(_userInfo)
                        
                        .setFeatureFlag("add-people.enabled", meetFeatureFlags.getBoolean("add-people.enabled"))
                        .setFeatureFlag("calendar.enabled", meetFeatureFlags.getBoolean("calendar.enabled"))
                        .setFeatureFlag("call-integration.enabled", meetFeatureFlags.getBoolean("call-integration.enabled"))
                        .setFeatureFlag("close-captions.enabled", meetFeatureFlags.getBoolean("close-captions.enabled"))
                        .setFeatureFlag("conference-timer.enabled", meetFeatureFlags.getBoolean("conference-timer.enabled"))
                        .setFeatureFlag("chat.enabled", meetFeatureFlags.getBoolean("chat.enabled"))
                        .setFeatureFlag("invite.enabled", meetFeatureFlags.getBoolean("invite.enabled"))
                        .setFeatureFlag("kick-out.enabled", meetFeatureFlags.getBoolean("kick-out.enabled"))
                        .setFeatureFlag("live-streaming.enabled", meetFeatureFlags.getBoolean("live-streaming.enabled"))
                        .setFeatureFlag("meeting-name.enabled", meetFeatureFlags.getBoolean("meeting-name.enabled"))
                        .setFeatureFlag("meeting-password.enabled", meetFeatureFlags.getBoolean("meeting-password.enabled"))
                        .setFeatureFlag("pip.enabled", meetFeatureFlags.getBoolean("pip.enabled"))
                        .setFeatureFlag("raise-hand.enabled", meetFeatureFlags.getBoolean("raise-hand.enabled"))
                        .setFeatureFlag("recording.enabled", meetFeatureFlags.getBoolean("recording.enabled"))
                        .setFeatureFlag("server-url-change.enabled", meetFeatureFlags.getBoolean("server-url-change.enabled"))
                        .setFeatureFlag("tile-view.enabled", meetFeatureFlags.getBoolean("tile-view.enabled"))
                        .setFeatureFlag("toolbox.alwaysVisible", meetFeatureFlags.getBoolean("toolbox.alwaysVisible"))
                        .setFeatureFlag("toolbox.enabled", meetFeatureFlags.getBoolean("toolbox.enabled"))
                        .setFeatureFlag("video-share.enabled", meetFeatureFlags.getBoolean("video-share.enabled"))
                        .setFeatureFlag("welcomepage.enabled", meetFeatureFlags.getBoolean("welcomepage.enabled"))
                        .setFeatureFlag("filmstrip.enabled", meetFeatureFlags.getBoolean("filmstrip.enabled"))
                        .setFeatureFlag("audio-mute.enabled", meetFeatureFlags.getBoolean("audio-mute.enabled"))
                        .setFeatureFlag("audio-mute.enabled", meetFeatureFlags.getBoolean("audio-mute.enabled"))
                        .setFeatureFlag("video-mute.enabled", meetFeatureFlags.getBoolean("video-mute.enabled"))
                        .setFeatureFlag("overflow-menu.enabled", meetFeatureFlags.getBoolean("overflow-menu.enabled")) 
                        .build();
                    mJitsiMeetViewReference.getJitsiMeetView().join(options);
                }
            }
        });
    }

    @ReactMethod
    public void audioCall(String url, ReadableMap userInfo) {
        UiThreadUtil.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (mJitsiMeetViewReference.getJitsiMeetView() != null) {
                    RNJitsiMeetUserInfo _userInfo = new RNJitsiMeetUserInfo();
                    if (userInfo != null) {
                        if (userInfo.hasKey("displayName")) {
                            _userInfo.setDisplayName(userInfo.getString("displayName"));
                          }
                          if (userInfo.hasKey("email")) {
                            _userInfo.setEmail(userInfo.getString("email"));
                          }
                          if (userInfo.hasKey("avatar")) {
                            String avatarURL = userInfo.getString("avatar");
                            try {
                                _userInfo.setAvatar(new URL(avatarURL));
                            } catch (MalformedURLException e) {
                            }
                          }
                    }
                    RNJitsiMeetConferenceOptions options = new RNJitsiMeetConferenceOptions.Builder()
                            .setRoom(url)
                            .setAudioOnly(true)
                            .setUserInfo(_userInfo)
                            .build();
                    mJitsiMeetViewReference.getJitsiMeetView().join(options);
                }
            }
        });
    }

    @ReactMethod
    public void endCall() {
        UiThreadUtil.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (mJitsiMeetViewReference.getJitsiMeetView() != null) {
                    mJitsiMeetViewReference.getJitsiMeetView().leave();
                }
            }
        });
    }
}
