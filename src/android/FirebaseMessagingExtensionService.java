package ca.koho.cordova.plugin.firebase.messaging;

import android.annotation.SuppressLint;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Map;

import io.intercom.android.sdk.push.IntercomPushClient;

public class FirebaseMessagingExtensionService extends FirebaseMessagingService {
    private final IntercomPushClient intercomPushClient = new IntercomPushClient();
    private static final String TAG = "FirebaseMessagingExtensionService";

    @SuppressLint("LongLogTag")
    @Override
    public void onCreate() {
        Log.d(TAG, "onCreate");
    }

    @SuppressLint("LongLogTag")
    @Override
    public void onNewToken(String token) {
        Log.d(TAG, "onNewToken");
        intercomPushClient.sendTokenToIntercom(getApplication(), token);
    }

    @SuppressLint("LongLogTag")
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
       Log.d(TAG, "onMessageReceived");
        Map message = remoteMessage.getData();
        if (intercomPushClient.isIntercomPush(message)) {
            intercomPushClient.handlePush(getApplication(), message);
        }
    }
}