package com.dyx.rmp.bean.json;

import java.util.List;

/**
 * project name：RxJava-MVP-Project
 * class describe：
 * create person：dayongxin
 * create time：16/8/3 下午5:43
 * alter person：dayongxin
 * alter time：16/8/3 下午5:43
 * alter remark：
 */
public class NewsBean {

    /**
     * status : true
     * total : 841
     * tngou : [{"count":4722,"fcount":0,"galleryclass":3,"id":1,"img":"/ext/150714/aeb85cdb34f325ccfb3ae0928f846d2d.jpg","rcount":0,"size":18,"time":1436874237000,"title":"絕對吸引眼球的超級美腿"},{"count":2214,"fcount":0,"galleryclass":3,"id":4,"img":"/ext/150714/1c1ac4694da29dbd38e78f93d12cbf5b.jpg","rcount":0,"size":15,"time":1436875098000,"title":"Lucy 俏丽短裙"},{"count":2558,"fcount":0,"galleryclass":4,"id":5,"img":"/ext/150714/2badf623a0acf17ab93294891f67a9f7.jpg","rcount":0,"size":11,"time":1436875200000,"title":"靓丽美女模特兔女郎内衣秀"},{"count":2567,"fcount":0,"galleryclass":5,"id":6,"img":"/ext/150714/426262edf0f5975f41cf4abf8274b21b.jpg","rcount":0,"size":13,"time":1436875387000,"title":"知名嫩模莫露大尺度布条装写真"},{"count":1731,"fcount":0,"galleryclass":3,"id":7,"img":"/ext/150714/f821bc2cb8e0c09a7478be57dae4ed66.jpg","rcount":0,"size":12,"time":1436875874000,"title":"吊带黑丝极致诱惑"}]
     */

    private boolean status;
    private int total;
    /**
     * count : 4722
     * fcount : 0
     * galleryclass : 3
     * id : 1
     * img : /ext/150714/aeb85cdb34f325ccfb3ae0928f846d2d.jpg
     * rcount : 0
     * size : 18
     * time : 1436874237000
     * title : 絕對吸引眼球的超級美腿
     */

    private List<TngouEntity> tngou;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<TngouEntity> getTngou() {
        return tngou;
    }

    public void setTngou(List<TngouEntity> tngou) {
        this.tngou = tngou;
    }

    public static class TngouEntity {
        private int count;
        private int fcount;
        private int galleryclass;
        private int id;
        private String img;
        private int rcount;
        private int size;
        private long time;
        private String title;

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public int getFcount() {
            return fcount;
        }

        public void setFcount(int fcount) {
            this.fcount = fcount;
        }

        public int getGalleryclass() {
            return galleryclass;
        }

        public void setGalleryclass(int galleryclass) {
            this.galleryclass = galleryclass;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public int getRcount() {
            return rcount;
        }

        public void setRcount(int rcount) {
            this.rcount = rcount;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public long getTime() {
            return time;
        }

        public void setTime(long time) {
            this.time = time;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
