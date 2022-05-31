package com.shenzhou.logistics.controller;

import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.JWT;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import com.shenzhou.logistics.Exception.BizCodeEnum;
import com.shenzhou.logistics.Exception.EmailException;
import com.shenzhou.logistics.Exception.UserNameException;
import com.shenzhou.logistics.annotation.UserLoginToken;
import com.shenzhou.logistics.entity.UserEntity;
import com.shenzhou.logistics.service.TokenService;
import com.shenzhou.logistics.service.UserService;
import com.shenzhou.logistics.utils.EmailUtil;
import com.shenzhou.logistics.utils.PageUtils;
import com.shenzhou.logistics.utils.R;
import com.shenzhou.logistics.utils.Result;
import com.shenzhou.logistics.vo.UserLoginVo;
import com.shenzhou.logistics.vo.UserRegisterVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.mail.MailException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.time.Duration;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;


/**
 * Create with IntelliJ IDAE
 *
 * @Author: JINLEI
 * @Description: 用户相关接口
 * @Date: 2022/1/22
 * @Time: 18:49
 **/
@CrossOrigin(origins = "*")
@RequestMapping("/user")
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private Producer producer;

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    @Autowired
    private TokenService tokenService;


    public boolean isAdmin(String token){
        String current_user_id = JWT.decode(token).getAudience().get(0);
        return Objects.equals(redisTemplate.opsForValue().get(current_user_id), token)
                && userService.getById(current_user_id).getLevel_id() > 99;
    }

    @RequestMapping("/dimQuery")
    public R dimQuery(HttpServletRequest httpServletRequest,
                   @RequestParam("username") String username,
                   @RequestParam("real_name") String real_name,
                   @RequestParam("phone") String phone,
                   @RequestParam("idnumber") String idnumber){
        String token = httpServletRequest.getHeader("token");
        if(isAdmin(token)){
            return R.ok().put("data", userService.dimQuery(username, real_name, phone, idnumber));
        }
        return R.error(BizCodeEnum.PERMISSIONS_DENIED_EXCEPTION.getCode(), BizCodeEnum.PERMISSIONS_DENIED_EXCEPTION.getMsg());
    }

    @RequestMapping("/userList")
    public R userList(HttpServletRequest httpServletRequest){
        String token = httpServletRequest.getHeader("token");
        String userId = JWT.decode(token).getAudience().get(0);
        if(Objects.equals(redisTemplate.opsForValue().get(userId), token)){
            return R.ok().put("data", userService.userList());
        }
        return R.error(14000, "用户登录凭证过期");
    }

    /**
     * 用户注册
     * @return
     */
    @RequestMapping("/register")
    public R register(@RequestBody UserRegisterVo vo) {
        try {
            System.out.println(" 注册用户发送的验证码 " + vo.getEmailCode());
            System.out.println(" redis中保存的验证码 " + redisTemplate.opsForValue().get(vo.getEmail()));
            if (vo.getEmailCode().equals(redisTemplate.opsForValue().get(vo.getEmail()))){
                userService.register(vo);
            }
            else {
                return R.error(BizCodeEnum.EMAIL_CODE_INCORRECT_EXCEPTION.getCode(), BizCodeEnum.EMAIL_CODE_INCORRECT_EXCEPTION.getMsg());
            }
        } catch (EmailException e) {
            return R.error(BizCodeEnum.EMAIL_EXIST_EXCEPTION.getCode(), BizCodeEnum.EMAIL_EXIST_EXCEPTION.getMsg());
        } catch (UserNameException e) {
            return R.error(BizCodeEnum.USER_EXIST_EXCEPTION.getCode(), BizCodeEnum.USER_EXIST_EXCEPTION.getMsg());
        }
        return R.ok();
    }

    /**
     * 用户登录
     * @return
     */
    @PostMapping("/login")
    public R login(@RequestBody UserEntity user) {

//        Result userEntity = userService.login(user);
//        JSONObject jsonObject = new JSONObject();
        UserEntity userEntity = userService.findByUsername(user);

        if (userEntity != null) {

            //获取到数据库里的password
            String password1 = userEntity.getPassword();
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            //进行密码匹配
            boolean matches = passwordEncoder.matches(user.getPassword(), password1);
            if (!matches) {
                return R.error(BizCodeEnum.LOGIN_ACCOUNT_PASSWORD_EXCEPTION.getCode(), BizCodeEnum.LOGIN_ACCOUNT_PASSWORD_EXCEPTION.getMsg());
            }
            //生成Token令牌
            String token = tokenService.getToken(userEntity);
            //存到redis
            redisTemplate.opsForValue().set(userEntity.getId(),token, Duration.ofHours(24));
            return R.ok().setData(token);

        } else {
            return R.error(BizCodeEnum.LOGIN_ACCOUNT_PASSWORD_EXCEPTION.getCode(), BizCodeEnum.LOGIN_ACCOUNT_PASSWORD_EXCEPTION.getMsg());
        }
    }

    @RequestMapping("/logout")
    public R logout(HttpServletRequest httpServletRequest){
        String userId = JWT.decode(httpServletRequest.getHeader("token")).getAudience().get(0);
        Boolean isDel = redisTemplate.delete(userId);
        if (isDel != null && isDel) {
            return R.ok("已删除");
        }else {
            return R.error("删除失败");
        }
    }

    @UserLoginToken
    @RequestMapping("/getMessage")
    public String getMessage(){
        return "你已通过验证";
    }

    /**
     * 列表
     */
    @PostMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = userService.queryPage(params);

        return R.ok().put("page", page);
    }

    /**
     * 通过token获取用户信息
     */
    @UserLoginToken
    @RequestMapping(value = "/getinfo")
    public R info(HttpServletRequest httpServletRequest){
        String token = httpServletRequest.getHeader("token");
        String userId = JWT.decode(token).getAudience().get(0);
        if(Objects.equals(redisTemplate.opsForValue().get(userId), token)){
            UserEntity user = userService.getById(userId);
            return R.ok().put("data", user);
        }
        else {
            return R.error(BizCodeEnum.TOKEN_EXPIRED_EXCEPTION.getCode(), BizCodeEnum.TOKEN_EXPIRED_EXCEPTION.getMsg());
        }
    }

    /**
     * 图片验证码
     * @param request
     * @param response
     * @throws IOException
     */
    @GetMapping("/verify")
    public void captcha(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setHeader("Cache-Control","no-store,no-cache");
        response.setContentType("image/jpeg");
        //验证码内容
        String text = producer.createText();
        //得到验证码图片
        BufferedImage image = producer.createImage(text);
        HttpSession session = request.getSession();
        //将验证码放到session
        session.setAttribute(Constants.KAPTCHA_SESSION_KEY,text);
        //设置过期时间
        session.setMaxInactiveInterval(60);
        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(image,"jpg",out);
        try {
            out.flush();
        } finally {
            out.close();
        }
        return;
    }

    /**
     * 邮箱验证码
     * @param email
     * @return
     */
    @RequestMapping("/sendEmailCode")
    public R send(@RequestParam("email") String email) {

        String[] to = new String[]{email};
        String subject = "验证码信息";
        String emailCode = EmailUtil.next();
        String text = "您好：\n    您正在注册神州物流会员，邮箱验证码为: " + emailCode + "，验证码有效时长5分钟，请勿转发他人。";
        redisTemplate.opsForValue().set(email, emailCode, Duration.ofMinutes(5));
        try {
            userService.sendEmail(to,subject,text);
            return R.ok("邮件发送成功");
        } catch (MailException e) {
            e.printStackTrace();
            return R.error("邮件发送失败");
        }

    }

    @RequestMapping("/sendRegisterEmailCode")
    public R sendRegisterEmailCode(@RequestParam("email") String email) {
        QueryWrapper<UserEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("email", email);
        int co = userService.count(queryWrapper);
        if(co > 0){
            return R.error(14000, "邮箱已被注册");
        }else {
            String[] to = new String[]{email};
            String subject = "验证码信息";
            String emailCode = EmailUtil.next();
            String text = "您好：\n    您正在注册神州物流会员，邮箱验证码为: " + emailCode + "，验证码有效时长15分钟，请勿转发他人。";
            redisTemplate.opsForValue().set(email, emailCode, Duration.ofMinutes(15));
            try {
                userService.sendEmail(to,subject,text);
                return R.ok("邮件发送成功");
            } catch (MailException e) {
                e.printStackTrace();
                return R.error("邮件发送失败");
            }
        }
    }

    @RequestMapping("/findpassword")
    public R findPassword(@RequestBody UserEntity user) {
        boolean isFind = userService.findUserByNameAndEmail(user.getUsername(), user.getEmail());
        if(isFind){
            String[] to = new String[]{user.getEmail()};
            String subject = "验证码信息";
            String emailCode = EmailUtil.next();
            String text = "神州物流会员您好：\n    您正在修改密码，验证码为: " + emailCode + "，验证码有效时长5分钟，请勿转发他人。";
            redisTemplate.opsForValue().set(user.getEmail(), emailCode, Duration.ofMinutes(5));
            try {
                userService.sendEmail(to,subject,text);
                return R.ok("邮件发送成功");
            } catch (MailException e) {
                e.printStackTrace();
                return R.error("邮件发送失败");
            }
        }
        return R.error(BizCodeEnum.ACCOUNT_MISMATCH_EMAIL_EXCEPTION.getCode(), BizCodeEnum.ACCOUNT_MISMATCH_EMAIL_EXCEPTION.getMsg());
    }

    @RequestMapping("/validateEmailCode")
    public R validateEmailCode(@RequestBody UserRegisterVo vo){
        boolean hasKey = Objects.equals(redisTemplate.hasKey(vo.getEmail()), Boolean.TRUE);
        if (hasKey) {
            if (redisTemplate.opsForValue().get(vo.getEmail()).equals(vo.getCode())) {
                return R.ok().put("data", "邮箱验证通过");
            }
        }
        return R.error(BizCodeEnum.EMAIL_CODE_INCORRECT_EXCEPTION.getCode(), BizCodeEnum.EMAIL_CODE_INCORRECT_EXCEPTION.getMsg());
    }

    @RequestMapping("/changePasswordByEmail")
    public R changePasswordByEmail(@RequestBody UserRegisterVo vo){
        boolean hasKey = Objects.equals(redisTemplate.hasKey(vo.getEmail()), Boolean.TRUE);
        if (hasKey) {
            if (redisTemplate.opsForValue().get(vo.getEmail()).equals(vo.getCode())) {
                // TODO 修改密码
                userService.changePasswordByEmail(vo);
                return R.ok();
            }
        }
        return R.error(BizCodeEnum.EMAIL_CODE_INCORRECT_EXCEPTION.getCode(), BizCodeEnum.EMAIL_CODE_INCORRECT_EXCEPTION.getMsg());
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("member:member:save")
    public R save(@RequestBody UserEntity user){
        userService.save(user);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("member:member:update")
    public R update(HttpServletRequest httpServletRequest, @RequestBody UserEntity user){
        String token = httpServletRequest.getHeader("token");
        String userId = JWT.decode(token).getAudience().get(0);
        if(Objects.equals(redisTemplate.opsForValue().get(userId), token)){
//            System.out.println(user);
            UpdateWrapper<UserEntity> updateWrapper = new UpdateWrapper<>();
            updateWrapper
                    .eq("id", userId)
                    .eq("username", user.getUsername())
                    .eq("email", user.getEmail())
                    .set("nickname", user.getNickname())
                    .set("signature", user.getSignature())
                    .set("phone", user.getPhone())
                    .set("postcode", user.getPostcode())
                    .set("area", user.getArea())
                    .set("qq", user.getQq())
                    .set("wechat", user.getWechat())
                    .set("subscribe", user.getSubscribe());
            if (userService.update(updateWrapper)){
                return R.ok();
            } else {
                return R.error(14000, "用户信息更新失败");
            }
        }
        return R.error(14000, "用户身份校验失败");
    }

}
