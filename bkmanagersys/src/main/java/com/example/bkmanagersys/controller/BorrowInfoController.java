package com.example.bkmanagersys.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.bkmanagersys.pojo.*;
import com.example.bkmanagersys.service.AdminInfoService;
import com.example.bkmanagersys.service.BorrowInfoCheckListService;
import com.example.bkmanagersys.service.BorrowInfoService;
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
 * @date 2022/4/16 21:40
 * @version：1.0
 */
@Api(tags = "借阅信息控制器")
@RestController

@RequestMapping("/borrow")
public class BorrowInfoController {
    @Autowired
    private BorrowInfoService borrowInfoService;
    @Autowired
    private BorrowInfoCheckListService borrowInfoCheckListService;
    @Autowired
    private AdminInfoService adminInfoService;

    @ApiOperation("新增借阅信息到审核表")
    @PostMapping("/add")
    public Result addBorrowInfoToCheckList(HttpServletRequest request,
            @ApiParam("要新增的所有的BorrowInfo的JSON集合")@RequestBody Collection<BorrowInfoCheckList> borrowInfoCheckLists
    ){
        // 接收参数
        // 调用服务层方法完成增加
        String token=request.getHeader("token");
        int userId=JwtHelper.getUserId(token);
        AdminInfo admin = adminInfoService.getAdminById(userId);
        String username=admin.getUsername();
        for (BorrowInfoCheckList borrowInfoCheckList:borrowInfoCheckLists) {
            borrowInfoCheckList.setKeyboarder(username);
            borrowInfoCheckList.setStadiumstype(admin.getVenue());
            borrowInfoCheckListService.save(borrowInfoCheckList);
        }
        return Result.ok();
    }

    @ApiOperation("新增借阅信息到馆藏表")
    @PostMapping("/addBorrowInfo")
    public Result addBook(
            @ApiParam("要新增的所有的BorrowInfo的JSON集合")@RequestBody Collection<BorrowInfo> borrowInfos
    ){
        // 接收参数
        // 调用服务层方法完成增加
        borrowInfoService.saveBatch(borrowInfos);
        return Result.ok();
    }

    @ApiOperation("获取所有借阅信息")
    @RequestMapping("/list")
    public Result getBorrowInfoList(HttpServletRequest request){
        String token=request.getHeader("token");
        int userId= JwtHelper.getUserId(token);
        AdminInfo admin = adminInfoService.getAdminById(userId);
        String venue=admin.getVenue();

        List<BorrowInfo> list=borrowInfoService.getBorrowInfoList(venue);
        return Result.ok(list);
    }

    @ApiOperation("删除借阅信息")
    @DeleteMapping("/delete")
    public Result deleteBook(
            @ApiParam("要删除的所有的BorrowInfo的id的JSON集合") @RequestBody List<Integer> ids){

        borrowInfoService.removeByIds(ids);
        return Result.ok();

    }

    @ApiOperation("统计借阅信息")
    @RequestMapping("/count")
    public Result countBook(HttpServletRequest request){
        String token=request.getHeader("token");
        int userId=JwtHelper.getUserId(token);
        AdminInfo admin = adminInfoService.getAdminById(userId);
        String venue=admin.getVenue();
        QueryWrapper<BorrowInfo> queryWrapper=new QueryWrapper();
        queryWrapper.eq("stadiumstype",venue);
        int count = borrowInfoService.count(queryWrapper);
        return Result.ok(count);
    }

    @ApiOperation("读者编号、身份证号码,电话,书籍名,作者名")
    @GetMapping("/getList/{pageNo}/{pageSize}")
    public Result getBorrowInfo(HttpServletRequest request,
            @PathVariable("pageNo") Integer pageNo,
            @PathVariable("pageSize") Integer pageSize,
            @ApiParam("读者编号")String userid,
            @ApiParam("身份证号码")String idnumber,
            @ApiParam("电话")String tel,
            @ApiParam("书籍名")String title,
            @ApiParam("作者名")String author
){
        //分页带条件查询
        Page<BorrowInfo> pageParam=new Page<>(pageNo,pageSize);
        //通过服务层完成查询
        String token=request.getHeader("token");
        int userId= JwtHelper.getUserId(token);
        AdminInfo admin = adminInfoService.getAdminById(userId);
        String venue=admin.getVenue();
        IPage<BorrowInfo> page=borrowInfoService.getBorrowInfoByOpr(pageParam,userid,idnumber,tel,title,author,venue);
        return Result.ok(page);
    }

    @ApiOperation("修改借阅信息")
    @PostMapping("/update")
    public Result updateBorrowInfo(
            @ApiParam("要修改的BorrowInfo") BorrowInfo borrowInfo
    ){
        // 接收参数
        // 调用服务层方法完成修改
        borrowInfoService.updateById(borrowInfo);
        return Result.ok();
    }
}
