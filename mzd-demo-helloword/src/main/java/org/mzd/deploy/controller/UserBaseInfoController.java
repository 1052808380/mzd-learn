package org.mzd.deploy.controller;


import com.github.promeg.pinyinhelper.Pinyin;
import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
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

    public static void main(String[] args) {
//        String pingYin = toPinyin("胡彦熖");
        System.out.println("");


        String 胡彦熖 = Pinyin.toPinyin("胡彦熖", null);


        System.out.println("");

    }

    /**
     * 将字符串中的中文转化为拼音,英文字符不变
     *
     * @param inputString 汉字
     * @return
     */
    public static String getPingYin(String inputString) {
        logger.info("-------getPingYin----------inputString={}", inputString);
        StringBuilder output = new StringBuilder();
        try {
            HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
            format.setCaseType(HanyuPinyinCaseType.LOWERCASE);
            format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
            format.setVCharType(HanyuPinyinVCharType.WITH_V);

            if (inputString != null && inputString.length() > 0
                    && !"null".equals(inputString)) {
                char[] input = inputString.trim().toCharArray();
                try {
                    for (int i = 0; i < input.length; i++) {
                        if (Character.toString(input[i]).matches("[\\u4E00-\\u9FA5]+")) {
                            String[] temp = PinyinHelper.toHanyuPinyinStringArray(input[i], format);
                            if(null==temp||temp.length==0){
                                output.append(input[i]);
                            }else {
                                output.append(temp[0]);
                            }
                        } else {
                            output.append(input[i]);
                        }
                    }
                } catch (Exception e) {
                    logger.error("getPingYin={}", e);
                }
            } else {
                return "*";
            }
        } catch (Exception e) {
            logger.error("getPingYin={}", e);
        }
        return output.toString();
    }

    /**
     * 汉字转为拼音
     * @param chinese
     * @return
     */
    public static String toPinyin(String chinese){
        String pinyinStr = "";
        char[] newChar = chinese.toCharArray();
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
        defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        for (int i = 0; i < newChar.length; i++) {
            if (newChar[i] > 128) {
                try {
                    pinyinStr += PinyinHelper.toHanyuPinyinStringArray(newChar[i], defaultFormat)[0];
                } catch (Exception e) {
                    pinyinStr = pinyinStr +  newChar[i];
                }
            }else{
                pinyinStr += newChar[i];
            }
        }
        return pinyinStr;
    }









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
