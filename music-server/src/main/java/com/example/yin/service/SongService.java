package com.example.yin.service;

import com.example.yin.domain.Song;

import java.util.List;

public interface SongService {

    boolean addSong (Song song);

    boolean updateSongMsg(Song song);

    boolean updateSongUrl(Song song);

    boolean updateSongPic(Song song);

    boolean deleteSong(Integer id);

    List<Song> allSong();

    List<Song> songOfSingerId(Integer singerId);

    List<Song> songOfId(Integer id);

    List<Song> songOfSingerName(String name);
}

/*
SongService 是一个接口，定义了对歌曲信息进行操作的方法。以下是接口中声明的方法的解释：

addSong：添加歌曲。接收一个 Song 对象作为参数，将歌曲信息添加到系统中。返回一个布尔值，表示添加是否成功。

updateSongMsg：更新歌曲信息。接收一个 Song 对象作为参数，更新指定歌曲的信息。返回一个布尔值，表示更新是否成功。

updateSongUrl：更新歌曲的播放链接。接收一个 Song 对象作为参数，更新指定歌曲的播放链接。返回一个布尔值，表示更新是否成功。

updateSongPic：更新歌曲的封面图片。接收一个 Song 对象作为参数，更新指定歌曲的封面图片。返回一个布尔值，表示更新是否成功。

deleteSong：删除歌曲。接收一个整数类型的歌曲 ID 作为参数，删除指定 ID 的歌曲。返回一个布尔值，表示删除是否成功。

allSong：获取所有歌曲的信息。返回一个包含所有歌曲的列表。

songOfSingerId：根据歌手 ID 获取对应的歌曲列表。接收一个整数类型的歌手 ID 作为参数，返回一个包含该歌手的所有歌曲信息的列表。

songOfId：根据歌曲 ID 获取对应的歌曲列表。接收一个整数类型的歌曲 ID 作为参数，返回一个包含该歌曲信息的列表。

songOfSingerName：根据歌手名称获取对应的歌曲列表。接收一个字符串类型的歌手名称作为参数，返回一个包含该歌手的所有歌曲信息的列表。

该接口定义了对歌曲信息的增删改查操作，并提供了一些查询方法用于根据不同条件获取歌曲信息。具体的实现需要在实现类中完成。
 */