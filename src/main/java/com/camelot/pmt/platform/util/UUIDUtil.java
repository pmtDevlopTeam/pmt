package com.camelot.pmt.platform.util;

import java.util.UUID;

public class UUIDUtil {

    public static String getUUID() {
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        return uuid;
    }

}
