package com.example.controller;

/**
 * @author Tu
 * @Package com.example.controller
 * @date 2021/3/26 15:37
 */

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.json.JSON;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.common.lang.Result;
import com.example.entity.Devlist;
import com.example.service.DevlistService;
import com.zaxxer.hikari.util.SuspendResumeLock;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Data
@RestController

public class DevlistController {
    @Autowired
    DevlistService devlistService;

    @GetMapping("/getlist")
    public Result getlist() {
        return Result.succ("ok!");
    }

    @PostMapping("/save")
    public Result savedata(@RequestBody Devlist devlist) {
        Devlist dd = null;
        dd = new Devlist();
        dd.setCreated(LocalDateTime.now());
//        dd.setId(ShiroUtil.getProfile().getId());
        BeanUtil.copyProperties(devlist, dd, "created");
        devlistService.saveOrUpdate(dd);
        return Result.succ("保存成功！");
    }

    /**
     * [java.util.List<com.example.entity.Devlist>]
     *
     * @return com.example.common.lang.Result
     * @author Tu
     * @date 2021/3/27 10:32
     * @message 批量插入
     * List<Devlist> devlist 接收前端对象数组
     * 实现循环插入
     * 去重场景 实现之前查询表中是否有相同的 的数据 相同不插入 反之
     * 详情
     * 先查询是否存在
     * 为空则插入数据库
     *
     */
    @PostMapping("/savelist")
    public Result savelist(@RequestBody List<Devlist> devlist) {
        if(devlist.size()==0||devlist==null){
            return Result.fail("插入数据为空！");
        }
        Devlist dd = null;
        dd = new Devlist();
        dd.setCreated(LocalDateTime.now());
        List<Devlist> entityList = new ArrayList<>(devlist);
        for (int i = 0; i < entityList.size(); i++) {
            QueryWrapper<Devlist> queryWrapper = new QueryWrapper<>();
            queryWrapper.like("deviceid", entityList.get(i).getDeviceid());
            List<Devlist> dds = devlistService.list(queryWrapper);
            if (dds == null || dds.size() == 0) {
                BeanUtil.copyProperties(entityList.get(i), dd, "created");
                devlistService.saveOrUpdate(dd);
            }
        }

        return Result.succ("批量插入成功", null);
    }

    @GetMapping("/getdevlists")
    public Result getdevlists(Integer currentPage) {
        if (currentPage == null || currentPage < 1) {
            currentPage = 1;
        }
        Page page = new Page(currentPage, 5);
        QueryWrapper<Devlist> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("created");
        IPage pageData = devlistService.page(page, queryWrapper);

        return Result.succ("操作成功！", pageData);
    }

    /**
     * [java.lang.String, java.lang.Integer]
     *
     * @return com.example.common.lang.Result
     * @author Tu
     * @date 2021/3/27 16:05
     * @message 模糊查询功能
     */
    @GetMapping("/devquery")
    public Result devquery(String deviceid, Integer currentPage) {
        if (currentPage == null || currentPage < 1) {
            currentPage = 1;
        }
        Page page = new Page(currentPage, 5);
        QueryWrapper<Devlist> queryWrapper = new QueryWrapper<>();
        if(deviceid != null && deviceid.length() != 0){
            queryWrapper.like("deviceid", deviceid);
        }

        queryWrapper.orderByDesc("created");

        IPage pageData = devlistService.page(page, queryWrapper);

        List<Devlist> deviceLogLists = devlistService.list(queryWrapper);

        if (deviceLogLists == null || deviceLogLists.size() == 0) {
            return Result.fail("没有数据！");
        }
        return Result.succ("查询成功", pageData);
    }
}
