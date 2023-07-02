package com.example.yin.service.impl;

import com.example.yin.dao.SongMapper;
import com.example.yin.domain.Song;
import com.example.yin.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
/*
allSong：获取所有歌曲的信息。调用 songMapper 的 allSong 方法从数据库中获取所有歌曲的信息，并返回一个包含所有歌曲的列表。

addSong：添加歌曲。接收一个 Song 对象作为参数，调用 songMapper 的 insertSelective 方法将歌曲信息插入到数据库中。根据操作的结果返回布尔值，表示添加歌曲是否成功。

updateSongMsg：更新歌曲的信息。接收一个 Song 对象作为参数，调用 songMapper 的 updateSongMsg 方法更新数据库中对应歌曲的信息。根据操作的结果返回布尔值，表示更新是否成功。

updateSongUrl：更新歌曲的播放链接。接收一个 Song 对象作为参数，调用 songMapper 的 updateSongUrl 方法更新数据库中对应歌曲的播放链接。根据操作的结果返回布尔值，表示更新是否成功。

updateSongPic：更新歌曲的封面图片。接收一个 Song 对象作为参数，调用 songMapper 的 updateSongPic 方法更新数据库中对应歌曲的封面图片。根据操作的结果返回布尔值，表示更新是否成功。

deleteSong：删除歌曲。接收一个整数类型的歌曲 ID 作为参数，调用 songMapper 的 deleteSong 方法删除数据库中对应的歌曲。根据操作的结果返回布尔值，表示删除是否成功。

songOfSingerId：根据歌手 ID 获取对应的歌曲列表。接收一个整数类型的歌手 ID 作为参数，调用 songMapper 的 songOfSingerId 方法从数据库中获取该歌手的歌曲列表，并返回一个包含歌曲信息的列表。

songOfId：根据歌曲 ID 获取对应的歌曲列表。接收一个整数类型的歌曲 ID 作为参数，调用 songMapper 的 songOfId 方法从数据库中获取该歌曲的信息，并返回一个包含歌曲信息的列表。

songOfSingerName：根据歌手名称获取对应的歌曲列表。接收一个字符串类型的歌手名称作为参数，调用 songMapper 的 songOfSingerName 方法从数据库中获取该歌手的歌曲列表，并返回一个包含歌曲信息的列表。

这些方法实现了对歌曲信息的增删改查功能，并通过调用 songMapper 的相应方法来操作数据库。
 */
@Service
public class SongServiceImpl implements SongService {

    @Autowired
    private SongMapper songMapper;

    @Override
    public List<Song> allSong()
    {
        return songMapper.allSong();
    }

    @Override
    public boolean addSong(Song song)
    {

        return songMapper.insertSelective(song) > 0?true:false;
    }

    @Override
    public boolean updateSongMsg(Song song) {
        return songMapper.updateSongMsg(song) >0 ?true:false;
    }

    @Override
    public boolean updateSongUrl(Song song) {

        return songMapper.updateSongUrl(song) >0 ?true:false;
    }

    @Override
    public boolean updateSongPic(Song song) {

        return songMapper.updateSongPic(song) >0 ?true:false;
    }

    @Override
    public boolean deleteSong(Integer id) {
        return songMapper.deleteSong(id) >0 ?true:false;
    }

    @Override
    public List<Song> songOfSingerId(Integer singerId)

    {
        return songMapper.songOfSingerId(singerId);
    }

    @Override
    public List<Song> songOfId(Integer id)

    {
        return songMapper.songOfId(id);
    }

    @Override
    public List<Song> songOfSingerName(String name)

    {
        return songMapper.songOfSingerName(name);
    }
}
