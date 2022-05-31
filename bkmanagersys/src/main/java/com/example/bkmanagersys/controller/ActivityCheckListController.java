package com.example.bkmanagersys.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.bkmanagersys.pojo.Activity;
import com.example.bkmanagersys.pojo.ActivityCheckList;
import com.example.bkmanagersys.pojo.AdminInfo;
import com.example.bkmanagersys.service.ActivityCheckListService;
import com.example.bkmanagersys.service.ActivityService;
import com.example.bkmanagersys.service.AdminInfoService;
import com.example.bkmanagersys.util.JwtHelper;
import com.example.bkmanagersys.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @author FengChen
 * @description:
 * @date 2022/4/15 17:10
 * @version：1.0
 */
@Api(tags = "活动审核表控制器")
@RestController
@RequestMapping("/activitycheck")
public class ActivityCheckListController {

    @Autowired
    private ActivityService activityService;
    @Autowired
    private ActivityCheckListService activityCheckListService;
    @Autowired
    private AdminInfoService adminInfoService;



    @ApiOperation("通过审核活动信息")
    @RequestMapping("/pass")
    public Result pass(@RequestParam List<Integer> ids){

        for(int id:ids){
            ActivityCheckList activityCheckList = activityCheckListService.getById(id);

            UpdateWrapper<ActivityCheckList> updateWrapper = new UpdateWrapper<>();
            updateWrapper.eq("id" ,id).set("status", 1);
            activityCheckListService.update(updateWrapper);
            Activity activity = new Activity();
            BeanUtils.copyProperties(activityCheckList, activity);
//            Activity activity = JSONObject.parseObject(activityCheckList.toString(), Activity.class);
            activity.setId(0);

            activityService.save(activity);
        }
        return Result.ok();
    }




    //根据ID获取活动信息
    @ApiOperation("获取对应id的活动信息")
    @RequestMapping("/getActivityByID")
    public Result getActivityByID(@RequestParam List<Integer> ids){
        List<Activity> activities=new ArrayList<>();
        for(int id:ids){
            ActivityCheckList activityCheckList = activityCheckListService.getById(id);
            Activity activity = new Activity();
            BeanUtils.copyProperties(activityCheckList, activity);
            activities.add(activity);
        }

        return Result.ok(activities);
    }



    @ApiOperation("驳回活动信息")
    @RequestMapping("/reject")
    public Result reject(@RequestParam List<Integer> ids){
        for(int id:ids){
            UpdateWrapper<ActivityCheckList> updateWrapper = new UpdateWrapper<>();
            updateWrapper.eq("id" ,id).set("status", 2);
            activityCheckListService.update(updateWrapper);
        }
        return Result.ok();
    }



    @ApiOperation("获取所有活动信息")
    @RequestMapping("/list")
    public Result getActivityCheckList(HttpServletRequest request){
        String token=request.getHeader("token");
        int userId= JwtHelper.getUserId(token);
        AdminInfo admin = adminInfoService.getAdminById(userId);
        String venue=admin.getVenue();

        List<ActivityCheckList> list=activityCheckListService.getActivityCheckList(venue);
        return Result.ok(list);
    }




    @ApiOperation("驳回活动信息列表")
    @RequestMapping("/rejectlist")
    public Result getRejectList(HttpServletRequest request){
        String token=request.getHeader("token");
        int userId= JwtHelper.getUserId(token);
        AdminInfo admin = adminInfoService.getAdminById(userId);
        String venue=admin.getVenue();
        List<ActivityCheckList> data=activityCheckListService.getRejectList(venue);
        return Result.ok(data);
    }





}
