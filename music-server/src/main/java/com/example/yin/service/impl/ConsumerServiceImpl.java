package com.example.yin.service.impl;

import com.example.yin.dao.ConsumerMapper;
import com.example.yin.domain.Consumer;
import com.example.yin.service.ConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/*
addUser 方法用于新增用户。调用 consumerMapper.insertSelective(consumer) 方法将用户信息插入数据库，并返回插入的记录数。

updateUserMsg 方法用于更新用户信息。调用 consumerMapper.updateUserMsg(consumer) 方法更新数据库中的用户信息，并返回更新的记录数。

updatePassword 方法用于更新用户密码。调用 consumerMapper.updatePassword(consumer) 方法更新数据库中的用户密码，并返回更新的记录数。

updateUserAvator 方法用于更新用户头像。调用 consumerMapper.updateUserAvator(consumer) 方法更新数据库中的用户头像路径，并返回更新的记录数。

existUser 方法用于检查用户名是否存在。调用 consumerMapper.existUsername(username) 方法查询数据库中是否存在指定用户名的记录，并返回查询结果。

veritypasswd 方法用于验证用户名和密码是否匹配。调用 consumerMapper.verifyPassword(username, password) 方法验证用户名和密码是否匹配，并返回验证结果。

deleteUser 方法用于删除用户。调用 consumerMapper.deleteUser(id) 方法删除数据库中指定 ID 的用户记录，并返回删除的记录数。

allUser 方法用于获取所有用户信息。调用 consumerMapper.allUser() 方法查询数据库中的所有用户记录，并返回查询结果。

userOfId 方法用于获取指定 ID 的用户信息。调用 consumerMapper.userOfId(id) 方法查询数据库中指定 ID 的用户记录，并返回查询结果。

loginStatus 方法用于获取指定用户名的用户信息。调用 consumerMapper.loginStatus(username) 方法查询数据库中指定用户名的用户记录，并返回查询结果。

该类使用 @Autowired 注解将 ConsumerMapper 自动注入到 ConsumerServiceImpl 中，以便在服务类中可以直接调用 ConsumerMapper 的方法来操作数据库。
 */

@Service
public class ConsumerServiceImpl implements ConsumerService {

    @Autowired
    private ConsumerMapper consumerMapper;

    /**
     * 新增用户
     */
    @Override
    public boolean addUser(Consumer consumer) {
        return consumerMapper.insertSelective(consumer) > 0;
    }

    @Override
    public boolean updateUserMsg(Consumer consumer) {
        return consumerMapper.updateUserMsg(consumer) > 0;
    }

    @Override
    public boolean updatePassword(Consumer consumer) {
        return consumerMapper.updatePassword(consumer) > 0;
    }

    @Override
    public boolean updateUserAvator(Consumer consumer) {
        return consumerMapper.updateUserAvator(consumer) > 0;
    }

    @Override
    public boolean existUser(String username) {
        return consumerMapper.existUsername(username) > 0;
    }

    @Override
    public boolean veritypasswd(String username, String password) {
        return consumerMapper.verifyPassword(username, password) > 0;
    }

    // 删除用户
    @Override
    public boolean deleteUser(Integer id) {
        return consumerMapper.deleteUser(id) > 0;
    }

    @Override
    public List<Consumer> allUser() {
        return consumerMapper.allUser();
    }

    @Override
    public List<Consumer> userOfId(Integer id) {
        return consumerMapper.userOfId(id);
    }

    @Override
    public List<Consumer> loginStatus(String username) {
        return consumerMapper.loginStatus(username);
    }
}
