package com.example.yin.dao;

import com.example.yin.domain.RankList;
import org.apache.ibatis.annotations.Param;
//注解用于在MyBatis的SQL语句中指定参数的名称。它可以应用在Mapper接口方法的参数上，为参数指定一个名称，
// 以便在SQL语句中引用该参数。/
import org.springframework.stereotype.Repository;

//通常用于与数据库或其他持久化机制进行数据访问和操作。
//它通常与其他注解（如@Autowired和@Transactional）一起使用，以便实现依赖注入和事务管理等功能。
@Repository
//通过将@Repository注解应用于类上，Spring框架将能够自动扫描和识别该类作为数据访问组件，并为其提供相关的功能和特性。
// 这包括将异常转换为Spring的数据访问异常体系、处理事务和连接管理等。
public interface RankListMapper {
    //    这是一个RankListMapper接口，用于与排行榜（RankList）对象相关的数据库操作。
    int insert(RankList record);

    int insertSelective(RankList record);

    /**
     * 查总分
     *
     * @param songListId
     * @return
     */
    int selectScoreSum(Long songListId);

    /**
     * 查总评分人数
     *
     * @param songListId
     * @return
     */
    int selectRankNum(Long songListId);

    /**
     * 查制定用户评分
     *
     * @param consumerId
     * @param songListId
     * @return
     */
//    int selectUserRank(@Param("consumerId") Long consumerId, @Param("songListId") Long songListId);

    //    查询指定用户对指定歌单的评分
    Integer selectUserRank(@Param("consumerId") Long consumerId, @Param("songListId") Long songListId);

}
