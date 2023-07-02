package com.example.yin.service.impl;

import com.example.yin.dao.AdminMapper;
import com.example.yin.service.AdminService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
//@Service 注解标记了 AdminServiceImpl 类为一个服务类。它是 Spring 框架中的一个注解，
// 用于表明该类承担着业务逻辑处理的角色。
public class AdminServiceImpl implements AdminService {

    @Autowired
//    通过这个注解，Spring 框架会在需要使用 AdminServiceImpl 的地方自动创建并注入该类的实例。
    private AdminMapper adminMapper;

    @Override
    public boolean veritypasswd(String name, String password) {

        return adminMapper.verifyPassword(name, password)>0?true:false;
    }
//    adminMapper.verifyPassword(name, password) 方法返回一个整数值，表示匹配的结果。
//    如果大于0，则表示用户名和密码匹配成功，返回 true，否则返回 false。
//    综上所述，该方法的作用是根据用户名和密码验证管理员的身份，返回验证结果。
}
