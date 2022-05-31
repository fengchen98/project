package com.shenzhou.logistics.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shenzhou.logistics.entity.CarouselEntity;

import java.util.List;

/**
 * @ClassName CarouselService
 * @Description TODO
 * @Author GuoShuai
 * @Date 2022/4/23 19:23
 * @Version 1.0
 */
public interface CarouselService extends IService<CarouselEntity> {

    List<CarouselEntity> onlineList();

    List<CarouselEntity> offlineList();

    List<CarouselEntity> allList();

    boolean online(String id);

    boolean offline(String id);

    boolean del(String id);
}
