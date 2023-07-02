package com.example.yin.service.impl;

import com.example.yin.dao.CollectMapper;
import com.example.yin.domain.Collect;
import com.example.yin.service.CollectService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
/*
addCollection 方法用于添加收藏。它调用 collectMapper.insertSelective(collect) 方法将收藏信息插入到数据库，并返回插入的记录数。如果插入成功（插入记录数大于0），则返回 true，否则返回 false。

existSongId 方法用于检查收藏是否存在。它调用 collectMapper.existSongId(userId, songId) 方法查询数据库中是否存在指定用户和歌曲的收藏记录。如果存在（返回结果大于0），则返回 true，否则返回 false。

deleteCollect 方法用于删除收藏。它调用 collectMapper.deleteCollect(userId, songId) 方法删除数据库中指定用户和歌曲的收藏记录。如果删除成功（删除记录数大于0），则返回 true，否则返回 false。

collectionOfUser 方法用于获取指定用户的收藏列表。它调用 collectMapper.collectionOfUser(userId) 方法查询数据库中指定用户的收藏记录，并返回结果列表。
 */
@Service
//用于实现依赖注入和组件扫描。
public class CollectServiceImpl implements CollectService {
    @Autowired
//    CollectMapper 自动注入到 CollectServiceImpl 中，以便在服务层中可以直接调用 CollectMapper 的方法来操作数据。
    private CollectMapper collectMapper;

    @Override
    public boolean addCollection(Collect collect) {
        return collectMapper.insertSelective(collect) > 0 ? true : false;
    }
//    方法用于添加收藏，调用 collectMapper.insertSelective(collect) 方法将收藏信息插入数据库，并返回插入的记录数。

    @Override
    public boolean existSongId(Integer userId, Integer songId) {
        return collectMapper.existSongId(userId, songId) > 0 ? true : false;
    }
//    方法用于添加收藏，调用 collectMapper.insertSelective(collect) 方法将收藏信息插入数据库，并返回插入的记录数。

    @Override
    public boolean deleteCollect(Integer userId, Integer songId) {
        return collectMapper.deleteCollect(userId, songId) > 0 ? true : false;
    }
//    方法用于添加收藏，调用 collectMapper.insertSelective(collect) 方法将收藏信息插入数据库，并返回插入的记录数。

    @Override
    public List<Collect> collectionOfUser(Integer userId)

    {
        return collectMapper.collectionOfUser(userId);
    }
}
//    collectionOfUser 方法用于获取指定用户的收藏列表，调用 collectMapper.collectionOfUser(userId) 方法查询数据库中指定用户的收藏记录。
