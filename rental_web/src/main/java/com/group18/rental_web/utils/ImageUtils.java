package com.group18.rental_web.utils;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public class ImageUtils {
    public static byte[] covertToBytes(MultipartFile file)  {
        try {
            return Base64.encodeBase64(file.getBytes());
        } catch (IOException e) {
            System.out.println(e.getLocalizedMessage());
        }
        return new byte[0];
    }

    public static String getBase64String(byte[] code) {
        return Base64.encodeBase64String(code);
    }
}
