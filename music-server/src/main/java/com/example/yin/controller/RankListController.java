package com.example.yin.controller;

import com.example.yin.common.ErrorMessage;
import com.example.yin.common.SuccessMessage;
import com.example.yin.domain.RankList;
import com.example.yin.service.impl.RankListServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class RankListController {

    @Autowired
    private RankListServiceImpl rankListService;

    @ResponseBody
    @RequestMapping(value = "/rankList/add", method = RequestMethod.POST)
    public Object addRank(HttpServletRequest req) {
        String songListId = req.getParameter("songListId").trim();
        String consumerId = req.getParameter("consumerId").trim();
        String score = req.getParameter("score").trim();

        RankList rankList = new RankList();
        rankList.setSongListId(Long.parseLong(songListId));
        rankList.setConsumerId(Long.parseLong(consumerId));
        rankList.setScore(Integer.parseInt(score));

        boolean res = rankListService.addRank(rankList);
        if (res) {
            return new SuccessMessage<>("评价成功").getMessage();
        } else {
            return new ErrorMessage("评价失败").getMessage();
        }
    }

    @RequestMapping(value = "/rankList", method = RequestMethod.GET)
    public Object rankOfSongListId(HttpServletRequest req) {
        String songListId = req.getParameter("songListId");

        return new SuccessMessage<>(null, rankListService.rankOfSongListId(Long.parseLong(songListId))).getMessage();
    }

    //    @RequestMapping(value = "/rankList/user", method = RequestMethod.GET)
//    public Object getUserRank(HttpServletRequest req) {
//        String consumerId = req.getParameter("consumerId");
//        String songListId = req.getParameter("songListId");
//
//        return new SuccessMessage<>(null, rankListService.getUserRank(Long.parseLong(consumerId), Long.parseLong(songListId))).getMessage();
//    }
    @RequestMapping(value = "/rankList/user", method = RequestMethod.GET)
    public Object getUserRank(HttpServletRequest req) {
        String consumerId = req.getParameter("consumerId");
        String songListId = req.getParameter("songListId");

        // 检查参数是否为空
        if (consumerId == null || songListId == null || consumerId.isEmpty() || songListId.isEmpty()) {
            return new ErrorMessage("参数不能为空").getMessage();
        }

        try {
            // 将参数转换为long类型
            long consumerIdLong = Long.parseLong(consumerId);
            long songListIdLong = Long.parseLong(songListId);

            // 调用服务方法并返回结果
            return new SuccessMessage<>(null, rankListService.getUserRank(consumerIdLong, songListIdLong)).getMessage();
        } catch (NumberFormatException e) {
            return new ErrorMessage("参数格式错误").getMessage();
        }
    }
}
