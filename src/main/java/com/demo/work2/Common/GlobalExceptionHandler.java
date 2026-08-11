package com.demo.work2.Common;

import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // JSON参数校验多条错误拼接
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result<?> validException(MethodArgumentNotValidException e) {
        //获取所有字段校验失败的错误信息集合
        List<FieldError> errors = e.getBindingResult().getFieldErrors();
        //使用流式处理：取出每个错误的提示信息，用中文分号拼接成一条完整消息
        String msg = errors.stream()
                .map(FieldError::getDefaultMessage)
                .collect(Collectors.joining("；"));
        //返回失败结果，状态码400，拼接错误提示
        return Result.fail(400, "参数错误：" + msg);
    }

    /**
     *
     * @param e 非法访问异常对象
     * @return 统一返回结果
     */

    // 非法参数 /非法访问异常捕获
    @ExceptionHandler(IllegalAccessException.class)
    public Result<?>illegalParam(IllegalAccessException e){
        //返回400错误码，直接抛出异常自带的提示信息
        return Result.fail(400,e.getMessage());
    }

    /**
     * 自定义资源不存在异常处理器，对应404业务异常
     * @param e 自定义资源未找到异常
     * @return 统一返回结果
     */
    // 资源不存在404业务异常
    @ExceptionHandler(ResourceNotFoundException.class)
    public Result<?> notFound(ResourceNotFoundException e) {
        //返回404状态码，异常内自定义提示文案
        return Result.fail(404, e.getMessage());
    }

    /**
     * 全局兜底异常处理器：捕获所有未被上面规则匹配到的未知异常
     * @param e  任意Exception类型异常
     * @return  统一返回结果
     */

    // 兜底异常
    @ExceptionHandler(Exception.class)
    public Result<?> allException(Exception e) {
        // 在控制台打印异常堆栈，方便后端排查问题
            e.printStackTrace();
        //对外隐藏具体异常详情，只返回友好提示，状态码500服务器内部错误
        return Result.fail(500, "服务器异常");
    }
}