package com.example.net.entity;

import java.util.List;

/**
 * Created by moment on 2017/11/27.
 */

public class Book {
    private Long id;
    private int type;
    private String name = "";
    private String bannerImages = "";
    private String verticalImages = "";
    private String introduction = "";
    private String authorName = "";
    private int readMode;
    private int showMode;
    private Long clubId;
    private String updateValueLabel = "";
    private String recentUpdateTime = "";
    private int chapterCount;
    private int isOver;
    private int star;
    private String chapterUpdateInfo = "";
    private String categoryLabel = "";
    private String category = "";
    private String shareUrl = "";
    private String clickUrl = "";
    private String updateChapterName;
    private Long firstChapterId;
    private Long lastReadChapterId;     //DB
    private int lastReadChapterIndex;   //DB
    private int lastReadIndex;          //DB
    private boolean historyChanged;   //DB
    private Long historyTime;       //DB
    private int isCollected;    //DB
    private boolean collectChanged;   //DB
    private Long collectTime;       //DB
    private int downloadStatus;     //DB
    private int downloadProgress;   //DB
    private int downloadSize;   //DB
    private boolean automaticBuy;   //DB
    private List<Chapter> chapterList;
    //阅读量
    private Long readQuantity = 0L;
    private int priority;
    private String images = ""; //最近更新使用
    private int isAppear;      //最近更新使用
    private Long lastUpdateChapterId; //最近更新使用
    private String updateTime;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBannerImages() {
        return bannerImages;
    }

    public void setBannerImages(String bannerImages) {
        this.bannerImages = bannerImages;
    }

    public String getVerticalImages() {
        return verticalImages;
    }

    public void setVerticalImages(String verticalImages) {
        this.verticalImages = verticalImages;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public int getReadMode() {
        return readMode;
    }

    public void setReadMode(int readMode) {
        this.readMode = readMode;
    }

    public int getShowMode() {
        return showMode;
    }

    public void setShowMode(int showMode) {
        this.showMode = showMode;
    }

    public Long getClubId() {
        return clubId;
    }

    public void setClubId(Long clubId) {
        this.clubId = clubId;
    }

    public String getUpdateValueLabel() {
        return updateValueLabel;
    }

    public void setUpdateValueLabel(String updateValueLabel) {
        this.updateValueLabel = updateValueLabel;
    }

    public String getRecentUpdateTime() {
        return recentUpdateTime;
    }

    public void setRecentUpdateTime(String recentUpdateTime) {
        this.recentUpdateTime = recentUpdateTime;
    }

    public int getChapterCount() {
        return chapterCount;
    }

    public void setChapterCount(int chapterCount) {
        this.chapterCount = chapterCount;
    }

    public int getIsOver() {
        return isOver;
    }

    public void setIsOver(int isOver) {
        this.isOver = isOver;
    }

    public int getStar() {
        return star;
    }

    public void setStar(int star) {
        this.star = star;
    }

    public String getChapterUpdateInfo() {
        return chapterUpdateInfo;
    }

    public void setChapterUpdateInfo(String chapterUpdateInfo) {
        this.chapterUpdateInfo = chapterUpdateInfo;
    }

    public String getCategoryLabel() {
        return categoryLabel;
    }

    public void setCategoryLabel(String categoryLabel) {
        this.categoryLabel = categoryLabel;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getShareUrl() {
        return shareUrl;
    }

    public void setShareUrl(String shareUrl) {
        this.shareUrl = shareUrl;
    }

    public String getClickUrl() {
        return clickUrl;
    }

    public void setClickUrl(String clickUrl) {
        this.clickUrl = clickUrl;
    }

    public String getUpdateChapterName() {
        return updateChapterName;
    }

    public void setUpdateChapterName(String updateChapterName) {
        this.updateChapterName = updateChapterName;
    }

    public Long getFirstChapterId() {
        return firstChapterId;
    }

    public void setFirstChapterId(Long firstChapterId) {
        this.firstChapterId = firstChapterId;
    }

    public Long getLastReadChapterId() {
        return lastReadChapterId;
    }

    public void setLastReadChapterId(Long lastReadChapterId) {
        this.lastReadChapterId = lastReadChapterId;
    }

    public int getLastReadChapterIndex() {
        return lastReadChapterIndex;
    }

    public void setLastReadChapterIndex(int lastReadChapterIndex) {
        this.lastReadChapterIndex = lastReadChapterIndex;
    }

    public int getLastReadIndex() {
        return lastReadIndex;
    }

    public void setLastReadIndex(int lastReadIndex) {
        this.lastReadIndex = lastReadIndex;
    }

    public boolean isHistoryChanged() {
        return historyChanged;
    }

    public void setHistoryChanged(boolean historyChanged) {
        this.historyChanged = historyChanged;
    }

    public Long getHistoryTime() {
        return historyTime;
    }

    public void setHistoryTime(Long historyTime) {
        this.historyTime = historyTime;
    }

    public int getIsCollected() {
        return isCollected;
    }

    public void setIsCollected(int isCollected) {
        this.isCollected = isCollected;
    }

    public boolean isCollectChanged() {
        return collectChanged;
    }

    public void setCollectChanged(boolean collectChanged) {
        this.collectChanged = collectChanged;
    }

    public Long getCollectTime() {
        return collectTime;
    }

    public void setCollectTime(Long collectTime) {
        this.collectTime = collectTime;
    }

    public int getDownloadStatus() {
        return downloadStatus;
    }

    public void setDownloadStatus(int downloadStatus) {
        this.downloadStatus = downloadStatus;
    }

    public int getDownloadProgress() {
        return downloadProgress;
    }

    public void setDownloadProgress(int downloadProgress) {
        this.downloadProgress = downloadProgress;
    }

    public int getDownloadSize() {
        return downloadSize;
    }

    public void setDownloadSize(int downloadSize) {
        this.downloadSize = downloadSize;
    }

    public boolean isAutomaticBuy() {
        return automaticBuy;
    }

    public void setAutomaticBuy(boolean automaticBuy) {
        this.automaticBuy = automaticBuy;
    }

    public List<Chapter> getChapterList() {
        return chapterList;
    }

    public void setChapterList(List<Chapter> chapterList) {
        this.chapterList = chapterList;
    }

    public Long getReadQuantity() {
        return readQuantity;
    }

    public void setReadQuantity(Long readQuantity) {
        this.readQuantity = readQuantity;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public int getIsAppear() {
        return isAppear;
    }

    public void setIsAppear(int isAppear) {
        this.isAppear = isAppear;
    }

    public Long getLastUpdateChapterId() {
        return lastUpdateChapterId;
    }

    public void setLastUpdateChapterId(Long lastUpdateChapterId) {
        this.lastUpdateChapterId = lastUpdateChapterId;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }
}
