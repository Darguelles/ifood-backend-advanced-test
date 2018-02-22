package com.ifood.util;

import java.util.Base64;

public class Util {

    public static String getBase64EncodedString(String value) {
        return Base64.getEncoder().encodeToString(value.getBytes());
    }

}
