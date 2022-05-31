package com.shenzhou.logistics.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shenzhou.logistics.Exception.EmailException;
import com.shenzhou.logistics.Exception.UserNameException;
import com.shenzhou.logistics.entity.UserEntity;
import com.shenzhou.logistics.utils.PageUtils;
import com.shenzhou.logistics.utils.R;
import com.shenzhou.logistics.utils.Result;
import com.shenzhou.logistics.vo.UserLoginVo;
import com.shenzhou.logistics.vo.UserRegisterVo;

import java.util.List;
import java.util.Map;

/**
 * 用户
 * @author jinlei
 * @date 2021-12-21 00:15:58
 */
public interface UserService extends IService<UserEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<UserEntity> dimQuery(String username, String real_name, String phone, String idnumber);

    List<UserEntity> userList();

    /**
     * 用户注册
     * @param vo
     */
    void register(UserRegisterVo vo);

    /**
     * 用户登录
     * @param vo
     * @return
     */
    Result login(UserLoginVo vo);

    /**
     * 判断邮箱是否重复
     * @param email
     * @return
     */
    void checkEmailUnique(String email) throws EmailException;

    /**
     * 判断用户名是否重复
     * @param userName
     * @return
     */
    void checkUserNameUnique(String userName) throws UserNameException;

    /**
     * 发送邮箱验证码
     * @param to
     * @param subject
     * @param text
     */
    void sendEmail(String[] to,String subject,String text);

    /**
     * 根据token获取用户信息
     * @param token
     */
    Result getUserByToken(String token);

    UserEntity findUserById(String userId);

    UserEntity findByUsername(UserEntity user);

    UserEntity findByUsernameAndPassword(UserEntity user);

    boolean findUserByNameAndEmail(String name, String email);

    boolean changePasswordByEmail(UserRegisterVo vo);
}

