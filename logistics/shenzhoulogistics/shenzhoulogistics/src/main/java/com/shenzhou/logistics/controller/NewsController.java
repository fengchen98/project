package com.shenzhou.logistics.controller;

import com.auth0.jwt.JWT;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.shenzhou.logistics.entity.NewsEntity;
import com.shenzhou.logistics.entity.ProblemEntity;
import com.shenzhou.logistics.entity.UploadImageEntity;
import com.shenzhou.logistics.service.NewsService;
import com.shenzhou.logistics.service.UploadImageService;
import com.shenzhou.logistics.service.UserService;
import com.shenzhou.logistics.utils.R;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

/**
 * @ClassName NewsController
 * @Description TODO
 * @Author GuoShuai
 * @Date 2022/4/25 17:31
 * @Version 1.0
 */

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/webapp/news")
public class NewsController {

    @Autowired
    private UserService userService;

    @Autowired
    private NewsService newsService;

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    @Autowired
    private UploadImageService uploadImageService;

    @RequestMapping("/query/{id}")
    public R queryNews(@PathVariable String id){
        return R.ok().put("data", newsService.queryNews(id));
    }

    @RequestMapping("/titleList")
    public R titleList(){
        return R.ok().put("data", newsService.titleList());
    }
    @RequestMapping("/titleListByType/{type}")
    public R titleListByType(@PathVariable String type){
        return R.ok().put("data", newsService.titleListByType(type));
    }

    @RequestMapping("/save")
    public R save(HttpServletRequest httpServletRequest, @RequestBody NewsEntity newsEntity){
        String userId = JWT.decode(httpServletRequest.getHeader("token")).getAudience().get(0);
        newsEntity.setUsername(userService.getById(userId).getUsername());
        newsService.save(newsEntity);
        return R.ok().put("data", newsEntity);
    }

    @RequestMapping("/list")
    public R list(){
        return R.ok().put("data", newsService.List());
    }

    @RequestMapping("/alllist")
    public R alllist(HttpServletRequest httpServletRequest){
        String token = httpServletRequest.getHeader("token");
        String userId = JWT.decode(token).getAudience().get(0);
        if(Objects.equals(redisTemplate.opsForValue().get(userId), token)){
            return R.ok().put("data", newsService.allList());
        }
        return R.error();
    }

    @RequestMapping("/ListByType/{type}")
    public R ListByType(HttpServletRequest httpServletRequest, @PathVariable String type){
        String token = httpServletRequest.getHeader("token");
        String userId = JWT.decode(token).getAudience().get(0);
        if(Objects.equals(redisTemplate.opsForValue().get(userId), token)){
            return R.ok().put("data", newsService.ListByType(type));
        }
        return R.error();
    }

    @RequestMapping("/changeStatus/{status}/{id}")
    public R changeStatus(HttpServletRequest httpServletRequest, @PathVariable String status, @PathVariable String id){
        String token = httpServletRequest.getHeader("token");
        String userId = JWT.decode(token).getAudience().get(0);
        if(Objects.equals(redisTemplate.opsForValue().get(userId), token)){
            return R.ok().put("data", newsService.changeStatus(status, id));
        }
        return R.error();
    }

    @RequestMapping("/remove/{id}")
    public R remove(HttpServletRequest httpServletRequest, @PathVariable String id){
        String token = httpServletRequest.getHeader("token");
        String userId = JWT.decode(token).getAudience().get(0);
        if(Objects.equals(redisTemplate.opsForValue().get(userId), token)){
            return R.ok().put("data", newsService.remove(id));
        }
        return R.error();
    }

    @RequestMapping("/update")
    public R update(HttpServletRequest httpServletRequest, @RequestBody NewsEntity newsEntity){
        String token = httpServletRequest.getHeader("token");
        String userId = JWT.decode(token).getAudience().get(0);
        if(Objects.equals(redisTemplate.opsForValue().get(userId), token)){
            UpdateWrapper<NewsEntity> updateWrapper = new UpdateWrapper<>();
            updateWrapper.eq("id", newsEntity.getId());
            return R.ok().put("data", newsService.update(newsEntity, updateWrapper));
        }
        return R.error();
    }

    @RequestMapping("/upload/news")
    public R upload(HttpServletRequest httpServletRequest, @RequestParam("file") MultipartFile file) throws IOException {
        if(file.isEmpty()){
            return R.error(14000, "????????????????????????");
        }
        // ????????????????????????
        String userId = JWT.decode(httpServletRequest.getHeader("token")).getAudience().get(0);
        String originname = file.getOriginalFilename();
        String newname =  System.currentTimeMillis() + originname;
        String suffix = "." + FilenameUtils.getExtension(originname);
        String size = String.valueOf(file.getSize());
        File rootPath = new File(ResourceUtils.getURL("classpath:").getPath());
        System.out.println("rootPath: " + rootPath);
        if(!rootPath.exists()){
            rootPath = new File("");
        }
        // ????????????????????????????????????????????????
//        File parentFilePath = rootPath.getAbsoluteFile().getParentFile();
        File parentFilePath = rootPath.getAbsoluteFile();
        System.out.println("parentFilePath: " + parentFilePath);
        if(!parentFilePath.exists()){
            parentFilePath.mkdir();
        }
        File uploadPath = new File(parentFilePath.getAbsolutePath(), "static/uploadImages/news/");
        System.out.println("uploadPath: " + uploadPath);
        if(!uploadPath.exists()){
            uploadPath.mkdirs();
        }
        UploadImageEntity findOne = hasRepeat("news", originname, size); // ????????????????????????

        file.transferTo(new File(uploadPath, newname));

        String fileType = file.getContentType();
        UploadImageEntity uploadImageEntity = new UploadImageEntity();
        uploadImageEntity.setUserid(userId);
        uploadImageEntity.setApply("news");
        uploadImageEntity.setOriginname(originname);
        uploadImageEntity.setNewname(newname);
        uploadImageEntity.setSuffix(suffix);
        uploadImageEntity.setPath("uploadImages/news/" + newname);
        uploadImageEntity.setSize(String.valueOf(file.getSize()));
        uploadImageEntity.setFiletype(fileType);
        uploadImageEntity.setIsimg(Objects.requireNonNull(file.getContentType()).startsWith("image") ? "yes" : "no");
        if(uploadImageService.save(uploadImageEntity)){
            return R.ok().put("data", uploadImageEntity);
        }
        return R.error(14000, "????????????");
    }

    public UploadImageEntity hasRepeat(String type, String originName, String size){
        QueryWrapper<UploadImageEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("apply", type);
        queryWrapper.eq("originname", originName);
        queryWrapper.eq("size", size);
        return uploadImageService.getOne(queryWrapper, false);
    }
}
