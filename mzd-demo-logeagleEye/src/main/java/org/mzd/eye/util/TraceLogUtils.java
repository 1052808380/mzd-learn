package org.mzd.eye.util;

import java.util.UUID;

/**
 * @author: zhendong.mzd
 * @description:
 * @date: 2022/5/18 下午4:09
 */
public class TraceLogUtils {
    public static String getTraceId() {
        return UUID.randomUUID().toString();
    }
}
