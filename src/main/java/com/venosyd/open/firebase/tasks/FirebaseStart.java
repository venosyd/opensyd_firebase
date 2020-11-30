package com.venosyd.open.firebase.tasks;

import java.io.FileInputStream;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.venosyd.open.firebase.lib.FirebaseUtil;
import com.venosyd.open.commons.util.Cache;

/**
 * @author sergio lisan <sels@venosyd.com>
 */
public class FirebaseStart implements Runnable {

    @Override
    public void run() {
        try {
            var configlocation = FirebaseUtil.getValue("config-location");
            var appcode = FirebaseUtil.getValue("app-code");

            FileInputStream serviceAccount = new FileInputStream(configlocation);
            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl("https://" + appcode + ".firebaseio.com").build();

            FirebaseApp.initializeApp(options);

            Cache.INSTANCE.set("FIREBASE_STARTED", true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}