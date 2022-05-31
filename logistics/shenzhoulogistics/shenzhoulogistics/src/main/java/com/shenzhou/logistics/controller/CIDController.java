package com.shenzhou.logistics.controller;

import com.auth0.jwt.JWT;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.shenzhou.logistics.Exception.BizCodeEnum;
import com.shenzhou.logistics.entity.CIDEntity;
import com.shenzhou.logistics.entity.UserEntity;
import com.shenzhou.logistics.service.CIDService;
import com.shenzhou.logistics.service.UserService;
import com.shenzhou.logistics.utils.R;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.data.redis.core.RedisTemplate;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

/**
 * @ClassName CIDController
 * @Description TODO
 * @Author GuoShuai
 * @Date 2022/5/14 13:53
 * @Version 1.0
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/webapp/cid")
public class CIDController {

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    @Autowired
    private CIDService cidService;

    @Autowired
    private UserService userService;

    @RequestMapping("/save")
    public R save(HttpServletRequest httpServletRequest, @RequestBody CIDEntity cidEntity){
        String token = httpServletRequest.getHeader("token");
        String userId = JWT.decode(token).getAudience().get(0);
        if(Objects.equals(redisTemplate.opsForValue().get(userId), token)){
            UserEntity userEntity = userService.getById(userId);
            userEntity.setReal_name(cidEntity.getReal_name());
            userEntity.setIdnumber(cidEntity.getCid_number());
            userService.updateById(userEntity);
            cancelConfim(userId);
//            CIDEntity cidEntity1 = cidService.getOne(new QueryWrapper<CIDEntity>().eq("user_id", userId), false);
//            if(userEntity != null){
//                cidEntity.setId(cidEntity1.getId());
//                cidService.updateById(cidEntity);
//            }else {
//                cidService.save(cidEntity);
//            }
            return R.ok().put("data", userEntity);
        }
        return R.error(BizCodeEnum.TOKEN_EXPIRED_EXCEPTION.getCode(), BizCodeEnum.TOKEN_EXPIRED_EXCEPTION.getMsg());
    }

    @RequestMapping("/confirm/{user_id}")
    public R confirm(HttpServletRequest httpServletRequest, @PathVariable String user_id){
        String token = httpServletRequest.getHeader("token");
        String userId = JWT.decode(token).getAudience().get(0);
        if(Objects.equals(redisTemplate.opsForValue().get(userId), token)){
            if(userService.getById(userId).getLevel_id() > 1){
                UserEntity userEntity = userService.getById(user_id);
                if(userEntity.getConfirm_idnumber().equals("0")){
                    userEntity.setConfirm_idnumber("1");
                    userService.updateById(userEntity);

                    UpdateWrapper<CIDEntity> updateWrapper = new UpdateWrapper<>();
                    updateWrapper
                            .eq("user_id", user_id)
                            .set("real_name", userEntity.getReal_name())
                            .set("cid_number", userEntity.getIdnumber());
                    cidService.update(updateWrapper);
                    return R.ok();
                }else{
                    userEntity.setConfirm_idnumber("0");
                    userService.updateById(userEntity);
                    return R.ok();
                }
            }
            return R.error(BizCodeEnum.PERMISSIONS_DENIED_EXCEPTION.getCode(), BizCodeEnum.PERMISSIONS_DENIED_EXCEPTION.getMsg());
        }
        return R.error(BizCodeEnum.TOKEN_EXPIRED_EXCEPTION.getCode(), BizCodeEnum.TOKEN_EXPIRED_EXCEPTION.getMsg());
    }

    @RequestMapping("/get/{user_id}")
    public R get(HttpServletRequest httpServletRequest, @PathVariable String user_id){
        String token = httpServletRequest.getHeader("token");
        String userId = JWT.decode(token).getAudience().get(0);
        if(Objects.equals(redisTemplate.opsForValue().get(userId), token)){
            if(userService.getById(userId).getId().equals(user_id) || userService.getById(userId).getLevel_id() > 1){
                return R.ok().put("data", cidService.getByUserID(user_id));
            }
            return R.error(BizCodeEnum.PERMISSIONS_DENIED_EXCEPTION.getCode(), BizCodeEnum.PERMISSIONS_DENIED_EXCEPTION.getMsg());
        }
        return R.error(BizCodeEnum.TOKEN_EXPIRED_EXCEPTION.getCode(), BizCodeEnum.TOKEN_EXPIRED_EXCEPTION.getMsg());
    }

    @RequestMapping("/upload/{orient}")
    public R upload(HttpServletRequest httpServletRequest, @RequestParam("file") MultipartFile file, @PathVariable String orient) throws IOException {
        if(file.isEmpty()){
            return R.error(14000, "上传文件不能为空");
        }
        // 获取文件基本信息
        String userId = JWT.decode(httpServletRequest.getHeader("token")).getAudience().get(0);
        String suffix = "." + FilenameUtils.getExtension(file.getOriginalFilename());
        String newname =  "cid_" + userId + '_' + orient + '_' + System.currentTimeMillis() + suffix;
        File rootPath = new File(ResourceUtils.getURL("classpath:").getPath());
        System.out.println("rootPath: " + rootPath);
        if(!rootPath.exists()){
            rootPath = new File("");
        }
        // 获取静态资源父级绝对路径文件对象
        File parentFilePath = rootPath.getAbsoluteFile();
        if(!parentFilePath.exists()){
            parentFilePath.mkdir();
        }
        File uploadPath = new File(parentFilePath.getAbsolutePath(), "static/uploadImages/cid/" + userId);
        System.out.println("uploadPath: " + uploadPath);
        if(!uploadPath.exists()){
            uploadPath.mkdirs();
        }

        file.transferTo(new File(uploadPath, newname));
        cancelConfim(userId);
        CIDEntity cidEntity = cidService.getOne(new QueryWrapper<CIDEntity>().eq("user_id", userId));

        if(cidEntity != null){
            UpdateWrapper<CIDEntity> updateWrapper = new UpdateWrapper<>();
            if(Objects.equals(orient, "front")){
                File delFile = new File(parentFilePath.getAbsolutePath(), "static/" + cidEntity.getCid_front());
                delFile.delete();
                updateWrapper.eq("user_id", userId).set("cid_front", "uploadImages/cid/" + userId + "/" + newname);
            }
            if(Objects.equals(orient, "reverse")){
                File delFile = new File(parentFilePath.getAbsolutePath(), "static/" + cidEntity.getCid_reverse());
                delFile.delete();
                updateWrapper.eq("user_id", userId).set("cid_reverse", "uploadImages/cid/" + userId + "/" + newname);
            }
            cidService.update(updateWrapper);
            return R.ok().put("data", cidService.getOne(new QueryWrapper<CIDEntity>().eq("user_id", userId)));
        }else {
            CIDEntity cidEntity1 = new CIDEntity();
            cidEntity1.setUser_id(userId);
            if(Objects.equals(orient, "front")){
                cidEntity1.setCid_front("uploadImages/cid/" + userId + "/" + newname);
            }
            if(Objects.equals(orient, "reverse")){
                cidEntity1.setCid_reverse("uploadImages/cid/" + userId + "/" + newname);
            }
            cidService.save(cidEntity1);
            return R.ok().put("data", cidService.getOne(new QueryWrapper<CIDEntity>().eq("user_id", userId)));
        }
//        return R.error(14000, "上传失败");
    }

    private void cancelConfim(String userId){
        UserEntity userEntity = userService.getById(userId);
        userEntity.setConfirm_idnumber("0");
        userService.updateById(userEntity);
    }

}
