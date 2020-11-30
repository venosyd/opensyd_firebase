package com.venosyd.open.firebase.lib;

import com.venosyd.open.commons.util.ConfigReader;

/**
 * @author sergio lisan <sels@venosyd.com>
 */
public class FirebaseConfig extends ConfigReader {

    public static final FirebaseConfig INSTANCE = new FirebaseConfig();

    private FirebaseConfig() {
        super("firebase");
    }
}