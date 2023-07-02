package com.example.yin.service;

import com.example.yin.domain.Singer;

import java.util.List;

public interface SingerService {

    boolean addSinger (Singer singer);

    boolean updateSingerMsg(Singer singer);

    boolean updateSingerPic(Singer singer);

    boolean deleteSinger(Integer id);

    List<Singer> allSinger();

    List<Singer> singerOfName(String name);

    List<Singer> singerOfSex(Integer sex);
}

/*
SingerService 是一个接口，定义了对歌手信息进行操作的方法，以下是对这些方法的解释：

addSinger 方法用于添加歌手信息。它接收一个 Singer 对象作为参数，表示要添加的歌手信息。方法返回一个布尔值，表示添加操作是否成功。

updateSingerMsg 方法用于更新歌手信息。它接收一个 Singer 对象作为参数，表示要更新的歌手信息。方法返回一个布尔值，表示更新操作是否成功。

updateSingerPic 方法用于更新歌手头像。它接收一个 Singer 对象作为参数，表示要更新头像的歌手信息。方法返回一个布尔值，表示更新操作是否成功。

deleteSinger 方法用于删除歌手信息。它接收一个整数参数 id，表示要删除的歌手的 ID。方法返回一个布尔值，表示删除操作是否成功。

allSinger 方法用于获取所有歌手信息。它返回一个包含所有歌手信息的列表。

singerOfName 方法用于根据歌手姓名查找歌手信息。它接收一个字符串参数 name，表示要查找的歌手姓名。方法返回一个包含满足条件的歌手信息的列表。

singerOfSex 方法用于根据歌手性别查找歌手信息。它接收一个整数参数 sex，表示要查找的歌手性别。方法返回一个包含满足条件的歌手信息的列表。

SingerService 接口定义了对歌手信息的增删改查操作，具体实现可由不同的类来实现，以满足不同的业务需求。
 */