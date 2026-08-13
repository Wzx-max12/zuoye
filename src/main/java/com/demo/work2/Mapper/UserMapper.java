package com.demo.work2.Mapper;

import com.demo.work2.Dto.UserQueryDTO;
import com.demo.work2.Entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface UserMapper {
    //根据用户名查询
    User selectByUsername(String username);
    //新增用户
    int insert(User user);
    //根据ID查详细
    User selectById(Long id);
    //修改
    int updateById(User user);
    //删除
    int deleteById(Long id);

    List<User> getUserList(@Param ("query")UserQueryDTO query);
}
