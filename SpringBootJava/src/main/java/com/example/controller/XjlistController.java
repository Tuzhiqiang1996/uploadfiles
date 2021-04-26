package com.example.controller;

import cn.hutool.core.bean.BeanUtil;
import com.example.common.lang.Result;
import com.example.entity.Savefile;
import com.example.entity.Tylist;
import com.example.entity.Xjlist;
import com.example.service.XjlistService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.HashMap;
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
//        for (int i = 0; i < savefileList.size(); i++) {
//            BeanUtil.copyProperties((xjlists.get(i)), savefile);
//            xjlistService.saveOrUpdate(savefile);
//        }
        xjlistService.saveBatch(savefileList);
        return Result.succ("插入成功！", savefile);
    }

    @PostMapping("/xjreceivefilehas")
//    public Result receivefilehas( @RequestParam(value = "id",required = false) Integer id, @RequestParam(value = "savefiles",required = false) List<String> savefiles) {
    public Result tyreceivefilehas(@RequestBody HashMap<String, Object> map) {
        // 接收List
        List<Xjlist> savefiledata = (List<Xjlist>) map.get("savefiles");
        // 接收另外一个参数
        Integer id = (Integer) map.get("id");
        List<Xjlist> savefiles = new ArrayList<>(savefiledata);
        for (int i = 0; i < savefiledata.size(); i++) {

            Xjlist savefile = new Xjlist();
            savefile.setOrderId(id);
            savefile.setDeviceid(String.valueOf(savefiledata.get(i)));
            BeanUtil.copyProperties(savefiledata.get(i), savefile, "order_id", "deviceid");
//            System.out.println(savefile);
            xjlistService.saveOrUpdate(savefile);
        }
        return Result.succ("插入成功！");
    }
    @PostMapping("/getfiles")
    public Result getfiles(MultipartFile file) {

        String fileName = file.getOriginalFilename();
        System.out.println(fileName);
        return Result.fail("ok");
    }
}
