package com.example.yin.service;

public interface AdminService {

    boolean veritypasswd(String name, String password);
}
//该方法接受两个参数：name（管理员用户名）和 password（管理员密码）。
//        方法返回一个布尔值，表示用户名和密码是否匹配。