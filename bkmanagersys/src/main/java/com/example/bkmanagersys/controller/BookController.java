package com.example.bkmanagersys.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.bkmanagersys.pojo.*;
import com.example.bkmanagersys.service.AdminInfoService;
import com.example.bkmanagersys.service.BookCheckListService;
import com.example.bkmanagersys.service.BookService;
import com.example.bkmanagersys.util.JwtHelper;
import com.example.bkmanagersys.util.Result;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
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
 * @date 2022/4/16 20:44
 * @version：1.0
 */
@Api(tags = "馆藏控制器")
@RestController

@RequestMapping("/book")
public class BookController {
    @Autowired
    private BookService bookService;
    @Autowired
    private BookCheckListService bookCheckListService;
    @Autowired
    private AdminInfoService adminInfoService;

    @ApiOperation("新增馆藏信息到审核表")
    @PostMapping("/add")
    public Result addBookToCheckList(HttpServletRequest request,
            @ApiParam("要新增的所有的BookToCheckList的JSON集合")@RequestBody Collection<BookCheckList> bookCheckLists
    ){
        // 接收参数
        // 调用服务层方法完成增加
        String token=request.getHeader("token");
        int userId=JwtHelper.getUserId(token);
        AdminInfo admin = adminInfoService.getAdminById(userId);
        String username=admin.getUsername();
        for (BookCheckList bookCheckList:bookCheckLists) {
            bookCheckList.setKeyboarder(username);
            bookCheckList.setStadiumstype(admin.getVenue());

            bookCheckListService.save(bookCheckList);
        }

        return Result.ok();
    }

    @ApiOperation("新增馆藏信息到馆藏表")
    @PostMapping("/addBook")
    public Result addBook(
            @ApiParam("要新增的所有的Book的JSON集合")@RequestBody Collection<Book> books
    ){
        // 接收参数
        // 调用服务层方法完成增加
        System.out.println("___________________________________________");
        for (Book book:books) {
            System.out.println(book.getId());
        }
        bookService.saveBatch(books);

        return Result.ok();
    }

    @ApiOperation("获取所有馆藏信息")
    @RequestMapping("/list")
    public Result getBookList(HttpServletRequest request){
        String token=request.getHeader("token");
        int userId=JwtHelper.getUserId(token);
        AdminInfo admin = adminInfoService.getAdminById(userId);
        String venue=admin.getVenue();

        List<Book> list=bookService.getBookList(venue);
        return Result.ok(list);
    }

    @ApiOperation("删除馆藏信息")
    @DeleteMapping("/delete")
    public Result deleteBook(
            @ApiParam("要删除的所有的Book的id的JSON集合") @RequestBody List<Integer> ids){

        bookService.removeByIds(ids);
        return Result.ok();

    }

    @ApiOperation("统计馆藏总数")
    @RequestMapping("/count")
    public Result countBook(HttpServletRequest request){
        String token=request.getHeader("token");
        int userId=JwtHelper.getUserId(token);
        AdminInfo admin = adminInfoService.getAdminById(userId);
        String venue=admin.getVenue();
        QueryWrapper<Book> queryWrapper=new QueryWrapper();
        queryWrapper.eq("stadiumstype",venue);
        int count = bookService.count(queryWrapper);
        return Result.ok(count);
    }

    @ApiOperation("根据书名、作者名模糊查询,带分页")
    @GetMapping("/getList/{pageNo}/{pageSize}")
    public Result getBook(HttpServletRequest request,
            @PathVariable("pageNo") Integer pageNo,
            @PathVariable("pageSize") Integer pageSize,
            @ApiParam("书名")String title,
            @ApiParam("作者名")String author){
        //分页带条件查询
        Page<Book> pageParam=new Page<>(pageNo,pageSize);
        //通过服务层完成查询
        String token=request.getHeader("token");
        int userId= JwtHelper.getUserId(token);
        AdminInfo admin = adminInfoService.getAdminById(userId);
        String venue=admin.getVenue();
        IPage<Book> page=bookService.getBookByOpr(pageParam,title,author,venue);
        return Result.ok(page);
    }

    @ApiOperation("修改馆藏信息")
    @PostMapping("/update")
    public Result updateBook(
            @ApiParam("要修改的Book") Book book
    ){
        // 接收参数
        // 调用服务层方法完成修改
        bookService.updateById(book);
        return Result.ok();
    }


}
