package com.example.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.json.JSON;
import com.example.common.lang.Result;
import com.example.entity.Savefile;
import com.example.entity.Tylist;
import com.example.service.SavefileService;
import com.example.service.TylistService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author Tu
 * @Package com.example.controller
 * @date 2021/3/30-15:12
 */
@Data
@RestController
public class TyfileController {
    @Autowired
    TylistService tylistService;

    /**
     * [java.util.HashMap<java.lang.String,java.lang.Object>]
     *
     * @return com.example.common.lang.Result
     * @author Tu
     * @date 2021/3/31 15:06
     * @message 循环插入数据库 第一种
     * 在接收前端数据是  只传入 {id:12,savefiles:["1","2","3",....]}
     * 此时后端接收  @RequestBody HashMap<String,Object> map
     * 接受一个List类型和Integer类型参数
     * 然后 获取数据
     * // 接收List
     * List<Savefile> savefiledata = (List<Savefile>) map.get("savefiles");
     * // 接收另外一个参数
     * Integer id = (Integer) map.get("id");
     * 在循环插入时候 一个错误 就是将新建的对象放在循环外
     * 正确的做法是放在循环内 让她每次循环创建新的对象 而不是只更改一条数据
     */

    @PostMapping("/tyreceivefilehas")
//    public Result receivefilehas( @RequestParam(value = "id",required = false) Integer id, @RequestParam(value = "savefiles",required = false) List<String> savefiles) {
    public Result tyreceivefilehas(@RequestBody HashMap<String, Object> map) {
        // 接收List
        List<Tylist> savefiledata = (List<Tylist>) map.get("savefiles");
        // 接收另外一个参数
        Integer id = (Integer) map.get("id");
        List<Tylist> savefiles = new ArrayList<>(savefiledata);
        for (int i = 0; i < savefiledata.size(); i++) {

            Tylist savefile = new Tylist();
            savefile.setOrderId(id);
            savefile.setDeviceid(String.valueOf(savefiledata.get(i)));
            BeanUtil.copyProperties(savefiledata.get(i), savefile, "order_id", "deviceid");
//            System.out.println(savefile);
            tylistService.saveOrUpdate(savefile);
        }
        return Result.succ("插入成功！");
    }

    /**
     * [java.util.List<com.example.entity.Savefile>]
     *
     * @return com.example.common.lang.Result
     * @author Tu
     * @date 2021/3/31 15:13
     * @message 第二种 接收一个list 数据
     * 前端传入数据 [{Id=null, sn=null, orderId=55,},{Id=null, sn=null, orderId=55,}] 每条中的数据与新建的数据结构相似
     * List<Savefile> savefileList = new ArrayList<>(savefiles);
     * 拿到数据进行巴拉巴拉..
     */
    @PostMapping("/tyreceivefile")
    public Result tyreceivefile(@RequestBody List<Tylist> savefiles) {
        if (savefiles.size() == 0 || savefiles == null) {
            return Result.fail("插入数据为空！");
        }
        Tylist savefile = new Tylist();

        List<Tylist> savefileList = new ArrayList<>(savefiles);
        /**
         * 第一种时间长
         * 1000/1min
         * 1000每次
         */
//        for (int i = 0; i < savefileList.size(); i++) {
//            BeanUtil.copyProperties((savefiles.get(i)), savefile);
//            tylistService.saveOrUpdate(savefile);
//        }
        /**
         * 第二种时间明显减少
         * 1000/78ms
         * 1000每次
         */
       tylistService.saveBatch(savefileList);
        return Result.succ("插入成功！", savefile);
    }

}
