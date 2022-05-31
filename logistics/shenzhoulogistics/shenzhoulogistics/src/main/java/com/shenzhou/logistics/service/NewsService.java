package com.shenzhou.logistics.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shenzhou.logistics.entity.NewsEntity;
import com.shenzhou.logistics.entity.ProblemEntity;

import java.util.List;

/**
 * @ClassName NewsService
 * @Description TODO
 * @Author GuoShuai
 * @Date 2022/4/25 17:21
 * @Version 1.0
 */
public interface NewsService extends IService<NewsEntity> {

    NewsEntity queryNews(String id);

    List<NewsEntity> titleList();

    List<NewsEntity> titleListByType(String type);

    List<NewsEntity> List();

    List<NewsEntity> allList();

    List<NewsEntity> ListByType(String type);

    boolean changeStatus(String type, String id);

    boolean remove(String id);

}
