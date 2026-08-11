package com.demo.work2.Dto;


import lombok.Data;

@Data
public class UserQueryDTO {
    private String username;
    // 用户名模糊查询
    private Integer status;
    // 状态筛选
    private Integer pageNum = 1;
    private Integer pageSize = 10;
}