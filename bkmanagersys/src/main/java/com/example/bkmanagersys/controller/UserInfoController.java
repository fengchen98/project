package com.example.bkmanagersys.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.bkmanagersys.pojo.*;
import com.example.bkmanagersys.service.AdminInfoService;
import com.example.bkmanagersys.service.UserInfoCheckListService;
import com.example.bkmanagersys.service.UserInfoService;
import com.example.bkmanagersys.util.JwtHelper;
import com.example.bkmanagersys.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.List;

/**
 * @author FengChen
 * @description:
 * @date 2022/4/17 8:41
 * @version：1.0
 */
@Api(tags = "用户控制器")
@RestController


@RequestMapping("/user")
public class UserInfoController {
    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private UserInfoCheckListService userInfoCheckListService;
    @Autowired
    private AdminInfoService adminInfoService;

    @ApiOperation("新增用户信息到审核表")
    @PostMapping("/add")
    public Result addUserInfoToCheckList(HttpServletRequest request,
            @ApiParam("要新增的所有的UserInfoCheckList的JSON集合")@RequestBody Collection<UserInfoCheckList> userInfoCheckLists
    ){
        // 接收参数
        // 调用服务层方法完成增加
        String token=request.getHeader("token");
        int userId=JwtHelper.getUserId(token);
        AdminInfo admin = adminInfoService.getAdminById(userId);
        String username=admin.getUsername();
        for (UserInfoCheckList userInfoCheckList:userInfoCheckLists) {
            userInfoCheckList.setKeyboarder(username);
            userInfoCheckList.setStadiumstype(admin.getVenue());
            userInfoCheckListService.save(userInfoCheckList);
        }
        return Result.ok();
    }

    @ApiOperation("新增用户信息到馆藏表")
    @PostMapping("/addUserInfo")
    public Result addUserInfo(
            @ApiParam("要新增的所有的UserInfo的JSON集合")@RequestBody Collection<UserInfo> userInfos
    ){
        // 接收参数
        // 调用服务层方法完成增加
        userInfoService.saveBatch(userInfos);
        return Result.ok();
    }

    @ApiOperation("获取所有用户信息")
    @RequestMapping("/list")
    public Result getUserInfoList(HttpServletRequest request){
        String token=request.getHeader("token");
        int userId=JwtHelper.getUserId(token);
        AdminInfo admin = adminInfoService.getAdminById(userId);
        String venue=admin.getVenue();
        List<UserInfo> list=userInfoService.getUserInfoList(venue);
        return Result.ok(list);
    }

    @ApiOperation("删除用户信息")
    @DeleteMapping("/delete")
    public Result deleteUserInfo(
            @ApiParam("要删除的所有的UserInfo的id的JSON集合") @RequestBody List<Integer> ids){

        userInfoService.removeByIds(ids);
        return Result.ok();

    }

    @ApiOperation("统计用户总数")
    @RequestMapping("/count")
    public Result countUserInfo(HttpServletRequest request){
        String token=request.getHeader("token");
        int userId= JwtHelper.getUserId(token);
        AdminInfo admin = adminInfoService.getAdminById(userId);
        String venue=admin.getVenue();
        QueryWrapper<UserInfo> queryWrapper=new QueryWrapper();
        queryWrapper.eq("stadiumstype",venue);
        int count = userInfoService.count(queryWrapper);
        return Result.ok(count);
    }


    @ApiOperation("读者编号、性别、读者类型、职业模糊查询,带分页")
    @GetMapping("/getList/{pageNo}/{pageSize}")
    public Result getUserInfo(HttpServletRequest request,
            @PathVariable("pageNo") Integer pageNo,
            @PathVariable("pageSize") Integer pageSize,
            @ApiParam("读者编号")String userid,
            @ApiParam("性别")String usertype,
            @ApiParam("读者类型")String usertype1,
            @ApiParam("职业")String usertype2){
        //分页带条件查询
        Page<UserInfo> pageParam=new Page<>(pageNo,pageSize);
        //通过服务层完成查询
        String token=request.getHeader("token");
        int userId= JwtHelper.getUserId(token);
        AdminInfo admin = adminInfoService.getAdminById(userId);
        String venue=admin.getVenue();
        IPage<UserInfo> page=userInfoService.getUserInfoByOpr(pageParam,userid,usertype,usertype1,usertype2,venue);
        return Result.ok(page);
    }

    @ApiOperation("修改用户信息")
    @PostMapping("/update")
    public Result updateUserInfo(
            @ApiParam("要修改的UserInfo") UserInfo userInfo
    ){
        // 接收参数
        // 调用服务层方法完成修改
        userInfoService.updateById(userInfo);
        return Result.ok();
    }
}
