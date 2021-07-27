package org.mzd.com.mzd.service;

import org.mzd.com.mzd.util.HttpRequestUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author: zhendong.mzd
 * @description: face++所需要参数
 * @date: 2021/7/21 下午3:16
 */

public class FaceTest {

    public static void main(String[] args) {

        Map<String,String> map = new HashMap<>();

        map.put("api_key","y04LMzDivpFzU1kEjpNOWFPdj_MTZ93k");
        map.put("api_secret","bSQxIRkf45iQgxGLmwy9hx2UauQ2MukY");
        map.put("image_url","https://yungu-public.oss-cn-hangzhou.aliyuncs.com/11111.png");
        map.put("return_attributes","gender,age,smiling,headpose,facequality,blur,eyestatus,emotion,ethnicity,beauty,mouthstatus,eyegaze,skinstatus");

        String s = HttpRequestUtil.httpPost("https://api-cn.faceplusplus.com/facepp/v3/detect", Objects.toString(map));
        System.out.printf("-==========="+s);


    }











}
