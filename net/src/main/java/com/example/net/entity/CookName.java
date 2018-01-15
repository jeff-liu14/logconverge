package com.example.net.entity;

import java.util.List;

/**
 * Created by moment on 16/5/24.
 */
public class CookName {

    /**
     * count : 0
     * description :
     * disease :
     * fcount : 0
     * food :
     * id : 1599
     * img : /food/150804/2ad85610af25a980b5fd6d1828bbade1.jpg
     * keywords :
     * name : 棕榈油
     * rcount : 0
     * summary :
     * message :
     */
    private List<TngouBean> tngou;

    public List<TngouBean> getTngou() {
        return tngou;
    }

    public void setTngou(List<TngouBean> tngou) {
        this.tngou = tngou;
    }

    public static class TngouBean {
        private long count;
        private String description;
        private String disease;
        private long fcount;
        private String food;
        private long id;
        private String img;
        private String keywords;
        private String name;
        private long rcount;
        private String summary;
        private String message;

        @Override
        public String toString() {
            return "TngouBean{" +
                    "count=" + count +
                    ", description='" + description + '\'' +
                    ", disease='" + disease + '\'' +
                    ", fcount=" + fcount +
                    ", food='" + food + '\'' +
                    ", id=" + id +
                    ", img='" + img + '\'' +
                    ", keywords='" + keywords + '\'' +
                    ", name='" + name + '\'' +
                    ", rcount=" + rcount +
                    ", summary='" + summary + '\'' +
                    ", message='" + message + '\'' +
                    '}';
        }

        public long getCount() {
            return count;
        }

        public void setCount(long count) {
            this.count = count;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getDisease() {
            return disease;
        }

        public void setDisease(String disease) {
            this.disease = disease;
        }

        public long getFcount() {
            return fcount;
        }

        public void setFcount(long fcount) {
            this.fcount = fcount;
        }

        public String getFood() {
            return food;
        }

        public void setFood(String food) {
            this.food = food;
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getKeywords() {
            return keywords;
        }

        public void setKeywords(String keywords) {
            this.keywords = keywords;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public long getRcount() {
            return rcount;
        }

        public void setRcount(long rcount) {
            this.rcount = rcount;
        }

        public String getSummary() {
            return summary;
        }

        public void setSummary(String summary) {
            this.summary = summary;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }

    @Override
    public String toString() {
        return "CookName{" +
                "tngou=" + tngou.toString() +
                '}';
    }
}
