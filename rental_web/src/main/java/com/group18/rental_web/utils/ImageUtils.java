package com.group18.rental_web.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public class ImageUtils {
    public static Byte[] covertToBytes(MultipartFile file) throws IOException {
        Byte[] bytes = new Byte[file.getBytes().length];
        int i = 0;
        for (byte b: file.getBytes()) {
            bytes[i++] = b;
        }
        return bytes;
    }
}
