package com.demo.work2.Common;


import lombok.Data;

@Data
public class Result<T> {
    private Integer code;
    private String message;
    private T data;

    public static<T> Result<T>success(T data){
        Result<T> r = new Result<T>();
        r.setCode(200);
        r.setData(data);
        r.setMessage("success");
        return r;
    }
    public static<T> Result<T>success(){
        return success(null);
    }
    //失败时
    public static<T> Result<T>fail(int code,String message){
        Result<T> r = new Result<T>();
        r.setCode(code);
        r.setMessage(message);
        r.setData(null);
        return r;
    }
}