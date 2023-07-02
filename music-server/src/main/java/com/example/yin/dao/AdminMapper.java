package com.example.yin.dao;

import com.example.yin.domain.Admin;
//这行代码导入了名为Admin的类，该类位于com.example.yin.domain包中。
import org.springframework.stereotype.Repository;
//用于标记数据访问层（DAO）组件类
//@Repository注解是用于表示一个类是数据访问层的组件，通常用于与数据库或其他持久化机制进行数据访问和操作。
//它通常与其他注解（如@Autowired和@Transactional）一起使用，以便实现依赖注入和事务管理等功能。
@Repository
//通过将@Repository注解应用于类上，Spring框架将能够自动扫描和识别该类作为数据访问组件，
// 并为其提供相关的功能和特性这包括将异常转换为Spring的数据访问异常体系、处理事务和连接管理等。
public interface AdminMapper {
    int deleteByPrimaryKey(Integer id);
//  根据指定的id值从数据库中删除对应的Admin记录。
    int insert(Admin record);
//  向数据库中插入一条Admin记录
    int insertSelective(Admin record);
//  根据Admin对象的非空字段向数据库中插入一条记录。
    Admin selectByPrimaryKey(Integer id);
//  根据指定的id值从数据库中查询并返回对应的Admin记录。
    int updateByPrimaryKeySelective(Admin record);
//  根据Admin对象的非空字段更新数据库中对应的记录。
    int updateByPrimaryKey(Admin record);
//  根据Admin对象的非空字段更新数据库中对应的记录。
    int verifyPassword(String username, String password);
//  根据给定的用户名和密码验证用户密码的正确性。
}
//这些方法可能是用于对Admin实体对象在数据库中的增删改查操作，以及密码验证等特定操作的抽象定义。
//这个接口可能被用于定义数据访问层（DAO）的接口，并由具体的实现类提供相应的实现逻辑。