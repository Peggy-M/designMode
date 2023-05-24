package com.peggy;

/**
 * @Projectname: designMode
 * @Filename: File
 * @Author: peggy
 * @Data:2023/5/24 18:48
 * @Description: TODO
 */

public class File extends Entry{
    private String fileName;
    private int size;

    public File(String fileName, int size) {
        this.fileName = fileName;
        this.size = size;
    }

    @Override
    public String getName() {
        return fileName;
    }

    @Override
    public int getSize() {
        return size;
    }


    @Override
    public Entry add(Entry entry) {
        return null;
    }

    @Override
    public void printList(String preFix) {
        System.out.println(preFix+"/"+this);
    }
}
