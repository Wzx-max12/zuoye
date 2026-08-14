package com.demo.work2.Dto;
 import jakarta.validation.constraints.NotBlank;
 import lombok.Data;

    @Data
    public class UpdateDto {
        @NotBlank(message = "用户ID不能为空")
        private Long id;
        private String nickname;
        private String phone;
        private String email;
        private Integer status;
        private String username;
        private String password;
    }
