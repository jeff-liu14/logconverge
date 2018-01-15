package com.example.net.entity;

/**
 * Created by moment on 2017/11/27.
 */

public class Meta {

    private boolean hasMorePage;
    private String token = "";
    private String systemAvatar = "";
    private String systemUserName = "";
    private String systemReplyContent = "";
    private String title = "";
    private String images = "";
    private String introduction = "";
    private int isNew;
    private int totalCount;
    private int position;
    private int extensible;
    private long comicCount;
    private long novelCount;
    private long postsCount;
    private int userCount;
    private long score;
    private long scoreBalance;
    private long pointBalance;

    public boolean isHasMorePage() {
        return hasMorePage;
    }

    public void setHasMorePage(boolean hasMorePage) {
        this.hasMorePage = hasMorePage;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getSystemAvatar() {
        return systemAvatar;
    }

    public void setSystemAvatar(String systemAvatar) {
        this.systemAvatar = systemAvatar;
    }

    public String getSystemUserName() {
        return systemUserName;
    }

    public void setSystemUserName(String systemUserName) {
        this.systemUserName = systemUserName;
    }

    public String getSystemReplyContent() {
        return systemReplyContent;
    }

    public void setSystemReplyContent(String systemReplyContent) {
        this.systemReplyContent = systemReplyContent;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public int getIsNew() {
        return isNew;
    }

    public void setIsNew(int isNew) {
        this.isNew = isNew;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getExtensible() {
        return extensible;
    }

    public void setExtensible(int extensible) {
        this.extensible = extensible;
    }

    public long getComicCount() {
        return comicCount;
    }

    public void setComicCount(long comicCount) {
        this.comicCount = comicCount;
    }

    public long getNovelCount() {
        return novelCount;
    }

    public void setNovelCount(long novelCount) {
        this.novelCount = novelCount;
    }

    public long getPostsCount() {
        return postsCount;
    }

    public void setPostsCount(long postsCount) {
        this.postsCount = postsCount;
    }

    public int getUserCount() {
        return userCount;
    }

    public void setUserCount(int userCount) {
        this.userCount = userCount;
    }

    public long getScore() {
        return score;
    }

    public void setScore(long score) {
        this.score = score;
    }

    public long getScoreBalance() {
        return scoreBalance;
    }

    public void setScoreBalance(long scoreBalance) {
        this.scoreBalance = scoreBalance;
    }

    public long getPointBalance() {
        return pointBalance;
    }

    public void setPointBalance(long pointBalance) {
        this.pointBalance = pointBalance;
    }
}
