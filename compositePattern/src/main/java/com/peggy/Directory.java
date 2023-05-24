package com.peggy;

import java.util.ArrayList;
import java.util.List;

/**
 * @Projectname: designMode
 * @Filename: Directory
 * @Author: peggy
 * @Data:2023/5/24 18:51
 * @Description: TODO
 */

public class Directory extends Entry {
    private String name;
    private List<Entry> directory=new ArrayList<>();

    public Directory(String name) {
        this.name = name;
    }


    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getSize() {
        int size=0;
        for (Entry entry : directory) {
            size+=entry.getSize();
        }
        return size;
    }

    @Override
    public Entry add(Entry entry) {
        directory.add(entry);
        return this;
    }

    @Override
    public void printList(String preFix) {
        System.out.println("/"+this);
        for (Entry entry : directory) {
            entry.printList("/"+name);
        }
    }
}
