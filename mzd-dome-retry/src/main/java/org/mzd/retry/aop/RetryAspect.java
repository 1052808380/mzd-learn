package org.mzd.retry.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.mzd.retry.template.RetryTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by yihui on 2017/8/30.
 */
@Aspect
@Component
@Slf4j
public class RetryAspect {

    ExecutorService executorService = new ThreadPoolExecutor(3, 5,
            1, TimeUnit.MINUTES,
            new LinkedBlockingQueue<Runnable>());


    @Around(value = "@annotation(retryDot)")
    public Object execute(ProceedingJoinPoint joinPoint, RetryDot retryDot) throws Exception {
        RetryTemplate retryTemplate = new RetryTemplate() {
            @Override
            protected Object doBiz() throws Throwable {
                return joinPoint.proceed();
            }
        };

        retryTemplate.setRetryCount(retryDot.count())
                .setSleepTime(retryDot.sleep());


        if (retryDot.asyn()) {
            return retryTemplate.submit(executorService);
        } else {
            return retryTemplate.execute();
        }
    }


}
