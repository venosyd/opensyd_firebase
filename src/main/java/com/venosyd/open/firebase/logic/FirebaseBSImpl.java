package com.venosyd.open.firebase.logic;

import java.io.FileInputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.MulticastMessage;
import com.google.firebase.messaging.Notification;
import com.venosyd.open.commons.log.Debuggable;
import com.venosyd.open.firebase.lib.FirebaseUtil;

/**
 * @author sergio lisan <sels@venosyd.com>
 */
class FirebaseBSImpl implements FirebaseBS, Debuggable {

    /** */
    @Override
    public void multicastMessageTo(List<String> tokens, String matter, String msg, String payload) {
        try {
            var notification = new Notification(matter, msg);

            var message = MulticastMessage.builder().setNotification(notification)
                    .putData("click_action", "FLUTTER_NOTIFICATION_CLICK").putData("payload", payload)
                    .addAllTokens(tokens).build();

            FirebaseMessaging.getInstance().sendMulticast(message).getSuccessCount();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /** */
    @Override
    public void sendMessageTo(String token, String matter, String msg, String payload) {
        try {
            var notification = new Notification(matter, msg);

            var message = Message.builder().setNotification(notification)
                    .putData("click_action", "FLUTTER_NOTIFICATION_CLICK").putData("payload", payload).setToken(token)
                    .build();

            FirebaseMessaging.getInstance().send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /** send firebase request */
    public HttpURLConnection sendRequest(String baseurl, String endpoint) throws Exception {
        var url = new URL(baseurl + endpoint);

        var httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setRequestProperty("Authorization", "Bearer " + _getAccessToken());
        httpURLConnection.setRequestProperty("Content-Type", "application/json; UTF-8");

        return httpURLConnection;
    }

    /** firebase authorization tokens */
    private String _getAccessToken() throws Exception {
        var inputstream = new FileInputStream(FirebaseUtil.getValue("config-location"));
        var scopes = Arrays.asList("https://www.googleapis.com/auth/firebase.messaging");

        var googleCredential = GoogleCredential.fromStream(inputstream).createScoped(scopes);
        googleCredential.refreshToken();

        return googleCredential.getAccessToken();
    }
}