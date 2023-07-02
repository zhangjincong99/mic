package com.example.yin.service;

import com.example.yin.domain.SongList;

import java.util.List;

public interface SongListService {

    boolean addSongList (SongList songList);

    boolean updateSongListMsg(SongList songList);

    boolean updateSongListImg(SongList songList);

    boolean deleteSongList(Integer id);

    List<SongList> allSongList();

    List<SongList> likeTitle(String title);

    List<SongList> likeStyle(String style);
}

/*

SongListService 接口定义了与歌单相关的操作的服务契约。以下是接口中声明的方法的解释：

addSongList：该方法用于添加新的歌单。它以 SongList 对象作为参数，并返回一个表示操作是否成功的布尔值。

updateSongListMsg：该方法用于更新歌单的详细信息。它以 SongList 对象作为参数，并返回一个表示操作是否成功的布尔值。

updateSongListImg：该方法用于更新歌单的图片。它以 SongList 对象作为参数，并返回一个表示操作是否成功的布尔值。

deleteSongList：该方法用于删除歌单。它以 Integer 类型的 ID 作为参数，并返回一个表示操作是否成功的布尔值。

allSongList：该方法用于获取所有歌单的详细信息。它返回一个包含所有歌单的 SongList 对象列表。

likeTitle：该方法用于根据指定的标题关键字搜索相似的歌单。它以 String 类型的参数表示标题关键字，并返回一个符合搜索条件的 SongList 对象列表。

likeStyle：该方法用于根据指定的风格关键字搜索相似的歌单。它以 String 类型的参数表示风格关键字，并返回一个符合搜索条件的 SongList 对象列表。

这些方法定义了对歌单执行基本操作的方式，例如添加、更新、删除和根据不同的搜索条件检索歌单。
 */