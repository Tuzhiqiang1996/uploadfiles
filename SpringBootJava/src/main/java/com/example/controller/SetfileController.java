package com.example.controller;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.common.lang.Result;
import com.example.entity.Setfile;
import com.example.service.SetfileService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * @author Tu
 * @Package com.example.controller
 * @date 2021/3/30-15:12
 */
@Data
@RestController
public class SetfileController {
    @Autowired
    SetfileService setfileService;

    @GetMapping("getlistnumber")
    public Result getlistnumber(String orderNumber) {

        QueryWrapper<Setfile> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("order_number", orderNumber);
        List<Setfile> files = setfileService.list(queryWrapper);
        //判断数据是否为空
        if (files == null || files.size() == 0) {
            return Result.fail("没有数据！");
        }
        return Result.succ("查询成功！", files);
    }

    /**
     * [com.example.entity.Setfile]
     *
     * @return com.example.common.lang.Result
     * @author Tu
     * @date 2021/3/30 18:27
     * @message 自增id 系统自动
     *  BeanUtil.copyProperties(setfile, s);
     *  将
     */
    @PostMapping("/saveorderfile")
    public Result saveorder(@RequestBody Setfile setfile) {
        Setfile s = new Setfile();
        BeanUtil.copyProperties(setfile, s);
        setfileService.saveOrUpdate(s);
        return Result.succ("插入数据成功！", s);
    }

    @GetMapping("getlistnumbers")
    public Result getlistnumbers(String orderNumber) {
        QueryWrapper<Setfile> queryWrapper = new QueryWrapper<>();
        List<Setfile> files = setfileService.list(queryWrapper);

        return Result.succ("查询成功！", files);
    }

}
