package com.peggy;

/**
 * @Projectname: designMode
 * @Filename: DataLoader
 * @Author: peggy
 * @Data:2023/5/24 15:23
 * @Description: 抽象文件读写接口
 */

public interface DataLoader {
    String redFile();
    void write(String data);
}
