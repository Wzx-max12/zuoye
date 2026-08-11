package com.demo.work2.Service;


import com.demo.work2.Dto.LoginDTO;
import com.demo.work2.Dto.RegisterDTO;
import com.demo.work2.Dto.UserQueryDTO;
import com.demo.work2.Entity.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
void register(RegisterDTO dto);
String login(LoginDTO dto);

    void getUserList(UserQueryDTO query);

    User getUserById(Long id);

    void updateUser(User user);
void deleteUser(Integer id);

    void deleteUser(Long id);
}