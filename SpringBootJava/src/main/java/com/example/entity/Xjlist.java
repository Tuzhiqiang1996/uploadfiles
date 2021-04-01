package com.example.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author Tu
 * @Package com.example.entity
 * @date 2021/4/1-10:19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tb_device_list_xiaojiang")
public class Xjlist implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "Id", type = IdType.AUTO)
    private Long Id;
    private String sn;

    private Integer orderId;
    private String deviceid;

    private String addr1;
    private String addr2;
    private LocalDateTime createTime;
    private Integer testResult;
    private LocalDateTime testDatetime;
    private Integer checkResult;
    private LocalDateTime checkDatetime;
    private Integer checkCount;

    private Integer packages;
    private LocalDateTime packageDatetime;

}
