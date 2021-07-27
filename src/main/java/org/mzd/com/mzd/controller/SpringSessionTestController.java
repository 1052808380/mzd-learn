package org.mzd.com.mzd.controller;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.mzd.com.mzd.domain.User;
import org.mzd.com.mzd.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.Objects;
import java.net.URI;

import org.apache.http.HttpEntity;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClients;

/**
 * @author: zhendong.mzd
 * @description:
 * @date: 2021/7/8 下午12:41
 */
@Controller
public class SpringSessionTestController {
    @Autowired
    private UserService userService;

    @RequestMapping("/get/{name}")
    @ResponseBody
    public String getSesseion(HttpServletRequest request, @PathVariable("name") String name) {
        HttpSession session = request.getSession();
        String value = (String) session.getAttribute(name);
        return "sessionId:" + session.getId() + " value:" + value;
    }

    @RequestMapping("/index")
    String index(Model model) {
        model.addAttribute("user", new User());
        return "index";
    }


    @RequestMapping("/")
    String welcome(Model model) {


        model.addAttribute("user", new User());
        return "index";
    }

    @RequestMapping(value = "/learn")
    public void viewImages(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //使用字节流读取本地图片
        ServletOutputStream out = null;
        BufferedInputStream buf = null;
        //创建一个文件对象，对应的文件就是python把词云图片生成后的路径以及对应的文件名
        File file = new File("src/main/resources/static/images/learn.jpg");
        try {
            //使用输入读取缓冲流读取一个文件输入流
            buf = new BufferedInputStream(new FileInputStream(file));
            //利用response获取一个字节流输出对象
            out = response.getOutputStream();
            //定义个数组，由于读取缓冲流中的内容
            byte[] buffer = new byte[1024];
            //while循环一直读取缓冲流中的内容到输出的对象中
            while (buf.read(buffer) != -1) {
                out.write(buffer);
            }
            //写出到请求的地方
            out.flush();
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        } finally {
            if (buf != null) buf.close();
            if (out != null) out.close();
        }
    }

    @RequestMapping("/notVerify")
    @ResponseBody
    String notVerify() {
        return "username or password NOT correct";
    }

    @RequestMapping("/login")
    String login(Model model, HttpServletRequest request, HttpServletResponse response) {
        model.addAttribute("user", new User());
        return "login";
    }

    @RequestMapping("/register")
    String register(Model model, HttpServletRequest request, HttpServletResponse response) {
        model.addAttribute("user", new User());
        return "register";
    }

    @RequestMapping(value = "/registerUser", method = RequestMethod.POST)
    @ResponseBody
    String registerUser(User user, Model model) {
        return userService.registerUser(user);
    }

    @RequestMapping(value = "/userLogin", method = RequestMethod.POST)
    String userLogin(User user, Model model, HttpServletRequest request, HttpServletResponse response) {
//        request.setContentType("text/html;charset=gbk");
        User userDto = userService.getUser(user);
        if (Objects.nonNull(userDto)) {
            model.addAttribute("name", userDto.getName());
            model.addAttribute("userName", userDto.getUserName());
            model.addAttribute("userEName", userDto.getUserEName());
            model.addAttribute("avatar", userDto.getAvatar());
            model.addAttribute("nickName", userDto.getNickName());
            model.addAttribute("mobile", userDto.getMobile());

            request.getSession().setAttribute("accountId", userDto.getName());
            request.getSession().setAttribute("userName", userDto.getUserName());

            return "result";
        } else {
            return "redirect:/notVerify";
        }

    }


    /**
     * 所有的应用的"退出登录"的逻辑处理;
     *
     * @param request
     * @param model
     * @return
     */
    @RequestMapping("/logout")
    public String logout(HttpServletRequest request, Model model) {
        // todo 一旦登出，session清除
        // 使这个会话无效，然后解除绑定到它的任何对象。
        request.getSession().invalidate();
        model.addAttribute("user", new User());
        return "login";
    }


    /**
     * face++ 识别人脸接口
     *  https://console.faceplusplus.com.cn/documents/4888373
     * @return
     */
    @RequestMapping("/face")
    @ResponseBody
    public String face() {
        HttpClient httpclient = HttpClients.createDefault();
        String api_key = "y04LMzDivpFzU1kEjpNOWFPdj_MTZ93k";
        String api_secret = "bSQxIRkf45iQgxGLmwy9hx2UauQ2MukY";
        String image_url = "https://yungu-public.oss-cn-hangzhou.aliyuncs.com/123.JPG";
        String return_attributes = "gender,age,smiling,headpose,facequality,blur,eyestatus,emotion,ethnicity,beauty,mouthstatus,eyegaze,skinstatus";
        try {
            URIBuilder builder = new URIBuilder("https://api-cn.faceplusplus.com/facepp/v3/detect");
            URI uri = builder.build();
            HttpPost request = new HttpPost(uri);
            StringEntity reqEntity = new StringEntity("api_key=" + api_key + "&api_secret=" + api_secret + "&image_url=" + image_url + "&return_attributes=" + return_attributes + "");
            reqEntity.setContentType("application/x-www-form-urlencoded");
            request.setEntity(reqEntity);
            HttpResponse response = httpclient.execute(request);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                return EntityUtils.toString(entity);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
       return null;
    }
}

