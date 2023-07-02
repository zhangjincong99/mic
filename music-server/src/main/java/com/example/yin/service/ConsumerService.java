package com.example.yin.service;

import com.example.yin.domain.Consumer;

import java.util.List;

/*
ConsumerService 接口定义了与用户相关的服务方法，包括：

addUser 方法用于新增用户。
updateUserMsg 方法用于更新用户信息。
updateUserAvator 方法用于更新用户头像。
updatePassword 方法用于更新用户密码。
existUser 方法用于检查用户名是否存在。
veritypasswd 方法用于验证用户名和密码是否匹配。
deleteUser 方法用于删除用户。
allUser 方法用于获取所有用户信息。
userOfId 方法用于获取指定 ID 的用户信息。
loginStatus 方法用于获取指定用户名的用户信息。
 */

public interface ConsumerService {

    boolean addUser(Consumer consumer);

    boolean updateUserMsg(Consumer consumer);

    boolean updateUserAvator(Consumer consumer);

    boolean updatePassword(Consumer consumer);

    boolean existUser(String username);

    boolean veritypasswd(String username, String password);

    boolean deleteUser(Integer id);

    List<Consumer> allUser();

    List<Consumer> userOfId(Integer id);

    List<Consumer> loginStatus(String username);

}
