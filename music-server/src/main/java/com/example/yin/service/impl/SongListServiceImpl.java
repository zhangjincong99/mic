package com.example.yin.service.impl;

import com.example.yin.dao.SongListMapper;
import com.example.yin.domain.SongList;
import com.example.yin.service.SongListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/*
SongListServiceImpl 类是 SongListService 接口的实现类。它包含了对歌单相关操作的具体实现逻辑。以下是该类中声明的方法的解释：

updateSongListMsg：该方法用于更新歌单的详细信息。它接收一个 SongList 对象作为参数，并返回一个表示操作是否成功的布尔值。在实现中，它调用 songListMapper 的 updateSongListMsg 方法来更新数据库中的歌单信息，并根据返回的受影响行数判断操作是否成功。

deleteSongList：该方法用于删除歌单。它接收一个 Integer 类型的 ID 参数，并返回一个表示操作是否成功的布尔值。在实现中，它调用 songListMapper 的 deleteSongList 方法来从数据库中删除指定 ID 的歌单，并根据返回的受影响行数判断操作是否成功。

allSongList：该方法用于获取所有歌单的详细信息。它返回一个包含所有歌单的 SongList 对象列表。在实现中，它调用 songListMapper 的 allSongList 方法从数据库中获取所有歌单的信息。

likeTitle：该方法用于根据指定的标题关键字搜索相似的歌单。它接收一个 String 类型的标题关键字参数，并返回一个符合搜索条件的 SongList 对象列表。在实现中，它调用 songListMapper 的 likeTitle 方法根据标题关键字从数据库中检索相似的歌单。

likeStyle：该方法用于根据指定的风格关键字搜索相似的歌单。它接收一个 String 类型的风格关键字参数，并返回一个符合搜索条件的 SongList 对象列表。在实现中，它调用 songListMapper 的 likeStyle 方法根据风格关键字从数据库中检索相似的歌单。

addSongList：该方法用于添加新的歌单。它接收一个 SongList 对象作为参数，并返回一个表示操作是否成功的布尔值。在实现中，它调用 songListMapper 的 insertSelective 方法将歌单信息插入到数据库中，并根据返回的受影响行数判断操作是否成功。

updateSongListImg：该方法用于更新歌单的图片。它接收一个 SongList 对象作为参数，并返回一个表示操作是否成功的布尔值。在实现中，它调用 songListMapper 的 updateSongListImg 方法来更新数据库中的歌单图片信息，并根据返回的受影响行数判断操作是否成功。

这些方法实现了对歌单的增加、更新、删除以及根据不同的搜索条件检索歌单的功能。
 */


@Service
public class SongListServiceImpl implements SongListService {

    @Autowired
    private SongListMapper songListMapper;

    @Override
    public boolean updateSongListMsg(SongList songList) {
        return songListMapper.updateSongListMsg(songList) >0 ?true:false;
    }

    @Override
    public boolean deleteSongList(Integer id) {
        return songListMapper.deleteSongList(id) >0 ?true:false;
    }

    @Override
    public List<SongList> allSongList()
    {
        return songListMapper.allSongList();
    }

    @Override
    public List<SongList> likeTitle(String title)
    {
        return songListMapper.likeTitle(title);
    }

    @Override
    public List<SongList> likeStyle(String style)
    {
        return songListMapper.likeStyle(style);
    }

    @Override
    public boolean addSongList(SongList songList)
    {
        return songListMapper.insertSelective(songList) > 0?true:false;
    }

    @Override
    public boolean updateSongListImg(SongList songList) {

        return songListMapper.updateSongListImg(songList) >0 ?true:false;
    }
}
