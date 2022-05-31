package com.shenzhou.logistics.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shenzhou.logistics.entity.ProblemEntity;

import java.util.List;
import java.util.Map;

/**
 * @ClassName ProblemService
 * @Description TODO
 * @Author GuoShuai
 * @Date 2022/4/24 14:29
 * @Version 1.0
 */
public interface ProblemService extends IService<ProblemEntity> {

    ProblemEntity queryProblem(String id);

    List<ProblemEntity> titleList();

    List<ProblemEntity> List();

    List<ProblemEntity> allList();

    List<ProblemEntity> ListByType(String type);

    boolean changeStatus(String type, String id);

    boolean remove(String id);
}
