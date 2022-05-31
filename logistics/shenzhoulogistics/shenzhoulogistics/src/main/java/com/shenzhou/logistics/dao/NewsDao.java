package com.shenzhou.logistics.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shenzhou.logistics.entity.NewsEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * @ClassName NewsDao
 * @Description TODO
 * @Author GuoShuai
 * @Date 2022/4/25 17:17
 * @Version 1.0
 */
@Mapper
public interface NewsDao extends BaseMapper<NewsEntity> {
}
