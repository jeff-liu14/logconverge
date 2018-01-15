package com.example.net.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by moment on 2017/11/27.
 */

public class Chapter {
    private Long id;
    private Long bookId;
    private Long volumeId;
    private Long volumeNo;
    private String volumeName = "";
    private String content = "";
    private int ver;
    private int wordCount;
    private Long inDownloadTime;
    private Long downloadTime;    //db
    private String name = "";
    private String fullName = "";
    private String shareUrl = "";
    private int chapterNo;
    private int liked;      //db
    private int point;
    private int paid;
    private int supportScorePay;
    private long size;
    private int balance;
    private Long lastChapterId;
    private Long nextChapterId;
    private int position;
    private int downloadProgress;   //db
    private int downloadStatus ;    //db
    private int albumCount;
    private List<Album> albumList;
    private int pageCount;
    private ArrayList<NovelPage> pages;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public Long getVolumeId() {
        return volumeId;
    }

    public void setVolumeId(Long volumeId) {
        this.volumeId = volumeId;
    }

    public Long getVolumeNo() {
        return volumeNo;
    }

    public void setVolumeNo(Long volumeNo) {
        this.volumeNo = volumeNo;
    }

    public String getVolumeName() {
        return volumeName;
    }

    public void setVolumeName(String volumeName) {
        this.volumeName = volumeName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getVer() {
        return ver;
    }

    public void setVer(int ver) {
        this.ver = ver;
    }

    public int getWordCount() {
        return wordCount;
    }

    public void setWordCount(int wordCount) {
        this.wordCount = wordCount;
    }

    public Long getInDownloadTime() {
        return inDownloadTime;
    }

    public void setInDownloadTime(Long inDownloadTime) {
        this.inDownloadTime = inDownloadTime;
    }

    public Long getDownloadTime() {
        return downloadTime;
    }

    public void setDownloadTime(Long downloadTime) {
        this.downloadTime = downloadTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getShareUrl() {
        return shareUrl;
    }

    public void setShareUrl(String shareUrl) {
        this.shareUrl = shareUrl;
    }

    public int getChapterNo() {
        return chapterNo;
    }

    public void setChapterNo(int chapterNo) {
        this.chapterNo = chapterNo;
    }

    public int getLiked() {
        return liked;
    }

    public void setLiked(int liked) {
        this.liked = liked;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public int getPaid() {
        return paid;
    }

    public void setPaid(int paid) {
        this.paid = paid;
    }

    public int getSupportScorePay() {
        return supportScorePay;
    }

    public void setSupportScorePay(int supportScorePay) {
        this.supportScorePay = supportScorePay;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public Long getLastChapterId() {
        return lastChapterId;
    }

    public void setLastChapterId(Long lastChapterId) {
        this.lastChapterId = lastChapterId;
    }

    public Long getNextChapterId() {
        return nextChapterId;
    }

    public void setNextChapterId(Long nextChapterId) {
        this.nextChapterId = nextChapterId;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getDownloadProgress() {
        return downloadProgress;
    }

    public void setDownloadProgress(int downloadProgress) {
        this.downloadProgress = downloadProgress;
    }

    public int getDownloadStatus() {
        return downloadStatus;
    }

    public void setDownloadStatus(int downloadStatus) {
        this.downloadStatus = downloadStatus;
    }

    public int getAlbumCount() {
        return albumCount;
    }

    public void setAlbumCount(int albumCount) {
        this.albumCount = albumCount;
    }

    public List<Album> getAlbumList() {
        return albumList;
    }

    public void setAlbumList(List<Album> albumList) {
        this.albumList = albumList;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public ArrayList<NovelPage> getPages() {
        return pages;
    }

    public void setPages(ArrayList<NovelPage> pages) {
        this.pages = pages;
    }
}
