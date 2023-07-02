package com.example.yin.controller;

import com.example.yin.common.FatalMessage;
import com.example.yin.common.ErrorMessage;
import com.example.yin.common.SuccessMessage;
import com.example.yin.constant.Constants;
import com.example.yin.domain.Singer;
import com.example.yin.service.impl.SingerServiceImpl;

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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/*

以下是对 SingerController 中各个方法的解释：

addSinger 方法用于添加歌手信息。它从请求中获取歌手的各个属性，包括姓名、性别、出生日期、地区、简介等，并创建一个 Singer 对象来表示歌手信息。方法通过调用 singerService.addSinger 方法将歌手信息添加到数据库中，并根据添加结果返回相应的消息。

deleteSinger 方法用于删除歌手信息。它从请求中获取要删除的歌手的 ID，并调用 singerService.deleteSinger 方法来删除对应的歌手记录。根据删除结果返回相应的消息。

allSinger 方法用于获取所有歌手信息。它调用 singerService.allSinger 方法获取数据库中所有歌手的信息，并将其封装到 SuccessMessage 对象中返回。

singerOfName 方法用于根据歌手姓名查找歌手信息。它从请求中获取要查找的歌手姓名，并调用 singerService.singerOfName 方法来查找对应的歌手记录。根据查询结果将歌手信息封装到 SuccessMessage 对象中返回。

singerOfSex 方法用于根据歌手性别查找歌手信息。它从请求中获取要查找的歌手性别，并调用 singerService.singerOfSex 方法来查找对应的歌手记录。根据查询结果将歌手信息封装到 SuccessMessage 对象中返回。

updateSingerMsg 方法用于更新歌手信息。它从请求中获取要更新的歌手的各个属性，并创建一个 Singer 对象来表示更新后的歌手信息。方法通过调用 singerService.updateSingerMsg 方法将更新后的歌手信息保存到数据库中，并根据更新结果返回相应的消息。

updateSingerPic 方法用于更新歌手头像。它接收一个 MultipartFile 对象和歌手的 ID，表示要上传的头像文件和对应的歌手。方法将上传的头像文件保存到指定的路径中，并调用 singerService.updateSingerPic 方法来更新歌手的头像信息。根据更新结果返回相应的消息。

此外，SingerController 中还包含了一个内部静态类 MyPicConfig，用于配置静态资源的访问路径。该类实现了 WebMvcConfigurer 接口，并重写了 addResourceHandlers 方法，将 /img/singerPic/** 请求映射到指定的资源路径上。
 */


@RestController
public class SingerController {

    @Autowired
    private SingerServiceImpl singerService;

    @Configuration
    public static class MyPicConfig implements WebMvcConfigurer {
        @Override
        public void addResourceHandlers(ResourceHandlerRegistry registry) {
            registry.addResourceHandler("/img/singerPic/**")
                    .addResourceLocations(Constants.SINGER_PIC_PATH);
        }
    }

    // 添加歌手
    @ResponseBody
    @RequestMapping(value = "/singer/add", method = RequestMethod.POST)
    public Object addSinger(HttpServletRequest req) {
        String name = req.getParameter("name").trim();
        String sex = req.getParameter("sex").trim();
        String birth = req.getParameter("birth").trim();
        String location = req.getParameter("location").trim();
        String introduction = req.getParameter("introduction").trim();
        String pic = "/img/avatorImages/user.jpg";

        Singer singer = new Singer();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date myBirth = new Date();
        try {
            myBirth = dateFormat.parse(birth);
        } catch (Exception e) {
            e.printStackTrace();
        }
        singer.setName(name);
        singer.setSex(new Byte(sex));
        singer.setBirth(myBirth);
        singer.setLocation(location);
        singer.setIntroduction(introduction);
        singer.setPic(pic);

        boolean res = singerService.addSinger(singer);
        if (res) {
            return new SuccessMessage<Null>("添加成功").getMessage();
        } else {
            return new ErrorMessage("添加失败").getMessage();
        }
    }

    // 删除歌手
    @RequestMapping(value = "/singer/delete", method = RequestMethod.GET)
    public Object deleteSinger(HttpServletRequest req) {
        String id = req.getParameter("id");

        boolean res = singerService.deleteSinger(Integer.parseInt(id));
        if (res) {
            return new SuccessMessage<Null>("删除成功").getMessage();
        } else {
            return new ErrorMessage("删除失败").getMessage();
        }
    }

    // 返回所有歌手
    @RequestMapping(value = "/singer", method = RequestMethod.GET)
    public Object allSinger() {
        return new SuccessMessage<List<Singer>>(null, singerService.allSinger()).getMessage();
    }

    // 根据歌手名查找歌手
    @RequestMapping(value = "/singer/name/detail", method = RequestMethod.GET)
    public Object singerOfName(HttpServletRequest req) {
        String name = req.getParameter("name").trim();

        return new SuccessMessage<List<Singer>>(null, singerService.singerOfName(name)).getMessage();
    }

    // 根据歌手性别查找歌手
    @RequestMapping(value = "/singer/sex/detail", method = RequestMethod.GET)
    public Object singerOfSex(HttpServletRequest req) {
        String sex = req.getParameter("sex").trim();

        return new SuccessMessage<List<Singer>>(null, singerService.singerOfSex(Integer.parseInt(sex))).getMessage();
    }

    // 更新歌手信息
    @ResponseBody
    @RequestMapping(value = "/singer/update", method = RequestMethod.POST)
    public Object updateSingerMsg(HttpServletRequest req) {
        String id = req.getParameter("id").trim();
        String name = req.getParameter("name").trim();
        String sex = req.getParameter("sex").trim();
        String birth = req.getParameter("birth").trim();
        String location = req.getParameter("location").trim();
        String introduction = req.getParameter("introduction").trim();

        Singer singer = new Singer();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date myBirth = new Date();
        try {
            myBirth = dateFormat.parse(birth);
        } catch (Exception e) {
            e.printStackTrace();
        }
        singer.setId(Integer.parseInt(id));
        singer.setName(name);
        singer.setSex(new Byte(sex));
        singer.setBirth(myBirth);
        singer.setLocation(location);
        singer.setIntroduction(introduction);

        boolean res = singerService.updateSingerMsg(singer);
        if (res) {
            return new SuccessMessage<Null>("修改成功").getMessage();
        } else {
            return new ErrorMessage("修改失败").getMessage();
        }
    }

    // 更新歌手头像
    @ResponseBody
    @RequestMapping(value = "/singer/avatar/update", method = RequestMethod.POST)
    public Object updateSingerPic(@RequestParam("file") MultipartFile avatorFile, @RequestParam("id") int id) {
//        文件名=当前时间到毫秒+原来的文件名
        String fileName = System.currentTimeMillis() + avatorFile.getOriginalFilename();
//        文件路径
        String filePath = System.getProperty("user.dir") + System.getProperty("file.separator") + "img"
                + System.getProperty("file.separator") + "singerPic";
//        如果文件不存在，新增该路径
        File file1 = new File(filePath);
        if (!file1.exists()) {
            file1.mkdir();
        }
//        实际的文件地址
        File dest = new File(filePath + System.getProperty("file.separator") + fileName);
//        存储到数据库的相对文件地址
        String imgPath = "/img/singerPic/" + fileName;
        try {
            avatorFile.transferTo(dest);
            Singer singer = new Singer();
            singer.setId(id);
            singer.setPic(imgPath);

            boolean res = singerService.updateSingerPic(singer);
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
