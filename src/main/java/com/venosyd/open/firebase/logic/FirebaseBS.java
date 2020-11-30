package com.venosyd.open.firebase.logic;

import java.net.HttpURLConnection;
import java.util.List;

/**
 * @author sergio lisan <sels@venosyd.com>
 */
public interface FirebaseBS {

    /** */
    FirebaseBS INSTANCE = new FirebaseBSImpl();

    /** manda mensagem para varios tokens */
    void multicastMessageTo(List<String> tokens, String matter, String msg, String payload);

    /** manda mensagem para um token */
    void sendMessageTo(String token, String matter, String msg, String payload);

    /** send firebase request */
    HttpURLConnection sendRequest(String baseurl, String endpoint) throws Exception;

}