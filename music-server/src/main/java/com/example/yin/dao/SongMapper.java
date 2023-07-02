package com.example.yin.dao;

import com.example.yin.domain.Song;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SongMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Song record);

    int insertSelective(Song record);

    Song selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Song record);

    int updateByPrimaryKeyWithBLOBs(Song record);

    int updateByPrimaryKey(Song record);

    int updateSongMsg(Song record);

    int updateSongUrl(Song record);

    int updateSongPic(Song record);

    int deleteSong(Integer id);

    List<Song> allSong();
//    获取所有歌曲的列表，返回一个包含所有歌曲对象的列表。
    List<Song> songOfSingerId(Integer singerId);
//    根据指定的歌手ID查询歌曲，返回一个包含符合条件的歌曲对象的列表。
    List<Song> songOfId(Integer id);
//    根据指定的歌曲ID查询歌曲，返回一个包含符合条件的歌曲对象的列表。
    List<Song> songOfSingerName(String name);
//    根据指定的歌手名查询歌曲，返回一个包含符合条件的歌曲对象的列表。
}
