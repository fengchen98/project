package com.shenzhou.logistics.service.Impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shenzhou.logistics.dao.UserDao;
import com.shenzhou.logistics.entity.UserEntity;
import com.shenzhou.logistics.service.TokenService;
import com.shenzhou.logistics.vo.UserLoginVo;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;

/**
 * Create with IntelliJ IDAE
 *
 * @Author: JINLEI
 * @Description:
 * @Date: 2022/1/24
 * @Time: 20:55
 **/
@Service
public class TokenServiceImpl extends ServiceImpl<UserDao, UserEntity> implements TokenService {

    // 密钥时间
    private static final int EXPIRE_TIME;
    // 私钥
    private static final String TOKEN_SECRET;

    static {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("jwt");
        EXPIRE_TIME = Integer.parseInt(resourceBundle.getString("EXPIRE_TIME"));
        TOKEN_SECRET = resourceBundle.getString("TOKEN_SECRET");
    }

    @Override
    public String getToken(UserEntity user) {
        Algorithm algorithm = Algorithm.HMAC256(user.getPassword() + TOKEN_SECRET);
        Calendar nowTime = Calendar.getInstance();
        nowTime.add(Calendar.MINUTE, EXPIRE_TIME);
        Date expireDate = nowTime.getTime();
        return  JWT.create()
                .withAudience(user.getId()) // 将 user id 保存到 token 里面
//              .withClaim("create_time",new Date()) // -
                .withIssuedAt(new Date()) // +
                .withExpiresAt(expireDate)
                .withClaim("username",user.getUsername()) // +
                .sign(algorithm);// 以 password 作为 token 的密钥;
    }
}
