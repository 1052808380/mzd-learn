package org.mzd.deploy.controller;


import org.apache.commons.lang3.StringUtils;
import org.mzd.deploy.config.RedisUtil;
import org.mzd.deploy.dao.UserBaseInfoMapper;
import org.mzd.deploy.util.CommonThreadPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.ThreadPoolExecutor;

/**
 */
@RestController
@RequestMapping(value = "/api")
public class UserBaseInfoController {
    private static final Logger logger = LoggerFactory.getLogger(UserBaseInfoController.class);

   @Resource
   private UserBaseInfoMapper userBaseInfoMapper;

    @Resource
    private RedisUtil redisUtil;


    @RequestMapping(value = "/getName", method = RequestMethod.GET)
    public String getName(Long userId) {
       return userBaseInfoMapper.getById(userId);
    }


    @RequestMapping(value = "/getThreadPool", method = RequestMethod.GET)
    public void getThreadPool() {
        ThreadPoolExecutor commonExecutor = CommonThreadPool.getCommonExecutor();
        commonExecutor.execute(() -> {
            logger.info("==================1=========================================1======================={}");
            int i = 1;
            int i2 = 2;
            boolean b = i > i2;
            logger.info("===================2=========================================2======================{}");
        });
        logger.info("===================3=========================================3======================");
    }


    @RequestMapping("/putKey")
    @ResponseBody
    public String putKey(String user) {
        if (StringUtils.isEmpty(user)) {
            return "key must not empty";
        } else {
            if (redisUtil.set("user", user, 30000)) {
                return "success";
            }
        }
        return "fail";
    }

    @RequestMapping("/getKey")
    @ResponseBody
    public String getKey(String key) {
        if (StringUtils.isEmpty(key)) {
            return "key must not empty";
        } else {
            String user = (String) redisUtil.get(key);
            return user;
        }
    }
}
