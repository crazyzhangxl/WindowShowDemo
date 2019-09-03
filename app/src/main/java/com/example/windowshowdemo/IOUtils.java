package com.example.windowshowdemo;
import java.io.Closeable;
import java.io.IOException;

/**
 * @author zxl
 * @描述 IO流工具类
 */
public class IOUtils {
    /**
     * 安全的关闭一个文件 内置try-catch
     * @param io
     */
    public static boolean close(Closeable io) {
        if (io != null) {
            try {
                io.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return true;
    }



}