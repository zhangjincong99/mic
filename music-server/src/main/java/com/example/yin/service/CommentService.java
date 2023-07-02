package com.example.yin.service;

import com.example.yin.domain.Comment;

import java.util.List;

public interface CommentService {

    boolean addComment(Comment comment);

    boolean updateCommentMsg(Comment comment);

    boolean deleteComment(Integer id);

    List<Comment> commentOfSongId(Integer songId);

    List<Comment> commentOfSongListId(Integer songListId);
/*
addComment：添加新评论。
updateCommentMsg：更新现有评论的消息。
deleteComment：根据评论ID删除评论。
commentOfSongId：获取与指定歌曲ID相关的评论列表。
commentOfSongListId：获取与指定歌单ID相关的评论列表。
 */
}
