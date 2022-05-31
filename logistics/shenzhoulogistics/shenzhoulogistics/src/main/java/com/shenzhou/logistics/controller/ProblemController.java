package com.shenzhou.logistics.controller;

import com.auth0.jwt.JWT;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.shenzhou.logistics.entity.ProblemEntity;
import com.shenzhou.logistics.entity.UploadImageEntity;
import com.shenzhou.logistics.service.ProblemService;
import com.shenzhou.logistics.service.UploadImageService;
import com.shenzhou.logistics.service.UserService;
import com.shenzhou.logistics.utils.R;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

import org.springframework.data.redis.core.RedisTemplate;
/**
 * @ClassName ProblemController
 * @Description TODO
 * @Author GuoShuai
 * @Date 2022/4/24 14:31
 * @Version 1.0
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/webapp/problem")
public class ProblemController {

    @Autowired
    private UserService userService;

    @Autowired
    private ProblemService problemService;

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    @Autowired
    private UploadImageService uploadImageService;


    @RequestMapping("/query/{id}")
    public R queryProblem(@PathVariable String id){
        return R.ok().put("data", problemService.queryProblem(id));
    }

    @RequestMapping("/titleList")
    public R titleList(){
        return R.ok().put("data", problemService.titleList());
    }

    @RequestMapping("/save")
    public R save(HttpServletRequest httpServletRequest, @RequestBody ProblemEntity problemEntity){
        String userId = JWT.decode(httpServletRequest.getHeader("token")).getAudience().get(0);
        problemEntity.setUsername(userService.getById(userId).getUsername());
        problemService.save(problemEntity);
        return R.ok().put("data", problemEntity);
    }

    @RequestMapping("/list")
    public R list(){
        return R.ok().put("data", problemService.List());
    }

    @RequestMapping("/alllist")
    public R alllist(HttpServletRequest httpServletRequest){
        String token = httpServletRequest.getHeader("token");
        String userId = JWT.decode(token).getAudience().get(0);
        if(Objects.equals(redisTemplate.opsForValue().get(userId), token)){
            return R.ok().put("data", problemService.allList());
        }
        return R.error();
    }

    @RequestMapping("/ListByType/{type}")
    public R ListByType(HttpServletRequest httpServletRequest, @PathVariable String type){
        String token = httpServletRequest.getHeader("token");
        String userId = JWT.decode(token).getAudience().get(0);
        if(Objects.equals(redisTemplate.opsForValue().get(userId), token)){
            return R.ok().put("data", problemService.ListByType(type));
        }
        return R.error();
    }

    @RequestMapping("/changeStatus/{status}/{id}")
    public R changeStatus(HttpServletRequest httpServletRequest, @PathVariable String status, @PathVariable String id){
        String token = httpServletRequest.getHeader("token");
        String userId = JWT.decode(token).getAudience().get(0);
        if(Objects.equals(redisTemplate.opsForValue().get(userId), token)){
            return R.ok().put("data", problemService.changeStatus(status, id));
        }
        return R.error();
    }

    @RequestMapping("/remove/{id}")
    public R remove(HttpServletRequest httpServletRequest, @PathVariable String id){
        String token = httpServletRequest.getHeader("token");
        String userId = JWT.decode(token).getAudience().get(0);
        if(Objects.equals(redisTemplate.opsForValue().get(userId), token)){
            return R.ok().put("data", problemService.remove(id));
        }
        return R.error();
    }


    @RequestMapping("/update")
    public R update(HttpServletRequest httpServletRequest, @RequestBody ProblemEntity problemEntity){
        String token = httpServletRequest.getHeader("token");
        String userId = JWT.decode(token).getAudience().get(0);
        if(Objects.equals(redisTemplate.opsForValue().get(userId), token)){
            UpdateWrapper<ProblemEntity> updateWrapper = new UpdateWrapper<>();
            updateWrapper.eq("id", problemEntity.getId());
            return R.ok().put("data", problemService.update(problemEntity, updateWrapper));
        }
        return R.error();
    }

    @RequestMapping("/upload/problem")
    public R upload(HttpServletRequest httpServletRequest, @RequestParam("file") MultipartFile file) throws IOException {
        if(file.isEmpty()){
            return R.error(14000, "上传文件不能为空");
        }
        // 获取文件基本信息
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
        // 获取静态资源父级绝对路径文件对象
//        File parentFilePath = rootPath.getAbsoluteFile().getParentFile();
        File parentFilePath = rootPath.getAbsoluteFile();
        System.out.println("parentFilePath: " + parentFilePath);
        if(!parentFilePath.exists()){
            parentFilePath.mkdir();
        }
        File uploadPath = new File(parentFilePath.getAbsolutePath(), "static/uploadImages/problem/");
        System.out.println("uploadPath: " + uploadPath);
        if(!uploadPath.exists()){
            uploadPath.mkdirs();
        }
        UploadImageEntity findOne = hasRepeat("problem", originname, size); // 判断文件是否存在

        file.transferTo(new File(uploadPath, newname));

        String fileType = file.getContentType();
        UploadImageEntity uploadImageEntity = new UploadImageEntity();
        uploadImageEntity.setUserid(userId);
        uploadImageEntity.setApply("problem");
        uploadImageEntity.setOriginname(originname);
        uploadImageEntity.setNewname(newname);
        uploadImageEntity.setSuffix(suffix);
        uploadImageEntity.setPath("uploadImages/problem/" + newname);
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
