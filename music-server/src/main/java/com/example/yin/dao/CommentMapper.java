package com.example.yin.dao;

import com.example.yin.domain.Comment;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Comment record);

    int insertSelective(Comment record);

    Comment selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Comment record);

    int updateByPrimaryKey(Comment record);

    int updateCommentMsg(Comment record);

    int deleteComment(Integer id);

    List<Comment> commentOfSongId(Integer songId);
//    该方法用于根据给定的歌曲ID（songId），从数据库中查询并返回该歌曲相关的评论列表。
//    返回的结果是一个List<Comment>，表示包含多个评论对象的列表。

    List<Comment> commentOfSongListId(Integer songListId);
//    该方法用于根据给定的歌单ID（songListId），从数据库中查询并返回该歌单相关的评论列表。
//    返回的结果是一个List<Comment>，表示包含多个评论对象的列表。
}
//这两个方法可以用于查询特定歌曲或歌单的评论列表。具体的实现逻辑可能涉及到数据库查询操作，
// 根据传入的歌曲ID或歌单ID，在数据库中检索相应的评论记录，并将其封装为Comment对象的列表返回。
