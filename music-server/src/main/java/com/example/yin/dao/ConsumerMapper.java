package com.example.yin.dao;

import com.example.yin.domain.Consumer;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConsumerMapper {
    int deleteByPrimaryKey(Integer id);
//    根据指定的id值从数据库中删除对应的消费者记录。
    int insert(Consumer record);
//    向数据库中插入一条消费者记录。
    /**
     * 增加新用户
     * @param record - 用户信息
     * @return int
     */
    int insertSelective(Consumer record);
//    根据Consumer对象的非空字段向数据库中插入一条记录。
    Consumer selectByPrimaryKey(Integer id);
//    根据指定的id值从数据库中查询并返回对应的消费者记录。
    int updateByPrimaryKeySelective(Consumer record);
//    根据Consumer对象的非空字段更新数据库中对应的记录。
    int updateByPrimaryKey(Consumer record);
//    根据Consumer对象的非空字段更新数据库中对应的记录。
    int verifyPassword(String username, String password);
//    根据给定的用户名和密码验证用户的登录信息。
    int existUsername(String username);
//    检查数据库中是否存在指定用户名的用户
    int addUser(Consumer consumer);
//    添加新用户。
    int updateUserMsg(Consumer record);
//    更新用户的个人信息。
    int updateUserAvator(Consumer record);
//    更新用户的个人信息。
    int updatePassword(Consumer record);
//    更新用户的个人信息。
    int deleteUser(Integer id);
//    更新用户的个人信息。
    List<Consumer> allUser();
//    获取所有用户的列表。
    List<Consumer> userOfId(Integer id);
//    根据指定的用户ID查询并返回对应的用户列表。
    List<Consumer> loginStatus(String username);
//    根据用户名查询用户的登录状态。
}
