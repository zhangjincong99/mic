package com.example.yin.service;

import com.example.yin.domain.ListSong;

import java.util.List;


/*
ListSongService 接口定义了对歌单与歌曲关联关系进行操作的方法，包括：

addListSong 方法用于向歌单中添加歌曲。
updateListSongMsg 方法用于更新歌单与歌曲关联关系的信息。
deleteListSong 方法用于删除歌单与歌曲的关联关系。
allListSong 方法返回所有的歌单与歌曲关联关系列表。
listSongOfSongId 方法返回指定歌单 ID 的关联的歌曲列表。
这些方法提供了对歌单与歌曲关联关系的增删改查操作。实现该接口的类需要实现这些方法，并根据具体的业务逻辑进行相应的处理。
 */
public interface ListSongService {

    boolean addListSong(ListSong listSong);

    boolean updateListSongMsg(ListSong listSong);

    boolean deleteListSong(Integer songId);

    List<ListSong> allListSong();

    List<ListSong> listSongOfSongId(Integer songListId);
}
