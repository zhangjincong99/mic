package com.example.yin.controller;

import com.example.yin.common.ErrorMessage;
import com.example.yin.common.SuccessMessage;
import com.example.yin.domain.Collect;
import com.example.yin.service.impl.CollectServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
// 注解用于自动注入依赖对象，这里可以看到 HttpServletRequest 对象被自动注入到 CollectController 中，以便在处理方法中使用。
import org.springframework.web.bind.annotation.RequestMapping;
//注解用于映射请求路径和方法的关系。
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
//注解用于指示方法的返回值应该作为响应体返回给客户端，而不是视图名称。
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;

import java.util.Date;
import java.util.List;

@RestController
public class CollectController {

    @Autowired
    private CollectServiceImpl collectService;

    // 添加收藏的歌曲
    @ResponseBody
    @RequestMapping(value = "/collection/add", method = RequestMethod.POST)
    public Object addCollection(HttpServletRequest req) {
        String user_id = req.getParameter("userId");
        String type = req.getParameter("type");
        String song_id = req.getParameter("songId");
        String song_list_id = req.getParameter("songListId");

        Collect collect = new Collect();
//        将用户ID和类型设置为从字符串转换而来的整数值。根据类型的不同，设置歌曲ID或歌单ID。
        collect.setUserId(Integer.parseInt(user_id));
        collect.setType(new Byte(type));
        if (new Byte(type) == 0) {
            collect.setSongId(Integer.parseInt(song_id));
        } else if (new Byte(type) == 1) {
            collect.setSongListId(Integer.parseInt(song_list_id));
        }
        collect.setCreateTime(new Date());

//        调用 collectService 的 addCollection 方法，将收藏对象传递给它进行处理，并获取操作结果。
        boolean res = collectService.addCollection(collect);
        if (res) {
            return new SuccessMessage<Boolean>("收藏成功", true).getMessage();
        } else {
            return new ErrorMessage("收藏失败").getMessage();
        }
    }

    // 取消收藏的歌曲
    @RequestMapping(value = "/collection/delete", method = RequestMethod.DELETE)
    public Object deleteCollection(HttpServletRequest req) {
        String user_id = req.getParameter("userId").trim();
        String song_id = req.getParameter("songId").trim();

        boolean res = collectService.deleteCollect(Integer.parseInt(user_id), Integer.parseInt(song_id));
//        调用 collectService 的 deleteCollect 方法，传递用户ID和歌曲ID的整数值进行取消收藏操作，并获取操作结果。
        if (res) {
            return new SuccessMessage<Boolean>("取消收藏", false).getMessage();
        } else {
            return new ErrorMessage("取消收藏失败").getMessage();
        }
    }

    // 是否收藏歌曲
    @RequestMapping(value = "/collection/status", method = RequestMethod.POST)
    public Object isCollection(HttpServletRequest req) {
        String user_id = req.getParameter("userId").trim();
        String song_id = req.getParameter("songId").trim();
//        传递用户ID和歌曲ID的整数值进行判断是否已收藏的操作，并获取操作结果。
        boolean res = collectService.existSongId(Integer.parseInt(user_id), Integer.parseInt(song_id));
        if (res) {
            return new SuccessMessage<Boolean>("已收藏", true).getMessage();
        } else {
            return new SuccessMessage<Boolean>("未收藏", false).getMessage();
        }
    }

    // 返回的指定用户 ID 收藏的列表
    @RequestMapping(value = "/collection/detail", method = RequestMethod.GET)
    public Object collectionOfUser(HttpServletRequest req) {
        String userId = req.getParameter("userId");

        return new SuccessMessage<List<Collect>>("取消收藏", collectService.collectionOfUser(Integer.parseInt(userId))).getMessage();
    }
}
