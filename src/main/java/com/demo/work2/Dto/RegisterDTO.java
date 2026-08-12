package com.demo.work2.Dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegisterDTO {
    @NotBlank(message = "用户名不能为空")
    @Size(min = 3, max = 20 ,message = "用户名长度3-20位")
    private String username;

    //注意与变量对应
    @NotBlank(message = "密码不能为空")
    @Size(min =6,max = 16, message ="密码长度6-16位")
    private  String password;


    private String nickname;
    private String email;
    private String phone;
}