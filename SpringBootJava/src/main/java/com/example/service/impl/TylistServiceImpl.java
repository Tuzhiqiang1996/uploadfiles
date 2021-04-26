package com.example.service.impl;

import com.baomidou.mybatisplus.core.enums.SqlMethod;
import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.entity.Tylist;
import com.example.mapper.TylistMapper;
import com.example.service.TylistService;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ：
 * @since 2021-01-29
 */
@Service
public class TylistServiceImpl extends ServiceImpl<TylistMapper, Tylist> implements TylistService {


}
