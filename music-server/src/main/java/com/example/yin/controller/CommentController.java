package com.example.yin.controller;

import com.example.yin.common.ErrorMessage;
import com.example.yin.common.SuccessMessage;
import com.example.yin.domain.Comment;
import com.example.yin.service.impl.CommentServiceImpl;

import org.apache.commons.lang3.ObjectUtils.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/*
这是一个评论控制器类，处理与评论相关的请求。下面是每个方法的功能描述：

addComment：提交评论。从请求中获取用户ID、类型、歌单ID或歌曲ID、评论内容等信息，并调用评论服务层的方法进行添加评论操作。
deleteComment：删除评论。从请求中获取评论ID，并调用评论服务层的方法进行删除评论操作。
commentOfSongId：获取指定歌曲ID的评论列表。从请求中获取歌曲ID，并调用评论服务层的方法获取与该歌曲ID相关的评论列表。
commentOfSongListId：获取指定歌单ID的评论列表。从请求中获取歌单ID，并调用评论服务层的方法获取与该歌单ID相关的评论列表。
commentOfLike：点赞评论。从请求中获取评论ID和点赞数，并调用评论服务层的方法更新评论信息，实现点赞功能。
 */

@RestController
public class CommentController {
    @Autowired
    private CommentServiceImpl commentService;

    // 提交评论
    @ResponseBody
    @RequestMapping(value = "/comment/add", method = RequestMethod.POST)
    public Object addComment(HttpServletRequest req) {
        String user_id = req.getParameter("userId");
        String type = req.getParameter("type");
        String song_list_id = req.getParameter("songListId");
        String song_id = req.getParameter("songId");
        String content = req.getParameter("content").trim();

        Comment comment = new Comment();
        comment.setUserId(Integer.parseInt(user_id));
        comment.setType(new Byte(type));
        if (new Byte(type) == 0) {
            comment.setSongId(Integer.parseInt(song_id));
        } else if (new Byte(type) == 1) {
            comment.setSongListId(Integer.parseInt(song_list_id));
        }
        comment.setContent(content);
        comment.setCreateTime(new Date());
        boolean res = commentService.addComment(comment);
        if (res) {
            return new SuccessMessage<Null>("评论成功").getMessage();
        } else {
            return new ErrorMessage("评论失败").getMessage();
        }
    }

    // 删除评论
    @RequestMapping(value = "/comment/delete", method = RequestMethod.GET)
    public Object deleteComment(HttpServletRequest req) {
        String id = req.getParameter("id");

        boolean res = commentService.deleteComment(Integer.parseInt(id));
        if (res) {
            return new SuccessMessage<Null>("删除成功").getMessage();
        } else {
            return new ErrorMessage("删除失败").getMessage();
        }
    }

    // 获得指定歌曲 ID 的评论列表
    @RequestMapping(value = "/comment/song/detail", method = RequestMethod.GET)
    public Object commentOfSongId(HttpServletRequest req) {
        String songId = req.getParameter("songId");

        return new SuccessMessage<List<Comment>>(null, commentService.commentOfSongId(Integer.parseInt(songId))).getMessage();
    }

    // 获得指定歌单 ID 的评论列表
    @RequestMapping(value = "/comment/songList/detail", method = RequestMethod.GET)
    public Object commentOfSongListId(HttpServletRequest req) {
        String songListId = req.getParameter("songListId");

        return new SuccessMessage<List<Comment>>(null, commentService.commentOfSongListId(Integer.parseInt(songListId)))
                .getMessage();
    }

    // 点赞
    @ResponseBody
    @RequestMapping(value = "/comment/like", method = RequestMethod.POST)
    public Object commentOfLike(HttpServletRequest req) {
        String id = req.getParameter("id").trim();
        String up = req.getParameter("up").trim();

        Comment comment = new Comment();
        comment.setId(Integer.parseInt(id));
        comment.setUp(Integer.parseInt(up));

        boolean res = commentService.updateCommentMsg(comment);
        if (res) {
            return new SuccessMessage<Null>("点赞成功").getMessage();
        } else {
            return new ErrorMessage("点赞失败").getMessage();
        }
    }
}
