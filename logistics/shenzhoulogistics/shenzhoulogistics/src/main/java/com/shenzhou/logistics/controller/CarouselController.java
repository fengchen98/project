package com.shenzhou.logistics.controller;

import com.alibaba.fastjson.JSON;
import com.auth0.jwt.JWT;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.shenzhou.logistics.Exception.BizCodeEnum;
import com.shenzhou.logistics.dao.CarouselDao;
import com.shenzhou.logistics.dao.ProductDao;
import com.shenzhou.logistics.entity.CarouselEntity;
import com.shenzhou.logistics.entity.ProductEntity;
import com.shenzhou.logistics.entity.UploadImageEntity;
import com.shenzhou.logistics.entity.UserEntity;
import com.shenzhou.logistics.service.CarouselService;
import com.shenzhou.logistics.service.CartService;
import com.shenzhou.logistics.service.UploadImageService;
import com.shenzhou.logistics.service.UserService;
import com.shenzhou.logistics.utils.R;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.multipart.MultipartFile;

//轮播图
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/webapp/carousel")
public class CarouselController {

    @Autowired
    private UploadImageService uploadImageService;

    @Autowired
    private CarouselService carouselService;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private UserService userService;

    @GetMapping("/onlinelist")//返回表中所有数据（查）
    public R onlinelist(){
        return R.ok().put("data",carouselService.onlineList());
    }

    @GetMapping("/allList")//返回表中所有数据（查）
    public R allList(HttpServletRequest httpServletRequest){
        String token = httpServletRequest.getHeader("token");
        String userId = JWT.decode(token).getAudience().get(0);
        if(Objects.equals(redisTemplate.opsForValue().get(userId), token)){
            return R.ok().put("data",carouselService.allList());
        } else {
            return R.error(BizCodeEnum.TOKEN_EXPIRED_EXCEPTION.getCode(), BizCodeEnum.TOKEN_EXPIRED_EXCEPTION.getMsg());
        }
    }

    @GetMapping("/offlineList")//返回表中所有数据（查）
    public R offlineList(HttpServletRequest httpServletRequest){
        String token = httpServletRequest.getHeader("token");
        String userId = JWT.decode(token).getAudience().get(0);
        if(Objects.equals(redisTemplate.opsForValue().get(userId), token)){
            return R.ok().put("data",carouselService.offlineList());
        } else {
            return R.error(BizCodeEnum.TOKEN_EXPIRED_EXCEPTION.getCode(), BizCodeEnum.TOKEN_EXPIRED_EXCEPTION.getMsg());
        }
    }

    @GetMapping("/delete/{id}")//返回表中所有数据（查）
    public R delete(HttpServletRequest httpServletRequest,@PathVariable String id){
        String token = httpServletRequest.getHeader("token");
        String userId = JWT.decode(token).getAudience().get(0);
        if(Objects.equals(redisTemplate.opsForValue().get(userId), token)){
            return R.ok().put("data",carouselService.del(id));
        } else {
            return R.error(BizCodeEnum.TOKEN_EXPIRED_EXCEPTION.getCode(), BizCodeEnum.TOKEN_EXPIRED_EXCEPTION.getMsg());
        }
    }

    @GetMapping("/online/{id}")//返回表中所有数据（查）
    public R online(HttpServletRequest httpServletRequest, @PathVariable String id){
        String token = httpServletRequest.getHeader("token");
        String userId = JWT.decode(token).getAudience().get(0);
        if(Objects.equals(redisTemplate.opsForValue().get(userId), token)){
            return R.ok().put("data",carouselService.online(id));
        } else {
            return R.error(BizCodeEnum.TOKEN_EXPIRED_EXCEPTION.getCode(), BizCodeEnum.TOKEN_EXPIRED_EXCEPTION.getMsg());
        }
    }

    @GetMapping("/offline/{id}")//返回表中所有数据（查）
    public R offline(HttpServletRequest httpServletRequest, @PathVariable String id){
        String token = httpServletRequest.getHeader("token");
        String userId = JWT.decode(token).getAudience().get(0);
        if(Objects.equals(redisTemplate.opsForValue().get(userId), token)){
            return R.ok().put("data",carouselService.offline(id));
        } else {
            return R.error(BizCodeEnum.TOKEN_EXPIRED_EXCEPTION.getCode(), BizCodeEnum.TOKEN_EXPIRED_EXCEPTION.getMsg());
        }
    }

    @RequestMapping("/add")
    public R savePhoto(HttpServletRequest httpServletRequest, @RequestBody CarouselEntity carouselEntity){
        String token = httpServletRequest.getHeader("token");
        String userId = JWT.decode(token).getAudience().get(0);
        if(Objects.equals(redisTemplate.opsForValue().get(userId), token)){
            if(carouselService.save(carouselEntity)){
            return R.ok().put("data", true);
            }
            return R.error(14000, "保存失败");
        } else {
            return R.error(BizCodeEnum.TOKEN_EXPIRED_EXCEPTION.getCode(), BizCodeEnum.TOKEN_EXPIRED_EXCEPTION.getMsg());
        }
    }

    @RequestMapping("/upload/{type}")
    public R upload(HttpServletRequest httpServletRequest, @PathVariable String type, @RequestParam("file") MultipartFile file) throws IOException {
        if(file.isEmpty()){
            return R.error(14000, "上传文件不能为空");
        }
        // 获取文件基本信息
        String userId = JWT.decode(httpServletRequest.getHeader("token")).getAudience().get(0);
        String originname = file.getOriginalFilename();
        String newname =  System.currentTimeMillis() + originname;
        String suffix = "." + FilenameUtils.getExtension(originname);
        String size = String.valueOf(file.getSize());
        //  String distDirPath = httpServletRequest.getServletContext().getRealPath("") + "uploadImages" +  File.separator + type;
        // 获取文jar静态资源根路径
//        String distDirPath = ResourceUtils.getURL("classpath:").getPath() + "static/uploadImages/" + type;
        File rootPath = new File(ResourceUtils.getURL("classpath:").getPath());
        System.out.println("rootPath: " + rootPath);
        if(!rootPath.exists()){
            rootPath = new File("");
        }
        // 获取静态资源父级绝对路径文件对象
//        File parentFilePath = rootPath.getAbsoluteFile().getParentFile();
        File parentFilePath = rootPath.getAbsoluteFile();
        System.out.println("parentFilePath: " + parentFilePath);
        if(!parentFilePath.exists()){
            parentFilePath.mkdir();
        }
        File uploadPath = new File(parentFilePath.getAbsolutePath(), "static/uploadImages/" + type + "/");
        System.out.println("uploadPath: " + uploadPath);
        if(!uploadPath.exists()){
            uploadPath.mkdirs();
        }
        UploadImageEntity findOne = hasRepeat(type, originname, size); // 判断文件是否存在

            file.transferTo(new File(uploadPath, newname));

            String fileType = file.getContentType();
            UploadImageEntity uploadImageEntity = new UploadImageEntity();
            uploadImageEntity.setUserid(userId);
            uploadImageEntity.setApply(type);
            uploadImageEntity.setOriginname(originname);
            uploadImageEntity.setNewname(newname);
            uploadImageEntity.setSuffix(suffix);
            uploadImageEntity.setPath("uploadImages/" + type + "/" + newname);
            uploadImageEntity.setSize(String.valueOf(file.getSize()));
            uploadImageEntity.setFiletype(fileType);
            uploadImageEntity.setIsimg(Objects.requireNonNull(file.getContentType()).startsWith("image") ? "yes" : "no");
            if(uploadImageService.save(uploadImageEntity)){
                return R.ok().put("data", uploadImageEntity);
            }
        return R.error(14000, "上传失败");
    }

    public UploadImageEntity hasRepeat(String type, String originName, String size){
        QueryWrapper<UploadImageEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("apply", type);
        queryWrapper.eq("originname", originName);
        queryWrapper.eq("size", size);
//        queryWrapper.last("LIMIT  1");
//        return uploadImageService.getOne(queryWrapper);
        return uploadImageService.getOne(queryWrapper, false);
    }

}
