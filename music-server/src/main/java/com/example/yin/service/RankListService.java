package com.example.yin.service;

import com.example.yin.domain.RankList;

public interface RankListService {

    boolean addRank(RankList rankList);

    int rankOfSongListId(Long songListId);

    int getUserRank(Long consumerId, Long songListId);

}

/*
addRank 方法用于添加评分记录。它接收一个 RankList 对象作为参数，表示要添加的评分记录。方法返回一个布尔值，表示评分记录是否添加成功。

rankOfSongListId 方法用于获取指定歌单的评分。它接收一个 songListId 参数，表示要查询评分的歌单 ID。方法返回一个整数值，表示该歌单的平均评分。

getUserRank 方法用于获取指定用户对特定歌单的评分。它接收一个 consumerId 和 songListId 参数，分别表示用户 ID 和歌单 ID。方法返回一个整数值，表示用户对该歌单的评分。
 */