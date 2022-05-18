package org.mzd.retry.custom;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author: zhendong.mzd
 * @description:
 * @date: 2022/5/18 下午7:24
 */
public class CustomRetry {

    private static final Logger logger = LoggerFactory.getLogger(CustomRetry.class);

    public static final long RETRY_NUM_2 = 2L;
    public static final long RETRY_NUM_3 = 3l;

    public String getToken(){
        long delaySleepTime = 1000;
        long maxAttempts = 5;
        long retryCount = 1;
        do {
            try {
                //执行逻辑
                String token = "";
                if(StringUtils.isNotEmpty(token)){
                    return token;
                }
                throw new  RuntimeException("getTokenIsEmpty");
            } catch (Exception e) {
                logger.info("lastTime={}", delaySleepTime);
                try {
                    Thread.sleep(delaySleepTime);
                } catch (InterruptedException interruptedException) {
                    logger.error("getToken,sleepException={}", e);
                }
                retryCount++;
                if(retryCount==RETRY_NUM_2){
                    //第二次5分钟
                    delaySleepTime = 5 * delaySleepTime;
                }else if(retryCount==RETRY_NUM_3){
                    //第三次 30分钟
                    delaySleepTime = 6 * delaySleepTime;
                }
                logger.info("currentTime={}", delaySleepTime);
            }
        } while (retryCount <= maxAttempts);
        logger.error("getTokenIsEmpty,Exception");
        return null;
    }
}
