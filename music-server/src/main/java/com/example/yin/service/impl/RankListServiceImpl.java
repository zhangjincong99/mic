package com.example.yin.service.impl;

import com.example.yin.dao.RankListMapper;
import com.example.yin.domain.RankList;
import com.example.yin.service.RankListService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Administrator
 * addRank 方法用于添加评分记录。它接收一个 RankList 对象，并调用 rankMapper 的 insertSelective 方法将评分记录插入数据库。如果插入成功，则返回大于 0 的值，表示添加评分成功。
 * <p>
 * rankOfSongListId 方法用于获取指定歌单的评分。它接收一个 songListId 参数，并通过调用 rankMapper 的方法获取评分记录的总人数 (rankNum) 和总评分 (scoreSum)。如果评分总人数为 0，则返回 0；否则，返回评分总分除以评分总人数的平均值。
 * <p>
 * getUserRank 方法用于获取指定用户对特定歌单的评分。它接收一个 consumerId 和 songListId 参数，并通过调用 rankMapper 的方法查询用户对歌单的评分。
 */
@Service
public class RankListServiceImpl implements RankListService {

    private static final Logger logger = LogManager.getLogger(RankListServiceImpl.class);

    @Autowired
    private RankListMapper rankMapper;

    @Override
    public boolean addRank(RankList rankList) {
        return rankMapper.insertSelective(rankList) > 0;
    }

    @Override
    public int rankOfSongListId(Long songListId) {
        // 评分总人数如果为 0，则返回0；否则返回计算出的结果
        int rankNum = rankMapper.selectRankNum(songListId);
        return (rankNum <= 0) ? 0 : rankMapper.selectScoreSum(songListId) / rankNum;
    }

    //    @Override
//    public int getUserRank(Long consumerId, Long songListId) {
//        return rankMapper.selectUserRank(consumerId, songListId);
//    }
    @Override
    public int getUserRank(Long consumerId, Long songListId) {
        Integer userRank = rankMapper.selectUserRank(consumerId, songListId);
        return (userRank != null) ? userRank : 0;
    }
}
