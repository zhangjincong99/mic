package com.example.yin.controller;

import com.example.yin.common.FatalMessage;
import com.example.yin.common.ErrorMessage;
import com.example.yin.common.SuccessMessage;
import com.example.yin.constant.Constants;
import com.example.yin.domain.SongList;
import com.example.yin.service.impl.SongListServiceImpl;

import org.apache.commons.lang3.ObjectUtils.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;


/*
SongListController 类是一个基于 Spring MVC 的控制器，用于处理与歌单相关的 HTTP 请求。以下是该类中声明的方法的解释：

MyPicConfig：这是一个内部静态类，用于配置静态资源的处理。在这里，它配置了 /img/songListPic/** 路径下的资源映射到具体的文件路径。

addSongList：该方法用于添加歌单。它接收一个 HttpServletRequest 对象作为参数，从请求中获取歌单的标题、介绍、风格等信息，并调用 songListService 的 addSongList 方法将歌单添加到数据库中。根据操作的结果返回相应的成功或失败消息。

deleteSongList：该方法用于删除歌单。它接收一个 HttpServletRequest 对象作为参数，从请求中获取要删除的歌单的 ID，并调用 songListService 的 deleteSongList 方法删除对应的歌单。根据操作的结果返回相应的成功或失败消息。

allSongList：该方法用于获取所有歌单的详细信息。它不接收任何参数，直接调用 songListService 的 allSongList 方法从数据库中获取所有歌单的信息，并返回一个包含所有歌单的成功消息。

songListOfLikeTitle：该方法用于根据标题关键字搜索符合条件的歌单。它接收一个 HttpServletRequest 对象作为参数，从请求中获取标题关键字，并调用 songListService 的 likeTitle 方法根据标题关键字搜索歌单。返回一个包含符合条件的歌单的成功消息。

songListOfStyle：该方法用于根据风格关键字搜索符合条件的歌单。它接收一个 HttpServletRequest 对象作为参数，从请求中获取风格关键字，并调用 songListService 的 likeStyle 方法根据风格关键字搜索歌单。返回一个包含符合条件的歌单的成功消息。

updateSongListMsg：该方法用于更新歌单的信息。它接收一个 HttpServletRequest 对象作为参数，从请求中获取歌单的 ID、标题、介绍、风格等信息，并调用 songListService 的 updateSongListMsg 方法更新数据库中对应的歌单信息。根据操作的结果返回相应的成功或失败消息。

updateSongListPic：该方法用于更新歌单的图片。它接收一个 MultipartFile 对象和一个歌单的 ID 作为参数，从请求中获取上传的图片文件和歌单的 ID。首先将图片文件保存到指定的路径下，然后调用 songListService 的 updateSongListImg 方法更新数据库中对应的歌单的图片信息。根据操作的结果返回相应的成功或失败消息。

这些方法通过接收不同的 HTTP 请求，并调用相应的 songListService 方法来实现对歌单的添加、删除、查询和更新的功能，并根据操作的结果返回相应的消息。
 */
@RestController
public class SongListController {

    @Autowired
    private SongListServiceImpl songListService;

    @Configuration
    public static class MyPicConfig implements WebMvcConfigurer {
        @Override
        public void addResourceHandlers(ResourceHandlerRegistry registry) {
            registry.addResourceHandler("/img/songListPic/**")
                    .addResourceLocations(Constants.SONGLIST_PIC_PATH);
        }
    }

    // 添加歌单
    @ResponseBody
    @RequestMapping(value = "/songList/add", method = RequestMethod.POST)
    public Object addSongList(HttpServletRequest req) {
        String title = req.getParameter("title").trim();
        String introduction = req.getParameter("introduction").trim();
        String style = req.getParameter("style").trim();
        String pic = "/img/songListPic/123.jpg";

        SongList songList = new SongList();
        songList.setTitle(title);
        songList.setIntroduction(introduction);
        songList.setStyle(style);
        songList.setPic(pic);

        boolean res = songListService.addSongList(songList);
        if (res) {
            return new SuccessMessage<Null>("添加成功").getMessage();
        } else {
            return new ErrorMessage("添加失败").getMessage();
        }
    }

    // 删除歌单
    @RequestMapping(value = "/songList/delete", method = RequestMethod.GET)
    public Object deleteSongList(HttpServletRequest req) {
        String id = req.getParameter("id");

        boolean res = songListService.deleteSongList(Integer.parseInt(id));
        if (res) {
            return new SuccessMessage<Null>("删除成功").getMessage();
        } else {
            return new ErrorMessage("删除失败").getMessage();
        }
    }

    // 返回所有歌单
    @RequestMapping(value = "/songList", method = RequestMethod.GET)
    public Object allSongList() {
        return new SuccessMessage<List<SongList>>(null, songListService.allSongList()).getMessage();
    }

    // 返回标题包含文字的歌单
    @RequestMapping(value = "/songList/likeTitle/detail", method = RequestMethod.GET)
    public Object songListOfLikeTitle(HttpServletRequest req) {
        String title = req.getParameter("title").trim();

        return new SuccessMessage<List<SongList>>(null, songListService.likeTitle('%' + title + '%')).getMessage();
    }

    // 返回指定类型的歌单
    @RequestMapping(value = "/songList/style/detail", method = RequestMethod.GET)
    public Object songListOfStyle(HttpServletRequest req) {
        String style = req.getParameter("style").trim();

        return new SuccessMessage<List<SongList>>(null, songListService.likeStyle('%' + style + '%')).getMessage();
    }

    // 更新歌单信息
    @ResponseBody
    @RequestMapping(value = "/songList/update", method = RequestMethod.POST)
    public Object updateSongListMsg(HttpServletRequest req) {
        String id = req.getParameter("id").trim();
        String title = req.getParameter("title").trim();
        String introduction = req.getParameter("introduction").trim();
        String style = req.getParameter("style").trim();

        SongList songList = new SongList();
        songList.setId(Integer.parseInt(id));
        songList.setTitle(title);
        songList.setIntroduction(introduction);
        songList.setStyle(style);

        boolean res = songListService.updateSongListMsg(songList);
        if (res) {
            return new SuccessMessage<Null>("修改成功").getMessage();
        } else {
            return new ErrorMessage("修改失败").getMessage();
        }
    }

    // 更新歌单图片
    @ResponseBody
    @RequestMapping(value = "/songList/img/update", method = RequestMethod.POST)
    public Object updateSongListPic(@RequestParam("file") MultipartFile avatorFile, @RequestParam("id") int id) {
        String fileName = System.currentTimeMillis() + avatorFile.getOriginalFilename();
        String filePath = System.getProperty("user.dir") + System.getProperty("file.separator") + "img" + System.getProperty("file.separator") + "songListPic";
        File file1 = new File(filePath);
        if (!file1.exists()) {
            file1.mkdir();
        }

        File dest = new File(filePath + System.getProperty("file.separator") + fileName);
        String imgPath = "/img/songListPic/" + fileName;
        try {
            avatorFile.transferTo(dest);
            SongList songList = new SongList();
            songList.setId(id);
            songList.setPic(imgPath);

            boolean res = songListService.updateSongListImg(songList);
            if (res) {
                return new SuccessMessage<String>("上传成功", imgPath).getMessage();
            } else {
                return new ErrorMessage("上传失败").getMessage();
            }
        } catch (IOException e) {
            return new FatalMessage("上传失败" + e.getMessage()).getMessage();
        }
    }
}
