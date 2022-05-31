package com.example.bkmanagersys.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.bkmanagersys.pojo.AdminInfo;
import com.example.bkmanagersys.pojo.Culturalcenter;
import com.example.bkmanagersys.pojo.Library;
import com.example.bkmanagersys.service.AdminInfoService;
import com.example.bkmanagersys.service.LibraryService;
import com.example.bkmanagersys.util.JwtHelper;
import com.example.bkmanagersys.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author FengChen
 * @description:
 * @date 2022/4/15 10:55
 * @version：1.0
 */
@Api(tags = "图书馆控制器")
@RestController

@RequestMapping("/library")
public class LibraryController {

    @Autowired
    private LibraryService libraryService;
    @Autowired
    private AdminInfoService adminInfoService;

    @ApiOperation("根据地区行政代码查询归属场馆")
    @GetMapping("/getLibraryByAreaCode/{code}")
    public Result getLibraryByAreaCode(@PathVariable("code") String code){
        return Result.ok(libraryService.getLibraryByAreaCode(code));
    }

    @ApiOperation("根据省市县名称模糊查询,带分页")
    @GetMapping("/getList/{pageNo}/{pageSize}")
    public Result getLibrary(HttpServletRequest request,
            @PathVariable("pageNo") Integer pageNo,
                             @PathVariable("pageSize") Integer pageSize,
                             @ApiParam("省")String province,
                             @ApiParam("市")String city,
                             @ApiParam("县")String county){
        //分页带条件查询
        Page<Library> pageParam=new Page<>(pageNo,pageSize);
       /* //通过服务层完成查询
        String token=request.getHeader("token");
        int userId= JwtHelper.getUserId(token);
        AdminInfo admin = adminInfoService.getAdminById(userId);
        String venue=admin.getVenue();*/
        IPage<Library> page=libraryService.getLibraryByOpr(pageParam,province,city,county);
        return Result.ok(page);
    }

    @ApiOperation("新增或修改图书馆信息,有id属性是修改,没有则是增加")
    @PostMapping("/saveOrUpdateLibrary")
    public Result saveOrUpdateLibrary(
            @ApiParam("JSON的Library对象")@RequestBody Library library
    ){
        // 接收参数
        // 调用服务层方法完成增加或者修改
        libraryService.saveOrUpdate(library);
        return Result.ok();
    }


    @ApiOperation("删除图书馆信息")
    @DeleteMapping("/deleteLibrary")
    public Result deleteLibrary(
            @ApiParam("要删除的所有的library的id的JSON集合") @RequestBody List<Integer> ids){

        libraryService.removeByIds(ids);
        return Result.ok();

    }

    @ApiOperation("根据所属区域代码和isShow返回场馆名字和坐标")
    @GetMapping("/getNameLists")
    public Result getNameList(HttpServletRequest request,
                             @ApiParam("所属区域代码")String belongcode,
                              @ApiParam("是否展示")String isShow){
        List<Library> librarys=libraryService.getNameAndCode(belongcode,isShow);
        return Result.ok(librarys);
    }

}
