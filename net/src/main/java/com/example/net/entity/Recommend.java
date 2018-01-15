package com.example.net.entity;

import java.util.List;

/**
 * Created by moment on 2017/11/27.
 */

public class Recommend {

    private Long id;
    private int type;
    private String icon = "";
    private String title = "";
    private String introduction = "";
    private String clickUrl = "";
    private int showMode;
    private int showValue;
    private int priority;
    private String bookIds;
    private String onlyImage1;
    private String onlyImage2;
    private String onlyImageClickUrl1;
    private String onlyImageClickUrl2;
    private List<Book> dataList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getClickUrl() {
        return clickUrl;
    }

    public void setClickUrl(String clickUrl) {
        this.clickUrl = clickUrl;
    }

    public int getShowMode() {
        return showMode;
    }

    public void setShowMode(int showMode) {
        this.showMode = showMode;
    }

    public int getShowValue() {
        return showValue;
    }

    public void setShowValue(int showValue) {
        this.showValue = showValue;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getBookIds() {
        return bookIds;
    }

    public void setBookIds(String bookIds) {
        this.bookIds = bookIds;
    }

    public String getOnlyImage1() {
        return onlyImage1;
    }

    public void setOnlyImage1(String onlyImage1) {
        this.onlyImage1 = onlyImage1;
    }

    public String getOnlyImage2() {
        return onlyImage2;
    }

    public void setOnlyImage2(String onlyImage2) {
        this.onlyImage2 = onlyImage2;
    }

    public String getOnlyImageClickUrl1() {
        return onlyImageClickUrl1;
    }

    public void setOnlyImageClickUrl1(String onlyImageClickUrl1) {
        this.onlyImageClickUrl1 = onlyImageClickUrl1;
    }

    public String getOnlyImageClickUrl2() {
        return onlyImageClickUrl2;
    }

    public void setOnlyImageClickUrl2(String onlyImageClickUrl2) {
        this.onlyImageClickUrl2 = onlyImageClickUrl2;
    }

    public List<Book> getDataList() {
        return dataList;
    }

    public void setDataList(List<Book> dataList) {
        this.dataList = dataList;
    }
}
