package com.demo.work2.Service;


import com.demo.work2.Dto.LoginDTO;
import com.demo.work2.Dto.RegisterDTO;
import com.demo.work2.Dto.UpdateDto;
import com.demo.work2.Dto.UserQueryDTO;
import com.demo.work2.Entity.User;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
void register(RegisterDTO dto);
String login(LoginDTO dto);

    PageInfo<User> getUserList(UserQueryDTO query);

    User getUserById(Long id);

    void deleteUser(Long id);
    void updateUser(UpdateDto updateDto);
}