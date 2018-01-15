package com.example.net.entity;

import java.util.List;

/**
 * Created by moment on 16/5/24.
 */
public class CookList {

    /**
     * total : 111096
     * tngou : [{"count":14,"description":"","fcount":0,"food":"","id":111182,"images":"","img":"","keywords":" ","name":"经典美味奶油泡芙","rcount":0}]
     */

    private long total;
    /**
     * count : 14
     * description :
     * fcount : 0
     * food :
     * id : 111182
     * images :
     * img :
     * keywords :
     * name : 经典美味奶油泡芙
     * rcount : 0
     */

    private List<TngouBean> tngou;

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<TngouBean> getTngou() {
        return tngou;
    }

    public void setTngou(List<TngouBean> tngou) {
        this.tngou = tngou;
    }

    public static class TngouBean {
        private long count;
        private String description;
        private long fcount;
        private String food;
        private long id;
        private String images;
        private String img;
        private String keywords;
        private String name;
        private long rcount;

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

        public String getImages() {
            return images;
        }

        public void setImages(String images) {
            this.images = images;
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
    }
}
