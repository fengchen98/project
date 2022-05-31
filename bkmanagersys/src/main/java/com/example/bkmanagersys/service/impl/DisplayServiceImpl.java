package com.example.bkmanagersys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.bkmanagersys.mapper.DisplayMapper;
import com.example.bkmanagersys.mapper.LibraryMapper;
import com.example.bkmanagersys.pojo.Display;
import com.example.bkmanagersys.pojo.Library;
import com.example.bkmanagersys.service.DisplayService;
import com.example.bkmanagersys.service.LibraryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author FengChen
 * @description:
 * @date 2022/5/10 11:03
 * @version：1.0
 */
@Service("displayServiceImpl")
@Transactional
public class DisplayServiceImpl extends ServiceImpl<DisplayMapper, Display> implements DisplayService {
    @Override
    public List<Display> getInfo(String areacode) {
        QueryWrapper<Display> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("areacode",areacode);
        return baseMapper.selectList(queryWrapper);
    }
}
