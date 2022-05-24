package org.mzd.retry.controller;

import org.mzd.retry.custom.CustomRetry;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author: zhendong.mzd
 * @description:
 * @date: 2022/5/18 下午7:29
 */
@RestController
@RequestMapping(value = "/api")
public class RetryController {
    @Resource
    private CustomRetry customRetry;


    @RequestMapping(value = "/retry", method = RequestMethod.GET)
    public String retry() {
        return customRetry.getToken();
    }

}
