package org.mzd.eye.interceptor;
import org.mzd.eye.constant.Constants;
import org.mzd.eye.util.TraceLogUtils;
import org.slf4j.MDC;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登陆拦截器，为所有请求添加一个traceId
 *
 * @author hetiantian
 * @version 1.0
 * @Date 2020/03/14 10:20
 */
public class LogInterceptor  extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        MDC.put(Constants.LOG_TRACE_ID, TraceLogUtils.getTraceId());
        return true;
    }
    @Override
    public void afterCompletion(HttpServletRequest request,HttpServletResponse response,Object handler,Exception ex ){
        MDC.remove(Constants.LOG_TRACE_ID);
        return;
    }
}
