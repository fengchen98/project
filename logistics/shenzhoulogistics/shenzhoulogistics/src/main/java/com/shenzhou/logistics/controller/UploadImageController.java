package com.shenzhou.logistics.controller;

import com.alibaba.fastjson.JSON;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.shenzhou.logistics.Exception.BizCodeEnum;
import com.shenzhou.logistics.entity.ProductEntity;
import com.shenzhou.logistics.entity.UploadImageEntity;
import com.shenzhou.logistics.entity.UserEntity;
import com.shenzhou.logistics.service.ProductService;
import com.shenzhou.logistics.service.UploadImageService;
import com.shenzhou.logistics.service.UserService;
import com.shenzhou.logistics.utils.R;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * @ClassName UploadImageController
 * @Description TODO
 * @Author GuoShuai
 * @Date 2022/4/11 16:06
 * @Version 1.0
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/webapp/upload/")
public class UploadImageController {

    @Autowired
    private UploadImageService uploadImageService;

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    public UploadImageEntity hasRepeat(String type, String originName, String size){
        QueryWrapper<UploadImageEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("apply", type);
        queryWrapper.eq("originname", originName);
        queryWrapper.eq("size", size);
//        queryWrapper.last("LIMIT  1");
//        return uploadImageService.getOne(queryWrapper);
        return uploadImageService.getOne(queryWrapper, false);
    }

    @RequestMapping("/upload/{type}")
    public R upload(HttpServletRequest httpServletRequest, @RequestParam("file") MultipartFile file, @PathVariable String type) throws IOException {
        if(file.isEmpty()){
            return R.error(14000, "????????????????????????");
        }
        // ????????????????????????
        String userId = JWT.decode(httpServletRequest.getHeader("token")).getAudience().get(0);
        String originname = file.getOriginalFilename();
        String newname =  System.currentTimeMillis() + originname;
        String suffix = "." + FilenameUtils.getExtension(originname);
        String size = String.valueOf(file.getSize());
        //  String distDirPath = httpServletRequest.getServletContext().getRealPath("") + "uploadImages" +  File.separator + type;
        // ?????????jar?????????????????????
//        String distDirPath = ResourceUtils.getURL("classpath:").getPath() + "static/uploadImages/" + type;
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
        File uploadPath = new File(parentFilePath.getAbsolutePath(), "static/uploadImages/" + type + "/");
        System.out.println("uploadPath: " + uploadPath);
        if(!uploadPath.exists()){
            uploadPath.mkdirs();
        }
        UploadImageEntity findOne = hasRepeat(type, originname, size); // ????????????????????????
        if(findOne != null){
            UpdateWrapper<UserEntity> updateWrapper = new UpdateWrapper<>();
            updateWrapper.eq("id", userId).set("avatar", findOne.getPath());
            userService.update(null, updateWrapper);
            return R.ok().put("data", findOne);
        }else {
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
//            System.out.println(distDirPath + File.separator + newname);
            if(uploadImageService.save(uploadImageEntity)){
                QueryWrapper<UploadImageEntity> queryWrapper = new QueryWrapper<>();
                queryWrapper.eq("path", uploadImageEntity.getPath());
                UploadImageEntity uploadImageEntity1 = uploadImageService.getOne(queryWrapper);
                if(uploadImageEntity1 != null){
                    UpdateWrapper<UserEntity> updateWrapper = new UpdateWrapper<>();
                    updateWrapper.eq("id", userId).set("avatar", uploadImageEntity1.getPath());
                    userService.update(null, updateWrapper);
                    return R.ok().put("data", uploadImageEntity1);
                }
                return R.error(14000, "??????????????????");
            }

        }
        return R.error(14000, "????????????");
    }

    @RequestMapping("/batchUpload/{type}/{id}/{apply}")
    public R batchUpload(HttpServletRequest httpServletRequest, @RequestParam(value="files",required=true) MultipartFile[] files, @PathVariable String type, @PathVariable String id, @PathVariable String apply) throws IOException {
        System.out.println("??????????????????");
        System.out.println("MultipartFile[]: " + Arrays.toString(files));
        String userId = JWT.decode(httpServletRequest.getHeader("token")).getAudience().get(0);
        List<String> imgUrls = new ArrayList<>();
        if(userService.findUserById(userId) != null){
            // ??????????????????
            for(MultipartFile file : files){
                if(file.isEmpty()){
                    return R.error(14000, "??????????????????????????????????????????????????????");
                }
                // ????????????????????????
                String originname = file.getOriginalFilename();
                String newname =  System.currentTimeMillis() + originname;
                String suffix = "." + FilenameUtils.getExtension(originname);
                String size = String.valueOf(file.getSize());
                // ?????????jar?????????????????????
                File rootPath = new File(ResourceUtils.getURL("classpath:").getPath());
                System.out.println("rootPath: " + rootPath);
                if(!rootPath.exists()){
                    rootPath = new File("");
                }
                // ????????????????????????????????????????????????
                File parentFilePath = rootPath.getAbsoluteFile();
                System.out.println("parentFilePath: " + parentFilePath);
                if(!parentFilePath.exists()){
                    parentFilePath.mkdir();
                }
                File uploadPath = new File(parentFilePath.getAbsolutePath(), "static/uploadImages/" + type + "/" + id + "/" + apply);
                System.out.println("uploadPath: " + uploadPath);
                if(!uploadPath.exists()){
                    uploadPath.mkdirs();
                }
//                UploadImageEntity findOne = hasRepeat(type, originname, size); // ????????????????????????
                // ????????????????????????
//                if(findOne != null){
                    // TODO ??????????????????????????? ???
//                    imgUrls.add(findOne.getPath());
//                    return R.ok().put("data", findOne);
//                }else {
                    file.transferTo(new File(uploadPath, newname));

                    String fileType = file.getContentType();
                    UploadImageEntity uploadImageEntity = new UploadImageEntity();
                    // ????????????????????????
                    uploadImageEntity.setUserid(userId);
                    uploadImageEntity.setApply(type);
                    uploadImageEntity.setOriginname(originname);
                    uploadImageEntity.setNewname(newname);
                    uploadImageEntity.setSuffix(suffix);
                    uploadImageEntity.setPath("uploadImages/" + type + "/" + id + "/" + apply + "/" + newname);
                    uploadImageEntity.setSize(String.valueOf(file.getSize()));
                    uploadImageEntity.setFiletype(fileType);
                    uploadImageEntity.setIsimg(Objects.requireNonNull(file.getContentType()).startsWith("image") ? "yes" : "no");
                    // ???????????????????????????????????????
                    if(uploadImageService.save(uploadImageEntity)){
                        QueryWrapper<UploadImageEntity> queryWrapper = new QueryWrapper<>();
                        queryWrapper.eq("path", uploadImageEntity.getPath());
                        UploadImageEntity uploadImageEntity1 = uploadImageService.getOne(queryWrapper);
                        if(uploadImageEntity1 != null){
                            // TODO ??????????????????????????? ???
                            imgUrls.add(uploadImageEntity1.getPath());
//                            return R.ok().put("data", uploadImageEntity1);
                        }
                    } else {
                        return R.error(14000, "??????????????????");
                    }
//                }
            }
        }
        UpdateWrapper<ProductEntity> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", id).set(apply, JSON.toJSONString(imgUrls));
        productService.update(updateWrapper);
        return R.ok().put("data", productService.getById(id));
    }
}
