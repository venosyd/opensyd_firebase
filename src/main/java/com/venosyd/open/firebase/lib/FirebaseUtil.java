package com.venosyd.open.firebase.lib;

/**
 * @author sergio lisan <sels@venosyd.com>
 */
public abstract class FirebaseUtil {

    /**
     * retorna o valor de uma chave de configuracao do firebase no config.yaml
     */
    public static String getValue(String key) {
        return (String) FirebaseConfig.INSTANCE.get(key);
    }
}