package com.example.bkmanagersys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.bkmanagersys.pojo.Display;

import java.util.List;

/**
 * @author FengChen
 * @description:
 * @date 2022/5/10 11:02
 * @version：1.0
 */
public interface DisplayService extends IService<Display> {
    List<Display> getInfo(String areacode);
}
