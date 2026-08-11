package com.demo.work2.Controller;

import com.demo.work2.Common.Result;
import com.demo.work2.Dto.LoginDTO;
import com.demo.work2.Dto.RegisterDTO;
import com.demo.work2.Dto.UserQueryDTO;
import com.demo.work2.Entity.User;
import com.demo.work2.Service.UserService;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    // 1. 用户注册 POST
    @PostMapping("/register")
    public Result<Void> register(@Valid @RequestBody RegisterDTO dto){
        userService.register(dto);
        return Result.success();
    }

    // 2. 用户登录 POST 返回Token
    @PostMapping("/login")
    //@Valid 自动对该对象加上校验
    public Result<String> login(@Valid @RequestBody LoginDTO dto) {
        String token = userService.login(dto);
        
        return Result.success(token);
    }
    //3.查询用户列表（分页+条件筛选）
    @GetMapping("/page")
    public Result<PageInfo<User>> page(UserQueryDTO query){
        PageInfo<User> pageInfo = userService.getUserList(query);
        return Result.success(pageInfo);
    }
    // 4. 根据ID查询用户详情 GET
    @GetMapping("/{id}")
    public Result<User> getDetail(@PathVariable Long id) {
        User user = userService.getUserById(id);
        return Result.success(user);
    }

    // 5. 修改用户 PUT
    @PutMapping("/update")
    public Result<Void> update(@RequestBody User user) {
        userService.updateUser(user);
        return Result.success();
    }

    // 6. 删除用户 DELETE
    @DeleteMapping("/{id}")
    public Result<Void> delete(@PathVariable Long id) {
        userService.deleteUser(Math.toIntExact(id));
        return Result.success();
    }
}





