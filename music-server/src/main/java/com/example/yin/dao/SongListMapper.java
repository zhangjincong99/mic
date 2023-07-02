package com.example.yin.dao;

import com.example.yin.domain.SongList;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface SongListMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SongList record);

    int insertSelective(SongList record);

    SongList selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SongList record);

    int updateByPrimaryKeyWithBLOBs(SongList record);

    int updateByPrimaryKey(SongList record);

    int updateSongListMsg(SongList record);

    int updateSongListImg(SongList record);

    int deleteSongList(Integer id);

    List<SongList> allSongList();
//    获取所有歌单的列表，返回一个包含所有歌单对象的列表。
    List<SongList> likeTitle(String title);
//    根据指定的歌单标题模糊匹配歌单，返回一个包含符合条件的歌单对象的列表。
    List<SongList> likeStyle(String style);
//    根据指定的歌单标题模糊匹配歌单，返回一个包含符合条件的歌单对象的列表。
}
