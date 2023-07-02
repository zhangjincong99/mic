package com.example.yin.controller;

import com.example.yin.common.FatalMessage;
import com.example.yin.common.ErrorMessage;
import com.example.yin.common.SuccessMessage;
import com.example.yin.common.WarningMessage;
import com.example.yin.constant.Constants;
import com.example.yin.domain.Consumer;
import com.example.yin.service.impl.ConsumerServiceImpl;

import org.apache.commons.lang3.ObjectUtils.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/*
以下是该类中的方法功能描述：

addUser 方法用于用户注册。它从请求中获取用户的注册信息，创建 Consumer 对象，并调用 consumerService.addUser(consumer) 方法将用户信息添加到数据库中进行注册。

loginStatus 方法用于判断用户登录状态。它从请求中获取用户名和密码，调用 consumerService.veritypasswd(username, password) 方法验证用户名和密码是否匹配。如果匹配成功，将用户名存入会话（Session）中，并返回登录成功的消息和用户信息。

allUser 方法用于返回所有用户信息。调用 consumerService.allUser() 方法查询数据库中的所有用户记录，并返回查询结果。

userOfId 方法用于返回指定 ID 的用户信息。从请求中获取用户 ID，调用 consumerService.userOfId(Integer.parseInt(id)) 方法查询数据库中指定 ID 的用户记录，并返回查询结果。

deleteUser 方法用于删除用户。从请求中获取用户 ID，调用 consumerService.deleteUser(Integer.parseInt(id)) 方法删除数据库中指定 ID 的用户记录。

updateUserMsg 方法用于更新用户信息。从请求中获取用户的修改信息，创建 Consumer 对象，并调用 consumerService.updateUserMsg(consumer) 方法更新数据库中的用户信息。

updatePassword 方法用于更新用户密码。从请求中获取用户的原密码和新密码，验证原密码是否匹配，然后调用 consumerService.updatePassword(consumer) 方法更新数据库中的用户密码。

updateUserPic 方法用于更新用户头像。从请求中获取上传的头像文件和用户 ID，将头像文件保存到指定路径，并调用 consumerService.updateUserAvator(consumer) 方法更新数据库中的用户头像路径。

该类使用 @Autowired 注解将 ConsumerServiceImpl 自动注入到 ConsumerController 中，以便在控制器中可以直接调用 ConsumerServiceImpl 的方法来处理用户相关的业务逻辑。

此外，还定义了一个内部静态类 MyPicConfig，用于配置静态资源的访问路径。它实现了 WebMvcConfigurer 接口，并重写了 addResourceHandlers 方法，将 /img/avatorImages/** 映射到指定的静态资源路径。
 */

@RestController
public class ConsumerController {

    @Autowired
    private ConsumerServiceImpl consumerService;

    @Configuration
    public static class MyPicConfig implements WebMvcConfigurer {
        @Override
        public void addResourceHandlers(ResourceHandlerRegistry registry) {
            registry.addResourceHandler("/img/avatorImages/**")
                    .addResourceLocations(Constants.AVATOR_IMAGES_PATH);
        }
    }

    /**
     * 用户注册
     */
    @ResponseBody
    @RequestMapping(value = "/user/add", method = RequestMethod.POST)
    public Object addUser(HttpServletRequest req) {
        String username = req.getParameter("username").trim();
        String password = req.getParameter("password").trim();
        String sex = req.getParameter("sex").trim();
        String phoneNum = req.getParameter("phoneNum").trim();
        String email = req.getParameter("email").trim();
        String birth = req.getParameter("birth").trim();
        String introduction = req.getParameter("introduction").trim();
        String location = req.getParameter("location").trim();
        String avator = "/img/avatorImages/user.jpg";

        if(consumerService.existUser(username)) {
            return new WarningMessage("用户名已注册").getMessage();
        }

        Consumer consumer = new Consumer();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date myBirth = new Date();
        try {
            myBirth = dateFormat.parse(birth);
        } catch (Exception e) {
            e.printStackTrace();
        }
        consumer.setUsername(username);
        consumer.setPassword(password);
        consumer.setSex(new Byte(sex));
        if ("".equals(phoneNum)) {
            consumer.setPhoneNum(null);
        } else {
            consumer.setPhoneNum(phoneNum);
        }

        if ("".equals(email)) {
            consumer.setEmail(null);
        } else {
            consumer.setEmail(email);
        }
        consumer.setBirth(myBirth);
        consumer.setIntroduction(introduction);
        consumer.setLocation(location);
        consumer.setAvator(avator);
        consumer.setCreateTime(new Date());
        consumer.setUpdateTime(new Date());

        try {
            boolean res = consumerService.addUser(consumer);
            if (res) {
                return new SuccessMessage<Null>("注册成功").getMessage();
            } else {
                return new ErrorMessage("注册失败").getMessage();
            }
        } catch (DuplicateKeyException e) {
            return new FatalMessage(e.getMessage()).getMessage();
        }
    }

    /**
     * 登录判断
     */
    @ResponseBody
    @RequestMapping(value = "/user/login/status", method = RequestMethod.POST)
    public Object loginStatus(HttpServletRequest req, HttpSession session) {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        boolean res = consumerService.veritypasswd(username, password);
        if (res) {
            session.setAttribute("username", username);
            return new SuccessMessage<List<Consumer>>("登录成功", consumerService.loginStatus(username)).getMessage();
        } else {
            return new ErrorMessage("用户名或密码错误").getMessage();
        }
    }

    /**
     * 返回所有用户
     */
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public Object allUser() {
        return new SuccessMessage<List<Consumer>>(null, consumerService.allUser()).getMessage();
    }

    /**
     * 返回指定 ID 的用户
     */
    @RequestMapping(value = "/user/detail", method = RequestMethod.GET)
    public Object userOfId(HttpServletRequest req) {
        String id = req.getParameter("id");

        return new SuccessMessage<List<Consumer>>(null, consumerService.userOfId(Integer.parseInt(id))).getMessage();
    }

    /**
     * 删除用户
     */
    @RequestMapping(value = "/user/delete", method = RequestMethod.GET)
    public Object deleteUser(HttpServletRequest req) {
        String id = req.getParameter("id");
        
        boolean res = consumerService.deleteUser(Integer.parseInt(id));
        if (res) {
            return new SuccessMessage<Null>("删除成功").getMessage();
        } else {
            return new ErrorMessage("删除失败").getMessage();
        }
    }

    /**
     * 更新用户信息
     */
    @ResponseBody
    @RequestMapping(value = "/user/update", method = RequestMethod.POST)
    public Object updateUserMsg(HttpServletRequest req) {
        String id = req.getParameter("id").trim();
        String username = req.getParameter("username").trim();
        String sex = req.getParameter("sex").trim();
        String phoneNum = req.getParameter("phoneNum").trim();
        String email = req.getParameter("email").trim();
        String birth = req.getParameter("birth").trim();
        String introduction = req.getParameter("introduction").trim();
        String location = req.getParameter("location").trim();
        // System.out.println(username);

        Consumer consumer = new Consumer();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date myBirth = new Date();
        try {
            myBirth = dateFormat.parse(birth);
        } catch (Exception e) {
            e.printStackTrace();
        }
        consumer.setId(Integer.parseInt(id));
        consumer.setUsername(username);
        consumer.setSex(new Byte(sex));
        consumer.setPhoneNum(phoneNum);
        consumer.setEmail(email);
        consumer.setIntroduction(introduction);
        consumer.setLocation(location);
        consumer.setUpdateTime(new Date());
        consumer.setBirth(myBirth);

        boolean res = consumerService.updateUserMsg(consumer);
        if (res) {
            return new SuccessMessage<Null>("修改成功").getMessage();
        } else {
            return new ErrorMessage("修改失败").getMessage();
        }
    }

    /**
     * 更新用户密码
     */
    @ResponseBody
    @RequestMapping(value = "/user/updatePassword", method = RequestMethod.POST)
    public Object updatePassword(HttpServletRequest req) {
        String id = req.getParameter("id").trim();
        String username = req.getParameter("username").trim();
        String old_password = req.getParameter("old_password").trim();
        String password = req.getParameter("password").trim();

        boolean res = consumerService.veritypasswd(username, old_password);
        if (!res) {
            return new ErrorMessage("密码输入错误").getMessage();
        }

        Consumer consumer = new Consumer();
        consumer.setId(Integer.parseInt(id));
        consumer.setPassword(password);

        boolean result = consumerService.updatePassword(consumer);
        if (result) {
            return new SuccessMessage<Null>("密码修改成功").getMessage();
        } else {
            return new ErrorMessage("密码修改失败").getMessage();
        }
    }

    /**
     * 更新用户头像
     */
    @ResponseBody
    @RequestMapping(value = "/user/avatar/update", method = RequestMethod.POST)
    public Object updateUserPic(@RequestParam("file") MultipartFile avatorFile, @RequestParam("id") int id) {
        String fileName = System.currentTimeMillis() + avatorFile.getOriginalFilename();
        String filePath = Constants.PROJECT_PATH + System.getProperty("file.separator") + "img" + System.getProperty("file.separator") + "avatorImages";
        File file1 = new File(filePath);
        if (!file1.exists()) {
            file1.mkdir();
        }

        File dest = new File(filePath + System.getProperty("file.separator") + fileName);
        String imgPath = "/img/avatorImages/" + fileName;
        try {
            avatorFile.transferTo(dest);
            Consumer consumer = new Consumer();
            consumer.setId(id);
            consumer.setAvator(imgPath);
            boolean res = consumerService.updateUserAvator(consumer);
            if (res) {
                return new SuccessMessage<String>("上传成功", imgPath).getMessage();
            } else {
                return new ErrorMessage("上传失败").getMessage();
            }
        } catch (IOException e) {
            return new FatalMessage("上传失败" + e.getMessage()).getMessage();
        }
    }
}
