package com.shenzhou.logistics.service.Impl;


import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shenzhou.logistics.Exception.EmailException;
import com.shenzhou.logistics.Exception.UserNameException;
import com.shenzhou.logistics.dao.UserDao;
import com.shenzhou.logistics.dao.UserLevelDao;
import com.shenzhou.logistics.entity.UserEntity;
import com.shenzhou.logistics.entity.UserLevelEntity;
import com.shenzhou.logistics.service.UserService;
import com.shenzhou.logistics.utils.PageUtils;
import com.shenzhou.logistics.utils.Query;
import com.shenzhou.logistics.utils.R;
import com.shenzhou.logistics.utils.Result;
import com.shenzhou.logistics.vo.UserLoginVo;
import com.shenzhou.logistics.vo.UserRegisterVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;


@Service
public class UserServiceImpl extends ServiceImpl<UserDao, UserEntity> implements UserService {

//    @Resource
//    private UserLevelDao userLevelDao;

    @Resource
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String from;

    @Autowired
    private UserDao userDao;


    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<UserEntity> page = this.page(new
                Query<UserEntity>().getPage(params),
                new QueryWrapper<UserEntity>()
        );
        return new PageUtils(page);
    }

    @Override
    public List<UserEntity> dimQuery(String username, String real_name, String phone, String idnumber) {
        QueryWrapper<UserEntity> queryWrapper = new QueryWrapper<>();
            if(!StringUtils.isEmpty(username)){
                queryWrapper.like("username", username);
            }
            if(!StringUtils.isEmpty(real_name)){
                queryWrapper.like("real_name", real_name);
            }
            if(!StringUtils.isEmpty(phone)){
                queryWrapper.like("phone", phone);
            }
            if(!StringUtils.isEmpty(idnumber)){
                queryWrapper.like("idnumber", idnumber);
            }
        return userDao.selectList(queryWrapper);
    }

    @Override
    public List<UserEntity> userList() {
        return userDao.selectList(null);
    }

    @Override
    public void register(UserRegisterVo vo) {
        UserEntity userEntity = new UserEntity();

        //设置默认等级
//        UserLevelEntity levelEntity = UserLevelDao.getDefaultLevel();
//        userEntity.setLevelId(levelEntity.getId());

        //设置其它的默认信息
        //检查用户名和邮箱是否唯一。感知异常，异常机制
        checkEmailUnique(vo.getEmail());
        checkUserNameUnique(vo.getUserName());

        userEntity.setNickname(vo.getUserName());
        userEntity.setUsername(vo.getUserName());
        //密码进行MD5加密
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String encode = bCryptPasswordEncoder.encode(vo.getPassword());
        userEntity.setPassword(encode);
        userEntity.setEmail(vo.getEmail());
        userEntity.setGender(0);
        userEntity.setCreate_time(new Date());

        //保存数据
        this.baseMapper.insert(userEntity);
    }

    @Override
    public Result login(UserLoginVo vo) {

        String userName = vo.getUsername();
        String password = vo.getPassword();
        System.out.println(JSON.toJSONString(vo));
        //1、去数据库查询 SELECT * FROM user WHERE username = ? OR email = ?
        UserEntity userEntity = this.baseMapper.selectOne(new QueryWrapper<UserEntity>()
                .eq("username", userName));
        System.out.println(JSON.toJSONString(userEntity));

        if (userEntity == null) {
            //登录失败
            return Result.build(400, "用户名或密码错误");
        } else {
            //获取到数据库里的password
            String password1 = userEntity.getPassword();
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            //进行密码匹配
            boolean matches = passwordEncoder.matches(password, password1);
            if (matches) {
                //登录成功
                return Result.build(200, "登录成功");
            }
        }
        return null;
    }

    @Override
    public void checkEmailUnique(String email) throws EmailException {

        Integer emailCount = this.baseMapper.selectCount(new QueryWrapper<UserEntity>().eq("email", email));
        if (emailCount > 0) {
            throw new EmailException();
        }

    }

    @Override
    public void checkUserNameUnique(String userName) throws UserNameException {

        Integer usernameCount = this.baseMapper.selectCount(new QueryWrapper<UserEntity>().eq("username", userName));

        if (usernameCount > 0) {
            throw new UserNameException();
        }
    }

    @Override
    public void sendEmail(String[] to,String subject,String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        //发件人
        message.setFrom(from);
        //收件人
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        try {
            javaMailSender.send(message);
        } catch (MailException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Result getUserByToken(String token) {
        return null;
    }

    @Override
    public UserEntity findUserById(String userId) {
        return userDao.findUserById(userId);
    }

    @Override
    public UserEntity findByUsername(UserEntity user) {
        QueryWrapper<UserEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", user.getUsername());
        return userDao.selectOne(queryWrapper);
    }

    @Override
    public UserEntity findByUsernameAndPassword(UserEntity user){
        QueryWrapper<UserEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", user.getUsername()).eq("password", new BCryptPasswordEncoder().encode(user.getPassword()));
        return userDao.selectOne(queryWrapper);
    }

    @Override
    public boolean findUserByNameAndEmail(String name, String email) {
        QueryWrapper<UserEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", name);
        queryWrapper.eq("email", email);
        UserEntity user = userDao.selectOne(queryWrapper);
        return user != null;
    }

    @Override
    public boolean changePasswordByEmail(UserRegisterVo vo) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String encode = bCryptPasswordEncoder.encode(vo.getPassword());
        UpdateWrapper<UserEntity> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("username", vo.getUserName()).eq("email", vo.getEmail()).set("password", encode);
        userDao.update(null, updateWrapper);
        return false;
    }


}