package com.example.yin.service.impl;

import com.example.yin.dao.SingerMapper;
import com.example.yin.domain.Singer;
import com.example.yin.service.SingerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/*
SingerServiceImpl 是 SingerService 接口的实现类，以下是对其中各个方法的解释：

updateSingerMsg 方法用于更新歌手信息。它接收一个 Singer 对象，表示要更新的歌手信息。方法调用 singerMapper.updateSingerMsg 方法将更新后的歌手信息保存到数据库中，并根据更新结果返回布尔值。

updateSingerPic 方法用于更新歌手头像。它接收一个 Singer 对象，表示要更新头像的歌手信息。方法调用 singerMapper.updateSingerPic 方法将更新后的歌手头像信息保存到数据库中，并根据更新结果返回布尔值。

deleteSinger 方法用于删除歌手信息。它接收一个整数参数 id，表示要删除的歌手的 ID。方法调用 singerMapper.deleteSinger 方法删除对应的歌手记录，并根据删除结果返回布尔值。

allSinger 方法用于获取所有歌手信息。它调用 singerMapper.allSinger 方法从数据库中获取所有歌手的信息，并将其作为列表返回。

addSinger 方法用于添加歌手信息。它接收一个 Singer 对象，表示要添加的歌手信息。方法调用 singerMapper.insertSelective 方法将歌手信息插入到数据库中，并根据插入结果返回布尔值。

singerOfName 方法用于根据歌手姓名查找歌手信息。它接收一个字符串参数 name，表示要查找的歌手姓名。方法调用 singerMapper.singerOfName 方法从数据库中查找对应姓名的歌手记录，并将其作为列表返回。

singerOfSex 方法用于根据歌手性别查找歌手信息。它接收一个整数参数 sex，表示要查找的歌手性别。方法调用 singerMapper.singerOfSex 方法从数据库中查找对应性别的歌手记录，并将其作为列表返回。

SingerServiceImpl 类通过依赖注入 SingerMapper 对象来实现与数据库的交互，并提供了对应的方法来处理歌手信息的增删改查操作。
 */

@Service
public class SingerServiceImpl implements SingerService{

    @Autowired
    private SingerMapper singerMapper;

    @Override
    public boolean updateSingerMsg(Singer singer) {
        return singerMapper.updateSingerMsg(singer) > 0;
    }

    @Override
    public boolean updateSingerPic(Singer singer) {

        return singerMapper.updateSingerPic(singer) > 0;
    }

    @Override
    public boolean deleteSinger(Integer id) {
        return singerMapper.deleteSinger(id) > 0;
    }

    @Override
    public List<Singer> allSinger()
    {
        return singerMapper.allSinger();
    }

    @Override
    public boolean addSinger(Singer singer)  {

        return singerMapper.insertSelective(singer) > 0;
    }

    @Override
    public List<Singer> singerOfName(String name)

    {
        return singerMapper.singerOfName(name);
    }

    @Override
    public List<Singer> singerOfSex(Integer sex)

    {
        return singerMapper.singerOfSex(sex);
    }
}
