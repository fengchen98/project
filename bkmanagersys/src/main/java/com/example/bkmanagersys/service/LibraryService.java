package com.example.bkmanagersys.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.bkmanagersys.pojo.Library;

import java.util.List;

/**
 * @author FengChen
 * @description:
 * @date 2022/4/15 10:56
 * @version：1.0
 */
public interface LibraryService extends IService<Library> {
    IPage<Library> getLibraryByOpr(Page<Library> pageParam, String province, String city, String county);

     List<Library> getLibraryByAreaCode(String code);

    List<Library> getNameAndCode(String belongcode,String isShow);
}
