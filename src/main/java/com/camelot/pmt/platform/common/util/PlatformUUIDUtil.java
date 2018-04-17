package com.camelot.pmt.platform.common.util;

import java.util.UUID;

public class PlatformUUIDUtil {

    public static String getUUID() {
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        return uuid;
    }

}
