package com.lx.history.bean;

import java.util.List;

public class HomeData {

    public long id;
    public String time;
    public String title;
    public String type;
    public List<PicList> pics;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<PicList> getPics() {
        return pics;
    }

    public void setPics(List<PicList> pics) {
        this.pics = pics;
    }

    public class PicList{
        public long picId;
        public String pics;

        public long getPicId() {
            return picId;
        }

        public void setPicId(long picId) {
            this.picId = picId;
        }

        public String getPics() {
            return pics;
        }

        public void setPics(String pics) {
            this.pics = pics;
        }
    }
}
