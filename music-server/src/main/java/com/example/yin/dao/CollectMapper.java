package com.example.yin.dao;

import com.example.yin.domain.Collect;
import org.apache.ibatis.annotations.Param;
//注解用于在MyBatis的SQL语句中指定参数的名称。它可以应用在Mapper接口方法的参数上，为参数指定一个名称，
// 以便在SQL语句中引用该参数。/
import org.springframework.stereotype.Repository;
//通常用于与数据库或其他持久化机制进行数据访问和操作。
//它通常与其他注解（如@Autowired和@Transactional）一起使用，以便实现依赖注入和事务管理等功能。
import java.util.List;
//List接口提供了丰富的方法来操作列表中的元素，包括添加元素、删除元素、获取元素、查找元素等。
// 它还支持通过索引访问元素，以及对列表进行排序和搜索等操作。

@Repository
//通过将@Repository注解应用于类上，Spring框架将能够自动扫描和识别该类作为数据访问组件，并为其提供相关的功能和特性。
// 这包括将异常转换为Spring的数据访问异常体系、处理事务和连接管理等。
public interface CollectMapper {
    int deleteByPrimaryKey(Integer id);
//    根据指定的id值从数据库中删除对应的Collect记录。
    int insert(Collect record);
//    根据指定的id值从数据库中删除对应的Collect记录。
    int insertSelective(Collect record);
//    根据Collect对象的非空字段向数据库中插入一条记录。
    Collect selectByPrimaryKey(Integer id);
//    根据指定的id值从数据库中查询并返回对应的Collect记录。
    int updateByPrimaryKeySelective(Collect record);
//    根据指定的id值从数据库中查询并返回对应的Collect记录。
    int updateByPrimaryKey(Collect record);
//    更新数据库中对应的Collect记录，包括所有字段。
    int existSongId(@Param("userId") Integer userId, @Param("songId") Integer songId);
//    根据给定的用户ID和歌曲ID判断是否存在对应的收藏记录。
    int deleteCollect(@Param("userId") Integer userId, @Param("songId") Integer songId);
//    根据给定的用户ID和歌曲ID删除对应的收藏记录。
    List<Collect> collectionOfUser(Integer userId);
//    根据给定的用户ID查询并返回该用户的收藏列表。
}
//这些方法可能用于对Collect实体对象在数据库中的增删改查操作，以及一些特定的查询和操作。
// 这个接口可能被用于定义数据访问层（DAO）的接口，并由具体的实现类提供相应的实现逻辑。