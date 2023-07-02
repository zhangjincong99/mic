package com.example.yin.service.impl;

import com.example.yin.dao.ListSongMapper;
import com.example.yin.domain.ListSong;
import com.example.yin.service.ListSongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/*

ListSongServiceImpl 是 ListSongService 接口的实现类，提供了对歌单与歌曲关联关系的操作方法。该类包括以下方法：

allListSong 方法返回所有的歌单与歌曲关联关系列表。
updateListSongMsg 方法用于更新歌单与歌曲关联关系的信息。
deleteListSong 方法用于删除歌单与歌曲的关联关系。
addListSong 方法用于向歌单中添加歌曲。
listSongOfSongId 方法返回指定歌单 ID 的关联的歌曲列表。
这些方法通过调用 ListSongMapper 的相应方法与数据库进行交互，实现对歌单与歌曲关联关系的增删改查操作。

ListSongServiceImpl 类使用 @Service 注解进行标记，表明它是一个服务类，承担业务逻辑处理的角色。通过自动注入 ListSongMapper 实例，可以在需要时进行数据库操作。
 */

@Service
public class ListSongServiceImpl implements ListSongService {

    @Autowired
    private ListSongMapper listSongMapper;

    @Override
    public List<ListSong> allListSong()
    {
        return listSongMapper.allListSong();
    }

    @Override
    public boolean updateListSongMsg(ListSong listSong) {
        return listSongMapper.updateListSongMsg(listSong) > 0;
    }

    @Override
    public boolean deleteListSong(Integer songId) {
        return listSongMapper.deleteListSong(songId) > 0;
    }

    @Override
    public boolean addListSong(ListSong listSong)
    {
        return listSongMapper.insertSelective(listSong) > 0;
    }

    @Override
    public List<ListSong> listSongOfSongId(Integer songListId)
    {
        return listSongMapper.listSongOfSongId(songListId);
    }

}
