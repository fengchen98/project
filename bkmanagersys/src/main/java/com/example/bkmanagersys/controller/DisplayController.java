package com.example.bkmanagersys.controller;

import com.example.bkmanagersys.pojo.Display;
import com.example.bkmanagersys.pojo.Library;
import com.example.bkmanagersys.service.DisplayService;
import com.example.bkmanagersys.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author FengChen
 * @description:
 * @date 2022/5/10 11:07
 * @version：1.0
 */
@Api(tags = "展示控制器")
@RestController
@CrossOrigin
@RequestMapping("/display")
public class DisplayController {
    @Autowired
    private DisplayService displayService;

    @ApiOperation("根据所属区域代码返回基础信息")
    @GetMapping("/getProvince")
    public Result getProvince(@ApiParam("所属区域代码")String areacode){
        List<Display> displays=displayService.getInfo(areacode);
        return Result.ok(displays);
    }
}
