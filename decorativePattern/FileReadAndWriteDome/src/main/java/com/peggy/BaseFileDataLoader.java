package com.peggy;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @Projectname: designMode
 * @Filename: BaseFileDataLoader
 * @Author: peggy
 * @Data:2023/5/24 15:25
 * @Description: 具体组件重写读写方法
 */

public class BaseFileDataLoader implements DataLoader {

    String filePath;

    public BaseFileDataLoader(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public String redFile() {
        try {
            return FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void write(String data) {
        try {
            FileUtils.writeStringToFile(new File(filePath),data,StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
