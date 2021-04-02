package com.example.controller;

import cn.hutool.core.bean.BeanUtil;
import com.example.common.lang.Result;
import com.example.entity.Savefile;
import com.example.entity.Xjlist;
import com.example.service.XjlistService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Tu
 * @Package com.example.controller
 * @date 2021/4/1-10:19
 */
@Data
@RestController
public class XjlistController {
    @Autowired
    XjlistService xjlistService;

    @PostMapping("/xjlist")
    public Result xjlist(@RequestBody List<Xjlist> xjlists) {
        if (xjlists.size() == 0 || xjlists == null) {
            return Result.fail("插入数据为空！");
        }
        Xjlist savefile = new Xjlist();
        List<Xjlist> savefileList = new ArrayList<>(xjlists);
        for (int i = 0; i < savefileList.size(); i++) {
            BeanUtil.copyProperties((xjlists.get(i)), savefile);
            xjlistService.saveOrUpdate(savefile);
        }
        return Result.succ("插入成功！", savefile);
    }

    @PostMapping("/getfiles")
    public Result getfiles(MultipartFile file) {

        String fileName = file.getOriginalFilename();
        System.out.println(fileName);
        return Result.fail("ok");
    }
}
