package com.mybatis.util;

import java.util.UUID;

public class IDUtils {
    public static String getUUId() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}
