package com.group18.rental_web.utils;

import java.util.Base64;

public class ImageUtils {

    public static String getBase64String(byte[] code) {
        // return Base64.encodeBase64String(code);
        return Base64.getMimeEncoder().encodeToString(code);
    }
}
