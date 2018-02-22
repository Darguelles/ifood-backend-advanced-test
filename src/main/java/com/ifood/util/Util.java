package com.ifood.util;

import java.util.Base64;

public class Util {

    public static String getBase64EncodedString(String value) {
        return Base64.getEncoder().encode("810d7196f9dd4b19b7dcf3c07d3dbc84:bb683aaf8f234e2a9774e9b60bf2b6fa".getBytes()).toString();
    }

}
