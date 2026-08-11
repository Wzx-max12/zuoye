package com.demo.work2.Common;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class md5Util {

    // 真正的 MD5 加密方法
    public static String encrypt(String str) {
        try {
            //1.获取MD5算法消息摘要实例对象
            MessageDigest md = MessageDigest.getInstance("MD5");
            //2.将字符串转为字节数组，并通过MD5算法生成哈希摘要字节数组（原始加密结果)
            byte[] digest = md.digest(str.getBytes());
            //3.创建字符串拼接容器，用于把字节数组转成16进制字符串
            StringBuilder sb = new StringBuilder();
            //4.遍历MD5加密后的每一个字节
            for (byte b : digest) {
                //%02x：将字节转为两位小写十六进制，不足两位前面补0，保证固定32位长度
                sb.append(String.format("%02x", b));
            }
            //5.返回最终拼接完成的32位MD5加密结果
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            //6.捕获算法不存在异常（理论上MD5一定存在，属于兜底异常）
            //包装为运行时异常向上抛出，上层无需强制try-catch
            throw new RuntimeException("MD5加密失败", e);
        }
    }
}