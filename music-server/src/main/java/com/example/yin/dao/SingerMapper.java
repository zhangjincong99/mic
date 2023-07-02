package com.example.yin.dao;

import com.example.yin.domain.Singer;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SingerMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Singer record);

    int insertSelective(Singer record);
//    据Singer对象的非空字段向数据库中插入一条记录。
    Singer selectByPrimaryKey(Integer id);
//    更新数据库中对应的歌手记录，包括所有字段。
    int updateByPrimaryKeySelective(Singer record);
//    更新数据库中对应的歌手记录，包括所有字段。
    int updateByPrimaryKey(Singer record);

    int updateSingerMsg(Singer record);

    int updateSingerPic(Singer record);

    int deleteSinger(Integer id);

    List<Singer> allSinger();
//    获取所有歌手的列表。
    List<Singer> singerOfName(String name);
//    根据指定的歌手名查询并返回对应的歌手列表。
    List<Singer> singerOfSex(Integer sex);
//    根据指定的歌手名查询并返回对应的歌手列表。
}
