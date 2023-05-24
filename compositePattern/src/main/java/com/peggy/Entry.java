package com.peggy;

public abstract class Entry {
  public abstract String getName();
  public abstract int getSize();

  public abstract Entry add(Entry entry);

  public abstract void printList(String preFix);

    @Override
    public String toString() {
        return getName()+"("+getSize()+")";
    }
}
