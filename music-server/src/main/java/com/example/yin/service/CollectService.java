package com.example.yin.service;

import com.example.yin.domain.Collect;

import java.util.List;

public interface CollectService {

    boolean addCollection(Collect collect);

    boolean existSongId(Integer userId, Integer songId);

    boolean deleteCollect(Integer userId, Integer songId);

    List<Collect> collectionOfUser(Integer userId);
}
/*
addCollection 方法用于添加收藏，接受一个 Collect 对象作为参数，返回一个布尔值，表示操作是否成功。
existSongId 方法用于判断指定用户是否已收藏指定歌曲，接受用户ID和歌曲ID作为参数，返回一个布尔值，表示是否存在对应的收藏记录。
deleteCollect 方法用于删除指定用户对指定歌曲的收藏，接受用户ID和歌曲ID作为参数，返回一个布尔值，表示操作是否成功。
collectionOfUser 方法用于获取指定用户的收藏列表，接受用户ID作为参数，返回一个 List<Collect>，表示用户的收藏列表。
通过实现该接口，可以对收藏功能进行具体的实现，并进行相关的业务处理。
 */