package com.example.yin.controller;

import com.example.yin.common.ErrorMessage;
import com.example.yin.common.SuccessMessage;
import com.example.yin.service.impl.AdminServiceImpl;


import org.apache.commons.lang3.ObjectUtils.Null;
//表示空对象的特殊类。
import org.springframework.beans.factory.annotation.Autowired;
//Autowired（来自Spring框架）：用于自动装配依赖的注解。
import org.springframework.web.bind.annotation.RequestMapping;
//RequestMapping（来自Spring MVC）：用于将HTTP请求映射到特定处理方法的注解。
import org.springframework.web.bind.annotation.RequestMethod;
//RequestMethod（来自Spring框架）：用于指定处理请求的HTTP方法的枚举。
import org.springframework.web.bind.annotation.ResponseBody;
//ResponseBody（来自Spring MVC）：用于将方法返回值直接作为HTTP响应的主体内容的注解。
import org.springframework.web.bind.annotation.RestController;
//RestController（来自Spring MVC）：用于标识控制器类为RESTful风格的控制器，
// 同时将@Controller和@ResponseBody注解结合在一起。
import javax.servlet.http.HttpServletRequest;
//代表HTTP请求的对象，包含了HTTP请求的信息和方法，可以用于获取请求参数、请求头、会话信息等。
import javax.servlet.http.HttpSession;
//HttpServletRequest（来自javax.servlet.http包）：代表HTTP请求的对象，
// 包含了HTTP请求的信息和方法，可以用于获取请求参数、请求头、会话信息等。
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
// 注解表示这个类是一个RESTful风格的控制器，它会将处理结果直接以JSON格式返回给客户端。
public class AdminController {
    @Autowired
//    注解用于自动注入 AdminServiceImpl 类的实例，这样就可以在控制器中使用该服务类的方法。
    private AdminServiceImpl adminService;

    // 判断是否登录成功
    @ResponseBody
//    @ResponseBody 注解用于指定返回的对象将作为响应体返回给客户端。
    @RequestMapping(value = "/admin/login/status", method = RequestMethod.POST)
//    @RequestMapping 注解用于指定处理的请求路径和请求方法。
//    在这个例子中，处理的路径是 "/admin/login/status"，请求方法是 POST。
    public Object loginStatus(HttpServletRequest req, HttpSession session) {
//        loginStatus 方法用于处理登录状态的验证。它接受 HttpServletRequest 和 HttpSession
//        作为参数，用于获取请求中的用户名和密码，并进行验证。
        String name = req.getParameter("name");
        String password = req.getParameter("password");

        boolean res = adminService.veritypasswd(name, password);
        if (res) {
            session.setAttribute("name", name);
            return new SuccessMessage<Null>("登录成功").getMessage();
        } else {
            return new ErrorMessage("用户名或密码错误").getMessage();
        }
//        验证通过时，将用户名存储在会话中，并返回一个表示成功的消息对象（使用 SuccessMessage 类）。
//        验证失败时，返回一个表示错误的消息对象（使用 ErrorMessage 类）。
    }
}
