package com.demo.work2.Service.Impl;

import com.demo.work2.Common.ResourceNotFoundException;
import com.demo.work2.Common.md5Util;
import com.demo.work2.Dto.LoginDTO;
import com.demo.work2.Dto.RegisterDTO;
import com.demo.work2.Dto.UserQueryDTO;
import com.demo.work2.Entity.User;
import com.demo.work2.Mapper.UserMapper;
import com.demo.work2.Service.UserService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    /**
     * 注册：密码MD5加密存入数据库
     */
    @Override
    public void register(RegisterDTO dto) {
        // 1. 判断用户名是否已存在
        User exist = userMapper.selectByUsername(dto.getUsername());
        if (exist != null) {
            throw new IllegalArgumentException("用户名已被注册");
        }
        // 2. MD5加密密码
        String md5Pwd = md5Util.encrypt(dto.getPassword());
        // 3. 封装入库对象
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setPassword(md5Pwd);
        user.setNickname(dto.getNickname());
        user.setPhone(dto.getPhone());
        user.setEmail(dto.getEmail());
        user.setStatus(1); // 默认正常
        userMapper.insert(user);
    }




    /**
     * 登录：校验用户名+MD5密码，返回UUID当作Token
     */
    @Override
    public String login(LoginDTO dto) {
        User dbUser = userMapper.selectByUsername(dto.getUsername());
        if (dbUser == null) {
            throw new IllegalArgumentException("用户名不存在");
        }
        // 前端明文密码MD5加密后和库中比对
        String inputMd5 = md5Util.encrypt(dto.getPassword());
        if (!inputMd5.equals(dbUser.getPassword())) {
            throw new IllegalArgumentException("密码错误");
        }
        // 简易Token：实际项目用JWT
        return UUID.randomUUID().toString().replace("-", "");
    }

    @Override
    public void getUserList(UserQueryDTO query) {

    }



    /**
     * 分页+条件筛选
     */


    /**
     * 用户详情
     */
    @Override
    public User getUserById(Long id) {
        User user = userMapper.selectById(id);
        if (user == null) {
            throw new ResourceNotFoundException("用户ID:" + id + "不存在");
        }
        return user;
    }

    /**
     * 修改用户
     */
    @Override
    public void updateUser(User user) {
        User db = userMapper.selectById(user.getId());
        if (db == null) {
            throw new ResourceNotFoundException("待修改用户不存在");
        }
        // 禁止修改用户名和密码（如需放开自行加逻辑）
        user.setUsername(null);
        user.setPassword(null);
        userMapper.updateById(user);
    }

    @Override
    public void deleteUser(Integer id) {

    }

    /**
     * 删除用户
     */
    @Override
    public void deleteUser(Long id) {
        User db = userMapper.selectById(id);
        if (db == null) {
            throw new ResourceNotFoundException("用户不存在，无法删除");
        }
        userMapper.deleteById(id);
    }

}