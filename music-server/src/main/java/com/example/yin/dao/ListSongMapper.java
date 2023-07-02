package com.example.yin.dao;

import com.example.yin.domain.ListSong;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
//注解@Repository用于标识这个接口作为数据访问层的组件，以便在应用程序中进行依赖注入和管理。
public interface ListSongMapper {
//    这些方法用于对列表歌曲对象在数据库中的增删改查操作，以及一些特定的查询和操作。
    int deleteByPrimaryKey(Integer id);
//    根据指定的id值从数据库中删除对应的列表歌曲记录。
    int insert(ListSong record);
//    根据指定的id值从数据库中删除对应的列表歌曲记录。
    int insertSelective(ListSong record);
//    根据ListSong对象的非空字段向数据库中插入一条记录。
    ListSong selectByPrimaryKey(Integer id);
//    根据ListSong对象的非空字段向数据库中插入一条记录。
    int updateByPrimaryKeySelective(ListSong record);
//    根据ListSong对象的非空字段更新数据库中对应的记录。
    int updateByPrimaryKey(ListSong record);
//    更新数据库中对应的列表歌曲记录，包括所有字段。
    int updateListSongMsg(ListSong record);
//    更新列表歌曲的信息
    int deleteListSong(Integer songId);
//    删除指定歌曲ID的列表歌曲。
    List<ListSong> allListSong();
//    获取所有列表歌曲的列表。
    List<ListSong> listSongOfSongId(Integer songListId);
//    根据指定的歌单ID查询并返回对应的列表歌曲列表。
}
