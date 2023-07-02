package com.example.yin.controller;

import com.example.yin.common.FatalMessage;
import com.example.yin.common.ErrorMessage;
import com.example.yin.common.SuccessMessage;
import com.example.yin.constant.Constants;
import com.example.yin.domain.Song;
import com.example.yin.service.impl.SongServiceImpl;

import org.apache.commons.lang3.ObjectUtils.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.unit.DataSize;
import org.springframework.util.unit.DataUnit;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.MultipartConfigElement;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;


/*
SongController 是一个控制器类，用于处理与歌曲相关的请求。以下是该类中的方法的解释：

addSong 方法用于添加歌曲。它接收一个 HttpServletRequest 对象和一个 MultipartFile 对象作为参数，分别表示请求对象和上传的歌曲文件。方法通过解析请求参数和文件信息，创建一个 Song 对象，并调用 songService 的 addSong 方法将歌曲信息添加到数据库中。如果添加成功，则返回成功消息；否则，返回失败消息。

deleteSong 方法用于删除歌曲。它接收一个 HttpServletRequest 对象作为参数，从请求参数中获取要删除的歌曲的 ID。方法调用 songService 的 deleteSong 方法删除指定 ID 的歌曲。如果删除成功，则返回成功消息；否则，返回失败消息。

allSong 方法用于获取所有歌曲。它不接收任何参数，直接调用 songService 的 allSong 方法获取所有歌曲的信息，并返回成功消息。

songOfId 方法用于获取指定 ID 的歌曲信息。它接收一个 HttpServletRequest 对象作为参数，从请求参数中获取要获取的歌曲的 ID。方法调用 songService 的 songOfId 方法获取指定 ID 的歌曲信息，并返回成功消息。

songOfSingerId 方法用于获取指定歌手 ID 的歌曲信息。它接收一个 HttpServletRequest 对象作为参数，从请求参数中获取要获取的歌手的 ID。方法调用 songService 的 songOfSingerId 方法获取指定歌手 ID 的歌曲信息，并返回成功消息。

songOfSingerName 方法用于获取指定歌手名的歌曲信息。它接收一个 HttpServletRequest 对象作为参数，从请求参数中获取要获取的歌手名。方法调用 songService 的 songOfSingerName 方法获取满足条件的歌曲信息，并返回成功消息。

updateSongMsg 方法用于更新歌曲信息。它接收一个 HttpServletRequest 对象作为参数，从请求参数中获取要更新的歌曲信息。方法创建一个 Song 对象，设置要更新的歌曲信息，并调用 songService 的 updateSongMsg 方法进行更新。如果更新成功，则返回成功消息；否则，返回失败消息。

updateSongPic 方法用于更新歌曲图片。它接收一个 MultipartFile 对象和一个整数参数 id，分别表示上传的图片文件和要更新的歌曲的 ID。方法通过解析文件信息，将图片文件保存到指定路径，并创建一个 Song 对象，设置要更新的歌曲的 ID 和图片路径，然后调用 songService 的 updateSongPic 方法进行更新。如果更新成功，则返回成功消息；否则，返回失败消息。

updateSongUrl 方法用于更新歌曲文件。它接收一个 MultipartFile 对象和一个整数参数 id，分别表示上传的歌曲文件和要更新的歌曲的 ID。方法通过解析文件信息，将歌曲文件保存到指定路径，并创建一个 Song 对象，设置要更新的歌曲的 ID 和文件路径，然后调用 songService 的 updateSongUrl 方法进行更新。如果更新成功，则返回成功消息；否则，返回失败消息。

SongController 是一个处理歌曲相关请求的控制器类，通过调用 songService 的相应方法来实现歌曲的添加、删除、查询和更新功能，并返回相应的结果消息。
 */
@RestController
public class SongController {

    @Autowired
    private SongServiceImpl songService;

    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        // 文件最大10M,DataUnit提供5中类型B,KB,MB,GB,TB
        factory.setMaxFileSize(DataSize.of(20, DataUnit.MEGABYTES));
        // 设置总上传数据总大小10M
        factory.setMaxRequestSize(DataSize.of(20, DataUnit.MEGABYTES));
        return factory.createMultipartConfig();
    }

    @Configuration
    public static class MyPicConfig implements WebMvcConfigurer {
        @Override
        public void addResourceHandlers(ResourceHandlerRegistry registry) {
            registry.addResourceHandler("/img/songPic/**")
                    .addResourceLocations(Constants.SONG_PIC_PATH);
            registry.addResourceHandler("/song/**")
                    .addResourceLocations(Constants.SONG_PATH);
        }
    }

    // 添加歌曲
    @ResponseBody
    @RequestMapping(value = "/song/add", method = RequestMethod.POST)
    public Object addSong(HttpServletRequest req, @RequestParam("file") MultipartFile mpfile) {
        String singer_id = req.getParameter("singerId").trim();
        String name = req.getParameter("name").trim();
        String introduction = req.getParameter("introduction").trim();
        String pic = "/img/songPic/tubiao.jpg";
        String lyric = req.getParameter("lyric").trim();

        String fileName = mpfile.getOriginalFilename();
        String filePath = System.getProperty("user.dir") + System.getProperty("file.separator") + "song";
        File file1 = new File(filePath);
        if (!file1.exists()) {
            file1.mkdir();
        }

        File dest = new File(filePath + System.getProperty("file.separator") + fileName);
        String storeUrlPath = "/song/" + fileName;
        try {
            mpfile.transferTo(dest);
            Song song = new Song();
            song.setSingerId(Integer.parseInt(singer_id));
            song.setName(name);
            song.setIntroduction(introduction);
            song.setCreateTime(new Date());
            song.setUpdateTime(new Date());
            song.setPic(pic);
            song.setLyric(lyric);
            song.setUrl(storeUrlPath);
            boolean res = songService.addSong(song);
            if (res) {
                return new SuccessMessage<String>("上传成功", storeUrlPath).getMessage();
            } else {
                return new ErrorMessage("上传失败").getMessage();
            }
        } catch (IOException e) {
            return new FatalMessage("上传失败" + e.getMessage()).getMessage();
        }
    }

    // 删除歌曲
    @RequestMapping(value = "/song/delete", method = RequestMethod.GET)
    public Object deleteSong(HttpServletRequest req) {
        String id = req.getParameter("id");

        boolean res = songService.deleteSong(Integer.parseInt(id));
        if (res) {
            return new SuccessMessage<Null>("删除成功").getMessage();
        } else {
            return new ErrorMessage("删除失败").getMessage();
        }
    }

    // 返回所有歌曲
    @RequestMapping(value = "/song", method = RequestMethod.GET)
    public Object allSong() {
        return new SuccessMessage<List<Song>>(null, songService.allSong()).getMessage();
    }

    // 返回指定歌曲ID的歌曲
    @RequestMapping(value = "/song/detail", method = RequestMethod.GET)
    public Object songOfId(HttpServletRequest req) {
        String id = req.getParameter("id");

        return new SuccessMessage<List<Song>>(null, songService.songOfId(Integer.parseInt(id))).getMessage();
    }

    // 返回指定歌手ID的歌曲
    @RequestMapping(value = "/song/singer/detail", method = RequestMethod.GET)
    public Object songOfSingerId(HttpServletRequest req) {
        String singerId = req.getParameter("singerId");

        return new SuccessMessage<List<Song>>(null, songService.songOfSingerId(Integer.parseInt(singerId))).getMessage();
    }

    // 返回指定歌手名的歌曲
    @RequestMapping(value = "/song/singerName/detail", method = RequestMethod.GET)
    public Object songOfSingerName(HttpServletRequest req) {
        String name = req.getParameter("name");

        return new SuccessMessage<List<Song>>(null, songService.songOfSingerName('%' + name + '%')).getMessage();
    }

    // 更新歌曲信息
    @ResponseBody
    @RequestMapping(value = "/song/update", method = RequestMethod.POST)
    public Object updateSongMsg(HttpServletRequest req) {
        String id = req.getParameter("id").trim();
        String singer_id = req.getParameter("singerId").trim();
        String name = req.getParameter("name").trim();
        String introduction = req.getParameter("introduction").trim();
        String lyric = req.getParameter("lyric").trim();

        Song song = new Song();
        song.setId(Integer.parseInt(id));
        song.setSingerId(Integer.parseInt(singer_id));
        song.setName(name);
        song.setIntroduction(introduction);
        song.setUpdateTime(new Date());
        song.setLyric(lyric);

        boolean res = songService.updateSongMsg(song);
        if (res) {
            return new SuccessMessage<Null>("修改成功").getMessage();
        } else {
            return new ErrorMessage("修改失败").getMessage();
        }
    }

    // 更新歌曲图片
    @ResponseBody
    @RequestMapping(value = "/song/img/update", method = RequestMethod.POST)
    public Object updateSongPic(@RequestParam("file") MultipartFile urlFile, @RequestParam("id") int id) {
        String fileName = System.currentTimeMillis() + urlFile.getOriginalFilename();
        String filePath = System.getProperty("user.dir") + System.getProperty("file.separator") + "img" + System.getProperty("file.separator") + "songPic";
        File file1 = new File(filePath);
        if (!file1.exists()) {
            file1.mkdir();
        }

        File dest = new File(filePath + System.getProperty("file.separator") + fileName);
        String storeUrlPath = "/img/songPic/" + fileName;
        try {
            urlFile.transferTo(dest);
            Song song = new Song();
            song.setId(id);
            song.setPic(storeUrlPath);
            boolean res = songService.updateSongPic(song);
            if (res) {
                return new SuccessMessage<String>("上传成功", storeUrlPath).getMessage();
            } else {
                return new ErrorMessage("上传失败").getMessage();
            }
        } catch (IOException e) {
            return new FatalMessage("上传失败" + e.getMessage()).getMessage();
        }
    }

    // 更新歌曲
    @ResponseBody
    @RequestMapping(value = "/song/url/update", method = RequestMethod.POST)
    public Object updateSongUrl(@RequestParam("file") MultipartFile urlFile, @RequestParam("id") int id) {
        String fileName = urlFile.getOriginalFilename();
        String filePath = System.getProperty("user.dir") + System.getProperty("file.separator") + "song";
        File file1 = new File(filePath);
        if (!file1.exists()) {
            file1.mkdir();
        }

        File dest = new File(filePath + System.getProperty("file.separator") + fileName);
        String storeUrlPath = "/song/" + fileName;
        try {
            urlFile.transferTo(dest);
            Song song = new Song();
            song.setId(id);
            song.setUrl(storeUrlPath);
            boolean res = songService.updateSongUrl(song);
            if (res) {
                return new SuccessMessage<String>("更新成功", storeUrlPath).getMessage();
            } else {
                return new ErrorMessage("更新失败").getMessage();
            }
        } catch (IOException e) {
            return new FatalMessage("更新失败" + e.getMessage()).getMessage();
        }
    }
}
