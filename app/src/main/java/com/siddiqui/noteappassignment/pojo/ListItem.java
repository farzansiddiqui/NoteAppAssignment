package com.siddiqui.noteappassignment.pojo;

public class ListItem {
    String title;
    boolean checkBox = false;

    public ListItem() {
    }

    public ListItem(String title, boolean checkBox) {
        this.title = title;
        this.checkBox = checkBox;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isCheckBox() {
        return checkBox;
    }

    public void setCheckBox(boolean checkBox) {
        this.checkBox = checkBox;
    }
}
