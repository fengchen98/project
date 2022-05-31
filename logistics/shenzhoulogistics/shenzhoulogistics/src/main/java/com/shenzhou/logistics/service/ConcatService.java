package com.shenzhou.logistics.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shenzhou.logistics.entity.ConcatEntity;

import java.util.List;

/**
 * @ClassName ConcatService
 * @Description TODO
 * @Author GuoShuai
 * @Date 2022/5/13 19:45
 * @Version 1.0
 */
public interface ConcatService extends IService<ConcatEntity> {

    List<ConcatEntity> getList();

    boolean remove(String id);

    boolean deal(String id);

    List<ConcatEntity> search(String text);
}
