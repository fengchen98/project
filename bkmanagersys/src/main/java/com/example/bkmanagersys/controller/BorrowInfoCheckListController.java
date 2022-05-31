package com.example.bkmanagersys.controller;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.bkmanagersys.pojo.*;
import com.example.bkmanagersys.service.AdminInfoService;
import com.example.bkmanagersys.service.BorrowInfoCheckListService;
import com.example.bkmanagersys.service.BorrowInfoService;
import com.example.bkmanagersys.util.JwtHelper;
import com.example.bkmanagersys.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @author FengChen
 * @description:
 * @date 2022/4/16 21:40
 * @version：1.0
 */
@Api(tags = "借阅审核控制器")
@RestController

@RequestMapping("/borrowcheck")
public class BorrowInfoCheckListController {
    @Autowired
    private BorrowInfoService borrowInfoService;
    @Autowired
    private BorrowInfoCheckListService borrowInfoCheckListService;
    @Autowired
    private AdminInfoService adminInfoService;

    @ApiOperation("通过审核借阅信息")
    @RequestMapping("/pass")
    public Result pass(@RequestParam List<Integer> ids){

        for(int id:ids){
            BorrowInfoCheckList borrowInfoCheckList = borrowInfoCheckListService.getById(id);
            UpdateWrapper<BorrowInfoCheckList> updateWrapper = new UpdateWrapper<>();
            updateWrapper.eq("id" ,id).set("status", 1);
            borrowInfoCheckListService.update(updateWrapper);
            BorrowInfo borrowInfo = new BorrowInfo();
            BeanUtils.copyProperties(borrowInfoCheckList, borrowInfo);
            borrowInfo.setId(0);
            borrowInfoService.save(borrowInfo);
        }
        return Result.ok();
    }

    //根据ID获取借阅信息
    @ApiOperation("获取对应id的借阅信息")
    @RequestMapping("/getBorrowInfoByID")
    public Result getBorrowInfoByID(@RequestParam List<Integer> ids){
        List<BorrowInfo> borrowInfos=new ArrayList<>();
        for(int id:ids){
            BorrowInfoCheckList borrowInfoCheckList = borrowInfoCheckListService.getById(id);
            BorrowInfo borrowInfo = new BorrowInfo();
            BeanUtils.copyProperties(borrowInfoCheckList,borrowInfo);
            borrowInfos.add(borrowInfo);
        }

        return Result.ok(borrowInfos);
    }

    @ApiOperation("驳回借阅信息")
    @RequestMapping("/reject")
    public Result reject(@RequestParam List<Integer> ids){

        for(int id:ids){
            UpdateWrapper<BorrowInfoCheckList> updateWrapper = new UpdateWrapper<>();
            updateWrapper.eq("id" ,id).set("status", 2);
            borrowInfoCheckListService.update(updateWrapper);
        }
        return Result.ok();
    }

    @ApiOperation("获取所有借阅信息")
    @RequestMapping("/list")
    public Result getBookCheckList(HttpServletRequest request){
        String token=request.getHeader("token");
        int userId= JwtHelper.getUserId(token);
        AdminInfo admin = adminInfoService.getAdminById(userId);
        String venue=admin.getVenue();
        List<BorrowInfoCheckList> list=borrowInfoCheckListService.getBorrowInfoCheckList(venue);
        return Result.ok(list);
    }




    @ApiOperation("驳回借阅信息列表")
    @RequestMapping("/rejectlist")
    public Result getRejectList(HttpServletRequest request){
        String token=request.getHeader("token");
        int userId= JwtHelper.getUserId(token);
        AdminInfo admin = adminInfoService.getAdminById(userId);
        String venue=admin.getVenue();
        List<BorrowInfoCheckList> list=borrowInfoCheckListService.getRejectList(venue);
        return Result.ok(list);
    }

}
