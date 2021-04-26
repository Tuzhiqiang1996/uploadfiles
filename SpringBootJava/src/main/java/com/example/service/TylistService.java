package com.example.service;

import com.baomidou.mybatisplus.core.enums.SqlMethod;
import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.entity.Tylist;
import org.apache.ibatis.session.SqlSession;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *https://blog.csdn.net/weixin_38111957/article/details/92415413
 *
 * @since 2021-01-29
 */
public interface TylistService extends IService<Tylist> {



}
