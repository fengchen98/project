package com.shenzhou.logistics.controller;

import com.auth0.jwt.JWT;
import com.shenzhou.logistics.Exception.BizCodeEnum;
import com.shenzhou.logistics.entity.ConcatEntity;
import com.shenzhou.logistics.service.ConcatService;
import com.shenzhou.logistics.service.UserService;
import com.shenzhou.logistics.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.redis.core.RedisTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * @ClassName ConcatController
 * @Description TODO
 * @Author GuoShuai
 * @Date 2022/5/13 19:48
 * @Version 1.0
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/webapp/concat")
public class ConcatController {

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    @Autowired
    private ConcatService concatService;

    @Autowired
    private UserService userService;

    @RequestMapping("/get")
    public R getList(HttpServletRequest httpServletRequest){
        String token = httpServletRequest.getHeader("token");
        String userId = JWT.decode(token).getAudience().get(0);
        if(Objects.equals(redisTemplate.opsForValue().get(userId), token)){
            if(userService.getById(userId).getLevel_id() > 1){
                return R.ok().put("data", concatService.getList());
            }
            return R.error(BizCodeEnum.PERMISSIONS_DENIED_EXCEPTION.getCode(), BizCodeEnum.PERMISSIONS_DENIED_EXCEPTION.getMsg());
        }
        return R.error(BizCodeEnum.TOKEN_EXPIRED_EXCEPTION.getCode(), BizCodeEnum.TOKEN_EXPIRED_EXCEPTION.getMsg());
    }

    @RequestMapping("/search")
    public R search(HttpServletRequest httpServletRequest, @RequestParam String search){
        String token = httpServletRequest.getHeader("token");
        String userId = JWT.decode(token).getAudience().get(0);
        if(Objects.equals(redisTemplate.opsForValue().get(userId), token)){
            if(userService.getById(userId).getLevel_id() > 1){
                return R.ok().put("data", concatService.search(search));
            }
            return R.error(BizCodeEnum.PERMISSIONS_DENIED_EXCEPTION.getCode(), BizCodeEnum.PERMISSIONS_DENIED_EXCEPTION.getMsg());
        }
        return R.error(BizCodeEnum.TOKEN_EXPIRED_EXCEPTION.getCode(), BizCodeEnum.TOKEN_EXPIRED_EXCEPTION.getMsg());
    }

    @RequestMapping("/query/{id}")
    public R query(HttpServletRequest httpServletRequest, @PathVariable String id){
        String token = httpServletRequest.getHeader("token");
        String userId = JWT.decode(token).getAudience().get(0);
        if(Objects.equals(redisTemplate.opsForValue().get(userId), token)){
            if(userService.getById(userId).getLevel_id() > 1){
                return R.ok().put("data", concatService.getById(id));
            }
            return R.error(BizCodeEnum.PERMISSIONS_DENIED_EXCEPTION.getCode(), BizCodeEnum.PERMISSIONS_DENIED_EXCEPTION.getMsg());
        }
        return R.error(BizCodeEnum.TOKEN_EXPIRED_EXCEPTION.getCode(), BizCodeEnum.TOKEN_EXPIRED_EXCEPTION.getMsg());
    }

    @RequestMapping("/deal/{id}")
    public R deal(HttpServletRequest httpServletRequest, @PathVariable String id){
        String token = httpServletRequest.getHeader("token");
        String userId = JWT.decode(token).getAudience().get(0);
        if(Objects.equals(redisTemplate.opsForValue().get(userId), token)){
            if(userService.getById(userId).getLevel_id() > 1){
                return R.ok().put("data", concatService.deal(id));
            }
            return R.error(BizCodeEnum.PERMISSIONS_DENIED_EXCEPTION.getCode(), BizCodeEnum.PERMISSIONS_DENIED_EXCEPTION.getMsg());
        }
        return R.error(BizCodeEnum.TOKEN_EXPIRED_EXCEPTION.getCode(), BizCodeEnum.TOKEN_EXPIRED_EXCEPTION.getMsg());
    }

    @RequestMapping("/save")
    public R save(HttpServletRequest httpServletRequest, @RequestBody ConcatEntity concatEntity){
        String token = httpServletRequest.getHeader("token");
        String userId = JWT.decode(token).getAudience().get(0);
        if(Objects.equals(redisTemplate.opsForValue().get(userId), token)){
            return R.ok().put("data", concatService.save(concatEntity));
        }
        return R.error(BizCodeEnum.TOKEN_EXPIRED_EXCEPTION.getCode(), BizCodeEnum.TOKEN_EXPIRED_EXCEPTION.getMsg());
    }

    @RequestMapping("/remove/{id}")
    public R remove(HttpServletRequest httpServletRequest, @PathVariable String id){
        String token = httpServletRequest.getHeader("token");
        String userId = JWT.decode(token).getAudience().get(0);
        if(Objects.equals(redisTemplate.opsForValue().get(userId), token)){
            if(userService.getById(userId).getLevel_id() > 1){
                return concatService.remove(id) ? R.ok() : R.error();
            }
            return R.error(BizCodeEnum.PERMISSIONS_DENIED_EXCEPTION.getCode(), BizCodeEnum.PERMISSIONS_DENIED_EXCEPTION.getMsg());
        }
        return R.error(BizCodeEnum.TOKEN_EXPIRED_EXCEPTION.getCode(), BizCodeEnum.TOKEN_EXPIRED_EXCEPTION.getMsg());
    }

}
