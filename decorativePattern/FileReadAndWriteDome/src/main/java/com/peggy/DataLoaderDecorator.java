package com.peggy;

/**
 * @Projectname: designMode
 * @Filename: DataLoaderDecorator
 * @Author: peggy
 * @Data:2023/5/24 15:31
 * @Description: 抽象者装饰类
 */

public class DataLoaderDecorator implements DataLoader {

    private DataLoader wrapper;

    public DataLoaderDecorator(DataLoader wrapper) {
        this.wrapper = wrapper;
    }

    @Override
    public String redFile() {
        return wrapper.redFile();
    }

    @Override
    public void write(String data) {
        wrapper.write(data);
    }
}
